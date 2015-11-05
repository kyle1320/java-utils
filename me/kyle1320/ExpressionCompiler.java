package me.kyle1320;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.ToolProvider;
import javax.tools.JavaCompiler;

import java.util.function.BiFunction;

import java.lang.reflect.Method;

/**
 * compiles java expressions and returns a class instance that evaluates the expression.
 *
 * @author Kyle Cutler
 */
public class ExpressionCompiler {
	private static final String path = System.getProperty("user.dir") + System.getProperty("file.separator");

	// used to create unique class names
	// each time a class is created, this should be incremented
	private static int classNum = 1;

	// the class file we modify to create our expression
	private static final String classBase =
				"import static java.lang.Math.*;\n" +
				"public class %s implements %s {\n" +
				" public %s {\n" +
				"  return %s;\n" +
				" }\n" +
				"}";

	/**
	 * Returns a BiFunction that evaluates the given expression where the two parameters are "x" and "y"
	 *
	 * @param 	expression 	The expression that should be evaluated
	 *
	 * @return 				A BiFunction that takes two doubles and calculates the given expression
	 */
	@SuppressWarnings("unchecked")
	public static BiFunction<Double, Double, Double> compileDoubleBiFunction(String expression) {

		// check for obviously invalid expressions
		if (expression == null || expression.length() == 0)
			return null;

		return (BiFunction<Double, Double, Double>)
				createNewInstance(	"java.util.function.BiFunction<Double, Double, Double>",
									"Double apply(Double x, Double y)",
									expression);
	}

	/**
	 * Compiles an instance of the given interface that computes the given function.
	 *
	 * @param 	iface 		The interface to implement the function in. Must be
	 * 						a non-generic interface with a single non-generic method.
	 * @param 	expression 	The expression to be calculated in the new class
	 * @param 	params 		A varargs list of function parameter names, in the same
	 * 						order as they are declared in the interface.
	 *
	 * @return 				An Object of the given class type that computes the expression
	 */
	public static Object compileCustomFunction(Class iface, String expression, String... params) {

		// check for obviously invalid expressions
		if (expression == null || expression.length() == 0)
			return null;

		// class must be an interface
		if (!iface.isInterface())
			return null;

		String signature;

		try {
			Method[] methods = iface.getMethods();

			// must be a functional interface
			if (methods.length != 1)
				return null;

			Method method = methods[0];
			Class<?> returnType = method.getReturnType();
			Class<?>[] paramTypes = method.getParameterTypes();

			// method must return a value
			if (returnType == Void.class)
				return null;

			String paramString = "";

			// add parameter types and names to the parameter list
			for (int i=0; i < params.length; i++) {
				if (i > 0)
					paramString += ", ";
				paramString += paramTypes[i].getCanonicalName() + " " + params[i];
			}

			// build the method signature
			signature = returnType.getCanonicalName() + " " +
						method.getName() + "(" +
						paramString + ")";

		} catch (SecurityException e) {
			e.printStackTrace();
			return null;
		}

		return createNewInstance(iface.getCanonicalName(),
								 signature,
								 expression);
	}

	/**
	 * Writes the .java file, compiles it to a class, and returns true if everything was successful.
	 *
	 * @param 	className 	The name of the class to create
	 * @param 	implement 	The name of the parent interface to the new class
	 * @param 	header 		The header of the method that evaluates the expression
	 * @param 	expression 	The expression to be evaluated in the method
	 *
	 * @return 				True if the class was compiled successfully, false otherwise
	 */
	private static boolean writeAndCompile(String className, String implement, String header, String expression) {

		// build the class
		String classData = String.format(classBase, className, implement, header, expression);
		String classFileName = className + ".java";

		// write the .java file
		try (PrintWriter out = new PrintWriter(path + classFileName)) {
			out.println(classData);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}

		// compile the file
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		int result;

		if (compiler == null) {
			System.out.println("No Compiler! Trying external tools..");

			try {
				com.sun.tools.javac.Main javac = new com.sun.tools.javac.Main();
				result = javac.compile(new String[] {path + classFileName});
			} catch (NoClassDefFoundError e) {
				System.out.println("No external tools found!");
				return false;
			}

			//return false;
		} else
			result = compiler.run(null, null, null, path + classFileName);

		// delete the .java file, just because
		try {
			new File(path + classFileName).delete();

		// meh, we tried
		} catch (Exception e) {}

		// if result is 0, compilation was successful
		return result == 0;
	}

	/**
	 * Tries creating a new class and instantiating it. Returns an instance
	 * of that class if it is successful, or null if it fails.
	 */
	private static Object createNewInstance(String implement, String header, String expression) {

		// a feeble attempt at preventing arbitrary code execution
		// by limiting expressions to a single statement.
		// Otherwise the expression can be something like:
		// 	f(); } Object f() { System.exit(0); return null
		if (expression.contains(";"))
			return null;

		// since we might create multiple expressions, we need their names to be unique
		String className = "MyExpression" + classNum++;

		// if we compile the class susseccfully, try and instantiate it
		if (writeAndCompile(className, implement, header, expression)) {
			try {
				File classFile = new File(path + className + ".class");
				// load the class into the JVM, and try and get an instance of it
				//Class<?> newClass = ClassLoader.getSystemClassLoader().loadClass(className);

				URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { classFile.toURI().toURL() });
				Class<?> newClass = Class.forName(className, true, classLoader);

				// delete the class file, so we leave no trace
				// this is okay because we already loaded the class
				try {
					classFile.delete();

				// meh, we tried
				} catch (Exception e) {}

				return newClass.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
				//System.out.println("Couldn't load class");
				return null;
			}
		} else {
			System.out.println("Couldn't write / compile source");
		}

		// the class didn't compile
		return null;
	}
}