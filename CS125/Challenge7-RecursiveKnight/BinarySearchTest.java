import junit.framework.TestCase;

/**
 * You do not need to modify these tests.
 * @author angrave
 */
public class BinarySearchTest extends TestCase {

	private static final int[] DATA = new int[] { 1, 3, 5, 7, 9, 11 };
	
	public void testSearchWrapper() {
		for (int key = 0; key < 12; key++) {
			if (key % 2 == 1) { // Odd numbers are present
				assertTrue("Expected to find " + key, BinarySearch.search(DATA,
						key));
			} else {
				// Even numbers are not present
				assertFalse("Key " + key + " should not be present",
						BinarySearch.search(DATA, key));
			}
		}
	}
	
	public void testSearch() {
		for (int key = 0; key < 12; key++) {
			if (key % 2 == 1) // Odd numbers are present
				assertTrue(BinarySearch.search(DATA, key, 0, DATA.length - 1));
			else
				// Even numbers are not present so search should return false
				assertFalse(BinarySearch.search(DATA, key, 0, DATA.length - 1));
		}
	}
	
	public void testPartialSearch() {
		// We should not find the values 1 or 11 if we are only searching sub-array 3,5,7,9
		assertFalse(BinarySearch.search(DATA, 1, 1, DATA.length - 2));
		assertFalse(BinarySearch.search(DATA, 11, 1, DATA.length - 2));
		// We should 3 and 9 though -
		assertTrue(BinarySearch.search(DATA, 3, 1, DATA.length - 2));
		assertTrue(BinarySearch.search(DATA, 9, 1, DATA.length - 2));
		// Reduce our search to {5,7}; now we should not find 3 and 9 -
		assertFalse(BinarySearch.search(DATA, 3, 2, DATA.length - 2));
		assertFalse(BinarySearch.search(DATA, 9, 1, DATA.length - 3));
	}
	
}
