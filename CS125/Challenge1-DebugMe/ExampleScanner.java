import java.util.Scanner;


public class ExampleScanner {

	/**
	 * Hint: You'll need to use Scanner for Turings Craft  
	 * because TextIO is not part of the standard Java classes.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter three unique integer values.");
		
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int c = scanner.nextInt();
		
		if(a>b && b>c) System.out.println("You entered three integers in decreasing order.");
		if(a==b && b==c) System.out.println("You entered three integers that are the same!");
		if(a==42 || b==42 || c==42) System.out.println("The answer to life, the universe and everything.");

	}

}
