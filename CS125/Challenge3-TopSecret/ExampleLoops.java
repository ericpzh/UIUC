
public class ExampleLoops {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		simpleWhileLoop();
		simpleForLoop();
		multiplicationTable();
	}

	public static void simpleWhileLoop() {
		int time = 100;
		while(time > 0) {
			System.out.print(time);
			time -= 10;
		}
		System.out.println("Lift off! Using While!");
	}

	public static void simpleForLoop() {
		// the test you specify occurs every time before
		// running the main body of the for loop
		
		//for( _initial_  ;  boolean test  ; change  )
		for(int time = 100;time > 0;time -= 10) {
			System.out.print(time);
		}
		System.out.println("Lift off! Using For!");
		
		// You can leave out parts of the for:
		for( ; Math.random() < 0.98 ; ) {
			System.out.println(".");
		}
		// could be replaced with 
		while(Math.random() < 0.98) {
			System.out.println(".");
		}

	}

	public static void multiplicationTable() {
		for(int x=1;x<10;x++) {
			for(int y=1;y<10;y++) {
				System.out.print(x*y);
			}
			System.out.println();
		}
	}

}
