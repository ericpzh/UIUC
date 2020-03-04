import junit.framework.TestCase;

/**
 * Tests for static methods in StaticMethodsAreEasy.
 * You do not need to modify this file.
 * @author angrave
 *
 */
public class StaticMethodsAreEasyTest extends TestCase {

	public void testCreatePositions() {
		// There will be a compile error here until 
		// you create the createGeocaches method
		Geocache[] pts = StaticMethodsAreEasy.createGeocaches(-1);
		assertEquals(0, pts.length);
		
		pts = StaticMethodsAreEasy.createGeocaches(10);
		assertEquals(10, pts.length);
		for (int i = 0; i < 10; i++)
			assertNotNull(pts[i]);
		assertFalse(pts[0] == pts[1]);
	}

	public void testEnsureMinimumXValue() {
		Geocache[] pts = new Geocache[] { new Geocache(1., 2.), new Geocache(3., 4.),
				new Geocache(50., 60.), new Geocache(7., 8.) };
		double value = 25.0;
		StaticMethodsAreEasy.ensureMinimumXValue(pts, value);
		assertEquals(value, pts[0].getX());
		assertEquals(value, pts[1].getX());
		assertEquals(50.0, pts[2].getX());
		assertEquals(value, pts[3].getX());
	}

	public void testCountEqual() {
		Geocache[] pts = new Geocache[] { new Geocache(1, 2), new Geocache(3, 4),
				new Geocache(50, 60), new Geocache(1, 2) };
		Geocache origin = new Geocache(0,0);
		Geocache p12 = new Geocache(1,2);
		assertTrue(p12.equals( pts[0]));
		assertFalse(p12.equals(origin));
		
		assertEquals(0, StaticMethodsAreEasy.countEqual(pts, origin));
		assertEquals(2, StaticMethodsAreEasy.countEqual(pts, p12));
	}

	public void testPublicPrivateModifiers() {
		AutomaticGrader.checkPublicPrivateModifiers(StaticMethodsAreEasy.class);
	}
	public void setUp() throws Exception {
		super.setUp();
		CheckInputOutput.setUp();
	}
}
