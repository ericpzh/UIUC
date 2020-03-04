import junit.framework.TestCase;

/**
 * Test cases for the Factorial class. You do not need to modify this file.
 * However, it is worthwhile reading the test input and expected output for each
 * test case
 * 
 * @author angrave
 * 
 */
public class FactorialTest extends TestCase {

	public static void test1() {
		String input = "1\n";
		String requiredOutput = "Enter a number between 1 and 20 inclusive.\n"
				+ "1! = 1\n";
		CheckInputOutput.setInputCaptureOutput(input);
		Factorial.main(new String[0]);
		int line = CheckInputOutput
				.checkCompleteOutput(requiredOutput, "test1");
		if (line > 0)
			fail("Review incorrect output on line " + line);
	}

	public static void test10() {
		String input = "10\n";
		// Don't just copy the expected text below
		// Your program should _calculate_ the answer!

		String requiredOutput = "Enter a number between 1 and 20 inclusive.\n"
				+ "10! = 3628800\n";
		CheckInputOutput.setInputCaptureOutput(input);
		Factorial.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(requiredOutput,
				"test10");
		if (line > 0)
			fail("Review incorrect output on line " + line);
	}

	public static void test12() {
		String input = "12\n";
		// Don't just copy the expected text below
		// Your program should _calculate_ the answer!
		String requiredOutput = "Enter a number between 1 and 20 inclusive.\n"
				+ "12! = 479001600\n";
		CheckInputOutput.setInputCaptureOutput(input);
		Factorial.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(requiredOutput,
				"test12");
		if (line > 0)
			fail("Review incorrect output on line " + line);
	}

	public static void test17() {
		String input = "17\n";
		// Don't just copy the expected text below
		// Your program should _calculate_ the answer!
		String requiredOutput = "Enter a number between 1 and 20 inclusive.\n"
				+ "17! = 355687428096000\n";
		CheckInputOutput.setInputCaptureOutput(input);
		Factorial.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(requiredOutput,
				"test17");
		if (line > 0)
			fail("Review incorrect output on line " + line);
	}

	public static void testBadInput() {
		String input = "0\n-2\n21\n5\n";
		String requiredOutput = "Enter a number between 1 and 20 inclusive.\n"
				+ "Enter a number between 1 and 20 inclusive.\n"
				+ "Enter a number between 1 and 20 inclusive.\n"
				+ "Enter a number between 1 and 20 inclusive.\n" + "5! = 120\n";
		CheckInputOutput.setInputCaptureOutput(input);
		Factorial.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(requiredOutput,
				"testBadInput");
		if (line > 0)
			fail("Review incorrect output on line " + line);
	}

	public void testAuthorship() {
		boolean success = CheckInputOutput.checkAuthorship("Factorial.java");
		if (!success)
			fail("Fix @authorship");
	}

	public void tearDown() {
		CheckInputOutput.resetInputOutput();
	}

}
