/** Using the debugger.
 * 
 * You do not need to modify this file - it's purpose is to demonstrate using a debugger
 * @author angrave
 *
 */
public class ExampleDebugger {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Create a breakpoint on the following line by
		// right-clicking in this window's left-hand margin
		// and selecting 'Toggle BreakPoint'
		int depth = 10;
		// Debug As> Java Application. You can then single-step,
		// step into and step out of methods.
		// Sometimes it can be useful to run your program in slow motion
		// and watch how the variables' values change.
		// Sometimes a pencil and paper are the best way to understand what a
		// program is doing.
		int result = jump(0, depth);
		TextIO.putln(result);
		// p.s. why does this program print out such a large number?
	}

	public static int jump(int x, int remain) {
		if (remain < 1)
			return 1;
		int result = jump(x + 1, remain - 1);
		result += jump(x - 1, remain - 1);
		return result;
	}
}
