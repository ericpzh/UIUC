/**
 * 
 * @author 233
 *
 */
public class StaticMethodsAreEasy {
// Oh no... Someone removed  the methods but left the comments!!
// Hint: Get the Geocache class working and passing its tests first.

	/**
	 * Returns an array of num geocaches. Each geocache is initialized to a random
	 * (x,y) location.
	 * if num is less than zero, just return an empty array of length 0.
	 * 
	 * @param num
	 *            number of geocaches to create
	 * @return array of newly minted Points
	 */
	public static Geocache[] createGeocaches (int num){
		int k;
		if (num <= 0) {
			Geocache[] result = new Geocache[0];
			return result;
		}
		else {
			Geocache[] result = new Geocache[num];
			for (k = 0; k < num ; k++){
				result[k] = new Geocache(Math.random(),Math.random());
			}
			return result;
		}
		
	}
//write the method here...
	
	/**
	 * Modifies geocaches if the geocache's X value is less than the allowable minimum
	 * value.
	 * 
	 * @param p
	 *            array of geocaches
	 * @param minX
	 *            minimum X value.
	 * @return number of modified geocaches (i.e. x values were too small).
	 */
	//write the method here...
	public static Geocache[] ensureMinimumXValue(Geocache[] p, double minX){
		for (int k = 0; k < p.length ; k++){
			if (p[k].getX() < minX){
				p[k].setX(minX);
			}
		}
		return p;
	}

	/**
	 * Counts the number of geocaches that are equal to the given geocache
	 * Hint: Use geocache.equals() method 
	 * @param p -
	 *            geocache array
	 * @param test -
	 *            test geocache (compared using .equal)
	 * @return number of matching geocaches
	 */
	//write the method here...
	public static int countEqual(Geocache[] p, Geocache test){
		int count = 0;
		for (int k = 0; k < p.length; k++){
			if (p[k].equals(test)){
				count++;
			}
		}
		return count;
	}
}
