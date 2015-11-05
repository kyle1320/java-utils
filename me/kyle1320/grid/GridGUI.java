package me.kyle1320.grid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ComponentAdapter;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.util.HashMap;
import java.util.function.Function;

/**
 * Provides an easy-to-use GUI that displays a Grid in a customizable way
 *
 * @author Kyle Cutler
 */
public final class GridGUI<E> {
	// the grid
	private Grid<E> grid = null;

	// display components
	private JFrame 			window;
	private GridPanel 		panel;

	// menu components
	private MenuBar 					menuBar 	= new MenuBar();
	private HashMap<String, Menu> 		menus 		= new HashMap<>();
	private HashMap<String, MenuItem> 	menuItems 	= new HashMap<>();

	// the method for drawing individual cells
	private DisplayStrategy<E> 	 	displayStrategy 	= null;
	private Function<E, Boolean> 	displayCondition 	= null;
	private Function<E, Color> 	itemColorFunction 	= null;

	// interactions between the user and the grid
	private Grid.VoidOperation<E> clickAction 		= null;
	private Grid.VoidOperation<E> leftClickAction 	= null;
	private Grid.VoidOperation<E> rightClickAction 	= null;

	private Grid.VoidOperation<E> dragAction 		= null;
	private Grid.VoidOperation<E> leftDragAction 	= null;
	private Grid.VoidOperation<E> rightDragAction 	= null;

	private KeyAction keyAction = null;

	// visual options
	private boolean showGridLines 	= true;
	private Color 	gridLineColor 	= Color.BLACK;
	private Color 	itemColor 		= Color.BLACK;
	private int 	cellPadding 	= 0;
	private int 	margin 			= 1;

	// interactivity options
	private boolean constantDrag = false;

	// mouse / hover coordinates, to prevent multiple clicks
	// and keep track of where the mouse has been
	private int mouseX;
	private int mouseY;
	private int lastMouseX;
	private int lastMouseY;
	private int dragX = -1;
	private int dragY = -1;

	// visual variables, for use when drawing
	// or to know something about what is on the screen
	private int 	drawWidth;
	private int 	drawHeight;
	private int[] 	lineSpacingsX;
	private int[] 	lineSpacingsY;

	/**
	 * Creates a new GridGUI that displays the given grid
	 *
	 * @param  grid  The Grid to display
	 */
	public GridGUI(Grid<E> grid) {
		this.grid = grid;
		init();
	}

	/**
	 * Initializes the components used in the GUI
	 */
	private void init() {
		window = new JFrame();
		panel = new GridPanel();

		window.setMenuBar(menuBar);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(panel);
		window.pack();
	}

	/**
	 * Displays the GUI frame
	 */
	public void show() {
		window.setVisible(true);
	}

	/**
	 * Hides the GUI frame
	 */
	public void hide() {
		window.setVisible(false);
	}

	/**
	 * Redraws the grid
	 */
	public void update() {
		panel.repaint();
	}

	/**
	 * Changes the grid displayed in the GUI, and redraws the grid
	 *
	 * @param  grid  The new Grid to display
	 */
	public void setGrid(Grid<E> grid) {
		if (this.grid != grid) {
			this.grid = grid;
			resetData();
			update();
		}
	}

	/*********************/
	//      options      //
	/*********************/

	/**
	 * Changes whether or not lines should be drawn between cells in the grid, and redraws the grid
	 *
	 * @param 	show 	True if grid lines should be drawn, false for grid lines to not be shown
	 */
	public void showGridLines(boolean show) {
		if (this.showGridLines != show) {
			this.showGridLines = show;
			update();
		}
	}

	/**
	 * Sets the margin to be shown around the edges of the GUI, and redraws the grid
	 *
	 * @param 	margin 	The desired margin, in pixels
	 */
	public void setMargin(int margin) {
		if (this.margin != margin) {
			this.margin = margin;
			update();
		}
	}

	/**
	 * Changes the color of the grid lines when they are drawn, and redraws the grid
	 *
	 * @param 	color 	The desired grid line color
	 */
	public void setGridLineColor(Color color) {
		if (this.gridLineColor != color) {
			this.gridLineColor = color;
			update();
		}
	}

	/**
	 * Changes the default color to draw cells with, and redraws the grid
	 *
	 * @param 	color 	The desired default cell color
	 */
	public void setItemColor(Color color) {
		if (this.itemColor != color) {
			this.itemColor = color;
			update();
		}
	}

	/**
	* Changes the color to draw individual cells with, and redraws the grid
	*
	* @param 	color 	The function to use to determine cell color
	*/
	public void setItemColorFunction(Function<E, Color> colorFunction) {
		if (this.itemColorFunction != colorFunction) {
			this.itemColorFunction = colorFunction;
			update();
		}
	}

	/**
	 * Changes the padding between cells, or between cells and grid lines, and redraws the grid
	 *
	 * @param 	padding 	The desired padding around cells, in pixels
	 */
	public void setCellPadding(int padding) {
		if (this.cellPadding != padding) {
			this.cellPadding = padding;
			update();
		}
	}

	/**
	 * Changes the color shown behind cells / grid lines, and redraws the grid
	 *
	 * @param 	color 	The desired background color
	 */
	public void setBackground(Color color) {
		panel.setBackground(color);
		update();
	}

	/**
	 * Sets the window title
	 *
	 * @param 	title 	The desired title shown on the window
	 */
	public void setTitle(String title) {
		window.setTitle(title);
	}

	/**
	 * Sets whether or not dragging performs a clicking action only on different cells,
	 * Or on each mouse movement
	 *
	 * @param 	dragClicks 	True if dragging over cells should always click on them
	 */
	public void constantDrag(boolean constantDrag) {
		this.constantDrag = constantDrag;
	}

	/*********************/
	//      actions      //
	/*********************/

	/**
	 * Sets the strategy used when drawing cells, and redraws the grid
	 *
	 * @param 	strategy 	The DisplayStrategy to refer to when drawing each cell in the grid
	 */
	public void setDisplayStrategy(DisplayStrategy<E> strategy) {
		if (this.displayStrategy != strategy) {
			this.displayStrategy = strategy;
			update();
		}
	}

	/**
	* Sets the condition to use when determining whether to draw a cell
	*
	* @param 	strategy 	The Evaluation to use when determine whether a cell should be drawn
	*/
	public void setDisplayCondition(Function<E, Boolean> condition) {
		if (this.displayCondition != condition) {
			this.displayCondition = condition;
			update();
		}
	}

	/**
	 * Sets the operation to be performed when a cell is clicked
	 *
	 * @param 	action 	The Grid.VoidOperation specifying the operation to perform
	 */
	public void setClickAction(Grid.VoidOperation<E> action) {
		this.clickAction = action;
	}

	/**
	* Sets the operation to be performed when a cell is left clicked
	*
	* @param 	action 	The Grid.VoidOperation specifying the operation to perform
	*/
	public void setLeftClickAction(Grid.VoidOperation<E> action) {
		this.leftClickAction = action;
	}

	/**
	* Sets the operation to be performed when a cell is right clicked
	*
	* @param 	action 	The Grid.VoidOperation specifying the operation to perform
	*/
	public void setRightClickAction(Grid.VoidOperation<E> action) {
		this.rightClickAction = action;
	}

	/**
	 * Sets the operation to be performed when the mouse is dragged over a cell
	 *
	 * @param 	action 	The Grid.VoidOperation specifying the operation to perform
	 */
	public void setDragAction(Grid.VoidOperation<E> action) {
		this.dragAction = action;
	}

	/**
	 * Sets the operation to be performed when the mouse is left dragged over a cell
	 *
	 * @param 	action 	The Grid.VoidOperation specifying the operation to perform
	 */
	public void setLeftDragAction(Grid.VoidOperation<E> action) {
		this.leftDragAction = action;
	}

	/**
	 * Sets the operation to be performed when the mouse is right dragged over a cell
	 *
	 * @param 	action 	The Grid.VoidOperation specifying the operation to perform
	 */
	public void setRightDragAction(Grid.VoidOperation<E> action) {
		this.rightDragAction = action;
	}

	/**
	 * Sets the action to be performed when a key is typed
	 *
	 * @param 	action 	The KeyAction specifying the action to perform
	 */
	public void setKeyAction(KeyAction action) {
		this.keyAction = action;
	}

	/*********************/
	//     menu items    //
	/*********************/

	public void addMenuItem(ActionListener action, String menuName, String itemName) {
		addMenuItem(action, menuName, itemName, itemName);
	}

	public void removeMenuItem(String menuName, String itemName) {
		menuItems.remove(menuName + ":" + itemName);
	}

	public void addToggleMenuItem(ActionListener action, String menuName, String itemName, boolean checked) {
		final String label = itemName;
		final String checkedLabel = "* " + label;

		addMenuItem(new ActionListener() {
						private boolean c = checked;
						public void actionPerformed(ActionEvent e) {
							action.actionPerformed(e);
							setItemLabel(menuName, itemName, (c = !c) ? checkedLabel : label);
						}
					},
					menuName, itemName, checked ? checkedLabel : label);
	}

	private void addMenuItem(ActionListener action, String menuName, String itemName, String itemLabel) {
		String accessorString = menuName + ":" + itemName;

		// menu item doesn't exist
		if (!menuItems.containsKey(accessorString)) {
			Menu menu = menus.get(menuName);
			MenuItem item = new MenuItem(itemLabel);

			if (menu == null) {
				menu = new Menu(menuName);
				menuBar.add(menu);
				menus.put(menuName, menu);
			}

			item.addActionListener(action);
			menu.add(item);
			menuItems.put(accessorString, item);
		}
	}

	private void setItemLabel(String menuName, String itemName, String label) {
		MenuItem item = menuItems.get(menuName + ":" + itemName);

		if (item != null)
			item.setLabel(label);
	}

	/*********************/
	//       resets      //
	/*********************/

	public void resetOptions() {
		displayStrategy 	= null;
		displayCondition 	= null;
		itemColorFunction 	= null;

		clickAction 		= null;
		leftClickAction 	= null;
		rightClickAction 	= null;

		dragAction 		= null;
		leftDragAction 	= null;
		rightDragAction = null;

		keyAction 		= null;

		showGridLines 	= true;
		gridLineColor 	= Color.BLACK;
		itemColor 		= Color.BLACK;
		cellPadding 	= 0;
		margin 			= 1;

		constantDrag = false;

		panel.setBackground(Color.WHITE);
		window.setTitle("");
	}

	public void resetData() {
		mouseX = 0;
		mouseY = 0;
		lastMouseX = 0;
		lastMouseY = 0;
		dragX = -1;
		dragY = -1;

		drawWidth = 0;
		drawHeight = 0;
		lineSpacingsX = null;
		lineSpacingsY = null;
	}

	public void resetMenus() {
		while (menuBar.getMenuCount() > 0) {
			menuBar.remove(0);
		}

		menus.clear();
		menuItems.clear();
	}

	/**
	 * A JPanel put inside the window, responsible for drawing the grid
	 */
	private class GridPanel extends JPanel {

		// we paint the grid on the image, and the image is displayed later.
		//private BufferedImage 	image;
		//private Graphics2D 		imageGraphics;

		/**
		 * Creates a new GridPanel of size (600, 600)
		 */
		public GridPanel() {
			super();

			setBackground(Color.WHITE);
			//calculateSize();
			setPreferredSize(new Dimension(603, 603)); // bah
			setFocusable(true);

			// line spacings must be recalculated
			addComponentListener(new ComponentAdapter() {
				public void componentResized(ComponentEvent e) {
					lineSpacingsX = null;
					lineSpacingsY = null;
				}
			});

			// handle key events, if set
			addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (keyAction != null)
						keyAction.act(e.getKeyCode());
				}
			});

			// handle mouse press events, if set
			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					doClicks(e);
				}
			});

			// handle mouse drag events, if set
			addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					doDrags(e);
				}
			});
		}

		/**
		 * Performs the various click events depending on the given mouse event
		 *
		 * @param 	e 	The MouseEvent to use
		 */
		private void doClicks(MouseEvent e) {
			advanceMousePosition(e);

			if (SwingUtilities.isLeftMouseButton(e))
				click(leftClickAction);
			else
				click(rightClickAction);

			click(clickAction);
		}

		/**
		 * Performs the various drag events depending on the given mouse event
		 *
		 * @param 	e 	The MouseEvent to use
		 */
		private void doDrags(MouseEvent e) {
			advanceMousePosition(e);

			if (SwingUtilities.isLeftMouseButton(e))
				drag(leftDragAction);
			else
				drag(rightDragAction);

			drag(dragAction);
		}

		/**
		 * Sets the new and old mouse coordinate instance variables
		 *
		 * @param 	e 	The MouseEvent to look get coordinates from
		 */
		private void advanceMousePosition(MouseEvent e) {
			lastMouseX = mouseX;
			lastMouseY = mouseY;

			mouseX = e.getX();
			mouseY = e.getY();
		}

		/**
		 * Calculates the coordinate of the cell the mouse is over
		 * and sets the proper instance variables
		 */
		private boolean calculateMouseCoords() {
			int x = ((mouseX - margin) * grid.getWidth()) / drawWidth;
			int y = ((mouseY - margin) * grid.getHeight()) / drawHeight;

			boolean changed = !(x == dragX && y == dragY);

			dragX = x;
			dragY = y;

			return changed;
		}

		/**
		 * Simulates a click at the current mouse position, performing
		 * the current click action if it is set
		 */
		private void click(Grid.VoidOperation<E> action) {
			if (action != null) {
				calculateMouseCoords();

				if (dragX >= 0 && dragX < grid.getWidth() &&
					dragY >= 0 && dragY < grid.getHeight())
					grid.applyOperation(action, dragX, dragY);
			}
		}

		/**
		 * Simulates a drag at the current mouse position, performing
		 * the current drag action if it is set
		 */
		private void drag(Grid.VoidOperation<E> action) {
			if (action != null) {
				if (!calculateMouseCoords() && !constantDrag)
					return;

				if (dragX >= 0 && dragX < grid.getWidth() &&
					dragY >= 0 && dragY < grid.getHeight())
					grid.applyOperation(action, dragX, dragY);
			}
		}

		/**
		 * Draws the grid based on data inside the grid, the drawing strategy, window size, and options
		 *
		 * @param 	g 	The Graphics object to draw to
		 */
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			if (grid != null) {
				int canvasWidth = (int)g.getClipBounds().getWidth(), canvasHeight = (int)g.getClipBounds().getHeight();

				drawWidth = (canvasWidth - margin*2);
				drawHeight = (canvasHeight - margin*2);

				// if not showing grid lines, paddings are collapsed, so we end up with extra space.
				// This makes up for the extra space on the end by pretending we have more space than we really do
				if (!showGridLines) {
					canvasWidth += cellPadding;
					canvasHeight += cellPadding;
				}

				if (lineSpacingsX == null || lineSpacingsY == null) {
					// coordinates surrounding each cell
					lineSpacingsX = new int[grid.getWidth()+1];
					lineSpacingsY = new int[grid.getHeight()+1];

					// calculate x spacings
					for (int i=0; i <= grid.getWidth(); i++)
						lineSpacingsX[i] = (margin + (canvasWidth - margin * 2) * i / grid.getWidth());

					// calculate y spacings
					for (int i=0; i <= grid.getHeight(); i++)
						lineSpacingsY[i] = (margin + (canvasHeight - margin * 2) * i / grid.getHeight());
				}

				// draw cells
				int posX, posY, width, height;

				//Grid<E> grid = GridGUI.this.grid.copy();

				synchronized(grid) {
					for (int y=0; y < grid.getHeight(); y++) {
						for (int x=0; x < grid.getWidth(); x++) {
							if ((displayCondition == null && grid.get(x, y) != null) ||
								(displayCondition != null && displayCondition.apply(grid.get(x, y)))) {
								// if there are grid lines, we use 2 padding widths because paddings don't collapse
								posX = lineSpacingsX[x] + (showGridLines ? cellPadding : 0);
								posY = lineSpacingsY[y] + (showGridLines ? cellPadding : 0);
								width = lineSpacingsX[x+1] - posX - cellPadding;
								height = lineSpacingsY[y+1] - posY - cellPadding;

								// use the proper color (default or determined by a function)
								g.setColor(itemColorFunction == null ? itemColor : itemColorFunction.apply(grid.get(x, y)));

								// if there's no display strategy, just fill cells that aren't null
								if (displayStrategy == null) {
									g.fillRect(posX, posY, width, height);
								} else {
									displayStrategy.display(grid.get(x, y), (Graphics2D)g.create(posX, posY, width, height));
								}
							}
						}
					}
				}

				//System.out.println(System.currentTimeMillis());

				if (showGridLines) {
					g.setColor(gridLineColor);

					// draw vertical lines between cells
					for (int i=0; i <= grid.getWidth(); i++) {
						posX = lineSpacingsX[i];
						g.drawLine(posX, margin, posX, canvasHeight - margin);
					}

					// draw horizontal lines between cells
					for (int i=0; i <= grid.getHeight(); i++) {
						posY = lineSpacingsY[i];
						g.drawLine(margin, posY, canvasWidth - margin, posY);
					}
				}
			}
		}

		private void calculateSize() {
			if (grid == null) {
				setPreferredSize(new Dimension(603, 603));
				return;
			}

			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int screenWidth = (int)screenSize.getWidth();
			int screenHeight = (int)screenSize.getHeight();

			int paddingx = 	margin * 2 +
							(showGridLines ? (grid.getWidth() + 1) : (0)) +
							(showGridLines ? (grid.getWidth() * cellPadding * 2) :
											(grid.getWidth() * cellPadding - 1)) +
							1;
			int paddingy = 	margin * 2 +
							(showGridLines ? (grid.getWidth() + 1) : (0)) +
							(showGridLines ? (grid.getWidth() * cellPadding * 2) :
											(grid.getWidth() * cellPadding - 1)) +
							1;
			int remainingx = screenWidth - paddingx;
			int remainingy = screenHeight - paddingy;

			int cellSize = Math.min(remainingx / grid.getWidth(),
									remainingy / grid.getHeight());
			int windowWidth = cellSize * grid.getWidth() + paddingx;
			int windowHeight = cellSize * grid.getHeight() + paddingy;

			// System.out.println(grid.getWidth() + ", " + grid.getHeight());
			// System.out.println(screenWidth + ", " + screenHeight);
			// System.out.println(paddingx + ", " + paddingy);
			// System.out.println(remainingx + ", " + remainingy);
			// System.out.println(cellSize);
			// System.out.println(windowWidth + ", " + windowHeight);

			setPreferredSize(new Dimension(windowWidth, windowHeight));
		}
	}

	/*********************/
	//    interfaces     //
	/*********************/

	/**
	 * An interface used to set a "strategy" used when drawing cells in a grid
	 *
	 * @author 	Kyle Cutler
	 */
	public interface DisplayStrategy<E> {
		/**
		 * Draws an individual cell given the data contained in that cell
		 *
		 * @param 	item 	The object contained in the particular cell to be drawn
		 * @param 	g 		The Graphics in which to draw the cell
		 */
		public void display(E item, Graphics2D g);
	}

	/**
	 * An interface used to define the action to perform when a key is pressed
	 *
	 * @author 	Kyle Cutler
	 */
	public interface KeyAction {
		/**
		 * Performs an action based on the given key code.
		 * Key codes are as defined in the KeyEvent class.
		 */
		public void act(int keyCode);
	}

	public static void main(String[] args) {
		GridGUI<Object> gui = new GridGUI<>(new Grid<Object>(10, 10));

		gui.setDragAction((x, y, o) -> System.out.printf("Drag (%d, %d)\n", x, y));
		gui.setLeftDragAction((x, y, o) -> System.out.printf("Left Drag (%d, %d)\n", x, y));
		gui.setRightDragAction((x, y, o) -> System.out.printf("Right Drag (%d, %d)\n", x, y));
		gui.setClickAction((x, y, o) -> System.out.printf("Click (%d, %d)\n", x, y));
		gui.setLeftClickAction((x, y, o) -> System.out.printf("Left Click (%d, %d)\n", x, y));
		gui.setRightClickAction((x, y, o) -> System.out.printf("Right Click (%d, %d)\n", x, y));
		gui.setKeyAction((c) -> System.out.printf("%s Key\n", KeyEvent.getKeyText(c)));

		gui.show();
	}
}
