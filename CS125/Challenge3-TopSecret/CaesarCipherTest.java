import junit.framework.TestCase;

/**
 * Tests CaesarCipher. See CaesarCipher.txt for more details.
 * 
 * @author angrave
 * 
 */
public class CaesarCipherTest extends TestCase {
	public void testCompletes() {
		String input = "3\n"
				+ "Friends, Romans, countrymen, lend me your ears; I come to bury Caesar, not to praise him\n\n";
		CheckInputOutput.setInputCaptureOutput(input);
		CaesarCipher.main(new String[0]);
		// We don't test the output.
	}

	public void testCheckInputValues() {
		String input = "0\n" + "26\n" + "-26\n" + "-100\n" + "1\n"
				+ "THEUNAVOIDABLEPRICEOFRELIABILITYISSIMPLICITY\n" + "\n";
		CheckInputOutput.setInputCaptureOutput(input);
		String expected = "Please enter the shift value (between -25..-1 and 1..25)\n"
				+ "0 is not a valid shift value.\n"
				+ "Please enter the shift value (between -25..-1 and 1..25)\n"
				+ "26 is not a valid shift value.\n"
				+ "Please enter the shift value (between -25..-1 and 1..25)\n"
				+ "-26 is not a valid shift value.\n"
				+ "Please enter the shift value (between -25..-1 and 1..25)\n"
				+ "-100 is not a valid shift value.\n"
				+ "Please enter the shift value (between -25..-1 and 1..25)\n"
				+ "Using shift value of 1\n"
				+ "Please enter the source text (empty line to quit)\n"
				+ "Source   :THEUNAVOIDABLEPRICEOFRELIABILITYISSIMPLICITY\n"
				+ "Processed:UIFVOBWPJEBCMFQSJDFPGSFMJBCJMJUZJTTJNQMJDJUZ\n"
				+ "Please enter the source text (empty line to quit)\n"
				+ "Bye.\n";
		CaesarCipher.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(expected);
		if (line > 0)
			fail("Review incorrect output on line " + line);
	}

	public void testEncryptSimplest() {
		CheckInputOutput
				.setInputCaptureOutput("3\nTHEPURPOSEOFCOMPUTINGISINSIGHTNOTNUMBERS\n\n");
		String expected = "Please enter the shift value (between -25..-1 and 1..25)\n"
				+ "Using shift value of 3\n"
				+ "Please enter the source text (empty line to quit)\n"
				+ "Source   :THEPURPOSEOFCOMPUTINGISINSIGHTNOTNUMBERS\n"
				+ "Processed:WKHSXUSRVHRIFRPSXWLQJLVLQVLJKWQRWQXPEHUV\n"
				+ "Please enter the source text (empty line to quit)\n"
				+ "Bye.\n";
		CaesarCipher.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(expected);
		if (line > 0)
			fail("Review incorrect output on line " + line);
	}

	public void testRot13UpperLowerCase() {
		CheckInputOutput
				.setInputCaptureOutput("13\nAlwaysBeWaryoftheSoftwareEngineerWhoCarriesAScrewdriver\n\n");
		String expected = "Please enter the shift value (between -25..-1 and 1..25)\n"
				+ "Using shift value of 13\n"
				+ "Please enter the source text (empty line to quit)\n"
				+ "Source   :AlwaysBeWaryoftheSoftwareEngineerWhoCarriesAScrewdriver\n"
				+ "Processed:NYJNLFORJNELBSGURFBSGJNERRATVARREJUBPNEEVRFNFPERJQEVIRE\n"
				+ "Please enter the source text (empty line to quit)\n"
				+ "Bye.\n";
		CaesarCipher.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(expected);
		if (line > 0)
			fail("Review incorrect output on line " + line);

	}

	public void testSkipPunctutation() {
		CheckInputOutput
				.setInputCaptureOutput("5\nShould array indices start at 0 or 1? (My compromise of 0.5 was rejected without, I thought, proper consideration!)\n\n");
		String expected = "Please enter the shift value (between -25..-1 and 1..25)\n"
				+ "Using shift value of 5\n"
				+ "Please enter the source text (empty line to quit)\n"
				+ "Source   :Should array indices start at 0 or 1? (My compromise of 0.5 was rejected without, I thought, proper consideration!)\n"
				+ "Processed:XMTZQI FWWFD NSINHJX XYFWY FY 0 TW 1? (RD HTRUWTRNXJ TK 0.5 BFX WJOJHYJI BNYMTZY, N YMTZLMY, UWTUJW HTSXNIJWFYNTS!)\n"
				+ "Please enter the source text (empty line to quit)\n"
				+ "Bye.\n";
		CaesarCipher.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(expected);
		if (line > 0)
			fail("Review incorrect output on line " + line);
	}

	public void testDecrypt() {
		CheckInputOutput.setInputCaptureOutput("-3\nABCXYZ\n\n");
		String expected = "Please enter the shift value (between -25..-1 and 1..25)\n"
				+ "Using shift value of -3\n"
				+ "Please enter the source text (empty line to quit)\n"
				+ "Source   :ABCXYZ\n"
				+ "Processed:XYZUVW\n"
				+ "Please enter the source text (empty line to quit)\n"
				+ "Bye.\n";
		CaesarCipher.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(expected);
		if (line > 0)
			fail("Review incorrect output on line " + line);
	}

	public void testDecryptMultipleLines() {
		CheckInputOutput
				.setInputCaptureOutput("-3\nXKV ZILA ZXK EXSB QEB CXZQP; EXSFKD LMFKFLKP FP XK XOQ.\nLW'V KDUG HQRXJK WR ILQG DQ HUURU LQ BRXU FRGH ZKHQ BRX'UH ORRNLQJ IRU LW; LW'V HYHQ KDUGHU ZKHQ BRX'YH DVVXPHG BRXU FRGH LV HUURU-IUHH.\n\n");
		String expected = "Please enter the shift value (between -25..-1 and 1..25)\n"
				+ "Using shift value of -3\n"
				+ "Please enter the source text (empty line to quit)\n"
				+ "Source   :XKV ZILA ZXK EXSB QEB CXZQP; EXSFKD LMFKFLKP FP XK XOQ.\n"
				+ "Processed:UHS WFIX WUH BUPY NBY ZUWNM; BUPCHA IJCHCIHM CM UH ULN.\n"
				+ "Please enter the source text (empty line to quit)\n"
				+ "Source   :LW'V KDUG HQRXJK WR ILQG DQ HUURU LQ BRXU FRGH ZKHQ BRX'UH ORRNLQJ IRU LW; LW'V HYHQ KDUGHU ZKHQ BRX'YH DVVXPHG BRXU FRGH LV HUURU-IUHH.\n"
				+ "Processed:IT'S HARD ENOUGH TO FIND AN ERROR IN YOUR CODE WHEN YOU'RE LOOKING FOR IT; IT'S EVEN HARDER WHEN YOU'VE ASSUMED YOUR CODE IS ERROR-FREE.\n"
				+ "Please enter the source text (empty line to quit)\n"
				+ "Bye.\n";
		CaesarCipher.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(expected);
		if (line > 0)
			fail("Review incorrect output on line " + line);
	}

	public void testPositionShift() {
		CheckInputOutput
				.setInputCaptureOutput("999\nThere are two ways to write error-free programs, but only the third one works.\n\n");
		String expected = "Please enter the shift value (between -25..-1 and 1..25)\n"
				+ "Using position shift\n"
				+ "Please enter the source text (empty line to quit)\n"
				+ "Source   :There are two ways to write error-free programs, but only the third one works.\n"
				+ "Processed:TIGUI GYM DHA KPOJ MI SOGSE GUVTX-NAOP CFDWISFM, YSS PPOC ZOM DSUER EEW QJNHQ.\n"
				+ "Please enter the source text (empty line to quit)\n"
				+ "Bye.\n";

		CaesarCipher.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(expected);
		if (line > 0)
			fail("Review incorrect output on line " + line);
	}

	public void testDecodePositionShift() {
		CheckInputOutput
				.setInputCaptureOutput("-999\nTIGUI GYM DHA KPOJ MI SOGSE GUVTX-NAOP CFDWISFM, YSS PPOC ZOM DSUER EEW QJNHQ.\n\n");
		String expected = "Please enter the shift value (between -25..-1 and 1..25)\n"
				+ "Using position shift\n"
				+ "Please enter the source text (empty line to quit)\n"
				+ "Source   :TIGUI GYM DHA KPOJ MI SOGSE GUVTX-NAOP CFDWISFM, YSS PPOC ZOM DSUER EEW QJNHQ.\n"
				+ "Processed:THERE ARE TWO WAYS TO WRITE ERROR-FREE PROGRAMS, BUT ONLY THE THIRD ONE WORKS.\n"
				+ "Please enter the source text (empty line to quit)\n"
				+ "Bye.\n";
		CaesarCipher.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(expected);
		if (line > 0)
			fail("Review incorrect output on line " + line);
	}

	public void tearDown() {
		CheckInputOutput.resetInputOutput();
	}

	public void setUp() throws Exception {
		super.setUp();
		CheckInputOutput.setUp();
	}

	public void testAuthorship() {
		boolean success = CheckInputOutput.checkAuthorship("CaesarCipher.java");
		if (!success)
			fail("Fix authorship");
	}
}