import java.util.Arrays;

import junit.framework.TestCase;
/* You do not need to modify this file, however it may be useful
* to read how each test works.
* @author angrave
*
*/
public class PlayListUtilTest extends TestCase {

	public void testListAll() {
		int i = (int)(10000*Math.random());

		String[] testdata = new String[] { "AA"+i, "BB", "CC" };
		String expected = "1. AA"+i+"\n2. BB\n3. CC\n";
		CheckInputOutput.setInputCaptureOutput("");
		PlayListUtil.list(testdata, -1); // -1 = all items
		int line = CheckInputOutput.checkCompleteOutput(expected);
		if (line > 0)
			fail("Review incorrect output on line " + line);
	}

	public void testListWithMax() {
		int i = (int)(10000*Math.random());

		String[] testdata = new String[] { "AA"+i, "BB", "CC" };
		String expected = "1. AA"+i+"\n2. BB\n";
		CheckInputOutput.setInputCaptureOutput("");
		PlayListUtil.list(testdata, 2); // only two items
		int line = CheckInputOutput.checkCompleteOutput(expected);
		if (line > 0)
			fail("Review incorrect output on line " + line);
	}

	public void testUtilAppend() {
		int i = (int)(10000*Math.random());
		String[] testdata = new String[] { "CC"+i, "DD", "EE" };
		String[] expected = new String[] { "CC"+i, "DD", "EE", "XX" };
		
		String[] result = PlayListUtil.add(testdata, "XX", false);
		checkArray(expected, result);
	}

	public void testUtilPrepend() {
		int i = (int)(10000*Math.random());

		String[] testdata = new String[] { "DD"+i, "EE", "FF" };
		String[] expected = new String[] { "AA", "DD"+i, "EE", "FF" };
		
		String[] result = PlayListUtil.add(testdata, "AA", true);
		checkArray(expected, result);
	}

	public void testUtilDiscard() {
		int i = (int)(10000*Math.random());

		String[] testdata = new String[] { "CC"+i, "dd", "EE", "FF" };
		String[] expected = new String[] { "CC"+i, "EE", "FF" };
		
		String[] result = PlayListUtil.discard(testdata, 1);
		checkArray(expected, result);
	}

	public void checkArray(String[] expected, String[] result) {
		assertNotNull(result);
		assertNotNull(expected);
		String testName = CheckInputOutput.getTestName();
		if (result == null && expected == null)
			return;
		if (result == null)
			fail(testName + ":Array is null. Expected "
					+ Arrays.toString(expected));

		boolean ok = result.length == expected.length;
		if (!ok)
			System.out.println(testName + ":Expected length=" + expected.length
					+ " actual=" + result.length);

		for (int i = 0; ok && i < expected.length; i++)
			ok = expected[i].equals(result[i]);

		if (!ok) {
			System.out.println(testName + ":Expected "
					+ Arrays.toString(expected));
			System.out.println(testName + ":Actual " + Arrays.toString(result));
			fail(testName + ":Result array contents incorrect");
		}
	}

	public void tearDown() {
		CheckInputOutput.resetInputOutput();
	}

	public void setUp() throws Exception {
		super.setUp();
		CheckInputOutput.setUp();
	}
	
	public void testAuthorship() {
		boolean success = CheckInputOutput.checkAuthorship("PlayListUtil.java");
		if (!success)
			fail("Fix authorship");
	}
}
