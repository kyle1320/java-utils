package me.kyle1320;

/**
 * A basic generic immutable 2-tuple class
 *
 * @author Kyle Cutler
 */
public class Tuple<T, U> {
	public final T first;
	public final U second;

	/**
	 * Creates a new Tuple from the given elements
	 *
	 * @param 	first 	The first element in the tuple
	 * @param 	second 	The second element in the tuple
	 */
	public Tuple(T first, U second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * Returns this Tuple as a string in the format (first, second).
	 *
	 * @return 	This Tuple represented as a string
	 */
	public String toString() {
		return "(" + first + ", " + second + ")";
	}

	/**
	 * Overridden Object.equals
	 *
	 * @param 	other 	The other object to compare equality against.
	 *
	 * @return 			true if the given object is a Tuple and this tuple's first and second elements equal the other tuple's respective first and second elements
	*/
	public boolean equals(Object other) {
		if (other == null || !(other instanceof Tuple))
			return false;
		else {
			Tuple tuple = (Tuple)other;

			return (!((tuple.first == null) ^ (first == null)) && (tuple.first == first || tuple.first.equals(first))) &&
				   (!((tuple.second == null) ^ (second == null)) && (tuple.second == second || tuple.second.equals(second)));
		}
	}
}