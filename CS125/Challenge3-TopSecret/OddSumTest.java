import junit.framework.TestCase;

public class OddSumTest extends TestCase {

	public void testOddSum5() {
		CheckInputOutput.setInputCaptureOutput("5\n");
		OddSum.main(null);
		String expected9 = "Max?\n1 + 3 + 5 = 9\n" + "9 = 5 + 3 + 1\n";
		int line = CheckInputOutput.checkCompleteOutput(expected9);
		if (line > 0)
			fail("Fix line " + line);
	}

	public void testOddSum8() {

		CheckInputOutput.setInputCaptureOutput("8\n");
		OddSum.main(null);
		String expected16 = "Max?\n1 + 3 + 5 + 7 = 16\n"
				+ "16 = 7 + 5 + 3 + 1\n";
		int line = CheckInputOutput.checkCompleteOutput(expected16);
		if (line > 0)
			fail("Fix line " + line);
	}

	public void testOddSum9() {

		CheckInputOutput.setInputCaptureOutput("9\n");
		OddSum.main(null);
		String expected25 = "Max?\n1 + 3 + 5 + 7 + 9 = 25\n"
				+ "25 = 9 + 7 + 5 + 3 + 1\n";
		int line = CheckInputOutput.checkCompleteOutput(expected25);
		if (line > 0)
			fail("Fix line " + line);
	}

	public void testAuthorship() {
		boolean success = CheckInputOutput.checkAuthorship("OddSum.java");
		if (!success)
			fail("Fix authorship");
	}

	public void tearDown() {
		CheckInputOutput.resetInputOutput();
	}

	public void setUp() throws Exception {
		super.setUp();
		CheckInputOutput.setUp();
	}

}
