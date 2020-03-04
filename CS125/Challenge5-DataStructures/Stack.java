/**
 * 
 * @author 233
 *
 */
public class Stack {
	private String[] stack = new String[0];
	/** Adds a value to the top of the stack.*/
	public void push(String value){

		//TODO renzhenzuoa yihouyaoyong
		String[] result = new String[stack.length+1];
		result[0] = value;
		for(int k = 1 ; k < stack.length+1 ; k++){
			result[k] = stack[k-1];
		}
		stack = new String[stack.length+1];
		for (int i = 0 ; i < stack.length ; i++){
			stack[i] = result[i];
			//System.out.print(stack[i]+"a");
		}
		
		//throw new RuntimeException("Elevator stuck. Abort Retry Fail?");
	}
	
	/** Removes the topmost string. If the stack is empty, returns null. */
	public String pop() {
		//System.out.print(stack.length);
		if (stack.length <= 0 || stack[0] == "") return null;
		else {
			String a = stack[0];
			String[] result = new String[stack.length-1];
			for(int k = 1 ; k < stack.length ; k++){
				result[k-1] = stack[k];
			}
			stack = new String[stack.length-1];
			for (int i = 0 ; i < stack.length ; i++){
				stack[i] = result[i];
				//System.out.print(stack[i]+"p");
			}
			System.out.println(a.toString());
			
			return a;
		}
		//throw new RuntimeException("Ones and Zeros. Mostly.");
	}
	
	/** Returns the topmost string but does not remove it. If the stack is empty, returns null. */
	public String peek() {
		if (stack.length == 0) return null;
		else {
			return stack[0];
		}
		//throw new RuntimeException("Don't peek. It's too scary");
		
	}
	
	/** Returns true iff the stack is empty */
	public boolean isEmpty() {
		if (stack.length <= 0) return true;
		else return false;
		//throw new RuntimeException("Need more");
	}

	/** Returns the number of items in the stack. */
	public int length() {
		return stack.length;
		//throw new RuntimeException("Must be relative");
	}
	
	/** Returns a string representation of the stack. Each string is separated by a newline. Returns an empty string if the stack is empty. */
	public String toString() {
		String result = "";
		if (stack.length <= 0) return "";
		else {
			for (int k = stack.length-1 ; k >= 0 ; k--){
				result += stack[k];
				result += '\n';
				//System.out.print(stack[k]);
				//System.out.println();				
			}
		}
		return result;
		//throw new RuntimeException("Rope is thicker but String is quicker");
	}
}
