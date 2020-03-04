import junit.framework.TestCase;
/** CipherBreaker tests - you do not need to modify this file.
 * @author angrave
 */
public class CipherBreakerTest extends TestCase {

	public void tearDown() {
		CheckInputOutput.resetInputOutput();
	}

	public void testAuthorship() {
		boolean success = CheckInputOutput
				.checkAuthorship("CipherBreaker.java");
		if (!success)
			fail("Fix authorship");
	}

	public void testUpperCase() {
		CheckInputOutput.setInputCaptureOutput("ABCX");
		CipherBreaker.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput("Text?\n" + "ABCX\n"
				+ "A:1\n" + "B:1\n" + "C:1\n" + "X:1\n");
		if (line > 0)
			fail("incorrect output on line " + line);
	}

	public void testMixedCase() {
		CheckInputOutput.setInputCaptureOutput("aBACxX\n");
		CipherBreaker.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput("Text?\n" + "aBACxX\n"
				+ "A:2\n" + "B:1\n" + "C:1\n" + "X:2\n");
		if (line > 0)
			fail("incorrect output on line " + line);
	}

	public void testIgnoreSomeCharacters() {
		CheckInputOutput.setInputCaptureOutput("#$%^&");
		CipherBreaker.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput("Text?\n" + "#$%^&\n");
		if (line > 0)
			fail("incorrect output on line " + line);
	}

	public void testDigitsAndSpace() {
		CheckInputOutput.setInputCaptureOutput("1335 2 6 89");
		CipherBreaker.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput("Text?\n"
				+ "1335 2 6 89\n" + "DIGITS:8\n" + "SPACES:3\n");
		if (line > 0)
			fail("incorrect output on line " + line);
	}

	public void testIgnoreOtherPunctuation() {
		CheckInputOutput.setInputCaptureOutput("A@#*()");
		CipherBreaker.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput("Text?\n"
				+ "A@#*()\n" + "A:1");
		if (line > 0)
			fail("incorrect output on line " + line);
	}
	public void testPunctuation() {
		// Remember \" inside a string means a single double-quote character
		CheckInputOutput.setInputCaptureOutput("\"'.,!--!,.'\"");
		CipherBreaker.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput("Text?\n"
				+ "\"'.,!--!,.'\"\n" + "PUNCTUATION:12");
		if (line > 0)
			fail("incorrect output on line " + line);
	}

	public void testTheWorks() {
		CheckInputOutput
				.setInputCaptureOutput("2. \"iF YOU want truly to understand something, try to change it.\"- K. Lewin");
		CipherBreaker.main(new String[0]);
		int line = CheckInputOutput
				.checkCompleteOutput("Text?\n"
						+ "2. \"iF YOU want truly to understand something, try to change it.\"- K. Lewin\n"
						+ "A:3\n" + "C:1\n" + "D:2\n" + "E:4\n" + "F:1\n"
						+ "G:2\n" + "H:2\n" + "I:4\n" + "K:1\n" + "L:2\n"
						+ "M:1\n" + "N:6\n" + "O:4\n" + "R:3\n" + "S:2\n"
						+ "T:8\n" + "U:3\n" + "W:2\n" + "Y:3\n" + "DIGITS:1\n"
						+ "SPACES:13\n" + "PUNCTUATION:7");
		if (line > 0)
			fail("incorrect output on line " + line);
	}

}
