public class ExampleCharacters {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int offset = -300;
		offset = offset % 26; // -25 to +25
		String mesg = "CHOOSE THE PATH TO WISDOM OR LET IT CHOOSE YOU";

		char c = mesg.charAt(0);
		int letter = c - 'A';
		int encryptedDelta = (26 + letter + offset) % 26;
		char encyptedAsLetter = (char) (encryptedDelta + 'A');

		System.out.println("Encrypted First char is " + encyptedAsLetter);
	}
}