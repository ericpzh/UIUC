public class InsecureCombinationLockBreaker {

	public static int breakLock(InsecureCombinationLock lock) {
		// Write your code here to break the combination lock
		// Read the combination lock source code to determine
		// the weakness in the lock

		// You do not need to use recursion.

		// - An inside programmer has written some extra
		// code that gives you a tiny hint about how close you
		// are to opening the lock.
		// Using this single bit of information, your job
		// is to find the integer value that opens the lock

		// This method is only for honors students and the curious to complete
		// Honor students: Be prepared to demonstrate how you completed
		// this problem!

		// **** This method is not graded as part of the MP ****

		// (but for your own local testing just remove
		// the 'xxx's in the test method in InsecureTest.java)

		// Beginner: This problem is not for beginners

		// Intermediate: It took me 90 minutes to create a robust solution
		// (including 15 minutes debugging it with several million tests).

		// Advanced: 'Don't be wasteful' -
		// Assume there is a 1s time penalty for every unlocking attempt,
		// make sure your code uses the fewest number of unlocking attempts
		// ie. It will open the lock as quickly as possible.
		// (My solution was little wasteful but was simpler to implement).
		int[] result = new int[] { 0, -1, -1, -1, -1, -1, -1, -1 ,-1};
		int i = 1;
		char[] test = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i' ,'j' };
		int key = 0;
		test[0] = lock.open(toint(result)).toCharArray()[0];
		int trial = 0;
		char[] b = lock.open(toint(result)).toCharArray();
		while (!lock.isUnlocked()  && i < 10&&trial <= 100) {
			test[i] = b[0];
			trial ++;
			//printchar(b);
			//System.out.println("aaa");
			//printchar(test);
			//System.out.println("aaa");
			//printint(result);
			//System.out.println("aaa");
			//System.out.println("i="+i);
			if (test[1] == '*' && test[2] == 'c') {
				result[8]+=1;
				b = lock.open(result[8]).toCharArray();
				//lock.open(result[7]);
				//System.out.println(result[7]);
				//System.out.println("yeah");
			}
			if (test[2] == '-' && test[3] == 'd') {
				result[7]++;
				b = lock.open(result[8] + result[7] * 10 ).toCharArray();
				//lock.open(result[7] + result[6] * 10);
			}
			if (test[3] == '*' && test[4] == 'e') {
				result[6]++;
				b = lock.open(result[8] + result[7] * 10 + result[6] * 100).toCharArray();
				//lock.open(result[7] + result[6] * 10 + result[5] * 100);
			}
			if (test[4] == '-' && test[5] == 'f') {
				result[5]++;
				b = lock.open(result[8] + result[7] * 10 + result[6] * 100 + result[5] * 1000).toCharArray();
				//lock.open(result[7] + result[6] * 10 + result[5] * 100 + result[4] * 1000);
			}
			if (test[5] == '*' && test[6] == 'g') {
				result[4]++;
				b = lock.open(result[8] + result[7] * 10 + result[6] * 100 + result[5] * 1000 + result[4] * 10000).toCharArray();
				//lock.open(result[7] + result[6] * 10 + result[5] * 100 + result[4] * 1000 + result[3] * 10000);
			}
			if (test[6] == '-' && test[7] == 'h') {
				result[3]++;
				b = lock.open(result[8] + result[7] * 10 + result[6] * 100 + result[5] * 1000 + result[4] * 10000+ result[3] * 100000).toCharArray();
				//lock.open(result[7] + result[6] * 10 + result[5] * 100 + result[4] * 1000 + result[3] * 10000+ result[2] * 100000);
			}
			if (test[7] == '*' && test[8] == 'i') {
				result[2]++;
				b = lock.open(result[8] + result[7] * 10 + result[6] * 100 + result[5] * 1000 + result[4] * 10000+ result[3] * 100000 + result[2] * 1000000).toCharArray();
				//lock.open(result[7] + result[6] * 10 + result[5] * 100 + result[4] * 1000 + result[3] * 10000+ result[2] * 100000 + result[1] * 1000000);
			}
			if (test[8] == '-'&& test[9] == 'j') { 
				result[1]++;
				b = lock.open(result[8] + result[7] * 10 + result[6] * 100 + result[5] * 1000 + result[4] * 10000+ result[3] * 100000 + result[2] * 1000000+result[1]*10000000).toCharArray();
				//lock.open(toint(result));
			}
			if (test[9] == '*'){
				result[0]++;
				//System.out.println("233");
				b = lock.open(toint(result)).toCharArray();
			}
			if (test[i] != b[0]){
				i++;
				//System.out.println("b="+b[i]+b[i-1]);
			}
		}
		key = toint(result);
		//key = key+100000000;
		return key;
	}

	public static void printchar(char[] x) {
		for (int i = 0; i < x.length ; i++) {
			System.out.print(x[i]);
		}
		System.out.println();
	}

	public static void printint(int[] x) {
		for (int i = 0; i < x.length ; i++) {
			System.out.print(x[i]+",");
		}
		System.out.println();
	}

	public static int toint(int[] a) {
		return a[0] * 100000000 + a[1] * 10000000 + a[2] * 1000000 + a[3] * 100000 + a[4] * 10000 + a[5] * 1000 + a[6] * 100
				+ a[7]*10+a[8];
	}

	public static void main(String[] args) {
		InsecureCombinationLock lock = new InsecureCombinationLock();
		int code = breakLock(lock);
		//code+=100000000;
		System.out.println("Unlock code:" + code);
		System.out.println(lock.isUnlocked() ? "Unlocked :-)" : "Still Locked :-(");
	}
}
