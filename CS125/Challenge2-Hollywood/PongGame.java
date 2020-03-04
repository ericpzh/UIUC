/**
 * This class provides some diversion - a game of Pong. Feel free to modify it
 * BUT remember that all files must compile without error - otherwise you score
 * 1/100 for the whole MP.
 * 
 * @author angrave
 * 
 */
public class PongGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int APPWIDTH = 500, APPHEIGHT = 400, DELAY = 5;

		int ballWidth = 9, ballHeight = 9;

		int ballX = 200, ballY = 100; // ball starting position

		int dx = 4, dy = 1; // ball velocity

		int bat1X = 100, bat2X = APPWIDTH - bat1X, bat1Y = 20, bat2Y = 30;

		int batWidth = 4, batHeight = 100;

		Zen.create(APPWIDTH, APPHEIGHT, "");

		while (Zen.isRunning()) {
			bat1Y = Zen.getMouseY();
			bat2Y = Zen.getMouseX();
			// Don't draw anything here -it will get overwritten with black:
			Zen.setColor(0, 0, 0); //Black
			Zen.fillRect(0, 0, APPWIDTH, APPHEIGHT); // Wipe the display area
			
			Zen.setColor(255, 255, 255); // white
			// Draw the ball and the two bats
			Zen.fillOval(ballX - ballWidth / 2, ballY - ballHeight / 2,
					ballWidth, ballHeight);
			Zen.fillRect(bat1X - batWidth / 2, bat1Y - batHeight / 2, batWidth,
					batHeight);
			Zen.fillRect(bat2X - batWidth / 2, bat2Y - batHeight / 2, batWidth,
					batHeight);

			// To remove flicker we use double buffering:
			// There's actually two canvas / two screens:
			// The one that is displayed to the user
			// and another behind-the-scenes one that we currently drawing too.
			// When you're finished drawing all of the objects
			// Call flipBuffer and they will swap roles.
			// In practice this also means that you need to clear
			// the contents of the old buffer after calling flipBuffer (see fillRect above)
			Zen.flipBuffer();

			Zen.sleep(DELAY);

			/**
			 * The velocity is negated if the ball hits either of the two
			 * paddles. The velocity components are negated if the ball is at
			 * the edge of the playing area. The ball's position is updated
			 * using the updated velocity state. First we check to see if the X
			 * and Y positions are sufficiently close.
			 */
			boolean hit1 = Math.abs(ballX - bat1X) < (batWidth + ballWidth) / 2
					&& Math.abs(ballY - bat1Y) < (batHeight + ballHeight) / 2;

			boolean hit2 = Math.abs(ballX - bat2X) < (batWidth + ballWidth) / 2
					&& Math.abs(ballY - bat2Y) < (batHeight + ballHeight) / 2;

			if (ballX > APPWIDTH || ballX < 0 || hit1 || hit2)
				dx = -dx;
			if (ballY > APPHEIGHT || ballY < 0 || hit1 || hit2)
				dy = -dy;

			ballX += dx;
			ballY += dy;
		} // while loop
	} // main method
} // class
