public class ExampleVariables {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int anIntegerUses4BytesOfStorage = 7;

		long iUse8BytesSoCanStoreMuchLargerIntegers = 1000L * 1000L * 1000L * 1000L;

		double floatingPointEightBytesOfStorage = 0.5;

		boolean iCanBeTrueOrFalse = 0 < anIntegerUses4BytesOfStorage;

		// Other Primitives include: float,char, byte
		
		int answer = 4 + 23;
		
		// OK Because all 4byte int values can be stored in a double.
		// What happens is that 27(integer) is converted into a double representation first
		// before being stored in the 8 bytes of 'answer2'
		double answer2 = 4 + 23;
		
		// A Tricky One:
		// Need to cast a double value to int - 
		// tell the compile to trust us that we know what we're doing...
		// It's OK I'm sure that answer2 holds a value that is not too large. ~-2billon .. +2 billion
		int answer3 = (int) answer2; 
		
		// Tricky Too:
		// answer4 is 12 not 12.5
		// Why? First do integer division 25/2 in integer land is 12
		// AFTER division, convert 12 to a floating point number (12.0)
		double answer4 = 25/2; 
		
		// Dividing Integers:
		// This example sets answer5 to 12.5 why?
		// The compiler converts 25 to a double.
		// When there is a calculation with a double type and an integer type
		// The integer (in this case "2") is converted to a double as well
		// Thus 25.0 / 2.0 which is 12.5
		double answer5 = ((double)25) / 2;
		
		// ALL OF THE ABOVE ARE ALSO TRUE FOR VARIABLES:
		int x = 5;
		int y = 7;
		double iAmZero = x/y;
		// convert to a double BEFORE division:
		double iAmNonZero = ((double)x) / y;
	
		// And in reverse:
		double d1 = 11;
		double d2 = 12;
		int iAmZero2 = (int)(d1/d2); // Round down afterwards
		int iAmZero3 = ((int)d1) / ((int)d2); // To integer division
	}
}
