import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * This program runs all unit tests and generates a score. A similar program
 * will be used to grade you work after the deadline. You do not need to modify
 * this file.
 * 
 * @author angrave
 * 
 */
public class AutomaticGrader {
	/* The main entry point of grading amusement */
	public static void main(String[] args) throws Exception {
		checkForCompileErrors();

		TestRunner runner = new TestRunner();
		TestSuite suite = new TestSuite();
		suite.addTestSuite(BlackBeltDebuggerChallengeTest.class);
		suite.addTestSuite(LinkedListTest.class);
		suite.addTestSuite(PersonTest.class);
		suite.addTestSuite(GeneAnalysisTest.class);

		TestResult result = runner.doRun(suite, false);

		int passed = result.runCount() - result.errorCount()
				- result.failureCount();
		int total = result.runCount();
		int score = (int) ((100. * passed) / total);

		System.out.println(passed + " passed out of " + total);
		System.out.println("Score=" + score);
		System.exit(score);

	}

	public static void checkForCompileErrors() {
		File[] files = new File(".").listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.getName().endsWith(".class")
					|| file.getName().endsWith(".java"))
				checkFileForCompileError(file);
		}
	}

	private static void checkFileForCompileError(File file) {
		boolean isClass = file.getName().endsWith(".class");
		if ("|CheckInputOutput|AutomaticGrader|TextIO|".contains("|"
				+ file.getName().replace(".java", "").replace(".class", "")
				+ "|"))
			return;
		try {
			byte[] buffer = new byte[(int) file.length()];
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));
			bis.read(buffer);
			String sourceCode;
			if (isClass)
				sourceCode = new String(buffer);
			else
				sourceCode = new String(buffer, "UTF8");
			// TextIO.putln(sourceCode);
			if (sourceCode.contains("Unresolved compilation problem")) {
				System.out
						.println("Fix Compilation Errors in "
								+ file.getName()
								+ " - see the Package explorer or Problems view for details.");
				System.exit(1);
			}

			if (sourceCode.contains("System.exit")) {
				System.out.println("Don't use System.exit (file: "
						+ file.getName() + ")- see README instructions");
				System.exit(1);
			}

			if (file.getName().endsWith("Test.java"))
				checkTestUnmodified(file, sourceCode);
			bis.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private static void checkTestUnmodified(File file, String sourceCode) {
		// drop comments, whitespace and import statements
		sourceCode = sourceCode.replaceAll("import[^;]*;", "");
		sourceCode = sourceCode.replaceAll("//.*", "").replaceAll("\\s+", "")
				.replaceAll("/\\*.*\\*/", "");
		sourceCode = sourceCode.replaceAll("\\s*", "");
		// Now we will check to see if the file looks like my original version

		
		if(file.getName().contains("BlackBelt") && sourceCode.contains("getNetId"))
		{
			sourceCode=sourceCode.replaceAll("StringgetNetId[^\\}]*\\}","");
			//TextIO.putln(sourceCode);
		}
		
		int h = calcHashCode(sourceCode);
		// System.out.println(sourceCode);
		String name = file.getName().replace(".java", "");
		int expected = getExpectedHash(name);
		if (h != expected) {
			System.err.println(name + " Incorrect verification code "
					+ expected + " but was " + h);
			System.err
					.println("Looks like "
							+ file.getName()
							+ " is modified.\n"
							+ "Replace it with an earlier version from subversion. (Replace With>Team>Revision)");
			// To ignore the above error just comment out the following line
			System.exit(-1);
		}
	}

	private static int getExpectedHash(String name) {

		if ("BlackBeltDebuggerChallengeTest".equals(name))
			return 1810998261;
		if("GeneAnalysisTest".equals(name))
			return -937779822;
		if("LinkedListTest".equals(name))
			return  -1517387034;
		if("PersonTest".equals(name))
			return  997525644;
		
		
		return 0;
	}

	private static int calcHashCode(String sourceCode) {
		int h = 0;
		for (int i = 0; i < sourceCode.length(); i++) {
			char c = sourceCode.charAt(i);
			if (c < 32 || c == ' ') {
				continue;
			}
			h = h * 37 + c;
		}
		return h;
	}
}
