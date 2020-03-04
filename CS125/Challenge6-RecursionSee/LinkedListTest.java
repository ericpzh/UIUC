import junit.framework.TestCase;

/**
 * Test file. You do do not need to modify this file.
 * @author angrave
 *
 */

public class LinkedListTest extends TestCase {

	public void testGetLetterCount() {
		// "a" -> "cat" -> null
		LinkedList cat = new LinkedList("cat",null);
		assertEquals(3,cat.getLetterCount());

		LinkedList the = new LinkedList("the",cat);
		assertEquals(6,the.getLetterCount());

		LinkedList blank = new LinkedList("",the);
		assertEquals(6,blank.getLetterCount());


	}

	public void testGetLongestWord() {
		// Case #1: Longest word is at the end
		LinkedList longWord = new LinkedList("Encapsulation",null);
		assertEquals("Encapsulation",longWord.getLongestWord());
		
		LinkedList middle = new LinkedList("small",longWord);
		assertEquals("Encapsulation",middle.getLongestWord());

		LinkedList front = new LinkedList("tiny",middle);
		assertEquals("Encapsulation",front.getLongestWord());

		// Case #2: Longest word is in the middle
		LinkedList tail = new LinkedList("tail",null);
		LinkedList longWord2 = new LinkedList("Abstraction",tail);
		LinkedList top = new LinkedList("top",longWord2);
		assertEquals("Abstraction",top.getLongestWord());

	}

	public void testGetSentence() {
		LinkedList tail = new LinkedList("false",null);
		LinkedList middle = new LinkedList("is",tail);
		LinkedList head = new LinkedList("This",middle);
//		System.out.println(head.toString());
		assertEquals("false.",tail.getSentence());
	}
	
	public void testGetReversedSentence() {
		LinkedList tail = new LinkedList("not",null);
		LinkedList middle = new LinkedList("too",tail);
		LinkedList head = new LinkedList("tricky",middle);
		assertEquals("not.",tail.getReversedSentence(""));
		//assertEquals("not too.",middle.getReversedSentence(""));
		assertEquals("not too tricky.",head.getReversedSentence(""));
	}

	public void testCreateLinkedList() {
		String words[] ={"Words","fail","me"};
		LinkedList list = LinkedList.createLinkedList(words);
		assertEquals("Words fail me.",list.getSentence());
			
	}

	public void testContains() {
		String words[] ={"a","picture","paints","a","thousand","words"};
		LinkedList list = LinkedList.createLinkedList(words);
		assertTrue(list.contains("words"));
		assertFalse(list.contains("giraffe"));
	}

	public void testFind() {
		
		LinkedList tail = new LinkedList("one",null);
		LinkedList middle = new LinkedList("to",tail);
		LinkedList head = new LinkedList("one",middle);
		
		assertEquals(head,head.find("one"));
		assertEquals(middle,head.find("to"));
		assertNull(head.find("three"));
		assertEquals(tail,middle.find("one"));
	}

	public void testFindLast() {
		
		LinkedList tail = new LinkedList("one",null);
		LinkedList middle = new LinkedList("to",tail);
		LinkedList head = new LinkedList("one",middle);
		
		assertEquals(tail,head.findLast("one"));
		assertEquals(middle,head.findLast("to"));
		assertNull(head.findLast("three"));
		assertEquals(tail,middle.findLast("one"));
	}

	public void testAppend() {
		LinkedList head = new LinkedList("Recursion",null);
		head.append("for");
		head.append("me");
		head.append("please");
		assertEquals("Recursion for me please.",head.getSentence());
	}
	
	public void testInsertInCorrectPosition() {
		LinkedList originalHead = new LinkedList("B",null);
		LinkedList head = originalHead.insert("C");
		assertTrue(head == originalHead); // expect no change
		head = head.insert("D");
		assertTrue(head == originalHead); // expect no change
		head = head.insert("A");
		assertTrue(head != originalHead); // expect no change
		//System.out.println(head.getword(head));
		assertEquals("A B C D.", head.getSentence());
		assertEquals(4,head.getCount());
	}
	
	protected void tearDown() throws Exception {
		CheckInputOutput.resetInputOutput();
	}
}
