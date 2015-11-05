package me.kyle1320.grid;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.concurrent.SynchronousQueue;

/**
 * A fixed-size 2-dimensional grid of items that can be changed
 *
 * @author Kyle Cutler
 */
public class Grid<T> {
	public static final int[][] SURROUNDING = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
	public static final int[][] ADJACENT 	= {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	public static final int[][] DIAGONAL 	= {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};

	private final int width, height;
	private final T[][] data;

	/**
	 * Creates an empty Grid with the specified width and height
	 *
	 * @param 	width 	The width of the grid
	 * @param 	height 	The height of the grid
	 */
	@SuppressWarnings("unchecked")
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;

		data = (T[][])new Object[height][width];
	}

	/**
	 * Creates a Grid with the specified width and height filled with the given object
	 *
	 * @param 	width 	The width of the grid
	 * @param 	height 	The height of the grid
	 * @param 	fill 	The object to fill the grid with
	 */
	public Grid(int width, int height, T fill) {
		this(width, height);

		this.fill(fill);
	}

	/**
	 * Creates a Grid with the specified width and height filled using the given factory
	 *
	 * @param 	width 	The width of the grid
	 * @param 	height 	The height of the grid
	 * @param 	fill 	The Factory to use to fill the grid
	 */
	public Grid(int width, int height, Factory<T> fill) {
		this(width, height);

		this.fill(fill);
	}

	/**
	 * Applies the given operation to the cell at the given coordinate
	 *
	 * @param 	op 	The Operation to apply to the cell
	 * @param 	x 	The x-coordinate of the cell to apply the operation to
	 * @param 	y 	The y-coordinate of the cell to apply the operation to
	 */
	public void applyOperation(Operation<T> op, int x, int y) {
		data[y][x] = op.apply(x, y, data[y][x]);
	}

	/**
	 * Applies the given void operation to the cell at the given coordinate
	 *
	 * @param 	op 	The VoidOperation to apply to the cell
	 * @param 	x 	The x-coordinate of the cell to apply the operation to
	 * @param 	y 	The y-coordinate of the cell to apply the operation to
	 */
	public void applyOperation(VoidOperation<T> op, int x, int y) {
		op.apply(x, y, data[y][x]);
	}

	/**
	 * Applies the given void operation to every cell in the grid
	 *
	 * @param 	op 	The VoidOperation to apply to each cell in the grid
	 */
	public synchronized void each(VoidOperation<T> op) {
		for (int y=0; y < height; y++)
			for (int x=0; x < width; x++)
				op.apply(x, y, data[y][x]);
	}

	/**
	 * Applies the given operation to every cell in the grid
	 *
	 * @param 	op 	The Operation to apply to each cell in the grid
	 *
	 * @return 		Whether or not the grid changed (according to .equals)
	 */
	public synchronized boolean transform(Operation<T> op) {
		boolean changed = false;

		for (int y=0; y < height; y++) {
			for (int x=0; x < width; x++) {
				T n = op.apply(x, y, data[y][x]);
				if (!changed)
					changed = (data[y][x] != n && (data[y][x] == null || n == null || !data[y][x].equals(n)));
				data[y][x] = n;
			}
		}

		return changed;
	}

	/**
	 * Applies the given operation to every cell in the grid
	 *
	 * @param 	op 				The Operation to apply to each cell in the grid
	 * @param 	multithread 	A boolean whether or not to use multiple threads when transforming
	 *
	 * @return 					Whether or not the grid changed (according to .equals)
	 */
	public synchronized boolean transform(Operation<T> op, boolean multithread) {
		if (multithread) {
			Grid<T> copy = this.copy();

			int threadcount = Runtime.getRuntime().availableProcessors();
			int cellcount = width * height;
			Thread[] threads = new Thread[threadcount];
			int[] starts = new int[threadcount+1];

			for (int i=1; i <= threadcount; i++) {
				starts[i] = (int)(cellcount * (double)i / threadcount);
			}

			for (int i=0; i < threadcount; i++) {
				int index = i;
				threads[i] = new Thread(() -> {
					int x, y;
					for (int j=starts[index]; j < starts[index+1]; j++) {
						x = j % width;
						y = j / width;

						data[y][x] = op.apply(x, y, data[y][x]);
					}
				});
				threads[i].start();
			}

			for (int i=0; i < threadcount; i++) {
				try {
					threads[i].join();
				} catch (InterruptedException e) {}
			}

			// take the lazy way out
			return !this.equals(copy);
		} else {
			return transform(op);
		}
	}

	/**
	 * Applies the given copied operation to every cell in the grid, using a single thread
	 *
	 * @param 	op 	The CopiedOperation to apply to each cell in the grid
	 *
	 * @return 		Whether or not the grid changed (according to .equals)
	 */
	public synchronized boolean transform(CopiedOperation<T> op) {
		Grid<T> copy = this.copy();
		boolean changed = false;

		for (int y=0; y < height; y++) {
			for (int x=0; x < width; x++) {
				T n = op.apply(x, y, copy, data[y][x]);
				if (!changed)
					changed = (data[y][x] != n && (data[y][x] == null || n == null || !data[y][x].equals(n)));
				data[y][x] = n;
			}
		}

		return changed;
	}

	/**
	 * Applies the given copied operation to every cell in the grid
	 *
	 * @param 	op 				The CopiedOperation to apply to each cell in the grid
	 * @param 	multithread 	A boolean whether or not to use multiple threads when transforming
	 *
	 * @return 					Whether or not the grid changed (according to .equals)
	 */
	public synchronized boolean transform(CopiedOperation<T> op, boolean multithread) {
		if (multithread) {
			Grid<T> copy = this.copy();

			int threadcount = Runtime.getRuntime().availableProcessors();
			int cellcount = width * height;
			Thread[] threads = new Thread[threadcount];
			int[] starts = new int[threadcount+1];

			for (int i=1; i <= threadcount; i++) {
				starts[i] = (int)(cellcount * (double)i / threadcount);
			}

			for (int i=0; i < threadcount; i++) {
				int index = i;
				threads[i] = new Thread(() -> {
					int x, y;
					for (int j=starts[index]; j < starts[index+1]; j++) {
						x = j % width;
						y = j / width;

						data[y][x] = op.apply(x, y, copy, data[y][x]);
					}
				});
				threads[i].start();
			}

			for (int i=0; i < threadcount; i++) {
				try {
					threads[i].join();
				} catch (InterruptedException e) {}
			}

			// take the lazy way out
			return !this.equals(copy);
		} else {
			return transform(op);
		}
	}

	/**
	 * Fills the grid using the given factory, overwriting existing items
	 *
	 * @param 	factory 	The Factory to use to fill the grid
	 */
	public synchronized void fill(Factory<T> factory) {
		for (int y=0; y < height; y++) {
			for (int x=0; x < width; x++) {
				set(x, y, factory.produce(x, y));
			}
		}
	}

	/**
	 * Fills the grid with the given object, overwriting existing items
	 *
	 * @param 	value 	The object to use to fill the grid
	 */
	public synchronized void fill(T value) {
		for (int y=0; y < height; y++) {
			for (int x=0; x < width; x++) {
				set(x, y, value);
			}
		}
	}

	/**
	* Fills the grid with null values.
	*/
	public synchronized void empty() {
		for (int y=0; y < height; y++) {
			for (int x=0; x < width; x++) {
				set(x, y, null);
			}
		}
	}

	/**
	 * Returns true if this grid nas no null values
	 *
	 * @return 	True if there are no null values contained in this grid
	 */
	public boolean isFull() {
		for (int y=0; y < height; y++) {
			for (int x=0; x < width; x++) {
				if (data[y][y] == null)
					return false;
			}
		}

		return true;
	}

	/**
	 * Returns a new grid that contains the same data as this grid
	 *
	 * @return 	A new Grid with the same data as this Grid
	 */
	public synchronized Grid<T> copy() {
		Grid<T> copy = new Grid<>(width, height);

		for (int y=0; y < height; y++) {
			System.arraycopy(data[y], 0, copy.data[y], 0, width);
		}

		return copy;
	}

	/**
	 * Returns the item at the given coordinates in the grid
	 *
	 * @param 	x 	The x coordinate of the item to be retrieved
	 * @param 	y 	The y coordinate of the item to be retrieved
	 *
	 * @return 		The item at the given position in the grid
	 *
	 * @exception 	ArrayIndexOutOfBoundsException 	If the coordinates are outside of the grid
	 */
	public T get(int x, int y) throws ArrayIndexOutOfBoundsException {
		return data[y][x];
	}

	/**
	 * Sets the item at the given coordinates in the grid to the given value, overwriting any existing item
	 *
	 * @param 	x 	The x coordinate to place the item
	 * @param 	y 	The y coordinate to place the item
	 *
	 * @exception 	ArrayIndexOutOfBoundsException 	If the coordinates are outside of the grid
	 */
	public void set(int x, int y, T value) throws ArrayIndexOutOfBoundsException {
		data[y][x] = value;
	}

	/**
	* Returns a list of the values of up to 8 neighbors surrounding the given cell
	*
	* @param 	x 	The x-coordinate of the cell to look around
	* @param 	y 	The y-coordinate of the cell to look around
	*
	* @return 		A list of up to 8 neighbor values
	*/
	public synchronized List<T> getNeighbors(int x, int y) {
		return getNeighbors(x, y, SURROUNDING);
	}

	/**
	 * Returns a list of the values of up to 8 neighbors surrounding the given cell
	 *
	 * @param 	x 			The x-coordinate of the cell to look around
	 * @param 	y 			The y-coordinate of the cell to look around
	 * @param 	relatives 	The list of relative coordinates to look for neighbors in
	 *
	 * @return 		A list of up to 8 neighbor values
	 */
	public synchronized List<T> getNeighbors(int x, int y, int[][] relatives) {
		return getNeighbors(x, y, relatives, (v) -> true);
	}

	/**
	 * Returns a list of the values of neighbors to the given cell
	 *
	 * @param 	x 			The x-coordinate of the cell to look around
	 * @param 	y 			The y-coordinate of the cell to look around
	 * @param 	relatives 	The list of relative coordinates to look for neighbors in
	 * @param 	test 		The boolean function to use to determine whether
	 * 						or not to include a cell in the neighbor list
	 *
	 * @return 				A list of neighbors to the given cell
	 */
	public synchronized List<T> getNeighbors(int x, int y, int[][] relatives, Function<T, Boolean> test) {
		return getNeighbors(x, y, relatives, test, false);
	}

	/**
	 * Returns a list of the values of neighbors to the given cell
	 *
	 * @param 	x 			The x-coordinate of the cell to look around
	 * @param 	y 			The y-coordinate of the cell to look around
	 * @param 	relatives 	The list of relative coordinates to look for neighbors in
	 * @param 	test 		The boolean function to use to determine whether
	 * 						or not to include a cell in the neighbor list
	 * @param 	wrap 		Whether or not to wrap the board edges, counting
	 * 						neighbors along the opposite edge
	 *
	 * @return 				A list of neighbors to the given cell
	 */
	public synchronized List<T> getNeighbors(int x, int y, int[][] relatives, Function<T, Boolean> test, boolean wrap) {
		List<T> neighbors = new ArrayList<>(relatives.length);

		if (wrap) {
			for (int[] neighbor : relatives) {
				T n = get((x+neighbor[0]+width)%width, (y+neighbor[1]+height)%height);

				if (n != null)
				neighbors.add(n);
			}
		} else {
			for (int[] neighbor : relatives) {
				try {
					T n = get(x+neighbor[0], y+neighbor[1]);

					if (test.apply(n))
						neighbors.add(n);
				} catch (ArrayIndexOutOfBoundsException e) {}
			}
		}

		return neighbors;
	}

	/**
	 * Returns the width of this Grid
	 *
	 * @return 	The width of this Grid
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of this Grid
	 *
	 * @return 	The height of this Grid
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Compares this grid with another object, and returns true if they are equal.
	 * For them to be equal, the other object must:
	 * 1. Be a Grid
	 * 2. Have width and height equal to this grid
	 * 3. Be filled with the same objects as this Grid, as checked by .equals()
	 *
	 * @param 	other 	Another object to check equality against
	 */
	public synchronized boolean equals(Object other) {
		if (!(other instanceof Grid))
			return false;
		Grid<?> grid = (Grid)other;

		if (height != grid.height || width != grid.width)
			return false;

		for (int y=0; y < height; y++) {
			for (int x=0; x < width; x++) {
				Object o1 = data[y][x];
				Object o2 = grid.data[y][x];

				if ((o1 == null) ^ (o2 == null))
					return false;
				else if (o1 == o2 || o1.equals(o2))
					continue;
				else
					return false;
			}
		}

		return true;
	}

	/**
	 * An interface used to fill a grid with generated items
	 *
	 * @author 	Kyle Cutler
	 */
	public interface Factory<T> {
		/**
		 * Returns a newly created object
		 *
		 * @param 	x 	The x-coordinate where the item is to be placed
		 * @param 	y 	The y-coordinate where the item is to be placed
		 *
		 * @return 		An object to be placed at the location (x, y) in the grid
		 */
		T produce(int x, int y);
	}

	/**
	 * An interface used to change elements in the grid based on their location and data
	 *
	 * @author 	Kyle Cutler
	 */
	public interface Operation<T> {
		/**
		 * Changes the object at a location in the grid and returns the changed item
		 *
		 * @param 	x 		The x-coordinate of the item to be changed
		 * @param 	y 		The y-coordinate of the item to be changed
		 * @param 	data 	The value currently at the location in the grid
		 *
		 * @return 			An object to be placed at the location (x, y) in the grid
		 */
		T apply(int x, int y, T data);
	}

	/**
	 * An interface used to change elements in the grid based on their location and data.
	 * A copy of the grid is also passed to the operation, so that current changes in the grid will not affect future operations.
	 * This is especially useful for multithreading operations, because order does not matter.
	 *
	 * @author 	Kyle Cutler
	 */
	public interface CopiedOperation<T> {
		/**
		 * Changes the object at a location in the grid and returns the changed item
		 *
		 * @param 	x 		The x-coordinate of the item to be changed
		 * @param 	y 		The y-coordinate of the item to be changed
		 * @param 	copy 	A copy of the original grid
		 * @param 	data 	The value currently at the location in the grid
		 *
		 * @return 			An object to be placed at the location (x, y) in the grid
		 */
		T apply(int x, int y, Grid<T> copy, T data);
	}

	/**
	 * An interface used to do something with the data in the grid
	 *
	 * @author 	Kyle Cutler
	 */
	public interface VoidOperation<T> {
		/**
		 * Takes an object at a location in the grid and does something
		 *
		 * @param 	x 		The x-coordinate of the item
		 * @param 	y 		The y-coordinate of the item
		 * @param 	data 	The value at the location in the grid
		 */
		void apply(int x, int y, T data);
	}
}