
import java.io.File;

import junit.framework.TestCase;
/**
 * Tests for UsingPublicFieldsIsEasy.
 * You do not need to modify this file.
 * @author angrave
 *
 */
public class UsingPublicFieldsIsEasyTest extends TestCase {

	public void testAnalyzeLoHi() {
		SimplePublicPair pair = new SimplePublicPair();
		pair.a = 99;
		pair.b = 101;
		SimplePublicTriple result = UsingPublicFieldsIsEasy.analyze(pair);
		assertEquals("99*101=9999", result.description);
		assertEquals(99, result.x);
		assertEquals(101, result.y);
	}

	public void testAnalyzeHiLo() {
		SimplePublicPair pair = new SimplePublicPair();
		pair.a = 11;
		pair.b = 9;
		SimplePublicTriple result = UsingPublicFieldsIsEasy.analyze(pair);
		assertEquals("11*9=99", result.description);
		assertEquals(9, result.x);
		assertEquals(11, result.y);
	}

	public void testAuthorshipOfAllFiles() {
		String[] files = new File(".").list();
		String todo = "";
		for (int i = 0; i < files.length; i++) {
			String f = files[i].trim();

			if (f.endsWith(".java")) {
				boolean success = CheckInputOutput.checkAuthorship(f);
				if (!success)
					todo += " " + f;
			}
		}
		if (todo.length() > 0)
			fail("Fix @authorship in" + todo);
	}
	public void setUp() throws Exception {
		super.setUp();
		CheckInputOutput.setUp();
	}
}
