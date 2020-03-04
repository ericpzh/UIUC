import junit.framework.TestCase;

/**
 * Test cases for Geocache. You do not need to modify this file.
 * 
 * @author angrave
 * 
 */
public class GeocacheTest extends TestCase {

	public void testGeocacheConstructor() {
		// There will be a compile error here until
		// you've created the Geocache constructor
		Geocache p = new Geocache(11.5, 21.5);
		assertEquals(11.5, p.getX());
		assertEquals(21.5, p.getY());
	}

	public void testGeocacheCopyConstructor() {
		Geocache other = new Geocache(3.3, 5.5);
		Geocache p = new Geocache(other);
		assertEquals(3.3, p.getX());
		assertEquals(5.5, p.getY());
	}

	public void testGetNumGeocachesCreated() {
		Geocache.resetCount();
		assertEquals(0, Geocache.getNumGeocachesCreated());
		Geocache other = new Geocache(3.3, 5.5);
		assertEquals(1, Geocache.getNumGeocachesCreated());
		new Geocache(other);
		assertEquals(2, Geocache.getNumGeocachesCreated());
		new Geocache(other);
		assertEquals(3, Geocache.getNumGeocachesCreated());
		Geocache.resetCount();
		assertEquals(0, Geocache.getNumGeocachesCreated());

	}

	public void testEqualsObject() {
		Geocache p = new Geocache(3.3, 5.5);
		Geocache p2 = new Geocache(3.3, 5.5);
		Geocache p3 = new Geocache(3.3, 3.3);
		Geocache p4 = new Geocache(5.5, 5.5);
		assertTrue(p.equals(p2));
		assertFalse(p.equals(p3));
		assertFalse(p.equals(p4));
		assertFalse(p.equals("Ho hum"));
	}

	public void testToString() {
		Geocache p = new Geocache(3.3, 5.5);
		assertEquals("(3.3,5.5)", p.toString());
	}

	public void testGetSetXGetSetY() {
		Geocache p = new Geocache(1.5, 2.5);
		Geocache p2 = new Geocache(99.5, 98.5);
		assertEquals(1.50, p.getX());
		assertEquals(2.50, p.getY());
		p.setX(11.5);
		p.setY(12.5);
		assertEquals(11.5, p.getX());
		assertEquals(12.5, p.getY());
		p2.setX(50.5);
		assertEquals(11.5, p.getX());
		assertEquals(50.5, p2.getX());
	}

	public void testPublicPrivateModifiers() {
		AutomaticGrader.checkPublicPrivateModifiers(Geocache.class);
	}
	public void testSetXLimits() {
		Geocache p = new Geocache(1.5, 2.5);
		assertEquals(1.50, p.getX());
		p.setX(11.5);
		assertEquals(11.5, p.getX());
		p.setX(999.9);
		assertEquals(999.9, p.getX());
		p.setX(1000.0);
		assertEquals(999.9, p.getX());
		p.setX(-1000.0);
		assertEquals(999.9, p.getX());
		p.setX(-999.9);
		assertEquals(-999.9, p.getX());
	}
	
	public void testId() {
		Geocache.resetCount();
		
		Geocache p0 = new Geocache(1.5, 2.5);
		Geocache p1 = new Geocache(199.5, 198.5);
		Geocache p2 = new Geocache(199.5, 298.5);
		Geocache p3 = new Geocache(p1);
		assertEquals(0, p0.getId());
		assertEquals(1, p1.getId());
		assertEquals(2, p2.getId());
		assertEquals(3, p3.getId());		
		Geocache.resetCount();
		Geocache pAlso0 = new Geocache(1.5, 2.5);
		assertEquals(0, pAlso0.getId());
		
		
	}
}
