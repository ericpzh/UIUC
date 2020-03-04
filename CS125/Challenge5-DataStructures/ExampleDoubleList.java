/** An example Double list that uses an array and an integer counter.
 * 
 * @author angrave
 *
 */
public class ExampleDoubleList {
// Instance Variables:	
	private double[] data = new double[8];
	private int count; // not array entries are valid
	
// Instance Methods:
	public int size() {
		return count;
	}

	public double get(int index) {
		return data[index];
	}

	public double removeAt(int index) {
		double result = data[index];

		for (int i = index; i < count; i++)
			data[i] = data[i + 1];

		count--;
		return result;
	}

	public void add(double value) {
		
		if (count == data.length) {
			// No more space left -create a new array and copy the values across
			double[] old = data;
			this.data = new double[data.length * 2];
			
			for (int i = 0; i < old.length; i++)
				data[i] = old[i];
		}
		
		data[count] = value;
		count++;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExampleDoubleList list1 = new ExampleDoubleList();
		list1.add(10.);
		list1.add(20.);
		list1.add(30.);
		double is10 = list1.get(0);
		double is20 = list1.removeAt(1);

	}

}
