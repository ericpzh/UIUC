import junit.framework.TestCase;

/**
 * Knight Tests. You do not need to modify this file.
 * @author angrave
 *
 */
public class RecursiveKnightTest extends TestCase {

	public void testDoNothing() {
		boolean[][] visited = new boolean [8][8];
		int[][] steps = new int[8][8];
		
		// Base case 1. out-of-range x,y values
		RecursiveKnight.explore(visited, -1, 0, steps,0);
		RecursiveKnight.explore(visited, 8, 0, steps,0);
		RecursiveKnight.explore(visited, 0, -1, steps,0);
		RecursiveKnight.explore(visited, 0, 8, steps,0);
	}
	
	public void testKnightMove() {
		boolean[][] visited = new boolean [8][8];
		visited[0][0] = true;
		int[][] steps = new int[8][8];
		
		RecursiveKnight.explore(visited, 0,0, steps, 0);
		RecursiveKnight.explore(visited, 7,0, steps, 0);
		RecursiveKnight.explore(visited, 0,7, steps, 0);
		RecursiveKnight.explore(visited, 7,7, steps, 0);
		//RecursiveKnight.print(steps, 0);
				
		assertEquals(0, steps[0][0]);
		assertEquals(2, steps[4][0]);

		assertEquals(1, steps[1][2]);
		assertEquals(1, steps[2][1]);
		assertEquals(1, steps[7-1][2]);
		assertEquals(1, steps[7-2][1]);
		assertEquals(1, steps[1][7-2]);
		assertEquals(1, steps[2][7-1]);
		assertEquals(1, steps[7-1][7-2]);
		assertEquals(1, steps[7-2][7-1]);	
	}
	
	public void testKnightMoveDepth2() {
		boolean[][] visited = new boolean [8][8];
		int[][] steps = new int[8][8];
		visited[0][0] = true;
		RecursiveKnight.explore(visited, 0,0, steps, 0);
		//RecursiveKnight.print(steps, 1);
		assertEquals(0, steps[0][0]);
		assertEquals(2, steps[4][0]);
		assertEquals(3, steps[6][1]);

		assertEquals(1, steps[1][2]);
		assertEquals(1, steps[2][1]);
	}
	public void testKnightMoveDepth3() {
		boolean[][] visited = new boolean [8][8];
		int[][] steps = new int[8][8];
		visited[0][0] = true;
		RecursiveKnight.explore(visited, 0,0, steps, 0);
		assertEquals(0, steps[0][0]);
		assertEquals(2, steps[4][0]);
		assertEquals(3, steps[6][1]);

		assertEquals(1, steps[1][2]);
		assertEquals(1, steps[2][1]);
	}
	public void testKnightMoveDepth10() {
		boolean[][] visited = new boolean [8][8];
		int[][] steps = new int[8][8];
		visited[0][0] = true;
		RecursiveKnight.explore(visited, 0,0, steps, 0);
		assertEquals(0, steps[0][0]);
		assertEquals(2, steps[4][0]);
		assertEquals(3, steps[6][1]);

		assertEquals(1, steps[1][2]);
		assertEquals(1, steps[2][1]);
	}
	protected void tearDown() throws Exception {
		CheckInputOutput.resetInputOutput();
	}
}
