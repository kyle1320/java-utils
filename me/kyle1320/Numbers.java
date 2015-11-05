package me.kyle1320;

import java.util.ArrayList;

/**	Contains various helper methods for arithmetic operations like averages or for generating numbers.
	@author Kyle Cutler
*/
public class Numbers {
	/**	Pushes the given integer to within the given bounds. (inclusive)
		@param num The number to set within bounds
		@param min The minimum bound
		@param max The maximum bound
		@return The integer pushed to within the bounds
	*/
	public static int limitBounds(int num, int min, int max) {
		return Math.min(max, Math.max(min, num));
	}

	/**	Returns an int array with the numbers from 0 (inclusive) to a maximum value (exclusive).
		@param max The maximum value to count up to
		@return An int array, empty if max <= 0
	*/
	public static int[] range(int max) {
		return range(0, max, 1);
	}

	/**	Returns an int array with the numbers from a minimum value (inclusive) to a maximum value (exclusive).
		@param min The minimum value to count from
		@param max The maximum value to count up to
		@return An int array, empty if max <= min
	*/
	public static int[] range(int min, int max) {
		return range(min, max, 1);
	}

	/**	Returns an int array with the numbers from min (inclusive) to max (exclusive) skipping by a given amount.
		@param min The minimum value to count from
		@param max The maximum value to count up to
		@param skip The number to count by each time
		@return An int array, empty if max <= min or skip <= 0
	*/
	public static int[] range(int min, int max, int skip) {
		if (max <= min || skip <= 0)
			return new int[] {};

		int[] fill = new int[(max-min-1)/skip + 1];

		int index = 0;
		for (int i=min; (skip > 0 ? i < max : i > max); i += skip)
			fill[index++] = i;

		return fill;
	}

	/**	Returns an array of prime Integers from 0 to max.
		@param max The maximum prime to find (exclusive)
		@return an Integer array of the prime numbers found
	*/
	public static Integer[] getPrimes(int max) {
		return getPrimes(2, max);
	}

	/**	Returns an array of prime Integers from a minimum value to a maximum value.
		@param min The minimum prime value to find (inclusive)
		@param max The maximum prime value to find (exclusive)
		@return An Integer array of the prime numbers found
	*/
	public static Integer[] getPrimes(int min, int max) {
		// should probably return an int[], gotta figure out how to make it work.
		boolean[] primes = new boolean[max-min];
		ArrayList<Integer> primesList = new ArrayList<Integer>();
		int sq = (int)Math.sqrt(max);

		for (int i=2; i < max; i++) {
			if (i <= sq) {
				for (int j=Math.max(i*i, min - (min%i)); j < max; j += i)
					if (j >= min)
						primes[j-min] = true;
			}

			if (i >= min)
				if (!primes[i-min])
					primesList.add(i);
		}

		Integer[] ret = primesList.toArray(new Integer[0]);

		return ret;
	}

	/**	Returns a random integer between 0 (inclusive) and a max (exclusive).
		@param max The maximum value
		@return The random value
	*/
	public static int randomInt(int max) {
		return Util.random.nextInt(max);
	}

	/**	Returns a random double between 0 (inclusive) and a max (exclusive).
		@param max The maximum value
		@return The random value
	*/
	public static double randomDouble(double max) {
		return Util.random.nextDouble()*max;
	}

	/**	Returns a random integer between a min (inclusive) and max (exclusive).
		@param min The minimum value
		@param max The maximum value
		@return The random value
	*/
	public static int randomInt(int min, int max) {
		return randomInt(max-min) + min;
	}

	/**	Returns a random double between a min (inclusive) and max (exclusive).
		@param min The minimum value
		@param max The maximum value
		@return The random value
	*/
	public static double randomDouble(double min, double max) {
		return randomDouble(max-min) + min;
	}

	/**	Returns the given double value rounded to the nearest value at the given place (base 10).
		For example a call to this method round(67.3, 1) would return 70.0, 
		and a call to this method round(45.38, -1) would return 45.4.
		
		@param num The double to round
		@param place The place to round toward
		@return The rounded double
	*/
	public static double round(double num, int place) {
		double unit = Math.pow(10, place);

		return Math.round(num/unit)*unit;
	}
	
	/**	Takes a list of Numbers and returns their sum as a double.
		@param elements A varargs list of Numbers to sum
		@return The sum of the elements as a double
	*/
	public static double sum(Number... elements) {
		double sum = 0;
		
		for (Number elem : elements)
			sum += elem.doubleValue();
		
		return sum;
	}
	
	/**	Takes a list of Numbers and returns their average as a double.
		@param elements A varargs list of Numbers to average
		@return The average of the elements as a double
	*/
	public static double average(Number... elements) {
		return sum(elements) / elements.length;
	}
}