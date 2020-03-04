public class ExampleCharArrays {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char[] array1 = createSimpleArray(100, 4);
		char[][] array2 = create2DDiagonal(10);
	}

	/**
	 * Creates and returns a new array filled with spaces and exclamation
	 * points.
	 * 
	 * @param len
	 *            length of the array
	 * @param repeatEvery
	 *            Exclamation point at index
	 *            0,repeatEvery,2repeatEvery,3repeatEvery ...
	 */
	public static char[] createSimpleArray(int len, int repeatEvery) {
		char[] myarray = new char[len];
		int pos = 0;
		while (pos < len) {
			if ((pos % repeatEvery) == 0)
				myarray[pos] = '!';
			else
				myarray[pos] = ' ';
			pos++;
		}
		return null;
	}

	/**
	 * Creates a 2 dimensional char array of size len x len. The leading
	 * diagonal entries are set to an '*' character. All other entries are a
	 * lowercase 'x'.
	 * 
	 * @param len
	 *            size of each dimension.
	 * @return the newly minted array.
	 */
	public static char[][] create2DDiagonal(int len) {
		char[][] result = new char[len][len];
		for (int x = 0; x < len; x++) {
			for (int y = 0; y < len; y++) {
				if (x == y)
					result[y][x] = '*';
				else
					result[y][x] = 'x';
			}
		}
		return result;
	}

}
