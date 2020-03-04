/**
 * An insecure combination lock.
 * You have 100 attempts to open this lock before the lock shuts forever
 * You do not need to modify this file.
 * @author angrave
 *
 */
public class InsecureCombinationLock {
	private static final String[] EVEN_ODD= {"*-*-*","-*-*-"};

	private int key;
	private int remain = 100; // number of attempts remaining
	private boolean unlocked;
	
	public InsecureCombinationLock(int k) {key = k;}
	public InsecureCombinationLock() {
		key = 12345678 + (int) (98765432 * Math.random());
		System.out.println(key);
	}
	public boolean isUnlocked() {
		return unlocked;
	}
	
	public String open(int attempt) {
		if (remain == 0)
			return "Too many attempts! Lock shut forever!";
		if (key == attempt) {
			unlocked = true;
			return "Success!";
		}
		remain--;

		// count number of correct digits
		int correctDigits = 0;
		for (int a = attempt, k = key; k != 0 || a != 0;) {
			// compare lowest (units) digit
			if ((a % 10) == (k % 10))
				correctDigits++;
			a = a / 10; // drop the units digit
			k = k / 10;
		}
		return EVEN_ODD[correctDigits % 2] +" Incorrect! (" + remain + " attempts remain) "+EVEN_ODD[correctDigits % 2];
	}
}
