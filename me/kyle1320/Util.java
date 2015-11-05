package me.kyle1320;

import java.util.Scanner;
import java.util.Collection;
import java.util.List;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Function;

import java.io.File;

/**	Contains various helper methods for things like verifying input from the user.
	@author Kyle Cutler
*/
public final class Util {
	public static final Random random = new Random(); // just for easy access to a Random from various methods

	/**	Prints the given message and continues prompting the user with the same message until they enter "y" or "n".
		@param prompt The message to prompt the user with
		@param scan The Scanner to scan for the input
		@return A boolean, true if the user entered "y" or false if they entered "n".
	*/
	public static boolean verifyYesNo(String prompt, Scanner scan) {
		String input;

		do {
			System.out.print(prompt);
			input = scan.next();

			if (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
				System.out.println("Invalid input! (y / n)");
				continue;
			}

			break;
		} while (true);

		return input.equalsIgnoreCase("y");
	}

	/**	Prompts the user for an "input" file. If the file does not exist, it asks the user to input another file.
		@param scan The scanner to scan for the file name
		@return The input file name
	*/
	public static String getInputFile(Scanner scan) {
		String fin;

		do {
			System.out.print("Enter the input file name: ");
			fin = scan.next();
			File f = new File(fin);

			if (!f.exists()) {
				System.out.println("File doesn't exist!");
				continue;
			}

			break;
		} while (true);

		return fin;
	}

	/**	Prompts the user for an "output" file. If the file already exists, it asks the user if they wish to overwrite the file.
		@param scan The scanner to scan for the file name
		@return The output file name
	*/
	public static String getOutputFile(Scanner scan) {
		String fout;

		do {
			System.out.print("Enter the output file name: ");
			fout = scan.next();
			File f = new File(fout);

			if (f.exists())
				if (!verifyYesNo("File already exists! Overwrite? (y / n): ", scan))
					continue;

			break;
		} while (true);

		return fout;
	}

	/**	Prompts the user with the given message and waits until they enter a valid integer as input, printing an error message each time they don't enter a valid number.
		@param prompt The message to prompt the user with
		@param scan The scanner to read input from
		@return The integer that is input
	*/
	public static int getIntegerInput(String prompt, Scanner scan) {
		int num;

		do {
			try {
				System.out.print(prompt);
				num = scan.nextInt();
			} catch (Exception e) {
				clearScanner(scan);
				System.out.println("Invalid input!");
				continue;
			}
			break;
		} while (true);

		return num;
	}

	/**	Prompts the user with the given message and waits until they enter a valid integer between the given min and max as input, printing an error message each time they don't enter a valid number.
		@param prompt The message to prompt the user with
		@param scan The scanner to read input from
		@param min The minimum valid input (inclusive)
		@param max The maximum valid input (inclusive)
		@return The integer that is input
	*/
	public static int getValidIntegerInput(String prompt, Scanner scan, int min, int max) {
		int num;

		do {
			try {
				System.out.print(prompt);
				num = scan.nextInt();
			} catch (Exception e) {
				clearScanner(scan);
				System.out.println("Invalid input!");
				continue;
			}

			if (num < min || num > max) {
				System.out.println("Invalid input!");
				continue;
			}

			break;
		} while (true);

		return num;
	}

	/**	Prompts the user with the given message and waits until they enter a valid double as input, printing an error message each time they don't enter a valid number.
		@param prompt The message to prompt the user with
		@param scan The scanner to read input from
		@return The double that is input
	*/
	public static double getDoubleInput(String prompt, Scanner scan) {
		double num;

		do {
			try {
				System.out.print(prompt);
				num = scan.nextDouble();
			} catch (Exception e) {
				clearScanner(scan);
				System.out.println("Invalid input!");
				continue;
			}
			break;
		} while (true);

		return num;
	}

	/**	Prompts the user with the given message and waits until they enter a valid double between the given min and max as input, printing an error message each time they don't enter a valid number.
		@param prompt The message to prompt the user with
		@param scan The scanner to read input from
		@param min The minimum valid input (inclusive)
		@param max The maximum valid input (inclusive)
		@return The double that is input
	*/
	public static double getValidDoubleInput(String prompt, Scanner scan, double min, double max) {
		double num;

		do {
			try {
				System.out.print(prompt);
				num = scan.nextDouble();
			} catch (Exception e) {
				clearScanner(scan);
				System.out.println("Invalid input!");
				continue;
			}

			if (num < min || num > max) {
				System.out.println("Invalid input!");
				continue;
			}

			break;
		} while (true);

		return num;
	}


	/**	Prompts the user with the given message and waits until they enter a string equal to a string in the given array as input.
		Prints an error message each time they don't enter a valid string.
		@param prompt The message to prompt the user with
		@param valid A String array of valid input strings.
		@param scan The scanner to read input from
		@return The input the user enters
	*/
	public static String getValidInput(String prompt, String[] valid, Scanner scan) {
		String input;

		do {
			System.out.print(prompt);
			input = scan.next();

			if (!Arrays.contains(valid, input)) {
				System.out.println("Invalid input!");
				continue;
			}

			break;
		} while (true);

		return input;
	}

	/**	Prompts the user with the given message and waits until they enter a string equal to a string in the given array as input (ignoring case).
		Prints an error message each time they don't enter a valid string.
		@param prompt The message to prompt the user with
		@param valid A String array of valid input strings. Case is ignored.
		@param scan The scanner to read input from
		@return The input the user enters
	*/
	public static String getValidInputIgnoreCase(String prompt, String[] valid, Scanner scan) {
		String input;

		do {
			System.out.print(prompt);
			input = scan.next();

			if (!Arrays.containsIgnoreCase(valid, input)) {
				System.out.println("Invalid input!");
				continue;
			}

			break;
		} while (true);

		return input;
	}

	/**	Prints the given message and continues prompting the user with the same message until they enter "y" or "n" in System.in.
		@param prompt The message to prompt the user with
		@return A boolean, true if the user entered "y" or false if they entered "n".
	*/
	public static boolean verifyYesNo(String prompt) {
		return verifyYesNo(prompt, new Scanner(System.in));
	}

	/**	Prompts the user for an "input" file and scans System.in for input. If the file does not exist, it asks the user to input another file.
		@return The input file name
	*/
	public static String getInputFile() {
		return getInputFile(new Scanner(System.in));
	}

	/**	Prompts the user for an "output" file and scans System.in for input. If the file already exists, it asks the user if they wish to overwrite the file.
		@return The output file name
	*/
	public static String getOutputFile() {
		return getOutputFile(new Scanner(System.in));
	}

	/**	Prompts the user with the given message and waits until they enter a valid integer in System.in, printing an error message each time they don't enter a valid number.
		@param prompt The message to prompt the user with
		@return The integer that is input
	*/
	public static int getIntegerInput(String prompt) {
		return getIntegerInput(prompt, new Scanner(System.in));
	}

	/**	Prompts the user with the given message and waits until they enter a string in System.in equal to a string in the given array as input.
		Prints an error message each time they don't enter a valid string.
		@param prompt The message to prompt the user with
		@param valid A String array of valid input strings.
		@return The input the user enters
	*/
	public static String getValidInput(String prompt, String[] valid) {
		return getValidInput(prompt, valid, new Scanner(System.in));
	}

	/**	Prompts the user with the given message and waits until they enter a string in System.in equal to a string in the given array as input (ignoring case).
		Prints an error message each time they don't enter a valid string.
		@param prompt The message to prompt the user with
		@param valid A String array of valid input strings. Case is ignored.
		@return The input the user enters
	*/
	public static String getValidInputIgnoreCase(String prompt, String[] valid) {
		return getValidInputIgnoreCase(prompt, valid, new Scanner(System.in));
	}

	/** Tries reading an integer from the scanner (doesn't wait for input). If one is not found, or it is invalid, a default value of 0 is returned.
		@param scan The scanner to read
		@return The integer found in the scanner, or 0 if one was not found.
	*/
	public static int readInt(Scanner scan) {
		return readInt(scan, 0);
	}

	/** Tries reading an integer from the scanner (doesn't wait for input). If one is not found, or it is invalid, a default value is returned.
		@param scan The scanner to read
		@param def The default value to return
		@return The integer found in the scanner, or the default value if one was not found.
	*/
	public static int readInt(Scanner scan, int def) {
		if (scan.hasNext())
			try {
				return scan.nextInt();
			} catch (Exception e) {
				return def;
			}
		else
			return def;
	}

	/** Tries reading a string from the scanner (doesn't wait for input). If one is not found, a default empty string is returned
		@param scan The scanner to read
		@return The string found in the scanner, or "" if one was not found.
	*/
	public static String readString(Scanner scan) {
		return readString(scan, "");
	}

	/** Tries reading a string from the scanner (doesn't wait for input). If one is not found, a default string is returned
		@param scan The scanner to read
		@param def The default string to return
		@return The string found in the scanner, or the default string if one was not found.
	*/
	public static String readString(Scanner scan, String def) {
		if (scan.hasNext())
			return scan.next();
		else
			return def;
	}

	/**	Runs the given scanner through all of its input, ignoring all of it. Useful for clearing unexpected newlines / extra input.
		@param scan The scanner to clear
	*/
	public static void clearScanner(Scanner scan) {
		while (scan.hasNext())
			scan.next();
	}

	/**	Prints the given list to System.out, one element per line, with indices in front of each item, starting at 1.
		@param list The varargs list to print out
	*/
	@SuppressWarnings("unchecked")
	public static <T> void printAsNumberedList(T... list) {
		int index = 1;

		for (T item : list)
			System.out.println(index++ + ":\t" + item);
	}

	/**	Prints the given Collection to System.out, one element per line, with indices in front of each item, starting at 1.
		@param list The Collection to print out
	*/
	public static <T> void printAsNumberedList(Collection<T> list) {
		int index = 1;

		for (T item : list)
			System.out.println(index++ + ":\t" + item);
	}

	/** Given a list of elements returns a random one of those elements
		@param elements a varargs list of elements to choose from
		@return A random one of the given elements
	*/
	@SuppressWarnings("unchecked")
	public static <T> T chooseRandom(T... elements) {
		return elements[Numbers.randomInt(elements.length)];
	}

	/** Given a List of elements returns a random one of those elements
		@param elements a List of elements to choose from
		@return A random one of the given elements
	*/
	public static <T> T chooseRandom(List<T> elements) {
		return elements.get(Numbers.randomInt(elements.size()));
	}

	/** Given a Collection of elements returns a random one of those elements
		@param elements a Collection of elements to choose from
		@return A random one of the given elements
	*/
	public static <T> T chooseRandom(Collection<T> elements) {
		int index = Numbers.randomInt(elements.size());
		Iterator<T> it = elements.iterator();

		for (int i=0; i < index-1; i++)
			it.next();

		return it.next();
	}

	/**	Returns the smallest value in the given array, or null if the array is empty or filled with null values
		@param elements an array of Comparable objects
		@return The first occurence of the smallest value in the array
	*/
	public static <T extends Comparable<? super T>> T minimum(T[] elements) {
		T min = null;

		for (T value : elements)
			if (value != null && (min == null || value.compareTo(min) < 0))
				min = value;

		return min;
	}

	/**	Returns the smallest value in the given Collection, or null if the Collection is empty or filled with null values
		@param elements a Collection of Comparable objects
		@return The first occurence of the smallest value in the Collection
	*/
	public static <T extends Comparable<? super T>> T minimum(Collection<T> elements) {
		T min = null;

		for (T value : elements)
			if (value != null && (min == null || value.compareTo(min) < 0))
				min = value;

		return min;
	}

	/**	Returns the largest value in the given array, or null if the array is empty or filled with null values
		@param elements an array of Comparable objects
		@return The first occurence of the largest value in the array
	*/
	public static <T extends Comparable<? super T>> T maximum(T[] elements) {
		T max = null;

		for (T value : elements)
			if (value != null && (max == null || value.compareTo(max) > 0))
				max = value;

		return max;
	}

	/**	Returns the largest value in the given Collection, or null if the Collection is empty or filled with null values
		@param elements a Collection of Comparable objects
		@return The first occurence of the largest value in the Collection
	*/
	public static <T extends Comparable<? super T>> T maximum(Collection<T> elements) {
		T max = null;

		for (T value : elements)
			if (value != null && (max == null || value.compareTo(max) > 0))
				max = value;

		return max;
	}

	/**	Returns a boolean based on a percent change given.
		@param percent The percent chance that this method will return true (should be between 0 and 1)
		@return A random boolean, true if the next random double generated is less than the given chance.
	*/
	public static boolean chance(double percent) {
		return random.nextDouble() < percent;
	}

	public static <T> int count(Iterable<T> elements, Function<T, Boolean> test) {
		int count = 0;

		for (T elem : elements) {
			if (test.apply(elem))
			count++;
		}

		return count;
	}
}