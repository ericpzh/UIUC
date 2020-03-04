/**
 * Example Picture Recursion Examples.
 * @author angrave
 *
 */
public class ExampleAsciiPictureRecursion {

	public static void printRepeatedChar(char c, int times) {
		if (times < 1)
			return;
		System.out.print(c);
		printRepeatedChar(c, times - 1);
	}
/*
Inverted Pyramid:
xxxxxxxxx
 xxxxxxx
  xxxxx
   xxx
    x

 */
	public static void invertedPyramid(int indent, int height) {
		if (height > 0) {
			printRepeatedChar(' ', indent);
			printRepeatedChar('x', height * 2 - 1);
			System.out.println();
			invertedPyramid(indent + 1, height - 1);
		}
	}
/*
Pyramid:
    x
   xxx
  xxxxx
 xxxxxxx
xxxxxxxxx
 */	
	public static void pyramid(int indent, int height) {
		if (height > 0) {
			pyramid(indent + 1, height - 1);
			printRepeatedChar(' ', indent);
			printRepeatedChar('x', height * 2 - 1);
			System.out.println();
		}
	}
/*
 Antenna:
x    x    x
 x   x   x
  x  x  x
   x x x
    xxx
 */
	public static void antenna(int indent, int height) {
		if (height > 0) {
			printRepeatedChar(' ', indent);
			System.out.print('x');
			printRepeatedChar(' ', height-1);
			System.out.print('x');
			printRepeatedChar(' ', height-1);
			System.out.println('x');
			antenna(indent + 1, height - 1);
		}
	}
/*
Inverted Antenna:
    xxx
   x x x
  x  x  x
 x   x   x
x    x    x
 */
	public static void invertedAntenna(int indent, int height) {
		if (height > 0) {
			invertedAntenna(indent + 1, height - 1);
			printRepeatedChar(' ', indent);
			System.out.print('x');
			printRepeatedChar(' ', height-1);
			System.out.print('x');
			printRepeatedChar(' ', height-1);
			System.out.println('x');
		}
	}

	/**
     x
     x
     x
     x
     x
    xxx
   x x x
  x  x  x
 x   x   x
x    x    x
	 */
	public static void rocket(int indent, int height) {
		if (height > 0) {
			printRepeatedChar(' ', indent + height);
			System.out.println('x');

			rocket(indent + 1, height - 1);
			
			printRepeatedChar(' ', indent);
			System.out.print('x');
			printRepeatedChar(' ', height-1);
			System.out.print('x');
			printRepeatedChar(' ', height-1);
			System.out.println('x');
		}
	}
/*
 blackHoleSun:
 
height = 9
radius = 4

	 X   X   X
	  X  X  X
	   X X X
	    XXX
	 XXXXXXXXX
	    XXX
	   X X X
	  X  X  X
	 X   X   X
*/
    public static void blackHoleSun(int height, int indent)
	{
	    blackHoleSunHelper(height, indent, height/2);
	}
	
    public static void blackHoleSunHelper(int height, int indent, int radius)
	{
	   
       printRepeatedChar(' ', indent);
	   if (radius == 0)
	   {
	     printRepeatedChar('X', height);
	     System.out.println();
	     return;
	   }

	   printRepeatedChar(' ', height/2 - radius);
	   System.out.print('X');
	   printRepeatedChar(' ', radius - 1);
	   System.out.print('X');
	   printRepeatedChar(' ', radius - 1);
	   System.out.println('X');

	   blackHoleSunHelper(height, indent, radius-1);

	   printRepeatedChar(' ', indent);

	   printRepeatedChar(' ', height/2 - radius);
	   System.out.print('X');
	   printRepeatedChar(' ', radius - 1);
	   System.out.print('X');
	   printRepeatedChar(' ', radius - 1);
	   System.out.println('X');
	}
/*
 * Wavy line:
/////////
 \\\\\\\
  /////
   \\\
    /
*/

    public static void wavyLine(int indent, int length, boolean forward) {
    	if(length < 1)
    		return;
    	
    	printRepeatedChar(' ',indent);
    	char c =  forward ? '/' : '\\';
    	printRepeatedChar(c,length);
    	System.out.println();
    	wavyLine(indent + 1,length - 2, !forward);
    }
	public static void main(String[] args) {
		System.out.println("Inverted Pyramid:");
		invertedPyramid(0, 5);
		
		System.out.println("\nPyramid:");
		pyramid(0,5);
		
		System.out.println("\nAntenna:");
		antenna(0,5);
		
		System.out.println("\nInverted Antenna:");
		invertedAntenna(0,5);

		System.out.println("\nWavy line:");
		wavyLine(0,9,true);
		
		System.out.println("\nRocket:");
		rocket(0,5);
		
		System.out.println("\nBlack Hole Sun:");
		blackHoleSun(9,0);
	}
}
