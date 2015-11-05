package me.kyle1320;

import java.util.Comparator;
import java.util.Collection;
import java.util.Iterator;

/**	Contains various helper methods for array operations like printing, sorting, and searching.
	@author Kyle Cutler
*/
public final class Arrays {
	/** Randomizes the order of the elements in the given array
		@param arr The array to randomize
	*/
	public static void randomize(Object[] arr) {
		randomize(arr, 0, arr.length);
	}

	/** Randomizes the order of the elements in the given array
		@param arr The array to randomize
	*/
	public static void randomize(short[] arr) {
		randomize(arr, 0, arr.length);
	}

	/** Randomizes the order of the elements in the given array
		@param arr The array to randomize
	*/
	public static void randomize(int[] arr) {
		randomize(arr, 0, arr.length);
	}

	/** Randomizes the order of the elements in the given array
		@param arr The array to randomize
	*/
	public static void randomize(long[] arr) {
		randomize(arr, 0, arr.length);
	}

	/** Randomizes the order of the elements in the given array
		@param arr The array to randomize
	*/
	public static void randomize(float[] arr) {
		randomize(arr, 0, arr.length);
	}

	/** Randomizes the order of the elements in the given array
		@param arr The array to randomize
	*/
	public static void randomize(double[] arr) {
		randomize(arr, 0, arr.length);
	}

	/** Randomizes the order of the elements in the given array
		@param arr The array to randomize
	*/
	public static void randomize(boolean[] arr) {
		randomize(arr, 0, arr.length);
	}

	/** Randomizes the order of the elements in the given array
		@param arr The array to randomize
	*/
	public static void randomize(byte[] arr) {
		randomize(arr, 0, arr.length);
	}

	/** Randomizes the order of the elements in the given array
		@param arr The array to randomize
	*/
	public static void randomize(char[] arr) {
		randomize(arr, 0, arr.length);
	}

	/** Randomizes the order of the elements in the given array between the given min (inclusive) and max (exclusive) index values.
		@param arr The array to randomize
		@param min The minimum index value to randomize
		@param max The index value to go up to while randomizing
	*/
	public static void randomize(Object[] arr, int min, int max) {
		for (int i=min; i < max; i++) {
			int swap = Numbers.randomInt(i, max);
			
			swapElements(arr, swap, i);
		}
	}

	/** Randomizes the order of the elements in the given array between the given min (inclusive) and max (exclusive) index values.
		@param arr The array to randomize
		@param min The minimum index value to randomize
		@param max The index value to go up to while randomizing
	*/
	public static void randomize(short[] arr, int min, int max) {
		for (int i=min; i < max; i++) {
			int swap = Numbers.randomInt(i, max);
			
			swapElements(arr, swap, i);
		}
	}

	/** Randomizes the order of the elements in the given array between the given min (inclusive) and max (exclusive) index values.
		@param arr The array to randomize
		@param min The minimum index value to randomize
		@param max The index value to go up to while randomizing
	*/
	public static void randomize(int[] arr, int min, int max) {
		for (int i=min; i < max; i++) {
			int swap = Numbers.randomInt(i, max);
			
			swapElements(arr, swap, i);
		}
	}

	/** Randomizes the order of the elements in the given array between the given min (inclusive) and max (exclusive) index values.
		@param arr The array to randomize
		@param min The minimum index value to randomize
		@param max The index value to go up to while randomizing
	*/
	public static void randomize(long[] arr, int min, int max) {
		for (int i=min; i < max; i++) {
			int swap = Numbers.randomInt(i, max);
			
			swapElements(arr, swap, i);
		}
	}

	/** Randomizes the order of the elements in the given array between the given min (inclusive) and max (exclusive) index values.
		@param arr The array to randomize
		@param min The minimum index value to randomize
		@param max The index value to go up to while randomizing
	*/
	public static void randomize(float[] arr, int min, int max) {
		for (int i=min; i < max; i++) {
			int swap = Numbers.randomInt(i, max);
			
			swapElements(arr, swap, i);
		}
	}

	/** Randomizes the order of the elements in the given array between the given min (inclusive) and max (exclusive) index values.
		@param arr The array to randomize
		@param min The minimum index value to randomize
		@param max The index value to go up to while randomizing
	*/
	public static void randomize(double[] arr, int min, int max) {
		for (int i=min; i < max; i++) {
			int swap = Numbers.randomInt(i, max);
			
			swapElements(arr, swap, i);
		}
	}

	/** Randomizes the order of the elements in the given array between the given min (inclusive) and max (exclusive) index values.
		@param arr The array to randomize
		@param min The minimum index value to randomize
		@param max The index value to go up to while randomizing
	*/
	public static void randomize(boolean[] arr, int min, int max) {
		for (int i=min; i < max; i++) {
			int swap = Numbers.randomInt(i, max);
			
			swapElements(arr, swap, i);
		}
	}

	/** Randomizes the order of the elements in the given array between the given min (inclusive) and max (exclusive) index values.
		@param arr The array to randomize
		@param min The minimum index value to randomize
		@param max The index value to go up to while randomizing
	*/
	public static void randomize(byte[] arr, int min, int max) {
		for (int i=min; i < max; i++) {
			int swap = Numbers.randomInt(i, max);
			
			swapElements(arr, swap, i);
		}
	}

	/** Randomizes the order of the elements in the given array between the given min (inclusive) and max (exclusive) index values.
		@param arr The array to randomize
		@param min The minimum index value to randomize
		@param max The index value to go up to while randomizing
	*/
	public static void randomize(char[] arr, int min, int max) {
		for (int i=min; i < max; i++) {
			int swap = Numbers.randomInt(i, max);
			
			swapElements(arr, swap, i);
		}
	}

	/**	Swaps two elements in the given array at the given indices.
		@param arr The array to change
		@param index1 The first index to swap
		@param index2 The second index to swap
	*/
	public static void swapElements(Object[] arr, int index1, int index2) {
		Object temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

	/**	Swaps two elements in the given array at the given indices.
		@param arr The array to change
		@param index1 The first index to swap
		@param index2 The second index to swap
	*/
	public static void swapElements(short[] arr, int index1, int index2) {
		short temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

	/**	Swaps two elements in the given array at the given indices.
		@param arr The array to change
		@param index1 The first index to swap
		@param index2 The second index to swap
	*/
	public static void swapElements(int[] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

	/**	Swaps two elements in the given array at the given indices.
		@param arr The array to change
		@param index1 The first index to swap
		@param index2 The second index to swap
	*/
	public static void swapElements(long[] arr, int index1, int index2) {
		long temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

	/**	Swaps two elements in the given array at the given indices.
		@param arr The array to change
		@param index1 The first index to swap
		@param index2 The second index to swap
	*/
	public static void swapElements(float[] arr, int index1, int index2) {
		float temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

	/**	Swaps two elements in the given array at the given indices.
		@param arr The array to change
		@param index1 The first index to swap
		@param index2 The second index to swap
	*/
	public static void swapElements(double[] arr, int index1, int index2) {
		double temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

	/**	Swaps two elements in the given array at the given indices.
		@param arr The array to change
		@param index1 The first index to swap
		@param index2 The second index to swap
	*/
	public static void swapElements(boolean[] arr, int index1, int index2) {
		boolean temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

	/**	Swaps two elements in the given array at the given indices.
		@param arr The array to change
		@param index1 The first index to swap
		@param index2 The second index to swap
	*/
	public static void swapElements(byte[] arr, int index1, int index2) {
		byte temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

	/**	Swaps two elements in the given array at the given indices.
		@param arr The array to change
		@param index1 The first index to swap
		@param index2 The second index to swap
	*/
	public static void swapElements(char[] arr, int index1, int index2) {
		char temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

	/** Returns the given array formatted as a string.
		@param arr The array to format
		@return The array as a String
	*/
	public static <T> String toString(T[][] arr) {
		int max = arr.length - 1;
		String as = "[";

		if (max < 0)
			return "[]";
		
		for (int i=0; i < max; i++) {
			as += toString(arr[i]).replaceAll("\n", "\n ") + ", \n ";
		}
		
		as += toString(arr[max]).replaceAll("\n", "\n ") + "]";

		return as;
	}
	
	/** Returns the given array formatted as a string.
		@param arr The array to format
		@return The array as a String
	*/
	@SuppressWarnings("unchecked")
	public static <T> String toString(T[] arr) {
		int max = arr.length - 1;
		String as = "[";

		if (max >= 0) {
			if (arr[0] != null && arr[0].getClass().isArray())
				return toString((T[][]) arr);
		} else
			return "[]";
		
		for (int i=0; i < max; i++) {
			as += arr[i] + ", ";
		}
		
		as += arr[max] + "]";

		return as;
	}

	/** Returns the given array formatted as a string.
		@param arr The array to format
		@return The array as a String
	*/
	public static String toString(short[] arr) {
		int max = arr.length - 1;
		String as = "[";

		if (max < 0)
			return "[]";
		
		for (int i=0; i < max; i++) {
			as += arr[i] + ", ";
		}
		
		as += arr[max] + "]";

		return as;
	}

	/** Returns the given array formatted as a string.
		@param arr The array to format
		@return The array as a String
	*/
	public static String toString(int[] arr) {
		int max = arr.length - 1;
		String as = "[";

		if (max < 0)
			return "[]";
		
		for (int i=0; i < max; i++) {
			as += arr[i] + ", ";
		}
		
		as += arr[max] + "]";

		return as;
	}

	/** Returns the given array formatted as a string.
		@param arr The array to format
		@return The array as a String
	*/
	public static String toString(long[] arr) {
		int max = arr.length - 1;
		String as = "[";

		if (max < 0)
			return "[]";
		
		for (int i=0; i < max; i++) {
			as += arr[i] + ", ";
		}
		
		as += arr[max] + "]";

		return as;
	}

	/** Returns the given array formatted as a string.
		@param arr The array to format
		@return The array as a String
	*/
	public static String toString(float[] arr) {
		int max = arr.length - 1;
		String as = "[";

		if (max < 0)
			return "[]";
		
		for (int i=0; i < max; i++) {
			as += arr[i] + ", ";
		}
		
		as += arr[max] + "]";

		return as;
	}

	/** Returns the given array formatted as a string.
		@param arr The array to format
		@return The array as a String
	*/
	public static String toString(double[] arr) {
		int max = arr.length - 1;
		String as = "[";

		if (max < 0)
			return "[]";
		
		for (int i=0; i < max; i++) {
			as += arr[i] + ", ";
		}
		
		as += arr[max] + "]";

		return as;
	}

	/** Returns the given array formatted as a string.
		@param arr The array to format
		@return The array as a String
	*/
	public static String toString(boolean[] arr) {
		int max = arr.length - 1;
		String as = "[";

		if (max < 0)
			return "[]";
		
		for (int i=0; i < max; i++) {
			as += arr[i] + ", ";
		}
		
		as += arr[max] + "]";

		return as;
	}

	/** Returns the given array formatted as a string.
		@param arr The array to format
		@return The array as a String
	*/
	public static String toString(byte[] arr) {
		int max = arr.length - 1;
		String as = "[";

		if (max < 0)
			return "[]";
		
		for (int i=0; i < max; i++) {
			as += arr[i] + ", ";
		}
		
		as += arr[max] + "]";

		return as;
	}

	/** Returns the given array formatted as a string.
		@param arr The array to format
		@return The array as a String
	*/
	public static String toString(char[] arr) {
		int max = arr.length - 1;
		String as = "[";

		if (max < 0)
			return "[]";
		
		for (int i=0; i < max; i++) {
			as += arr[i] + ", ";
		}
		
		as += arr[max] + "]";

		return as;
	}

	/** Returns the given array formatted as a string.
		@param arr The array to format
		@return The array as a String
	*/
	public static <T extends Collection> String toString(T arr) {
		Iterator it = arr.iterator();
		Object next = null;
		String as = "[";
		
		while (it.hasNext()) {
			next = it.next();
			if (it.hasNext())
				as += next + ", ";
		}
		
		if (next != null)
			as += next + "]";

		return as;
	}

	/**	Return true is the given array is sorted in ascending order. Null elements should be last.
		@param arr The array to look at
		@return True if the array is sorted in ascending order
	*/
	public static <T extends Comparable<? super T>> boolean isSorted(T[] arr) {
		return isSorted(arr, 0, arr.length);
	}

	/**	Return true is the given array is sorted in ascending order.
		@param arr The array to look at
		@return True if the array is sorted in ascending order
	*/
	public static boolean isSorted(short[] arr) {
		return isSorted(arr, 0, arr.length);
	}

	/**	Return true is the given array is sorted in ascending order.
		@param arr The array to look at
		@return True if the array is sorted in ascending order
	*/
	public static boolean isSorted(int[] arr) {
		return isSorted(arr, 0, arr.length);
	}

	/**	Return true is the given array is sorted in ascending order.
		@param arr The array to look at
		@return True if the array is sorted in ascending order
	*/
	public static boolean isSorted(long[] arr) {
		return isSorted(arr, 0, arr.length);
	}

	/**	Return true is the given array is sorted in ascending order.
		@param arr The array to look at
		@return True if the array is sorted in ascending order
	*/
	public static boolean isSorted(float[] arr) {
		return isSorted(arr, 0, arr.length);
	}

	/**	Return true is the given array is sorted in ascending order.
		@param arr The array to look at
		@return True if the array is sorted in ascending order
	*/
	public static boolean isSorted(double[] arr) {
		return isSorted(arr, 0, arr.length);
	}

	/**	Return true is the given array is sorted in ascending order.
		@param arr The array to look at
		@return True if the array is sorted in ascending order
	*/
	public static boolean isSorted(boolean[] arr) {
		return isSorted(arr, 0, arr.length);
	}

	/**	Return true is the given array is sorted in ascending order.
		@param arr The array to look at
		@return True if the array is sorted in ascending order
	*/
	public static boolean isSorted(byte[] arr) {
		return isSorted(arr, 0, arr.length);
	}

	/**	Return true is the given array is sorted in ascending order between the given min (inclusive) and max (exclusive) index values. 
		Null elements should be last.
		@param arr The array to look at
		@param min The minimum index to check
		@param max The index to go up to while checking
		@return True if the array is sorted in ascending order
	*/
	public static <T extends Comparable<? super T>> boolean isSorted(T[] arr, int min, int max) {
		for (int i=min+1; i < max; i++) {
			// nulls must come at the end of the list to be sorted
			// null, object is not a valid arrangement
			// object1, object2 is not a valid arrangement if object2 < object1
			if (arr[i] != null && (arr[i-1] == null || arr[i].compareTo(arr[i-1]) < 0))
				return false;
		}

		return true;
	}

	/**	Return true is the given array is sorted in ascending order between the given min (inclusive) and max (exclusive) index values.
		@param arr The array to look at
		@param min The minimum index to check
		@param max The index to go up to while checking
		@return True if the array is sorted in ascending order
	*/
	public static boolean isSorted(short[] arr, int min, int max) {
		for (int i=min+1; i < max; i++)
			if (arr[i] < arr[i-1])
				return false;

		return true;
	}

	/**	Return true is the given array is sorted in ascending order between the given min (inclusive) and max (exclusive) index values.
		@param arr The array to look at
		@param min The minimum index to check
		@param max The index to go up to while checking
		@return True if the array is sorted in ascending order
	*/
	public static boolean isSorted(int[] arr, int min, int max) {
		for (int i=min+1; i < max; i++)
			if (arr[i] < arr[i-1])
				return false;

		return true;
	}

	/**	Return true is the given array is sorted in ascending order between the given min (inclusive) and max (exclusive) index values.
		@param arr The array to look at
		@param min The minimum index to check
		@param max The index to go up to while checking
		@return True if the array is sorted in ascending order
	*/
	public static boolean isSorted(long[] arr, int min, int max) {
		for (int i=min+1; i < max; i++)
			if (arr[i] < arr[i-1])
				return false;

		return true;
	}

	/**	Return true is the given array is sorted in ascending order between the given min (inclusive) and max (exclusive) index values.
		@param arr The array to look at
		@param min The minimum index to check
		@param max The index to go up to while checking
		@return True if the array is sorted in ascending order
	*/
	public static boolean isSorted(float[] arr, int min, int max) {
		for (int i=min+1; i < max; i++)
			if (arr[i] < arr[i-1])
				return false;

		return true;
	}

	/**	Return true is the given array is sorted in ascending order between the given min (inclusive) and max (exclusive) index values.
		@param arr The array to look at
		@param min The minimum index to check
		@param max The index to go up to while checking
		@return True if the array is sorted in ascending order
	*/
	public static boolean isSorted(double[] arr, int min, int max) {
		for (int i=min+1; i < max; i++)
			if (arr[i] < arr[i-1])
				return false;

		return true;
	}

	/**	Return true is the given array is sorted in ascending order between the given min (inclusive) and max (exclusive) index values. 
		For booleans, the value false is smaller than the value true.
		@param arr The array to look at
		@param min The minimum index to check
		@param max The index to go up to while checking
		@return True if the array is sorted in ascending order
	*/
	public static boolean isSorted(boolean[] arr, int min, int max) {
		for (int i=min+1; i < max; i++)
			if (!arr[i] && arr[i-1])
				return false;

		return true;
	}

	/**	Return true is the given array is sorted in ascending order between the given min (inclusive) and max (exclusive) index values.
		@param arr The array to look at
		@param min The minimum index to check
		@param max The index to go up to while checking
		@return True if the array is sorted in ascending order
	*/
	public static boolean isSorted(byte[] arr, int min, int max) {
		for (int i=min+1; i < max; i++)
			if (arr[i] < arr[i-1])
				return false;

		return true;
	}

	/**	Return true is the given array is sorted in ascending order between the given min (inclusive) and max (exclusive) index values.
		@param arr The array to look at
		@param min The minimum index to check
		@param max The index to go up to while checking
		@return True if the array is sorted in ascending order
	*/
	public static boolean isSorted(char[] arr, int min, int max) {
		for (int i=min+1; i < max; i++)
			if (arr[i] < arr[i-1])
				return false;

		return true;
	}

	/**	Returns true if the given array contains an object equal to the given target object.
		@param arr The array to search
		@param target The target object to find
		@return True if the target is found in the array, false otherwise
	*/
	public static boolean contains(Object[] arr, Object target) {
		for (Object o : arr)
			if ((o == target) || (o != null && target != null && o.equals(target)))
				return true;
		return false;
	}

	/**	Returns true if the given array contains a short equal to the given target short.
		@param arr The array to search
		@param target The target short to find
		@return True if the target is found in the array, false otherwise
	*/
	public static boolean contains(short[] arr, short target) {
		for (short i : arr)
			if (i == target)
				return true;
		return false;
	}

	/**	Returns true if the given array contains an int equal to the given target int.
		@param arr The array to search
		@param target The target int to find
		@return True if the target is found in the array, false otherwise
	*/
	public static boolean contains(int[] arr, int target) {
		for (int i : arr)
			if (i == target)
				return true;
		return false;
	}

	/**	Returns true if the given array contains a long equal to the given target long.
		@param arr The array to search
		@param target The target long to find
		@return True if the target is found in the array, false otherwise
	*/
	public static boolean contains(long[] arr, long target) {
		for (long i : arr)
			if (i == target)
				return true;
		return false;
	}

	/**	Returns true if the given array contains a float equal to the given target float.
		@param arr The array to search
		@param target The target float to find
		@return True if the target is found in the array, false otherwise
	*/
	public static boolean contains(float[] arr, float target) {
		for (float i : arr)
			if (i == target)
				return true;
		return false;
	}

	/**	Returns true if the given array contains a double equal to the given target double.
		@param arr The array to search
		@param target The target double to find
		@return True if the target is found in the array, false otherwise
	*/
	public static boolean contains(double[] arr, double target) {
		for (double i : arr)
			if (i == target)
				return true;
		return false;
	}

	/**	Returns true if the given array contains a boolean equal to the given target boolean.
		@param arr The array to search
		@param target The target boolean to find
		@return True if the target is found in the array, false otherwise
	*/
	public static boolean contains(boolean[] arr, boolean target) {
		for (boolean i : arr)
			if (i == target)
				return true;
		return false;
	}

	/**	Returns true if the given array contains a byte equal to the given target byte.
		@param arr The array to search
		@param target The target byte to find
		@return True if the target is found in the array, false otherwise
	*/
	public static boolean contains(byte[] arr, byte target) {
		for (byte i : arr)
			if (i == target)
				return true;
		return false;
	}

	/**	Returns true if the given array contains a char equal to the given target char.
		@param arr The array to search
		@param target The target char to find
		@return True if the target is found in the array, false otherwise
	*/
	public static boolean contains(char[] arr, char target) {
		for (char i : arr)
			if (i == target)
				return true;
		return false;
	}

	/**	Returns true if the given String array contains a String equal to the given String (ignoring case).
		@param arr The array to search
		@param target The target String to find
		@return True if the target String is found in the array, false otherwise
	*/
	public static boolean containsIgnoreCase(String[] arr, String target) {
		for (String s : arr)
			if ((s == target) || (s != null && target != null && s.equalsIgnoreCase(target)))
				return true;
		return false;
	}

	/**	Returns the first index in the array with a null value.
		@param arr The array to search
		@return The first empty index, or -1 if there is no such index
	*/
	public static int firstEmpty(Object[] arr) {
		for (int i=0; i < arr.length; i++)
			if (arr[i] == null)
				return i;
		return -1;
	}

	/**	Returns the index in this array that contains the smallest value. 
		If there are several elements with the same minumum value, the first index at which one of these elements appears is returned. 
		If the array is empty or filled with null values, -1 is returned.

		@param arr The array to look at
		@return The minimum index, or -1 if one is not found
	*/
	public static <T extends Comparable<? super T>> int minIndex(T[] arr) {
		int min = -1;

		for (int i=0; i < arr.length; i++) {
			if (arr[i] != null && (min < 0 || arr[i].compareTo(arr[min]) < 0))
				min = i;
		}

		return min;
	}

	/**	Returns the index in this array that contains the smallest value. 
		If there are several elements with the same minumum value, the first index at which one of these elements appears is returned. 
		If the array is empty, -1 is returned.

		@param arr The array to look at
		@return The minimum index, or -1 if the array is empty
	*/
	public static int minIndex(short[] arr) {
		int min = -1;

		for (int i=0; i < arr.length; i++) {
			if (min < 0 || arr[i] < arr[min])
				min = i;
		}

		return min;
	}

	/**	Returns the index in this array that contains the smallest value. 
		If there are several elements with the same minumum value, the first index at which one of these elements appears is returned. 
		If the array is empty, -1 is returned.

		@param arr The array to look at
		@return The minimum index, or -1 if the array is empty
	*/
	public static int minIndex(int[] arr) {
		int min = -1;

		for (int i=0; i < arr.length; i++) {
			if (min < 0 || arr[i] < arr[min])
				min = i;
		}

		return min;
	}

	/**	Returns the index in this array that contains the smallest value. 
		If there are several elements with the same minumum value, the first index at which one of these elements appears is returned. 
		If the array is empty, -1 is returned.

		@param arr The array to look at
		@return The minimum index, or -1 if the array is empty
	*/
	public static int minIndex(long[] arr) {
		int min = -1;

		for (int i=0; i < arr.length; i++) {
			if (min < 0 || arr[i] < arr[min])
				min = i;
		}

		return min;
	}

	/**	Returns the index in this array that contains the smallest value. 
		If there are several elements with the same minumum value, the first index at which one of these elements appears is returned. 
		If the array is empty, -1 is returned.

		@param arr The array to look at
		@return The minimum index, or -1 if the array is empty
	*/
	public static int minIndex(float[] arr) {
		int min = -1;

		for (int i=0; i < arr.length; i++) {
			if (min < 0 || arr[i] < arr[min])
				min = i;
		}

		return min;
	}

	/**	Returns the index in this array that contains the smallest value. 
		If there are several elements with the same minumum value, the first index at which one of these elements appears is returned. 
		If the array is empty, -1 is returned.

		@param arr The array to look at
		@return The minimum index, or -1 if the array is empty
	*/
	public static int minIndex(double[] arr) {
		int min = -1;

		for (int i=0; i < arr.length; i++) {
			if (min < 0 || arr[i] < arr[min])
				min = i;
		}

		return min;
	}

	/**	Returns the index in this array that contains the smallest value. 
		If there are several elements with the same minumum value, the first index at which one of these elements appears is returned. 
		If the array is empty, -1 is returned.

		@param arr The array to look at
		@return The minimum index, or -1 if the array is empty
	*/
	public static int minIndex(byte[] arr) {
		int min = -1;

		for (int i=0; i < arr.length; i++) {
			if (min < 0 || arr[i] < arr[min])
				min = i;
		}

		return min;
	}

	/**	Returns the index in this array that contains the smallest value. 
		If there are several elements with the same minumum value, the first index at which one of these elements appears is returned. 
		If the array is empty, -1 is returned.

		@param arr The array to look at
		@return The minimum index, or -1 if the array is empty
	*/
	public static int minIndex(char[] arr) {
		int min = -1;

		for (int i=0; i < arr.length; i++) {
			if (min < 0 || arr[i] < arr[min])
				min = i;
		}

		return min;
	}

	/**	Returns the index in this array that contains the largest value. 
		If there are several elements with the same maximum value, the first index at which one of these elements appears is returned. 
		If the array is empty or filled with null values, -1 is returned.
		
		@param arr The array to look at
		@return The maximum index, or -1 if one is not found
	*/
	public static <T extends Comparable<? super T>> int maxIndex(T[] arr) {
		int max = -1;

		for (int i=1; i < arr.length; i++) {
			if (arr[i] != null && (max < 0 || arr[i].compareTo(arr[max]) > 0))
				max = i;
		}

		return max;
	}

	/**	Returns the index in this array that contains the largest value. 
		If there are several elements with the same maximum value, the first index at which one of these elements appears is returned. 
		If the array is empty, -1 is returned.
		
		@param arr The array to look at
		@return The maximum index, or -1 if the array is empty
	*/
	public static int maxIndex(short[] arr) {
		int max = -1;

		for (int i=1; i < arr.length; i++) {
			if (max < 0 || arr[i] > arr[max])
				max = i;
		}

		return max;
	}

	/**	Returns the index in this array that contains the largest value. 
		If there are several elements with the same maximum value, the first index at which one of these elements appears is returned. 
		If the array is empty, -1 is returned.
		
		@param arr The array to look at
		@return The maximum index, or -1 if the array is empty
	*/
	public static int maxIndex(int[] arr) {
		int max = -1;

		for (int i=1; i < arr.length; i++) {
			if (max < 0 || arr[i] > arr[max])
				max = i;
		}

		return max;
	}

	/**	Returns the index in this array that contains the largest value. 
		If there are several elements with the same maximum value, the first index at which one of these elements appears is returned. 
		If the array is empty, -1 is returned.
		
		@param arr The array to look at
		@return The maximum index, or -1 if the array is empty
	*/
	public static int maxIndex(long[] arr) {
		int max = -1;

		for (int i=1; i < arr.length; i++) {
			if (max < 0 || arr[i] > arr[max])
				max = i;
		}

		return max;
	}

	/**	Returns the index in this array that contains the largest value. 
		If there are several elements with the same maximum value, the first index at which one of these elements appears is returned. 
		If the array is empty, -1 is returned.
		
		@param arr The array to look at
		@return The maximum index, or -1 if the array is empty
	*/
	public static int maxIndex(float[] arr) {
		int max = -1;

		for (int i=1; i < arr.length; i++) {
			if (max < 0 || arr[i] > arr[max])
				max = i;
		}

		return max;
	}

	/**	Returns the index in this array that contains the largest value. 
		If there are several elements with the same maximum value, the first index at which one of these elements appears is returned. 
		If the array is empty, -1 is returned.
		
		@param arr The array to look at
		@return The maximum index, or -1 if the array is empty
	*/
	public static int maxIndex(double[] arr) {
		int max = -1;

		for (int i=1; i < arr.length; i++) {
			if (max < 0 || arr[i] > arr[max])
				max = i;
		}

		return max;
	}

	/**	Returns the index in this array that contains the largest value. 
		If there are several elements with the same maximum value, the first index at which one of these elements appears is returned. 
		If the array is empty, -1 is returned.
		
		@param arr The array to look at
		@return The maximum index, or -1 if the array is empty
	*/
	public static int maxIndex(byte[] arr) {
		int max = -1;

		for (int i=1; i < arr.length; i++) {
			if (max < 0 || arr[i] > arr[max])
				max = i;
		}

		return max;
	}

	/**	Returns the index in this array that contains the largest value. 
		If there are several elements with the same maximum value, the first index at which one of these elements appears is returned. 
		If the array is empty, -1 is returned.
		
		@param arr The array to look at
		@return The maximum index, or -1 if the array is empty
	*/
	public static int maxIndex(char[] arr) {
		int max = -1;

		for (int i=1; i < arr.length; i++) {
			if (max < 0 || arr[i] > arr[max])
				max = i;
		}

		return max;
	}

	/**	Copies an array, padding leftover space with the given object.
		@param from The source array
		@param to The destination array
		@param fill The object to fill leftover space with
	*/
	public static <T> void copyArray(T[] from, T[] to, T fill) {
		for (int i=0; i < to.length; i++) {
			if (from.length > i)
				to[i] = from[i];
			else
				to[i] = fill;
		}
	}

	/**	Copies an array, padding leftover space with the given short.
		@param from The source array
		@param to The destination array
		@param fill The short to fill leftover space with
	*/
	public static void copyArray(short[] from, short[] to, short fill) {
		for (int i=0; i < to.length; i++) {
			if (from.length > i)
				to[i] = from[i];
			else
				to[i] = fill;
		}
	}

	/**	Copies an array, padding leftover space with the given int.
		@param from The source array
		@param to The destination array
		@param fill The int to fill leftover space with
	*/
	public static void copyArray(int[] from, int[] to, int fill) {
		for (int i=0; i < to.length; i++) {
			if (from.length > i)
				to[i] = from[i];
			else
				to[i] = fill;
		}
	}

	/**	Copies an array, padding leftover space with the given long.
		@param from The source array
		@param to The destination array
		@param fill The long to fill leftover space with
	*/
	public static void copyArray(long[] from, long[] to, long fill) {
		for (int i=0; i < to.length; i++) {
			if (from.length > i)
				to[i] = from[i];
			else
				to[i] = fill;
		}
	}

	/**	Copies an array, padding leftover space with the given float.
		@param from The source array
		@param to The destination array
		@param fill The float to fill leftover space with
	*/
	public static void copyArray(float[] from, float[] to, float fill) {
		for (int i=0; i < to.length; i++) {
			if (from.length > i)
				to[i] = from[i];
			else
				to[i] = fill;
		}
	}

	/**	Copies an array, padding leftover space with the given double.
		@param from The source array
		@param to The destination array
		@param fill The double to fill leftover space with
	*/
	public static void copyArray(double[] from, double[] to, double fill) {
		for (int i=0; i < to.length; i++) {
			if (from.length > i)
				to[i] = from[i];
			else
				to[i] = fill;
		}
	}

	/**	Copies an array, padding leftover space with the given boolean.
		@param from The source array
		@param to The destination array
		@param fill The boolean to fill leftover space with
	*/
	public static void copyArray(boolean[] from, boolean[] to, boolean fill) {
		for (int i=0; i < to.length; i++) {
			if (from.length > i)
				to[i] = from[i];
			else
				to[i] = fill;
		}
	}

	/**	Copies an array, padding leftover space with the given byte.
		@param from The source array
		@param to The destination array
		@param fill The byte to fill leftover space with
	*/
	public static void copyArray(byte[] from, byte[] to, byte fill) {
		for (int i=0; i < to.length; i++) {
			if (from.length > i)
				to[i] = from[i];
			else
				to[i] = fill;
		}
	}

	/**	Copies an array, padding leftover space with the given char.
		@param from The source array
		@param to The destination array
		@param fill The char to fill leftover space with
	*/
	public static void copyArray(char[] from, char[] to, char fill) {
		for (int i=0; i < to.length; i++) {
			if (from.length > i)
				to[i] = from[i];
			else
				to[i] = fill;
		}
	}
	
	/**	Sorts an array using the insertion sorting method. Null elements are placed at the end.
		@param arr An array of objects which implement the Comparable interface
	*/
	public static <T extends Comparable<? super T>> void sortInsertion(T[] arr) {
		for (int i=1; i < arr.length; i++) {
			int index = i;
			T val = arr[i];
			
			if (val != null) {
				while (index > 0 && (arr[index-1] == null || arr[index-1].compareTo(val) > 0)) {
					arr[index] = arr[index-1];
					index--;
				}
			}
			
			arr[index] = val;
		}
	}

	/**	Sorts an array using the insertion sorting method.
		@param arr An array of ints
	*/
	public static void sortInsertion(int[] arr) {
		for (int i=1; i < arr.length; i++) {
			int index = i;
			int val = arr[i];

			while (index > 0 && arr[index-1] > val) {
				arr[index] = arr[index-1];
				index--;
			}
			
			arr[index] = val;
		}
	}
	
	/**	Sorts an array using the selection sorting method. Null elements are placed at the end.
		@param arr An array of objects which implement the Comparable interface
	*/
	public static <T extends Comparable<? super T>> void sortSelection(T[] arr) {
		int min;
		
		for (int i=0; i < arr.length - 1; i++) {
			min = i;
			
			for (int j=i+1; j < arr.length; j++)
				if (arr[min] == null || (arr[j] != null && arr[j].compareTo(arr[min]) < 0))
					min = j;
			
			swapElements(arr, i, min);
		}
	}

	/**	Sorts an array using the selection sorting method.
		@param arr An array of ints
	*/
	public static void sortSelection(int[] arr) {
		int min;
		
		for (int i=0; i < arr.length - 1; i++) {
			min = i;
			
			for (int j=i+1; j < arr.length; j++)
				if (arr[j] < arr[min])
					min = j;
			
			swapElements(arr, i, min);
		}
	}

	/**	Sorts an array using the merge sorting method. Null elements are placed at the end.
		@param arr An array of objects to be sorted
	*/
	public static <T extends Comparable<? super T>> void sortMerge(T[] arr) {
		sortMerge(arr, 0, arr.length - 1);
	}

	/**	A helper function for merge sort, this is the acual method called recursively.
		@param arr The array being sorted
		@param min The mimimum index to sort
		@param max The maximum value to sort
	*/
	private static <T extends Comparable<? super T>> void sortMerge(T[] arr, int min, int max) {
		if (max <= min)
			return;
		else {
			int mid = (min + max) / 2;

			sortMerge(arr, min, mid);
			sortMerge(arr, mid+1, max);

			merge(arr, min, mid, max);
		}
	}

	/**	Another helper method for merge sort, this method actually merges two parts of the array together to sort them.
		The ranges min to mid (inclusive) and mid + 1 to max (inclusive) are assumed to be already sorted.

		@param arr The array being sorted
		@param min The minimum index being merged
		@param mid The index separating the parts that are to be merged
		@param max The maximum index being merged
	*/
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<? super T>> void merge(T[] arr, int min, int mid, int max) {
		int lower = min;
		int upper = mid+1;
		int index = 0;
		T[] sorted = (T[])new Comparable[max-min + 1];

		while (lower <= mid && upper <= max) {
			if (arr[upper] == null || (arr[lower] != null && arr[lower].compareTo(arr[upper]) < 0)) {
				sorted[index++] = arr[lower++];
			}else {
				sorted[index++] = arr[upper++];
			}
		}

		while (lower <= mid) {
			sorted[index++] = arr[lower++];
		}

		while (upper <= max) {
			sorted[index++] = arr[upper++];
		}

		for (int i=0; i < sorted.length; i++)
			arr[i+min] = sorted[i];
	}

	public static <T extends Comparable<? super T>> void sortQuick(T[] arr) {
		sortQuick(arr, 0, arr.length-1, arr.length/2);
	}

	private static <T extends Comparable<? super T>> void sortQuick(T[] arr, int min, int max, int pivot) {
		if (max <= min)
			return;
		pivot = sortSides(arr, min, max, pivot);
		sortQuick(arr, min, pivot-1, (pivot+min)/2);
		sortQuick(arr, pivot+1, max, (pivot+max)/2);
	}

	private static <T extends Comparable<? super T>> int sortSides(T[] arr, int min, int max, int pivot) {
		while (max > min) {
			boolean swap = true;

			if (arr[pivot] != null && arr[max] != null && arr[max].compareTo(arr[pivot]) > 0) {
				swap = false;
				max--;
			}

			if (arr[pivot] == null || arr[min] == null || arr[min].compareTo(arr[pivot]) < 0) {
				swap = false;
				min++;
			}

			if (swap) {
				swapElements(arr, min, max);

				if (pivot == min) {
					pivot = max;
					min++;
				} else {
					if (pivot == max)
						pivot = min;
					else
						min++;
					max--;
				}
			}
		}

		return pivot;
	}

	/**	Sorts an array using the insertion sorting method.
		@param arr An array of objects to be sorted
		@param compare A Comparator object to be used to compare objects in the array
	*/
	public static <T> void sortInsertion(T[] arr, Comparator<? super T> compare) {
		for (int i=1; i < arr.length; i++) {
			int index = i;
			T val = arr[i];
			
			while (index > 0 && compare.compare(arr[index-1], val) > 0) {
				arr[index] = arr[index-1];
				index--;
			}
			
			arr[index] = val;
		}
	}

	/**	Sorts an array using the insertion sorting method.
		@param arr An array of ints to be sorted
		@param compare A Comparator object to be used to compare ints in the array
	*/
	public static void sortInsertion(int[] arr, Comparator<Integer> compare) {
		for (int i=1; i < arr.length; i++) {
			int index = i;
			int val = arr[i];
			
			while (index > 0 && compare.compare(arr[index-1], val) > 0) {
				arr[index] = arr[index-1];
				index--;
			}
			
			arr[index] = val;
		}
	}

	/**	Sorts an array using the selection sorting method.
		@param arr An array of objects to be sorted
		@param compare A Comparator object to be used to compare objects in the array
	*/
	public static <T> void sortSelection(T[] arr, Comparator<? super T> compare) {
		int min;
		
		for (int i=0; i < arr.length - 1; i++) {
			min = i;
			
			for (int j=i+1; j < arr.length; j++)
				if (compare.compare(arr[j], arr[min]) < 0)
					min = j;
			
			swapElements(arr, i, min);
		}
	}
	
	/**	Sorts an array using the selection sorting method.
		@param arr An array of ints to be sorted
		@param compare A Comparator object to be used to compare ints in the array
	*/
	public static void sortSelection(int[] arr, Comparator<Integer> compare) {
		int min;
		
		for (int i=0; i < arr.length - 1; i++) {
			min = i;
			
			for (int j=i+1; j < arr.length; j++)
				if (compare.compare(arr[j], arr[min]) < 0)
					min = j;
			
			swapElements(arr, i, min);
		}
	}
	
	/** Uses the sequential search pattern to find a target value in an array of elements. Uses .equals() to check for equality.
		@param arr The array in which to search for the target value
		@return The lowest index at which target.compareTo(element) returns 0, or -1 if no such element is found.
	*/
	public static int findSequential(Object[] arr, Object target) {
		for (int i=0; i < arr.length; i++)
			if (target.equals(arr[i]))
				return i;
		return -1;
	}
	
	/** Uses the binary search pattern to find a target value in an array of elements which implement the Comparable interface.
		@param arr The array in which to search for the target value. The array MUST be sorted in ascending order, or this method's return value is not guaranteed to be accurate.
		@return An index at which target.compareTo(element) returns 0, or -1 if no such element is found. This method does not guarantee which index will be returned if the array contains duplicate elements.
	*/
	public static <T extends Comparable<? super T>> int findBinary(T[] arr, T target) {
		int min = 0, max = arr.length - 1;
		int mid;
		
		do {
			mid = (min + max) / 2;
			int compare = target.compareTo(arr[mid]);
			
			if (compare > 0)
				min = mid+1;
			else if (compare < 0)
				max = mid-1;
			else
				return mid;
		} while (min <= max);
		
		return -1;
	}

	public static <T extends Comparable<? super T>> void sillySort(T[] arr) {
		int a, b;

		while (!isSorted(arr)) {
			a = Numbers.randomInt(arr.length);
			b = Numbers.randomInt(arr.length);

			if (a > b && arr[b].compareTo(arr[a]) > 0)
				swapElements(arr, a, b);
			else
				swapElements(arr, Numbers.randomInt(arr.length), Numbers.randomInt(arr.length));
		}
	}

	public static void sillySort(int[] arr) {
		int a, b;

		while (!isSorted(arr)) {
			a = Numbers.randomInt(arr.length);
			b = Numbers.randomInt(arr.length);

			if ((a > b) == (arr[b] >= arr[a])) // in reverse order & not equal
				swapElements(arr, a, b);
			else
				swapElements(arr, Numbers.randomInt(arr.length), Numbers.randomInt(arr.length));
		}
	}
}