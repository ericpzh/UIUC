import junit.framework.TestCase;
/* You do not need to modify this file, however it may be useful
* to read how each test works.
* @author angrave
*
*/
public class EffectsTest extends TestCase {
	public void testProcess() {
		int[][] source = { { 10, 20, 30 }, { 40, 50, 60 } };
		int[][] sourceB = { { 99, 88, 77 }, { 66, 55, 44 } };

		String [] commands = new String[] { "half", "rotate", "flip", "mirror","redeye","funky" , "resize", "merge", "key" };
		
		for (int i =0; i < commands.length;i++) {
			int[][] result = Effects.process(commands[i], source, sourceB);
			assertNotNull(result);
			assertNotSame(result, source);
			assertNotSame(result, sourceB);
		}
	}
	public void testAuthorship() {
		boolean success = CheckInputOutput.checkAuthorship("Effects.java");
		if (!success)
			fail("Fix Effects.java authorship");
	}
	public void tearDown() {
		CheckInputOutput.resetInputOutput();
	}

	public void setUp() throws Exception {
		super.setUp();
		CheckInputOutput.setUp();
	}
}
