import junit.framework.TestCase;

/**
 * You do not need to modify these tests
 * @author angrave
 */
public class GridCountingTest extends TestCase {
	
	
	public void testBaseCases() {
		assertEquals(1,GridCounting.count(10,15,10,15));
		assertEquals(0,GridCounting.count(11,15,10,15));
		assertEquals(0,GridCounting.count(10,16,10,15));
	}
	public void testOneStepAway() {
		assertEquals(1,GridCounting.count(9,15,10,15));
		assertEquals(1,GridCounting.count(10,14,10,15));
	}
	
	public void testTwoStepsAway() {
		assertEquals(2,GridCounting.count(8,15,10,15));
		assertEquals(1,GridCounting.count(10,13,10,15));
		assertEquals(2,GridCounting.count(9,14,10,15));//
	}
	
	public void testThreeStepsAway() {
		assertEquals(3,GridCounting.count(7,15,10,15));
		assertEquals(5,GridCounting.count(8,14,10,15));//
		assertEquals(1,GridCounting.count(11,12,11,15));
	}
	
}
