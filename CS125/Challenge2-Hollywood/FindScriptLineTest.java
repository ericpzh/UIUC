import junit.framework.TestCase;

/**
 * Tests FindScriptLine. See FindScriptLine.txt for more details.
 * @author angrave
 *
 */
public class FindScriptLineTest extends TestCase {
	public void testFind() {
		CheckInputOutput.setInputCaptureOutput("a cookie\n");
		String expected = "Please enter the word(s) to search for\nSearching for 'a cookie'\n215 - You better take a cookie.  Got a\n218 - He eyes her, then takes a cookie.\nDone Searching for 'a cookie'\n";
		FindScriptLine.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(expected);
		if(line>0) fail("Review incorrect output on line "+line);
	}
	
	public void testFindCaseInsensitive() {
		CheckInputOutput.setInputCaptureOutput("Spoon\n");
		String expected = "Please enter the word(s) to search for\nSearching for 'Spoon'\n12 - blocks.  A skinny BOY holds a SPOON which sways like a\n17 - The Boy smiles as Neo picks up a spoon and tries to\n21 - SPOON BOY\n22 - Your spoon does not bend because\n23 - it is just that, a spoon.  Mine\n24 - bends because there is no spoon,\n29 - SPOON BOY\n30 - Link yourself to the spoon.\n31 - Become the spoon and bend\n34 - Neo nods, again holding up his spoon.\n37 - There is no spoon.  Right.\n39 - He concentrates.  The spoon begins to bend just as the\n45 - Spoon Boy smiles.\nDone Searching for 'Spoon'\n";
		FindScriptLine.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(expected);
		if(line>0) fail("Review incorrect output on line "+line);		
	}
	
	public void testAuthorship() {
		boolean success = CheckInputOutput.checkAuthorship("FindScriptLine.java");
		if(!success) fail("Fix @authorship");
	}
	public void tearDown() {
		CheckInputOutput.resetInputOutput();
	}
	public void setUp() throws Exception{
		CheckInputOutput.setUp();
		super.setUp();
	}
}