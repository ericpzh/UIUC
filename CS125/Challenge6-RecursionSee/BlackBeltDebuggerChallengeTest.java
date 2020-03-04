import junit.framework.TestCase;

/**
 * Tests your debugging skills. 
 * You do not need to alter anything in this file apart from
 * Changing REPLACEWITHNETID to your netid in lowercase.
 * (In fact changes will be ignored by the autograder;
 *  this file will be automatically replaced when graded and 
 *  REPLACEWITHNETID will be replaced with your netid)
 * 
 */
public class BlackBeltDebuggerChallengeTest extends TestCase {
	/**
	 * Run this test case in debug mode. Once you step into the Ninja class and
	 * have mastered the Debugger controls you can discover the secret key to
	 * pass this test!
	 */
	public void testYourDebuggingSkillsTrial0() {
		char key = BlackBeltDebuggerChallenge.getTrial0Secret();
		int unique = getUniqueValue();

		Ninja n = new Ninja();
		n.trial0(key, unique);
	}

	/**
	 * Run this test case in debug mode. Once you step into the Ninja class and
	 * have mastered the Debugger controls you can discover the secret key to
	 * pass this test!
	 */

	public void testYourDebuggingSkillsTrial1() {
		char key = BlackBeltDebuggerChallenge.getTrial1Secret();
		int unique = getUniqueValue();
		Ninja n = new Ninja();
		n.trial1(key, unique);
	}

	/**
	 * Run this test case in debug mode. Once you step into the Ninja class and
	 * have mastered the Debugger controls you can discover the secret key to
	 * pass this test!
	 */
	public void testYourDebuggingSkillsTrial2() {
		char key = BlackBeltDebuggerChallenge.getTrial2Secret();
		int unique = getUniqueValue();
		Ninja n = new Ninja();

		n.trial2(key, unique);
	}

	/**
	 * Run this test case in debug mode. Once you step into the Ninja class and
	 * have mastered the Debugger controls you can discover the secret key to
	 * pass this test!
	 */
	public void testYourDebuggingSkillsTrial3() {
		char key = BlackBeltDebuggerChallenge.getTrial3Secret();
		int unique = getUniqueValue();
		Ninja n = new Ninja();
		n.trial3(key, unique);
	}

	/**
	 * Check authorship.
	 */
	public void testAuthorship() {

		String[] filenames = new String[] { "BlackBeltDebuggerChallenge.java",
				"GeneAnalysis.java", "LinkedList.java", "Person.java" };
		StringBuffer missing = new StringBuffer();
		for (int i = 0; i < filenames.length; i++) {
			String f = filenames[i];
			boolean success = CheckInputOutput.checkAuthorship(f);

			if (!success) {
				if (missing.length() > 0)
					missing.append(',');
				missing.append(f);
			}
		}
		if (missing.length() > 0)
			fail("Fix " + missing.toString() + " authorship");
	}

	/**
	 * Creates an integer value that is unique for each student
	 * 
	 * @return
	 */
	private int getUniqueValue() {
		String netId = getNetId();
		netId = netId.trim().toUpperCase();
		if (netId.length() == 0 || netId.length() > 10)
			throw new RuntimeException("Invalid NetID");

		int result = 0xBAADF00D;
		for (int i = 0; i < netId.length(); i++) {
			char c = netId.charAt(i);
			boolean ok = Character.isDigit(c) || (c >= 'A' && c <= 'Z')
					|| c == '-';
			if (!ok)
				throw new RuntimeException("Invalid NetID:" + c);
			result = (int) (2654435761L * result) ^ c; // Knuth's simple hash
		}
		//System.out.println(result);
		return result;
		
	}

	private String getNetId() {
 		return "zp3";
	}

}
