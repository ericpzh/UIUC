import junit.framework.TestCase;

/**
 * Test cases for the Winner class. You do not need to modify this file.
 * It is worthwhile reading the test input and expected output for each test case
 * @author angrave
 *
 */
public class WinnerTest extends TestCase {
	public void testA() {
		String input = "3\n2\n1\n";
		String expectedOutput = "Enter three unique integer scores.\n1st Place:a\n";
		
		CheckInputOutput.setInputCaptureOutput(input);
		Winner.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(expectedOutput,"testA");
		if (line > 0)
			fail("Incorrect output on line " + line);
	}
	public void testB() {
		String input = "2\n3\n1\n";
		String expectedOutput = "Enter three unique integer scores.\n1st Place:b\n";
		
		CheckInputOutput.setInputCaptureOutput(input);
		Winner.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(expectedOutput,"testB");
		if (line > 0)
			fail("Incorrect output on line " + line);
	}
	public void testC() {
		String input = "2\n3\n4\n";
		String expectedOutput = "Enter three unique integer scores.\n1st Place:c\n";
		
		CheckInputOutput.setInputCaptureOutput(input);
		Winner.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(expectedOutput,"testC");
		if (line > 0)
			fail("Incorrect output on line " + line);
	}
	
	public void testAuthorship() {
		boolean success = CheckInputOutput.checkAuthorship("Winner.java");
		if (!success)
			fail("Fix @authorship");
	}
	public void tearDown() {
		CheckInputOutput.resetInputOutput();
	}
}
