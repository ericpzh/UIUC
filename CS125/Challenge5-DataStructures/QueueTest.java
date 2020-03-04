import junit.framework.TestCase;

/**
 * Tests for the Queue class.
 * You do not need to modify this file.
 * @author angrave
 *
 */
public class QueueTest extends TestCase {

	// Also... see the comments in the Queue class.
	// To learn more about how each method should work.
	
	public void testAddRemove() {
		Queue q = new Queue();
		assertTrue(q.isEmpty());
		q.add(4.);
		assertFalse(q.isEmpty());
		q.add(5.);
		q.add(6.);
		assertEquals(3,q.length());
		// Remove first one added
		assertEquals(4. ,q.remove());
		assertEquals(5., q.remove());
		q.add(7.);
		assertEquals(2,q.length());
		
		assertEquals(6. ,q.remove());
		assertEquals(7. ,q.remove());
		
		assertEquals(0,q.length());
		assertEquals(0. ,q.remove());
	}

	public void testTwoQueues() {
		Queue q = new Queue();
		Queue q2 = new Queue();
		// This will fail if the internal implementation uses static...
		assertTrue(q.isEmpty());
		assertTrue(q2.isEmpty());
		q2.add(19);
		assertFalse(q2.isEmpty());
		assertTrue(q.isEmpty());
		assertEquals(1,q2.length());
		assertEquals(0,q.length());
	}

	public void testBigAddRemove() {
		Queue q = new Queue();
		int max = 1000;
		for(int i=0; i < max;i++) {
			q.add(i);
			q.add(i+0.5);
			assertEquals(i/2.0, q.remove());
		}
		assertEquals(max,q.length());
	}

	public void testToString() {
		Queue q = new Queue();
		for(int i=1; i < 10;i++)
			q.add(i);
		assertEquals("1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0",q.toString());
	}
	public void testPublicPrivateModifiers() {
		
		AutomaticGrader.checkPublicPrivateModifiers(Queue.class);
	}


}
