public class ExampleShiftRightitBitwiseOrAnd {

	// Play with ExampleHexademical first!
	public static void main(String[] args) {
		int redComponent = 0xff0000;
		int greenComponent = 0xff00;
		int blueComponent = 0xff;
		// For these values, adding is the same as bitwise or-ing the bits together
		// Why? Because there are no common bits shared by red,green or blue values
		// It's similar to adding 300 + 70 + 5 = 375 in base ten - the digits
		// are independent.
		int combined1 = redComponent | greenComponent | blueComponent;
		int combined2 = redComponent + greenComponent + blueComponent;
		
		TextIO.putln(combined1 == combined2); // true when combined1,2 do not share any common bits
		
		
		TextIO.putln("Red|Green|Blue: 0x" + Integer.toHexString(combined1)); // 0xffffff

		int start = 0xffddee; // 24 bits (8 bits;8bits;8bits)
		TextIO.putln("\nStart: 0x" + Integer.toHexString(start));
		
		int shiftedRightBy8 = start >> 8; // 0xffdd (we lost the lowest 8 bits)
		
		TextIO.putln("Shifted Right by 8: 0x"
				+ Integer.toHexString(shiftedRightBy8));
		
		int chopped = shiftedRightBy8 & 0xff; // take only the first 8 bits ie.
												// 0xdd
		TextIO.putln("Chopped: 0x" + Integer.toHexString(chopped));
	}

}
