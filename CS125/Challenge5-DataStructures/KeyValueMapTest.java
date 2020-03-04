import java.awt.Color;

import junit.framework.TestCase;

/**
 * Tests for the KeyValueMap class.
 * You do not need to modify this file.
 * @author angrave
 *
 */
public class KeyValueMapTest extends TestCase {

	public void testAddAndFind() {
		KeyValueMap map = new KeyValueMap();
		map.add("RED", Color.RED);
		map.add("BLUE", Color.BLUE);
		
		
		assertEquals(Color.RED, map.find("RED"));
		assertNull( map.find("BLACK"));

		// check keys are compared with .equals not ==
		assertEquals(Color.RED, map.find("red".toUpperCase()));
		assertEquals(Color.BLUE, map.find("blue".toUpperCase()));
		
		map.add("BLACK", Color.BLACK);
		
		assertEquals(Color.RED, map.find("RED"));
		assertEquals(Color.RED, map.find("red".toUpperCase()));

		assertEquals(Color.BLUE, map.find("blue".toUpperCase()));
		assertEquals(Color.BLACK, map.find("black".toUpperCase()));
}


	public void testExists() {
		KeyValueMap map = new KeyValueMap();
		map.add("RED", Color.RED);
		map.add("BLUE", Color.BLUE);
		map.add("BLACK", Color.BLACK);
		
		assertTrue(map.exists("RED"));
		assertTrue(map.exists("red".toUpperCase()));
		assertFalse(map.exists("GREEN"));
		
		map.add("GREEN", Color.GREEN);
		assertTrue(map.exists("GREEN"));
}

	public void testRemove() {
		KeyValueMap map = new KeyValueMap();
		map.add("RED", Color.RED);
		map.add("BLUE", Color.BLUE);
		map.add("BLACK", Color.BLACK);
		assertTrue(map.exists("RED"));
		assertTrue(map.exists("BLUE"));
		assertTrue(map.exists("BLACK"));
		map.remove("red".toUpperCase());
		assertFalse(map.exists("RED"));
		map.add("RED", Color.MAGENTA);
		assertEquals(Color.MAGENTA,map.find("RED"));
		map.remove("BLACK");
		assertNull(map.find("BLACK"));
	}
	public void testPublicPrivateModifiers() {
		AutomaticGrader.checkPublicPrivateModifiers(KeyValueMap.class);
	}
}
