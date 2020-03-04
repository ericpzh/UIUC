import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.JApplet;
/** 
 * An amazing game. You do not need to modify this file.
 * @author angrave
 *
 */
@SuppressWarnings("serial")
public class MazeApplet extends JApplet {
	public static final int SIZE = 15;

	private static final int CHANGE_MAZE = 0;

	private MazeRunner human, hunter1, hunter2;

	private boolean maze[][];

	private int mouseX, mouseY;

	synchronized void movePlayers(int count) {
		if (hunter1.caught(human) || hunter2.caught(human))
			createMaze();

		human.chase(maze, mouseX, mouseY);
		if (count % 4 == 0) {
			maze[hunter2.getX()][hunter2.getY()] = true;
			hunter1.chase(maze, human.getX(), human.getY());
			maze[hunter2.getX()][hunter2.getY()] = false;

			maze[hunter1.getX()][hunter1.getY()] = true;
			hunter2.chase(maze, human.getX(), human.getY());
			maze[hunter1.getX()][hunter1.getY()] = false;
		}
		
		int x = 1 + 2*(int)(Math.random() * (SIZE-2) / 2);
		if(CHANGE_MAZE > 0 && count % CHANGE_MAZE ==0 && x != human.getX()) {
			int y1 = 2 + 2*(int)(Math.random() * (SIZE-4) / 2);
			int y2 = 2 + 2*(int)(Math.random() * (SIZE-4) / 2);
			boolean t= maze[x][y1];
			maze[x][y1] = maze[x][y2]; maze[x][y2] = t;
		}
		
		repaint();
	}

	public void init() {
		createMaze();
		createRunners();
		
		enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
		setSize(SIZE * 20, SIZE * 20);
		new Thread() {
			public void run() {
				int count = 1;
				while (true) {
					try {
						Thread.sleep(50);
					} catch (Exception e) {
					}
					movePlayers(count++);
				}
			}
		}.start();
	}

	private void createRunners() {
		human = new MazeRunner(1, 1);
		hunter1 = new MazeRunner(SIZE - 2, SIZE - 2);
		hunter2 = new MazeRunner(SIZE / 2, SIZE / 2);
	}

	private void createMaze() {
		maze = new boolean[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			maze[i][0] = true;
			maze[i][SIZE - 1] = true;
			maze[0][i] = true;
			maze[SIZE - 1][i] = true;
		}
		int[][] NSEW = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		for (int x = 2; x < SIZE - 2; x += 2)
			for (int y = 2; y < SIZE - 2; y += 2) {
				int[] dir = NSEW[ (int) (Math.random() * 4)];
				maze[x][y] = true;
				maze[x + dir[0] ][y + dir[1]] = true;
			}
	}

	public synchronized void paint(Graphics g) {
		int w = getWidth() / SIZE;
		int h = getHeight() / SIZE;

		g.setColor(Color.DARK_GRAY);
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++) 
				g.fill3DRect(i * w, j * h, w, h, maze[i][j]);


		g.setColor(Color.BLUE);
		g.fillArc(human.getX() * w, human.getY() * h, w, h,30,300);
		
		g.drawRect(mouseX * w, mouseY * h, w, h);
		g.setColor(Color.RED);
		g.fillOval(hunter1.getX() * w, hunter1.getY() * h, w, h);
		g.fillOval(hunter2.getX() * w, hunter2.getY() * h, w, h);
	}

	public void processMouseMotionEvent(MouseEvent evt) {
		mouseX = (int) (SIZE * evt.getX() / (double) getWidth());
		mouseY = (int) (SIZE * evt.getY() / (double) getHeight());
	}
	public void update(Graphics g) {
		paint(g);
	}
}
