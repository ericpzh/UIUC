/**
 * 
 * @author 233
 *
 */
public class BinarySearch {
	/** Wrapper method. Just runs the recursive search method below for the entire array*/
	public static boolean search(int[] array, int key) {
		return search(array,key,0,array.length-1);
	}

	/**
	 * Recursive search using Divide and Conquer approach:
	 * The given array is already sorted so we can very quickly discover if the key is in the array or not.
	 * Hint: For the recursive case check the value at (lo+hi)/2
	 * and proceed accordingly.
	 */
	static boolean search(int[] arr, int k, int low, int high) {
		int midlow = (low+high);
		int midhigh = (low+high);
		if (midlow%2 == 0){
			midlow= midlow/2;
			midhigh = midhigh/2;
		}
		else {
			midlow = (midlow-1)/2;
			midhigh = (midhigh+1)/2;
		}
		if(arr[midlow] == k || arr[midhigh] == k) return true;
		if(midlow == low || midhigh==high ||midlow == high || midhigh==low) return false;
		if(arr[midlow] > k) {return search(arr,k,low,midhigh);}
		else {return search(arr,k,midlow,high);}
		//if(midlow == 0 || midhigh = arr.length) if(arr[0] == k || arr[])
		/*
		if( Math.abs(midlow-low) == 1){
			if(k == arr[midlow+1]) return true;
			if(k == arr[midlow]) return true;
			if(k != arr[midlow] && k != arr[midlow+1]) {System.out.println(midlow+"a"+midhigh);return false;}
			
		}
		if (Math.abs(high-midhigh) == 1 ){
			System.out.println("c");
			if(k == arr[midhigh-1]) return true;
			if(k == arr[midhigh]) return true;
			if(k != arr[midhigh] && k != arr[midhigh-1]) {System.out.println(midlow+"b"+low);return false;}
		}
		if(arr[midlow] > k) {System.out.println(k+"c"); return search(arr,k,low,midhigh);}
		else {System.out.println(k+"d");return search(arr,k,midlow,high);}
	*/
	//
	}
}
