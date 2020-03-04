import junit.framework.TestCase;
/**
 * Test file. You do do not need to modify this file.
 * @author angrave
 *
 */
public class PersonTest extends TestCase {
//For this exercise you may assume that persons always have unique names.
	Person j = new Person("John", 1, 'M', null, null);

	Person k = new Person("Karen", 2, 'F', null, null);

	Person g = new Person("George", 12, 'M', null, null);

	Person i = new Person("Ian", 10, 'M', null, null);

	Person f = new Person("Francis", 20, 'M', null, null);

	Person h = new Person("Harrriet", 18, 'F', j, k);

	Person d = new Person("Donna", 32, 'F', g, h);

	Person e = new Person("Eva", 33, 'F', null, i);

	Person c = new Person("Carl", 50, 'M', null, f);

	Person b = new Person("Barbara", 52, 'F', d, e);

	Person a = new Person("Adam", 75, 'M', b, c);

	public void testcount() {

		assertEquals(11,a.count());
		assertEquals(5,d.count() );
		assertEquals(1, k.count());

	}

	public void testCountGender() {

		assertEquals(5, a.countGender('F') );
		assertEquals(6, a.countGender('M') );
		assertEquals(3, b.countGender('M') );
		assertEquals(1, k.countGender('F') );
		assertEquals(0, k.countGender('M') );

	}

	public void testCountGrandChildern() {

		assertEquals(3, a.countGrandChildren() );
		assertEquals(0, i.countGrandChildren() );
		assertEquals(2, d.countGrandChildren() );

	}

	public void testCountMaxGenerations() {

		assertEquals(5,a.countMaxGenerations() );
		assertEquals(4,b.countMaxGenerations() );
		assertEquals(1,j.countMaxGenerations() );

	}

	public void testSearch() {

		Person testPerson = a.search(e.getName(), 5);
		assertEquals(e, testPerson);

		// Same test but max generations is now smaller
		testPerson = a.search(e.getName(), 1);
		assertNull(testPerson);

		testPerson = h.search(e.getName(), 5);
		assertNull(testPerson);

		testPerson = h.search(j.getName(), 2);
		assertEquals(j, testPerson);

		testPerson = i.search(h.getName(), 5);
		assertNull(testPerson);

	}

	protected void tearDown() throws Exception {
		CheckInputOutput.resetInputOutput();
	}
}
