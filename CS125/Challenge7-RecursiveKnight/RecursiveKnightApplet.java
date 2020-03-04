import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.JApplet;

/**
 * A chess-like puzzle. You do not need to modify this file.
 * 
 * @author angrave
 * 
 */
@SuppressWarnings("serial")
public class RecursiveKnightApplet extends JApplet {
	public static final int GRID = 5;
	public static final int STATUS = 16;
	private boolean[][] blocked = new boolean[GRID][GRID];
	private int[][] steps;

	private int x, y;;

	public void init() {
		blocked[0][0] = true;
		// Block steps along the main diagonal of the board
		for (int i = 0; i < GRID; i++)
			blocked[i][GRID - i - 1] = true;

		enableEvents(AWTEvent.MOUSE_EVENT_MASK);
	}

	public void paint(Graphics g) {
		// Reset the steps grid to all zeros
		steps = new int[GRID][GRID];
		RecursiveKnight.explore(blocked, x, y, steps, 0);

		int scaleX = getWidth() / GRID, scaleY = (getHeight() - STATUS)
				/ (GRID);

		g.clearRect(0, 0, getWidth(), getHeight());
		int remain = 0;
		for (int i = 0; i < GRID; i++)
			for (int j = 0; j < GRID; j++) {
				// draw blocks
				Color c = (i + j) % 2 == 0 ? Color.GRAY : Color.DARK_GRAY;

				if (blocked[i][j])
					c = Color.BLACK;
				else
					remain++;
				if (steps[i][j] == 1)
					c = Color.GREEN;
				else if (steps[i][j] == 2)
					c = new Color(0, 127, 0);
				g.setColor(c);
				g.fill3DRect(i * scaleX, j * scaleY, scaleX, scaleY, true);
				// draw block numbers
				g.setColor(Color.WHITE);
				if (steps[i][j] > 0) {
					String data = String.valueOf(steps[i][j]);
					g
							.drawChars(data.toCharArray(), 0, data.length(), i
									* scaleX + scaleX / 4, j * scaleY + scaleY
									/ 4 + 10);
				}
			}

		g.setColor(Color.BLUE);
		g.fillOval(x * scaleX + scaleX / 4, y * scaleY + scaleY / 4,
				scaleX / 2, scaleY / 2);

		String txt = remain + " remain";
		g.drawChars(txt.toCharArray(), 0, txt.length(), 0, getHeight() - 2);

	}

	public void processMouseEvent(MouseEvent evt) {

		int newx = (int) (GRID * evt.getX() / (double) getWidth());
		int newy = (int) (GRID * evt.getY() / (double) (getHeight() - STATUS));
		boolean clicked = evt.getButton() > 0
				&& evt.getID() == MouseEvent.MOUSE_PRESSED;
		if (clicked && steps[newx][newy] == 1) {

			steps[x][y] = 0;
			x = newx;
			y = newy;
			blocked[x][y] = true;

		}
		repaint();
	}

	public void update(Graphics g) {
		paint(g);
	}
}
