/**
 * A simple (and slow) implementation of Conway's Game of Life.
 * 
 * This implementation concentrates on code and algorithm simplicity and readability, not performance.
 * @see http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life 
 * @author angrave
 *
 */
public class ConwayGameOfLifeDemo {
	boolean[][] board; // the state of the board to display (the output of the simulation step)
	boolean[][] previous; // the previous state of the board (used in the simulation step)
	private double tileW; // the width of each board tile in screen pixels (used in display and mouse interaction)
	private double tileH; // the height of each board tile in screen pixels (used in display and mouse interaction)

	/**
	 * The main entry point for this demo
	 * @param args unused
	 */
	public static void main(String[] args) {
		ConwayGameOfLifeDemo simulation = new ConwayGameOfLifeDemo(256);
		Zen.flipBuffer(); // turn on double buffering before we display anything
		// otherwise initial display is very slow as it paints every tile to the screen

		while (Zen.isRunning()) {
			simulation.mouseInteraction(); // turn a few pixels on near the mouse
			simulation.calculate(); // calculate the next time step
			simulation.display(); // display the current board
			simulation.swapPreviousAndBoardArrays(); // now the current board can be used as the previous board for the next time step
		}
	}
	
	/**
	 * Constructor. Creates an initial random simulation with size*size tiles.
	 * @param size the size of the simulation in each dimension.
	 */
	public ConwayGameOfLifeDemo(int size) {
		board = new boolean[size][size];
		previous = new boolean[size][size];

		// Initialize the board by setting up a fictitious previous time step:
		// Turn a fraction of the tiles on.
		// The boundary tiles are never on (thus 1... size-1 limits)
		// Otherwise,we'd need to special case their simulation logic or implement
		// wrap-around.
		for (int i = 1; i < size - 1; i++)
			for (int j = 1; j < size - 1; j++)
				previous[i][j] = Math.random() < 0.5;
	}

	/**
	 * Handle mouse interaction
	 */
	private void mouseInteraction() {
		if (tileW < 0 && tileH < 0)
			return; // EARLY RETURN - we don't yet know the size of each tile

		// Flick some paint on tiles that are near the mouse
		// need to convert from pixels back to tile indices:
		for (int flick = 5; flick > 0; flick--) {
			int tileX = (int) (Zen.getMouseX() / tileW + Math.random() - 0.5);
			int tileY = (int) (Zen.getMouseY() / tileH + Math.random() - 0.5);
			previous[tileX][tileY] = true;

		}
	}

	/**
	 * Display the state of the board
	 */
	private void display() {
		// set the display to black
		Zen.setColor(0, 0, 0);
		Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());
		Zen.setColor(255, 255, 255);
		// now go through and draw white squares for every tile that is true.
		int numTilesWide = board.length;
		int numTilesHigh = board[0].length;
		tileW = Zen.getZenWidth() / (double) numTilesWide;
		tileH = Zen.getZenHeight() / (double) numTilesHigh;
		for (int i = 0; i < numTilesWide; i++)
			for (int j = 0; j < numTilesHigh; j++) {
				if (board[i][j])
					Zen.fillRect((int) (tileW * i), (int) (tileH * j), 1 + (int) tileW, 1 + (int) tileH);
			}

		Zen.flipBuffer(); // show it to the user.
		Zen.sleep(20); // sleep for some milliseconds so animation is not too fast

	}

	/**
	 * The simulation heart of the algorithm is here.
	 * For each tile check the 8 nearest neighbors.
	 * A tile is turned on if it's off and it has exactly 3 neighbors.
	 * If a tile is already on it needs exactly 2 or 3 neighbors to be turned on.
	 * Otherwise the tile is off.
	 * Reminder: This implementation is inefficient in space and time. 
	 */
	private void calculate() {
		int numTilesWide = board.length;
		int numTilesHigh = board[0].length;
		for (int i = 1; i < numTilesWide - 1; i++)
			for (int j = 1; j < numTilesHigh - 1; j++) {
				int neighbors = countNeighbors(previous, i, j);
				board[i][j] = previous[i][j] ? (neighbors == 2 || neighbors == 3) : neighbors == 3;
			}

	}
	/**
	 * Swap the two references 'previous' and 'board'
	 * so that we can use the current state of the tiles
	 * in the next timestep.
	 * This is swap is simpler and much faster than copying all size*size booleans!
	 */
	private void swapPreviousAndBoardArrays() {
		boolean[][] t = this.board;
		this.board = this.previous;
		this.previous = t;
	}

	/**
	 * Counts the number of nearest neighbors for a particular tile (0 to 8).
	 * Limitation: Boundary tiles are not supported (will throw an IndexOutOfBoundException)
	 * @param arr the array to process
	 * @param i index 1 arr[i][j]
	 * @param j index 2
	 * @return number of neighbors that are true (on). 0 to 8 (inclusive)
	 */
	private static int countNeighbors(boolean[][] arr, int i, int j) {
		int count = 0;
		for (int dx = -1; dx <= 1; dx++)
			for (int dy = -1; dy <= 1; dy++)
				if (arr[i + dx][j + dy])
					count++;
		if (arr[i][j])
			count--; // We overcounted! - we don't want to count the center square
		return count;
	}
}
