import junit.framework.TestCase;
/**
 * Tests PixelEffects.
 * You do not need to modify this file, however it may be useful
 * to read how each test works.
 * @author angrave
 *
 */
public class PixelEffectsTest extends TestCase {
	/* Creates a new array of the reference test data.
	 *
	 */
	public int[][] getTestSourceArray() {
		return new int[][] { { 0, 1, 2, 3 }, { 10, 11, 12, 13 },
				{ 20, 21, 22, 23 } };
	}

	public void assertSourceCopiedAndUnchanged(int[][] expected,int[][] source,int [][]dest) {
		String ERR = "The source array cannot be modified!";
		
		assertEquals(ERR, expected.length,source.length);
		for (int i = 0; i < expected.length; i++) {
			assertNotNull(ERR, source[i]);
			assertEquals(ERR, expected[i].length,source[i].length);
			for(int x=0; dest!=null && x<dest.length;x++) {
				assertTrue("Source and target cannot share subarrays", source[i] != dest[x]);
			}	
			for (int j = 0; j < expected[i].length; j++)
				assertEquals(ERR, expected[i][j], source[i][j]);
		}
		
	}
	public void assertEntriesEqual(int[][] expected, int[][] actual) {
		if (expected == actual)
			return;
		if (expected == null) {
			assertTrue(null == actual);
		} else
			assertNotNull(actual);
		assertEquals("Outer array dimensions are incorrect", expected.length,
				actual.length);
		for (int i = 0; i < expected.length; i++) {
			if (expected[i] == actual[i])
				continue;
			if (expected[i] == null) {
				assertTrue("arr[" + i + "] should be null", null == actual[i]);
			} else
				assertNotNull("arr[" + i + "] should not be null");

			assertEquals("arr[" + i + "] incorrect length", expected[i].length,
					actual[i].length);
			for (int j = 0; j < expected[i].length; j++)
				assertEquals("arr[" + i + "][" + j + "] incorrect value",
						expected[i][j], actual[i][j]);
		}
	}

	public void testCopy() {
		int[][] source = getTestSourceArray();

		int[][] result = PixelEffects.copy(source);
		assertNotNull(result);
		assertEntriesEqual(source, result); // check the individual elements
		assertEntriesEqual(source,getTestSourceArray());
		assertTrue("A new array should be returned", source != result);
		for (int i = 0; i < source.length; i++)
			assertTrue(
					"source and copy must not share the same sub-arrays (these need to be copied too)",
					source[i] != result[i]);

	}

	public void testHalf() {
		int[][] source = getTestSourceArray(); // 3 x 4 -> 1 x 2
		int[][] expected = { { 0, 2 } };
		int[][] actual = PixelEffects.half(source);
		
		assertEntriesEqual(expected, actual);
		assertSourceCopiedAndUnchanged(source,getTestSourceArray(),actual);
	}

	public void testResizeToArray() {
		int[][] source = getTestSourceArray(); // 3 x 4 -> 2 x 8
		int[][] reference = new int[2][8];
		int[][] expected = { { 0, 0, 1, 1, 2, 2, 3, 3 },
				{ 10, 10, 11, 11, 12, 12, 13, 13 }, };
		int[][] actual = PixelEffects.resize(source, reference);
		assertEntriesEqual(expected, actual);
		assertSourceCopiedAndUnchanged(source,getTestSourceArray(),actual);
	}

	public void testResizeDoubleSize() {
		int[][] source = getTestSourceArray(); // 3 x 4 -> 6 x 8
		int w = 6, h = 8;
		int[][] expected = { { 0, 0, 1, 1, 2, 2, 3, 3 },
				{ 0, 0, 1, 1, 2, 2, 3, 3 }, { 10, 10, 11, 11, 12, 12, 13, 13 },
				{ 10, 10, 11, 11, 12, 12, 13, 13 },
				{ 20, 20, 21, 21, 22, 22, 23, 23 },
				{ 20, 20, 21, 21, 22, 22, 23, 23 } };
		int[][] actual = PixelEffects.resize(source, w, h);
		assertEntriesEqual(expected, actual);
		assertSourceCopiedAndUnchanged(source,getTestSourceArray(),actual);
	}

	public void testFlip() {
		int[][] source = getTestSourceArray();
		int[][] expected = { { 3, 2, 1, 0 }, { 13, 12, 11, 10 },
				{ 23, 22, 21, 20 } };
		int[][] actual = PixelEffects.flip(source);
		assertEntriesEqual(expected, actual);
		assertSourceCopiedAndUnchanged(source,getTestSourceArray(),actual);
	}

	public void testMirror() {
		int[][] source = getTestSourceArray();
		int[][] expected = { { 20, 21, 22, 23 }, { 10, 11, 12, 13 },
				{ 0, 1, 2, 3 } };
		int[][] actual = PixelEffects.mirror(source);
		assertEntriesEqual(expected, actual);
		assertSourceCopiedAndUnchanged(source,getTestSourceArray(),actual);
	}

	public void testRotateLeft() {
		int[][] source = getTestSourceArray();
		int[][] expected = { { 20, 10, 0 }, { 21, 11, 1 }, { 22, 12, 2 },
				{ 23, 13, 3 } };
		int[][] actual = PixelEffects.rotateLeft(source);
		assertEntriesEqual(expected, actual);
		assertSourceCopiedAndUnchanged(source,getTestSourceArray(),actual);
	}

	public void testMerge() {
		int[][] source =     { { 0, 0x44, 0x300, 0x660000 } };
		int[][] background = { { 2, 4,    0x600, 2 } };
		int[][] expected =   { { 1, 0x24, 0x400 , 0x330001 } };
		int[][] actual = PixelEffects.merge(source, background);
		assertEntriesEqual(expected, actual);
		assertSourceCopiedAndUnchanged(source,new int[][]  { { 0, 0x44, 0x300, 0x660000 } },actual);
		assertSourceCopiedAndUnchanged(background,new int[][] { { 2, 4,    0x600, 2 } },actual);
		
	}

	public void testChromaKey() {
		int[][] source = { { 0, 0x44, 0x5500, 0x660000 } };
		int[][] background = { { 2, 4, 6, 8 } };
		int[][] expected = { { 0, 0x44, 6, 0x660000 } };
		int[][] actual = PixelEffects.chromaKey(source, background);
		assertEntriesEqual(expected, actual);
		assertSourceCopiedAndUnchanged(source,new int[][] { { 0, 0x44, 0x5500, 0x660000 } },actual);
		assertSourceCopiedAndUnchanged(background,new int[][] { { 2, 4, 6, 8 } },actual);

	}
	public void testFunky() {
		int[][] source = getTestSourceArray();
		int[][] background = getTestSourceArray();
		int[][] actual = PixelEffects.funky(source, background);
		assertNotNull(actual);
		assertSourceCopiedAndUnchanged(source,getTestSourceArray(),actual);

		int[][] source2 = new int[][] { { 0, 1, 2, 3 }, { 10, 11, 12, 13 },
				{ 20, 21, 22, 23 },{30,31,32,33},{40,41,42,43} };
		int[][] background2 = new int[][] {{10,20},{30,40},{50,60}};
		int[][] actual2 = PixelEffects.funky(source2, background2);
		assertNotNull(actual2);
		
	}
	public void testAuthorship() {
		boolean success = CheckInputOutput.checkAuthorship("PixelEffects.java");
		if (!success)
			fail("Fix PixelEffects.java authorship");
	}
	public void tearDown() {
		CheckInputOutput.resetInputOutput();
	}

	public void setUp() throws Exception {
		super.setUp();
		CheckInputOutput.setUp();
	}
}
