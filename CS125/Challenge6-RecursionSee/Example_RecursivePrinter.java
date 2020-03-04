public class Example_RecursivePrinter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String message = "Hello!";
		printer(message);
	}

	private static void printer(String s) {
		if(s.length() ==0) {
			System.out.println(" ...Done!"); // Base case
			return;
		}
		// Try swapping the order of the next two lines
		
		System.out.print( s.charAt(0)+","); //First character

		printer(s.substring(1) ); // Recurse on remaining characters 
	}
	
}
