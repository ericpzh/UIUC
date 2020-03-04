import junit.framework.TestCase;

/**
 * Unit tests for MagicEightBall. You do not need to edit this file.
 * 
 * @author angrave
 * 
 */

public class MagicEightBallTest extends TestCase {
	private static final String PREAMBLE = "Hours spent working on CS125?\nHappy?\nKnow mentor's innermost Java secrets?\n";

	private static final String RICK_ROLLED = "Rick Rolled Four Times in One Day",
			KEYS_SWAPPED = "Delete Enter Keys Swapped",
			MICHIGAN = "Embarrassing Michigan Road Trip",
			ACE_EXAM = "Ace CS125 Exam";

	public void testRickRolled() {
		// Social, unhappy students with more than 15 hours of study
		CheckInputOutput.setInputCaptureOutput("16\nn\ny\n");
		MagicEightBall.main(null);
		int line = CheckInputOutput.checkCompleteOutput(PREAMBLE + RICK_ROLLED);
		if (line > 0)
			fail("Fix line " + line);
	}

	public void testAceMidterm() {
		// Social happy students with more than 20 hours of CS125
		CheckInputOutput.setInputCaptureOutput("21\ny\ny\n");
		MagicEightBall.main(null);
		int line = CheckInputOutput.checkCompleteOutput(PREAMBLE + ACE_EXAM);
		if (line > 0)
			fail("Fix line " + line);
	}

	public void testMichiganA() {
		// Unhappy students with less than 10 hours of study
		CheckInputOutput.setInputCaptureOutput("9\nn\nn\n");
		MagicEightBall.main(null);
		int line = CheckInputOutput.checkCompleteOutput(PREAMBLE + MICHIGAN);
		if (line > 0)
			fail("Fix line " + line);
	}

	public void testMichiganB() {
		// Unhappy students with less than 10 hours of study
		CheckInputOutput.setInputCaptureOutput("9\nn\ny\n");
		MagicEightBall.main(null);
		int line = CheckInputOutput.checkCompleteOutput(PREAMBLE + MICHIGAN);
		if (line > 0)
			fail("Fix line " + line);
	}

	public void testKeysSwapped() {
		// All other students have their "Delete Enter Keys Swapped" by the ACM
		// office.
		CheckInputOutput.setInputCaptureOutput("1022\ny\nn\n");
		MagicEightBall.main(null);
		int line = CheckInputOutput
				.checkCompleteOutput(PREAMBLE + KEYS_SWAPPED);
		if (line > 0)
			fail("Fix line " + line);
	}

	public void testAuthorship() {
		boolean success = CheckInputOutput
				.checkAuthorship("MagicEightBall.java");
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
