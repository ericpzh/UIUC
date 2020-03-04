/**
 * A program to run a simple survey and report the results. See MovieSurvey.txt
 * for more information. TODO: add your netid to the line below
 * 
 * @author 233
 */
public class MovieSurvey {
	public static void main(String[] arg) {
		System.out.println("Welcome. We're interested in how people are watching movies this year.");
		System.out.println("Thanks for your time. - The WRITERS GUILD OF AMERICA.");
		System.out.println("Please tell us about how you've watched movies in the last month.");
		System.out.println("How many movies have you seen at the cinema?");
		System.out.println("How many movies have you seen using a DVD or VHS player?");
		System.out.println("How many movies have you seen using a Computer?");
		int a;
		int b;
		int c;
		a = TextIO.getlnInt();
		b = TextIO.getlnInt();
		c = TextIO.getlnInt();
		int total = a+b+c;
		System.out.println("Summary: " + a + " Cinema movies, " + b + " DVD/VHS movies, " + c +" Computer movies");
		System.out.println("Total: "+ total + " movies");
		double fa ;
		double fb ;
		fa = a/1.00;
		fa = fa/total;
		fa = fa*100;
		fa = Math.round(fa*100.0)/100.0;
		fb = b+c;
		fb = fb/1.00; 
		fb = fb/total;
		fb = fb*100;
		fb = Math.round(fb*100.0)/100.0;
		System.out.print("Fraction of movies seen at a cinema: ");
		System.out.format("%.2f", fa);
		System.out.print("%");
		System.out.println();
		System.out.print("Fraction of movies seen outside of a cinema: ");
		System.out.format("%.2f", fb);
		System.out.print("%");
		
		
		// TODO: Write your program here
		// Hints :
		// Formatted output
		// http://math.hws.edu/javanotes/c2/s4.html#basics.4.4
		
		// Don't just copy paste the expected output
		// For grading purposes your code may be tested
		// with other input values.
	}
}
