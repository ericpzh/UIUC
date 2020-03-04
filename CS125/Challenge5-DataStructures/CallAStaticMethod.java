/**
 * @author 233
 * Prints out only lines that contain an email address Each printed line is
 * justified to right by prepending the text with '.' characters The minimum
 * width of the line including padding is 40 characters. See the test case for
 * example input and expected output.
 */
class CallAStaticMethod {

	public static void main(String[] args) {

		while (!TextIO.eof()) {
			String line = TextIO.getln();
			
			if(isEmailAddress(line)){
				System.out.println(createPadding('.',40-line.length())+line);
			}
			// Use ExampleClassMethods
			// 'isEmailAddress' and 'createPadding' to complete this method
		}

	}
	public static boolean isEmailAddress(String text) {
		// Sufficient for our purposes
		// Email name must have at least one @ character:
		return text.indexOf("@") > 0;
	}
	public static String createPadding(int number) {
		return createPadding('_',number);
	}
	
	/**
	 * Creates a string with a repeated pad character.
	 */
	public static String createPadding(char pad, int number) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < number; i++)
			result.append(pad);
		return result.toString();
	}
}
