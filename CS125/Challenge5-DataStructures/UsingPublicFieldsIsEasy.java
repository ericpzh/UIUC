
/**
 * Complete the class method 'analyze' that takes a SimplePublicPair object as an argument
 * and returns a new SimplePublicTriple object.
 * The SimplePublicTriple needs to set up as follows:
 * x = the minimum value of 'a' and 'b'
 * y = the maximum value of 'a' and 'b'
 * description:a*b=M 
 *   where a,b, and M are replaced with the numerical values of a, b and the multiplication of a and b.
 * Your code will create a SimplePublicTriple, initializes the three fields and return a reference to the SimplePublicTriple object.
 * @author 233
 */
public class UsingPublicFieldsIsEasy {
	private int x;
	private int y;
	String description;
	public static SimplePublicTriple analyze(SimplePublicPair z){
		SimplePublicTriple result = new SimplePublicTriple();
		result.x = Math.min(z.a, z.b);
		result.y = Math.max(z.a, z.b);
		result.description = z.a + "*" + z.b + "=" + z.a*z.b;
		return result;
	}

	
}
