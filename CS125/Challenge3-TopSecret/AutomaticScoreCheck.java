import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * This program runs all four unit tests and generates a score. A similar
 * program will be used to grade you work after the deadline. You do not need to
 * modify this file.
 * 
 * @author angrave
 * 
 */
public class AutomaticScoreCheck {

	public static void main(String[] args) throws Exception {
		checkForCompileErrors();
		checkClassHasNoMutableStaticFields(CipherBreaker.class);
		checkClassHasNoMutableStaticFields(CaesarCipher.class);
		checkClassHasNoMutableStaticFields(MagicEightBall.class);
		checkClassHasNoMutableStaticFields(OddSum.class);

		TestRunner runner = new TestRunner();
		TestSuite suite = new TestSuite();
		suite.addTestSuite(CaesarCipherTest.class);
		suite.addTestSuite(MagicEightBallTest.class);
		suite.addTestSuite(OddSumTest.class);
		suite.addTestSuite(CipherBreakerTest.class);

		TestResult result = runner.doRun(suite, false);

		int passed = result.runCount() - result.errorCount()
				- result.failureCount();
		int total = result.runCount();
		int max = 100;
		try {
			if(args.length >0) max = Integer.parseInt(args[0]);
		} catch (Exception ignored) {
			
		}
		int score = (int) ((max * passed) / total);
		
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
	private static void checkClassHasNoMutableStaticFields(Class clss) {
		Field[] fields = clss.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			int m = fields[i].getModifiers();
			if (Modifier.isStatic(m) && !Modifier.isFinal(m)) {
				System.out
						.println("Static varibles can only be used for constants in this MP - they must be declared with 'final' modified e.g. public static final int constant=42;");
				System.exit(1);
			}
		}

	}

	private static void checkFileForCompileError(File file) {
		boolean isClass = file.getName().endsWith(".class");
		if ("|CheckInputOutput|AutomaticScoreCheck|TextIO|".contains("|"
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
				System.out
						.println("Don't use System.exit (file: "+file.getName()+")- see README instructions");
				System.exit(1);
			}
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
