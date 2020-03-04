import java.awt.Color;

import javax.swing.JOptionPane;

/**
 * This is an Easter Egg ()
 * Challenge: Can you clear the board of pieces?
 * Hints: Stuck in a corner? Try moving 2 steps.
 * 
 * This game uses the Queue class (that you need to complete)
 * to store a queue of moves
 * @author angrave
 * 
 */
public class ExampleQueuePuzzle {
	boolean[][] board;
	private int size;
	private int remain; // number of pieces to be flipped

	int x, y, dx, dy; // position and current direction

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExampleQueuePuzzle game = new ExampleQueuePuzzle();
		game.run();
	}

	public void run() {
		newBoard();
		drawBoard();
		while (true) {
			String input = JOptionPane.showInputDialog("Move? (e.g. 1,2,3,4 or q to quit)", "");
			if (input == null || input.indexOf("q") == 0)
				return;
			Queue moves = parseInput(input);
			animate(moves);
		}
	}

	private void animate(Queue moves) {

		while (!moves.isEmpty()) {
			int steps = (int) moves.remove();
			while (steps > 0) {
				x = bound(0, x + dx, size - 1);
				y = bound(0, y + dy, size - 1);
				board[x][y] = !board[x][y];
				steps--;
				if (steps == 0) {
					if (board[x][y])
						turnRight();
					else
						turnLeft();
				}
				drawBoard();
				Zen.sleep(200);
			}
		}

	}

	private void turnRight() {
		int temp = dx;
		dx = -dy;
		dy = temp;
	}

	private void turnLeft() {
		int temp = dx;
		dx = dy;
		dy = -temp;
	}

	private static int bound(int min, int v, int max) {
		if (v < min)
			return min;
		if (v > max)
			return max;
		return v;
	}

	private static Queue parseInput(String input) {
		String[] moves = input.split(",");
		Queue result = new Queue();
		try {
			for (int i = 0; i < moves.length; i++)
				result.add(Integer.valueOf(moves[i]));
		} catch (NumberFormatException e) {
			result.add(1);
		}
		return result;
	}

	private void newBoard() {
		size = 6;
		board = new boolean[size][size];
		for (int i = 0; i < size; i++)
			board[i][i] = (i % 2) == 0;
		;
		x = 0;
		y = 0;
		dx = 1;
		dy = 0;
	}

	private void drawBoard() {
		int tile = Zen.getZenHeight() / size;
		remain = 0;
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				Zen.setColor(board[i][j] ? Color.ORANGE : Color.white);
				if (board[i][j])
					remain++;
				Zen.fillRect(i * tile, j * tile, tile, tile);
			}
		Zen.setColor(Color.BLUE);
		for (int i = 0; i <= size; i++)
			Zen.drawLine(i * tile, 0, i * tile, size * tile);
		for (int i = 0; i <= size; i++)
			Zen.drawLine(0, i * tile, size * tile, i * tile);

		int xx = x * tile, yy = y * tile;
		Zen.fillOval(xx + tile / 4, yy + tile / 4, tile / 2, tile / 2);
		Zen.fillOval(xx + tile / 2 + dx * (tile / 4) - tile / 8, yy + tile / 2 + dy * (tile / 4) - tile / 8, tile / 4,
				tile / 4);

		Zen.setColor(Color.WHITE);
		Zen.setFont("Times-24");
		if (remain > 0)
			Zen.drawText(" Remain:" + remain, size * tile, 24);
		else
			Zen.drawText(" Congratulations", size * tile, 24);
		Zen.flipBuffer();
	}
}
