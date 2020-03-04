
/**
 * A program to calculate a factorial. The given code may contain errors. Fix the
 * given code and add additional code to calculate a factorial and pass the unit
 * tests. Hint sometimes an 'int' just 'aint big enough.
 * 
 * @see Factorial-ReadMe.txt for details on how to complete this program.
 * @author 233
 */
public class Factorial {
	public static void main(String[] args) {
		System.out.println("Enter a number between 1 and 20 inclusive.");
		long max = TextIO.getlnInt();
		while (max<1||max>20){
			System.out.println("Enter a number between 1 and 20 inclusive.");
			max = TextIO.getlnInt();
		}
		long a = max;
		long r = 1;
		if (max < 1 || max > 20){
			System.out.println("Enter a number between 1 and 20 inclusive.");
		}
		else{
		while (max >= 1 && max < 21) {
			r = r*max;
			max--;			
		}
		TextIO.putln(a+"! = "+r);
		}
	}
}
