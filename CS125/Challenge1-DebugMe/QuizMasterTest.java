import junit.framework.TestCase;

/**
 * You do not need to modify this file.
 * However, it is worthwhile reading the test input and expected output for each test case
 * @author angrave
 *
 */
public class QuizMasterTest extends TestCase {

	public static void testMichiganStudent() {
		String input = "48502\n2\n1\n2\n1\n";
		String requiredOutput = 
			"Please enter your zip code.\n" +
			"Which University CS Department was recently awarded $208 million to develop the worlds fastest computer?\n" +
			"1. Illinois\n" +
			"2. Michigan\n" +
			"3. MIT\n" +
			"Which University CS Department designed and built the pioneering ILLIAC series?\n" +
			"1. Illinois\n" +
			"2. Wisconsin\n" +
			"3. Berkeley\n" +
			"Which University released 'Mosaic' - the first multimedia cross-platform browser?\n" +
			"(Mosaic's source code was later licensed to Microsoft and Netscape Communications)\n" +
			"1. Illinois\n" +
			"2. Michigan\n" +
			"3. Wisconsin\n" +
			"True/False? Variables have four things: a type, name, value and a memory location.\n" +
			"1. True\n" +
			"2. False\n" +
			"You scored:0\n";
		
		CheckInputOutput.setInputCaptureOutput(input);
		QuizMaster.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(requiredOutput,"testMichiganStudent");
		if (line > 0)
			fail("Review incorrect output on line " + line);
	}
	
	public static void testIllinoisStudent() {
		String input = "61802\n1\n1\n1\n1\n";
		String requiredOutput = 
			"Please enter your zip code.\n" +
			"Which University CS Department was recently awarded $208 million to develop the worlds fastest computer?\n" +
			"1. Illinois\n" +
			"2. Michigan\n" +
			"3. MIT\n" +
			"Which University CS Department designed and built the pioneering ILLIAC series?\n" +
			"1. Illinois\n" +
			"2. Wisconsin\n" +
			"3. Berkeley\n" +
			"Which University released 'Mosaic' - the first multimedia cross-platform browser?\n" +
			"(Mosaic's source code was later licensed to Microsoft and Netscape Communications)\n" +
			"1. Illinois\n" +
			"2. Michigan\n" +
			"3. Wisconsin\n" +
			"True/False? Variables have four things: a type, name, value and a memory location.\n" +
			"1. True\n" +
			"2. False\n" +
			"You scored:40\nCongratulations!\n";
		
		CheckInputOutput.setInputCaptureOutput(input);
		QuizMaster.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(requiredOutput,"testIllinoisStudent");
		if (line > 0)
			fail("Review incorrect output on line " + line);
	}
	
	public static void testMichiganPhDStudent() {
		String input = "48502\n1\n1\n1\n1\n";
		String requiredOutput = 
			"Please enter your zip code.\n" +
			"Which University CS Department was recently awarded $208 million to develop the worlds fastest computer?\n" +
			"1. Illinois\n" +
			"2. Michigan\n" +
			"3. MIT\n" +
			"Which University CS Department designed and built the pioneering ILLIAC series?\n" +
			"1. Illinois\n" +
			"2. Wisconsin\n" +
			"3. Berkeley\n" +
			"Which University released 'Mosaic' - the first multimedia cross-platform browser?\n" +
			"(Mosaic's source code was later licensed to Microsoft and Netscape Communications)\n" +
			"1. Illinois\n" +
			"2. Michigan\n" +
			"3. Wisconsin\n" +
			"True/False? Variables have four things: a type, name, value and a memory location.\n" +
			"1. True\n" +
			"2. False\n" +
			"You scored:40\n";	
		CheckInputOutput.setInputCaptureOutput(input);
		QuizMaster.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(requiredOutput,"testMichiganPhDStudent");
		if (line > 0)
			fail("Review incorrect output on line " + line);
	}

	
	public static void testWisconsinStudent() {
		String input = "53701\n1\n2\n3\n1\n";
		String requiredOutput = 
			"Please enter your zip code.\n" +
			"Which University CS Department was recently awarded $208 million to develop the worlds fastest computer?\n" +
			"1. Illinois\n" +
			"2. Michigan\n" +
			"3. MIT\n" +
			"Which University CS Department designed and built the pioneering ILLIAC series?\n" +
			"1. Illinois\n" +
			"2. Wisconsin\n" +
			"3. Berkeley\n" +
			"Which University released 'Mosaic' - the first multimedia cross-platform browser?\n" +
			"(Mosaic's source code was later licensed to Microsoft and Netscape Communications)\n" +
			"1. Illinois\n" +
			"2. Michigan\n" +
			"3. Wisconsin\n" +
			"True/False? Variables have four things: a type, name, value and a memory location.\n" +
			"1. True\n" +
			"2. False\n" +
			"You scored:20\n";
		
		CheckInputOutput.setInputCaptureOutput(input);
		QuizMaster.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(requiredOutput,"testWisconsinStudent");
		if (line > 0)
			fail("Review incorrect output on line " + line);
	}
	

	public void testAuthorship() {
		boolean success = CheckInputOutput.checkAuthorship("QuizMaster.java");
		if (!success)
			fail("Fix @authorship");
	}
	public void tearDown() {
		CheckInputOutput.resetInputOutput();
	}

}
