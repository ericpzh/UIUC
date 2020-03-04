import java.util.Locale;

import junit.framework.TestCase;

/**
 * Tests MovieSurvey. See MovieSurvey.txt for more details.
 * Note this file cannot compile until you create the main method in MovieSurvey.java
 * @author angrave
 *
 */
/**
 * @author 78762
 *
 */
public class MovieSurveyTest extends TestCase {
	public void testSurveyOutput_3_5_11() {
		CheckInputOutput.setInputCaptureOutput("3\n5\n11\n");
		String expected = "Welcome. We're interested in how people are watching movies this year.\nThanks for your time. - The WRITERS GUILD OF AMERICA.\nPlease tell us about how you've watched movies in the last month.\nHow many movies have you seen at the cinema?\nHow many movies have you seen using a DVD or VHS player?\nHow many movies have you seen using a Computer?\nSummary: 3 Cinema movies, 5 DVD/VHS movies, 11 Computer movies\nTotal: 19 movies\nFraction of movies seen at a cinema: 15.79%\nFraction of movies seen outside of a cinema: 84.21%\n";
		MovieSurvey.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(expected);
		if(line>0) fail("Review incorrect output on line"+line);
	}
	public void testSurveyOutput_1_101_98() {
		CheckInputOutput.setInputCaptureOutput("1\n101\n98\n");
		String expected = "Welcome. We're interested in how people are watching movies this year.\nThanks for your time. - The WRITERS GUILD OF AMERICA.\nPlease tell us about how you've watched movies in the last month.\nHow many movies have you seen at the cinema?\nHow many movies have you seen using a DVD or VHS player?\nHow many movies have you seen using a Computer?\nSummary: 1 Cinema movies, 101 DVD/VHS movies, 98 Computer movies\nTotal: 200 movies\nFraction of movies seen at a cinema: 0.50%\nFraction of movies seen outside of a cinema: 99.50%\n"; 
		MovieSurvey.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(expected);
		if(line>0) fail("Review incorrect output on line"+line);
		
	}
	public void testAuthorship() {
		boolean success = CheckInputOutput.checkAuthorship("MovieSurvey.java");
		if(!success) fail("Fix @authorship");
	}
	public void tearDown() {
		CheckInputOutput.resetInputOutput();
	}
	public void setUp() throws Exception{
		CheckInputOutput.setUp();
		Locale.setDefault(Locale.US);
		super.setUp();
	}
}
