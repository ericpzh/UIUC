/**
 * This class tests your Debugger Fu. Use Debug As > Unit Test on the Test
 * class. You'll need to set breakpoints and single step through the code.
 * @author 233
 *
 */
public class BlackBeltDebuggerChallenge {
	private static String getNetId() {
 		return "zp3";
	}
	/**
	 * Use the Debugger's breakpoints, and the debugger controls to discover the
	 * character you need here to make the test pass.
	 * 
	 * @return the secret character
	 */
	public static char getTrial0Secret() {
		// Warning - the value you need here is specific to you
		char result = 'a';
		result += 1 + (getUniqueValue() & 7);
		return result;
	}

	/**
	 * Use the Debugger's breakpoints, and the debugger controls to discover the
	 * character you need here to make the test pass.
	 * 
	 * @return the secret character
	 */
	public static char getTrial1Secret() {
		char key = 'a';
		String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0 ; i < 52 ; i++){
			if (s.charAt(i) + (getUniqueValue() >> 2 & 7) + recursion(0) == 'z'){
				key = s.charAt(i);
		}
		}
		
		// Warning - the value you need here is specific to you
		return key;
	}

	/**
	 * Use the Debugger's breakpoints, and the debugger controls to discover the
	 * character you need here to make the test pass.
	 * 
	 * @return the secret character
	 */
	public static char getTrial2Secret() {
		//int levels = 2 + ((1180977348 >> 2) & 7);//levels = 3
		int key;
		char key2 = 'a';
		String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int a = 2 + ((getUniqueValue() >> 2) & 7);
		for (int i = 0 ; i < 52 ; i++){
			key =  s.charAt(i)-'A';
			for(int k = 0 ; k < a ; k++){
				methodA(key);
				key-=2;
			}
			if(key == 0) {
				key2 = s.charAt(i);
				break;
			}
			
		}
			
		// Warning - the value you need here is specific to you
		return key2;
	}

	/**
	 * Use the Debugger's breakpoints, and the debugger controls to discover the
	 * character you need here to make the test pass.
	 * 
	 * @return the secret character
	 */
	public static char getTrial3Secret() {
		return 9;
	}
	
	
	
	public static int methodA(int param) {
		int a = 1, b = 2, c = 3;
		int[] array = new int[29];
		array[a]++;
		array[5] = array[7 - b] - c;
		String s = "Hence forth".substring(0).toUpperCase();
		c = s.length() + param;
		char[] stuff = s.toCharArray();
		String result = new String(stuff);
		boolean sameObject = stuff == result.toCharArray();
		c += sameObject ? 1 : 2;
		int big = Integer.MAX_VALUE /2;
		if(c ==big) throw new RuntimeException("Not this time");
		return big;
	}
	public static int recursion(int level) {
		if (level > 6)
			return 1;
		return recursion(level + 1);
	}
	private static int getUniqueValue() {
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
}
