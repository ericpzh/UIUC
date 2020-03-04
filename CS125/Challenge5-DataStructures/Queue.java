/**
 * 
 * @author 233
 */
public class Queue {
	private double[] queue = new double[0];
	/** Adds the value to the front of the queue.
	 * Note the queue automatically resizes as more items are added. */
	public void add(double value) {
		//TODO 125 225 douyouzhegea
		double[] result = new double[queue.length+1];
		result[0] = value;
		for(int k = 1 ; k < queue.length+1 ; k++){
			result[k] = queue[k-1];
		}
		queue = new double[queue.length+1];
		for (int i = 0 ; i < queue.length ; i++){
			queue[i] = result[i];
			//System.out.print(queue[i]+"a");
		}
		
		
		//throw new RuntimeException("Don't step on the cracks");
	}
	/** Removes the value from the end of the queue. If the queue is empty, returns 0 */
	public double remove() {
		if (queue.length <= 0) return 0;
		else {
			double a = queue[queue.length-1];
			double[] result = new double[queue.length-1];
			for(int k = 0 ; k < queue.length-1 ; k++){
				result[k] = queue[k];
			}
			queue = new double[queue.length-1];
			for (int i = 0 ; i < queue.length ; i++){
				queue[i] = result[i];
				//System.out.print(queue[i]+"p");
			}
			
			return a;
		}
		//throw new RuntimeException("Grilled Cheese");
	}
	
	/** Returns the number of items in the queue. */
	public int length() {
		return queue.length;
		//throw new RuntimeException("I am not a number; I am free man.");		
	}
	
	/** Returns true iff the queue is empty */
	public boolean isEmpty() {
		if (queue.length == 0) return true;
		else return false;
		//throw new RuntimeException("The butler did it");
	}
	
	/** Returns a comma separated string representation of the queue. */
	public String toString() {
		String result = "";
		if (queue.length == 0) return null;
		else {
			for (int k = queue.length-1; k >= 0 ; k--){
				result += queue[k];
				if (k != 0) result += ",";
			}
		}
		return result;
		//throw new RuntimeException("Daisy daisy daisy");
	}
}
