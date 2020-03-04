import junit.framework.TestCase;

/**
 * Tests for the Stack class.
 * You do need to modify this file. 
 * @author angrave
 *
 */
public class StackTest extends TestCase {
	// Also... see the comments in the Stack class.
	// To learn more about how each method should work.
	
	public void testPushPeekPop() {
		Stack s = new Stack();
		assertTrue(s.isEmpty());
		assertEquals(0,s.length());
		s.push("Hi");
		assertEquals(1,s.length());
		assertEquals("Hi",s.peek());
		assertEquals("Hi",s.peek());
		assertEquals(1,s.length());
		// Now pop it off the stack
		assertEquals("Hi",s.pop());
		assertEquals(0,s.length());
		assertEquals(null,s.pop());
	}

	public void testTwoStacks() {
		Stack s1 = new Stack();
		Stack s2 = new Stack();
		assertTrue(s1.isEmpty());
		assertEquals(0,s1.length());
		assertTrue(s2.isEmpty());
		assertEquals(0,s2.length());
		
		s1.push("One");
		s1.push("Two");
		assertFalse(s1.isEmpty());
		assertEquals(2,s1.length());
		
		assertTrue(s2.isEmpty());
		assertEquals(0,s2.length());
		assertNull(s2.peek());
		assertNull(s2.pop());
	}
	
	public void testMutliplePush() {
		Stack s = new Stack();
		s.push("One");
		s.push("Two");
		s.push("Three");
		
		assertEquals("Three", s.pop());
		assertEquals("Two", s.pop());
		s.push("Four");
		assertEquals("Four",s.pop());
		assertEquals("One",s.pop());
	}
	
	public void testToString() {
		Stack s = new Stack();
		
		assertEquals(null,s.pop());
		assertEquals(null,s.peek());
		
		s.push("One");
		s.push("Two");
		s.push("Three");
		s.pop();
		
		assertEquals(2,s.length());
		assertEquals("One\nTwo\n",s.toString());
	}
	
	public void testMultiplePopsWhenEmpty() {
		Stack s = new Stack();
		s.push("A");
		s.pop();
		s.pop();
		s.pop();
		s.push("B");
		s.push("C");
		assertEquals("B\nC\n",s.toString());
		assertEquals(2,s.length());
		assertEquals("C",s.peek());
		assertEquals("C",s.pop());
		assertEquals("B",s.peek());
		assertEquals("B",s.pop());
		assertTrue(s.isEmpty());
	}
	
	public void testPublicPrivateModifiers() {
		AutomaticGrader.checkPublicPrivateModifiers(Queue.class);
	}
}
