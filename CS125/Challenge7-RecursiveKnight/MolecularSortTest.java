import junit.framework.TestCase;
/**
 * Molecular Sort Tests. You do not need to modify this file.
 * @author angrave
 *
 */
public class MolecularSortTest extends TestCase {

	public void testSortCoordsByZ() {
		double coords[][] = {{10,41,5},{20,42,4},{15,44,3},{11,0,-2}};
		double correct[][] = {{11,0,-2},{15,44,3},{20,42,4},{10,41,5}};

		MolecularSort.sortCoordsByZ(coords);
		assertEquals(correct,coords);
	}

	public void testRecursiveSort() {
		double coords[][] = {{10,41,5},{20,42,4},{15,44,7},{17,45,2},{11,0,-2}};
		double correct[][] = {{10,41,5},{17,45,2},{20,42,4},{15,44,7},{11,0,-2}};
		// ignore first and last entries:
		MolecularSort.recursiveSort(coords, 1, coords.length - 2);
		assertEquals(correct,coords);
	}

	public void testFindIndexOfZMinimum() {
		double coords[][] = {{10,41,80},{20,42,84},{15,44,87},{17,45,82},{11,0,-2}};
		assertEquals(4,MolecularSort.findIndexOfZMinimum(coords, 0, 4));
		assertEquals(4,MolecularSort.findIndexOfZMinimum(coords, 4, 4));
		assertEquals(2,MolecularSort.findIndexOfZMinimum(coords, 2, 2));
	}

	public void testSwap() {
		double coords[][] = {{10,41,80},{20,42,84},{15,44,87},{17,45,82},{11,0,-2}};
		double expected[][] = {{11,0,-2},{20,42,84},{15,44,87},{17,45,82},{10,41,80}};
		MolecularSort.swap(coords, 0, 4);
		assertEquals(expected,coords);
	}

	public void assertEquals(double[]expected, double[]vec) {
		assertEquals(expected.length,vec.length);
		for(int j=0;j<expected.length;j++) 
			assertEquals(expected[j],vec[j]);
	}
	public void assertEquals(double[][]expected,double[][]arr) {
		assertEquals(expected.length, arr.length);
		for(int i=0;i<expected.length;i++) 
			assertEquals(expected[i],arr[i]);
	}
	protected void tearDown() throws Exception {
		CheckInputOutput.resetInputOutput();
	}
}
