import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * A utility class to capture console output, feed a string as input then test
 * the output. These static methods are typically used by junit tests to test a
 * console program. You do not need to edit this class.
 * Last updated 9/9/2008 : Changed output format.
 * @author angrave
 * 
 */
public class CheckInputOutput {
	private static ByteArrayOutputStream out;

	private static PrintStream systemOut;

	private static InputStream systemIn;

	/**
	 * Before running the target program, use this method to capture it's output
	 * and feed it the given string as input.
	 * 
	 * @param input
	 */
	public static void setInputCaptureOutput(String input) {
		if (systemOut == null)
			systemOut = System.out;
		if (systemIn == null)
			systemIn = System.in;

		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		TextIO.rewrapStandardInputOutput();
	}

	/**
	 * Sends output to original console output, and get input from standard console input.
	 * Assumes setInputCaptureOutput() was called earlier (otherwise this method does nothing).
	 * 
	 */
	public static void resetInputOutput() {
		if (systemOut != null)
			System.setOut(systemOut);
		if (systemIn != null)
			System.setIn(systemIn);
	}

	/**
	 * Returns the captured output. Assumes setInputCaptureOutput was already invoked.
	 * @return captured output. Note \r chars are removed.
	 */
	public static String getCapturedOutput() {
		// Windows uses \n\r for newslines we remove the \r s.
		return out.toString().replace("\r", "");
	}

	/**
	 * Returns zero if the captured program is exactly correct. (Only allowed
	 * differences are Window's CR output includes an additional \r character.
	 * 
	 * @param expected output text.
	 * @return the line number (>=1) that the first inconsistenty was found
	 */
	public static int checkCompleteOutput(String expected, String testname) {
		testname = testname == null || testname.length() == 0 ? "test" : testname
				+ ": ";
		String actual = getCapturedOutput();
		expected = expected.replace("\r", "");
		/* Trailing newlines are ignored */
		while(actual.endsWith("\n")) actual=actual.substring(0,actual.length()-1);
		while(expected.endsWith("\n")) expected=expected.substring(0,expected.length()-1);
		if (actual.equals(expected)) {
			System.err.println(testname+"program output matches expected output.");
			return 0;
		}
		String expectedArr[] = expected.split("\n");
		String actualArr[] = actual.split("\n");
		boolean failed = expectedArr.length != actualArr.length;

		System.err.println(testname+"Expected " + expectedArr.length
				+ " lines. Actual " + actualArr.length + " lines.");
		int line;
		for (line = 0; line < expectedArr.length && line < actualArr.length; line++) {
			boolean pass = expectedArr[line].equals(actualArr[line]);
			String passAsString = pass ? "PASS" : "FAIL";
			System.err.print((1 + line) + ". " + passAsString + " > '"
					+ actualArr[line] + "'");
			if (!pass) {
				System.err.println(" Should be - \n" + (1 + line)
						+ ".        '" + expectedArr[line] + "'");
				failed = true;
				break;
			} else {
				System.err.println();
			}
		}
		if (failed)
			System.err.println(testname
					+ "Program output above was incorrect on output line "
					+ (line + 1));
		if (expectedArr.length > actualArr.length) {
			failed = true;
			String shouldBe = expectedArr[actualArr.length];
			if (shouldBe.length() == 0)
				shouldBe = "<BLANK LINE>";
			System.err.println(testname + "... Missing line #"
					+ (1+actualArr.length )+ " : " + shouldBe);
		}
		if(failed) System.err.println();
		return failed ? line + 1 : 0;
	}

	public static boolean checkOutputContains(String search, String testname) {
		String actual = getCapturedOutput();
		if (actual.contains(search))
			return true;

		testname = testname == null || testname.length() == 0 ? "" : testname
				+ ":";
		System.err.println(actual);
		System.err.println(testname + "COULD NOT FIND " + search
				+ " in the output above");

		return false;
	}

	public static boolean checkAuthorship(String file) {
		return checkAuthorship(file, null);
	}

	public static boolean checkAuthorship(String file, String ignoreDefault) {
		if (ignoreDefault == null)
			ignoreDefault = "@author insert-your-netid-here";

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException fnfe) {
			System.err.println("Checking (@author) line but the file '" + file
					+ "' could not be found");
			return false;
		}
		boolean found = false;
		String line;
		try {
			while ((line = reader.readLine()) != null) {

				if (line.contains(ignoreDefault)) {
					System.err
							.println("File "
									+ file
									+ " : @author comments at the beginning should list your netid");
					return false;
				} else
					found |= line.contains("@author");
			}
		} catch (Exception e) {
			System.err.println("Could not check '" + file
					+ "' contents for @author entry :" + e.getMessage());
		}
		if (!found)
			System.err
					.println("File "
							+ file
							+ " : @author line is missing. Please include @author netid");
		return found;
	}
}
