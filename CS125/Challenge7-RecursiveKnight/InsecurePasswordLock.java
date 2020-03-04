/**
 * An insecure password lock. You do not need to modify this file
 * 
 * @author angrave
 * 
 */
public class InsecurePasswordLock {
	char[] secret;
	private boolean unlocked;

	public InsecurePasswordLock() {
		// secrets are usually 30 to 50 upper case characters long
		// but here's a hard coded example one
		secret = "ASECRETTHATYOUWILLNOTGUESSTODAY".toCharArray();
	}
	public InsecurePasswordLock(int len) {
		// generate a random password of the required length
		secret = new char[len];
		for (int i = 0; i < secret.length; i++)
			secret[i] = (char) ('A' + (int) (26 * Math.random()));
	}

	public int open(char[] attempt) {
		// A badly implemented open method that leaks too much information.
		
		if (attempt == null || attempt.length != secret.length)
			return -1; // Incorrect length!
 
		// Check for incorrect characters
		for (int i = 0; i < secret.length; i++)
			if (secret[i] != attempt[i])
				return i; // EARLY RETURN

		unlocked = true;
		return secret.length; // SUCCESS
	}

	public boolean isUnlocked() {
		return unlocked;
	}
}
