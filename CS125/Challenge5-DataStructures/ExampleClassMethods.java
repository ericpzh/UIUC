/** Some Example Class Methods - notice that these methods contain
 * the word 'static' ; they are not connected to a specific object;
 * they do not contain a 'this' reference.
 * 
 * You do not need to modify this file - it's purpose is to demonstrate class methods
 * @author angrave
 *
 */
public class ExampleClassMethods {

	/**
	 * Creates a padding string with a repeated underscore.
	 */
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

	/**
	 * Returns true if the string looks like an email address.
	 */
	public static boolean isEmailAddress(String text) {
		// Sufficient for our purposes
		// Email name must have at least one @ character:
		return text.indexOf("@") > 0;
	}

}
