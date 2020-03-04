import junit.framework.TestCase;
/**
 * You do not need to modify these tests.
 * @author angrave
 */

public class RobotTest extends TestCase {

	public void testToString() {
		Robot r1 = new Robot(11, 12, true);
		Robot r2 = new Robot(5, 6, false);
		assertEquals("(11,12);-)",r1.toString());
		assertEquals("(5,6):-(",r2.toString());
	}

	public void testRobotCopyConstructor() {
		Robot r1 = new Robot(11, 12, true);
		Robot r2 = new Robot(r1);
		assertEquals(r1.toString(), r2.toString());
		assertTrue(r1.equals(r2));
	}

	public void testFlipMood() {
		Robot r1 = new Robot(11, 12, true);
		Robot r2 = new Robot(11, 12, false);
		assertFalse(r1.toString().equals(r2.toString()));
		r1.flipMood();
		assertEquals(r1.toString(), r2.toString());
		r1.flipMood();
		assertFalse(r1.toString().equals(r2.toString()));
	}

	public void testIsHappy() {
		Robot r1 = new Robot(11, 12, true);
		Robot r2 = new Robot(11, 12, false);
		assertTrue(r1.isHappy());
		assertFalse(r2.isHappy());
	}

	public void testIsFlying() {
		Robot r1 = new Robot(11, 12, true);
		Robot r2 = new Robot(11, -12, true);
		assertTrue(r1.isFlying());
		assertFalse(r2.isFlying());
	}

	public void testGetDistanceFromHome() {
		Robot r1 = new Robot(-5, 0, true);
		Robot r2 = new Robot(0, -4, true);
		Robot r3 = new Robot(3, 4, true);
		assertTrue(Math.abs(r1.getDistanceFromHome() - 5) < 0.0001);
		assertTrue(Math.abs(r2.getDistanceFromHome() - 4) < 0.0001);
		assertTrue(Math.abs(r3.getDistanceFromHome() - 5) < 0.0001);

	}

	public void testEqualsObject() {
		Robot r1 = new Robot(-5, -2, true);
		Robot r2 = new Robot(-5, -2, true);
		Robot r3 = new Robot(-5, 2, true);
		Robot r4 = new Robot(-5, -2, false);
		assertTrue(r1.equals(r2));
		assertFalse(r1.equals(r3));
		assertFalse(r1.equals(r4));
	}

}
