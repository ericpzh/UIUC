/**
 * Do not modify this class. In particular you do not need to add
 * any methods or make the instance variables public etc.
 * Instead, use the existing methods and constructors
 * @author angrave
 * 
 */
public class Robot {
	private int x, y; // must be private
	private boolean happy; // must be private

	// Hint use the existing methods and constructors
	public String toString() {
		String smiley = happy ? ";-)" : ":-(";
		return "(" + x + "," + y + ")" + smiley;
	}

	public Robot(int _x, int _y, boolean _happy) {
		x = _x;
		y = _y;
		happy = _happy;
	}

	public Robot(Robot other) {
		x = other.x;
		y = other.y;
		happy = other.happy;
	}

	public void flipMood() {
		happy = !happy;
	}

	public boolean isHappy() {
		return happy;
	}

	public boolean isFlying() {
		return y > 0;
	}

	public double getDistanceFromHome() {
		return Math.sqrt(x * x + y * y);
	}

	public boolean equals(Object other) {
		if (other == null || !(other instanceof Robot))
			return false;
		Robot r = (Robot) other;
		if(r == this) return true;
		return r.x == this.x && r.y == this.y && r.happy == this.happy;
	}
}
