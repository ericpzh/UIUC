import junit.framework.TestCase;

/**
 * Tests for GeocacheList. You do not need to modify this file.
 * 
 * @author angrave
 * 
 */
public class GeocacheListTest extends TestCase {

	public void testAddGetGeocacheGetSize() {
		GeocacheList list = new GeocacheList();
		assertEquals(0, list.getSize());
		// There will be a compile error here until
		// you've created the Geocache constructor
		Geocache p = new Geocache(1, 2);
		list.add(p);
		assertEquals(1, list.getSize());
		assertTrue(list.getGeocache(0) == p);
		Geocache p2 = new Geocache(3, 4);
		list.add(p2);
		assertEquals(2, list.getSize());
		assertTrue(list.getGeocache(0) == p);
		assertTrue(list.getGeocache(1) == p2);
	}

	public void testShallowCopy() {
		GeocacheList list = new GeocacheList();
		Geocache p1 = new Geocache(10, 20);
		Geocache p2 = new Geocache(30, 40);
		list.add(p1);
		list.add(p2);
		GeocacheList acopy = new GeocacheList(list, false);
		assertEquals(2, list.getSize());
		assertTrue(list.getGeocache(0) == p1);
		assertTrue(list.getGeocache(1) == p2);
		assertEquals(2, acopy.getSize());
		assertTrue(acopy.getGeocache(0) == p1);
		assertTrue(acopy.getGeocache(1) == p2);
		// Following operations have no effect on the other list:
		list.removeFromTop();
		assertEquals(1, list.getSize());
		assertEquals(2, acopy.getSize());
		list.add(new Geocache(50, 60));
		assertTrue(acopy.getGeocache(1) == p2);
	}

	public void testDeepCopy() {
		GeocacheList list = new GeocacheList();
		Geocache p1 = new Geocache(10, 20);
		Geocache p2 = new Geocache(30, 40);
		list.add(p1);
		list.add(p2);
		GeocacheList acopy = new GeocacheList(list, true);
		assertEquals(2, list.getSize());
		assertTrue(list.getGeocache(0) == p1);
		assertTrue(list.getGeocache(1) == p2);
		assertEquals(2, acopy.getSize());
		assertTrue(acopy.getGeocache(0) != p1);
		assertTrue(acopy.getGeocache(1) != p2);
		// The lists do not share geocache objects:
		assertTrue(acopy.getGeocache(0).getX() == 10);
		assertTrue(acopy.getGeocache(0).getY() == 20);
		assertTrue(acopy.getGeocache(1).getX() == 30);
		assertTrue(acopy.getGeocache(1).getY() == 40);
		// Following operations have no effect on the other list:
		list.removeFromTop();
		assertEquals(1, list.getSize());
		assertEquals(2, acopy.getSize());
		list.add(new Geocache(50, 60));
		assertTrue(acopy.getGeocache(1).getX() == 30);
	}

	public void testRemoveFromTop() {
		GeocacheList list = new GeocacheList();
		Geocache p1 = new Geocache(10, 20);
		Geocache p2 = new Geocache(30, 40);
		Geocache p3 = new Geocache(50, 60);
		list.add(p1);
		list.add(p2);
		assertEquals(2, list.getSize());
		assertTrue(list.getGeocache(1) == p2);
		list.removeFromTop();
		assertEquals(1, list.getSize());
		list.add(p3);
		assertTrue(list.getGeocache(1) == p3);
		list.removeFromTop();
		list.removeFromTop();
		assertEquals(0, list.getSize());
	}

	public void testToString() {
		GeocacheList list = new GeocacheList();
		Geocache p1 = new Geocache(1, 2);
		Geocache p2 = new Geocache(3, 4);
		Geocache p3 = new Geocache(5, 6);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		String expected = "GeocacheList:" + p1 + "," + p2 + "," + p3;
		assertEquals(expected, list.toString());
	}

	public void testPublicPrivateModifiers() {
		AutomaticGrader.checkPublicPrivateModifiers(GeocacheList.class);
	}
	public void setUp() throws Exception {
		super.setUp();
		CheckInputOutput.setUp();
	}
}
