import junit.framework.TestCase;

/**
 * MazeRunner tests. You do not need to modify this file.
 * @author angrave
 *
 */
public class MazeRunnerTest extends TestCase {
	/* ---- NON RECURSIVE METHODS ---- */
	public void testMazeRunnerConstructor() {
		MazeRunner r = new MazeRunner(2, 3);
		assertEquals(2, r.getX());
		assertEquals(3, r.getY());
	}
	public void testMoveOne() {
		MazeRunner r = new MazeRunner(10,100);
		r.moveOne('?');
		assertEquals(10,r.getX() );
		assertEquals(100,r.getY() );
		
		r.moveOne('N');
		assertEquals(10,r.getX() );
		assertEquals(101,r.getY() );
		
		r.moveOne('S');
		assertEquals(10,r.getX() );
		assertEquals(100,r.getY() );
		
		r.moveOne('E');
		assertEquals(11,r.getX() );
		assertEquals(100,r.getY() );
		
		r.moveOne('W');
		assertEquals(10,r.getX() );
		assertEquals(100,r.getY() );
	}
	public void testSafeStringLength() {
		assertEquals(0,MazeRunner.safeStringLength(""));
		assertEquals(1,MazeRunner.safeStringLength("N"));
		// null strings correspond to impossible paths
		// they should have a length of 'infinity' .. well at the least the
		// largest number that an int can store.
		assertEquals(Integer.MAX_VALUE, 
				MazeRunner.safeStringLength(null));
	}
	public void testCaught() {
		MazeRunner r = new MazeRunner(2, 3);
		MazeRunner r1 = new MazeRunner(7, 3);
		MazeRunner r2 = new MazeRunner(2, 7);
		MazeRunner r3 = new MazeRunner(3, 2);
		MazeRunner samePos = new MazeRunner(2, 3);
		assertFalse(r.caught(r1));
		assertFalse(r.caught(r2));
		assertFalse(r.caught(r3));
		assertTrue(r.caught(samePos));
	}
	/* TEST RECURSIVE FUNCTIONS*/
	public void testShortestString() {
		String result0 = "NESWNES";
		String result1 = "SENWE";
		String result2 = null;
		String result3 = "NSSEEW";
	
		String[] paths = {result0,result1,result2,result3};
		
		int shortest = MazeRunner.findShortestString(paths, 0,3);
		assertEquals(1,shortest);
		
		String[] nullpaths = {null,null,null,null};
		int any = MazeRunner.findShortestString(nullpaths, 0,3);
		assertTrue(any>=0 && any<=3);
		
		for(int expected=0; expected <4; expected++) {
			for(int i=0;i<4;i++) {
				int len = 6+(int)(7*(Math.random()));
				paths[i] = "NNNNNNNNNNNNNNNN".substring(0,len);
			}
			paths[(expected + 1) % 4 ] = null;
			paths[expected] = "NNNNN"; // shortest
			int result = MazeRunner.findShortestString(paths, 0, 3);
			assertEquals(expected,result);
		}
		
	}
	public void testShortestPathBaseCases() {
		boolean maze[][] = new boolean[4][4];
		maze[3][2] = true;
		// Out of range cases:
		assertNull(MazeRunner.shortestPath( -1,1,  3,3, maze));
		assertNull(MazeRunner.shortestPath( 1,-1,  3,3, maze));
		assertNull(MazeRunner.shortestPath( 1,5,  3,3, maze));
		assertNull(MazeRunner.shortestPath( 5,1,  3,3, maze));
		// There's a wall at 3,2
		assertNull( MazeRunner.shortestPath( 3,2,  3,2, maze));
		// Already at target position
		assertEquals("" , MazeRunner.shortestPath( 3,1,  3,1, maze));

	}
	
	// helper function for testShortestPathSimple
	// NNEE , EENN , NENE , ENEN are all 
	// the possible responses to the shortest path from 1,1 to 3,3
	private static int countLetters(String path, char dir) {
		if(path == null)return 0;
		int count = 0;
		for(int i=0;i < path.length();i++)
			if(path.charAt(i) == dir)
				count++;
		return count;
	}
	public void testShortestPathSimple() {
		boolean maze[][] = new boolean[4][4];
		String s1 = MazeRunner.shortestPath( 1,1,  3,3, maze);
		//System.out.println(s1);
		assertEquals(4,s1.length());
		assertEquals(2,countLetters(s1,'N'));
		assertEquals(2,countLetters(s1,'E'));	
		
		String s2 = MazeRunner.shortestPath( 2,2,  1,1, maze);
		assertEquals(2,s2.length());
		assertTrue(s2.contains("W"));
		assertTrue(s2.contains("S"));
	}
	
	public void testShortestPathWithMaze1NullPath() {
		boolean maze[][] = { toTF("*****"), toTF("*   *"), toTF("*** *"),
				toTF("* * *"), toTF("*   *"), toTF("*****") };
		assertFalse(maze[1][2]); // test the test; these two should never fail
		assertTrue( maze[4][4]);
		// Should return null because start or end points are on a wall
		// ... so there is no path
		String result1 = MazeRunner.shortestPath( 1,2,  4,4, maze);
		//System.out.println("result1="+result1);
		assertNull(result1);
		String result2 = MazeRunner.shortestPath( 4,4,  1,2, maze);
		assertNull(result2);
	}
	public void testShortestPathWithMaze2Paths() {
		boolean maze[][] = { 
				toTF("*****"),//x0
				toTF("*   *"),//x1	     WEST
				toTF("*** *"),//x2  SOUTH<<    >> NORTH ('y' direction)
				toTF("* * *"),//x3		 EAST
				toTF("*   *"),//x4
				toTF("*****") };
		assertFalse(maze[1][2]);
		assertFalse( maze[3][1]);
		String result1 = MazeRunner.shortestPath( 1,2,  3,1, maze);
		assertEquals("NEEESSW", result1);
		String result2 = MazeRunner.shortestPath( 3,1,  1,2, maze);
		assertEquals("ENNWWWS", result2);
		maze[2][1] = false;
		String result3 = MazeRunner.shortestPath( 3,1,  1,2, maze);
		assertEquals("WWN", result3);	
	}
	
	public void testShortestPathWithBlockedMaze() {
		boolean maze[][] = {{true,false},{false,true}};
		assertFalse(maze[0][1] || maze[1][0]); // test the test: start & end points should be valid
		String nopath= MazeRunner.shortestPath( 0,1, 1,0, maze);
		assertNull(nopath); // no path from 01 to 10
		maze[1][1]=false; // unblock
		String result2= MazeRunner.shortestPath( 0,1, 1,0, maze);
		assertEquals("ES",result2);
	}
	
	/**
	 * chase is not a recursive method - however
	 * it will only work after you've implemented the other methods
	 * chase will move the runner one step closer to the target,
	 * by first finding the shortest path.
	 * Watch out for the special case the the target is the starting position!
	 */
	public void testChase() {
		MazeRunner runner = new MazeRunner(2,1);
		boolean[][] maze = new boolean[4][4];
		maze[1][1]=true;
		runner.chase(maze, 1, 2);
		assertTrue(runner.getX() ==2);
		assertTrue(runner.getY() ==2);
		System.out.print(runner.getX()+"b"+runner.getY());
		runner.chase(maze, 1, 2);
		System.out.print(runner.getX()+"a"+runner.getY());
		assertTrue(runner.getX() ==1);
		assertTrue(runner.getY() ==2);
		runner.chase(maze, 1, 2);
		assertTrue(runner.getX() ==1);
		assertTrue(runner.getY() ==2);
	}


	// Helper method to convert ascii maze to true-false array
	public void testShortestPathWithRealMaze() {
		boolean maze[][] = { 
				toTF("*****"),//x0
				toTF("*   *"),//x1	     WEST
				toTF("*** *"),//x2  SOUTH<<    >> NORTH ('y' direction)
				toTF("* * *"),//x3		 EAST
				toTF("*   *"),//x4
				toTF("*****") };
		assertFalse(maze[1][2]);
		assertFalse( maze[3][1]);
		String result1 = MazeRunner.shortestPath( 1,2,  3,1, maze);
		assertEquals("NEEESSW", result1);
		String result2 = MazeRunner.shortestPath( 3,1,  1,2, maze);
		assertEquals("ENNWWWS", result2);
		maze[2][1] = false;
		String result3 = MazeRunner.shortestPath( 3,1,  1,2, maze);
		assertEquals("WWN", result3);	
	}
	private boolean[] toTF(String s) {
		boolean[] result = new boolean[s.length()];
		for (int i = 0; i < s.length(); i++)
			result[i] = s.charAt(i) != ' ';
		return result;

	}
	protected void tearDown() throws Exception {
		CheckInputOutput.resetInputOutput();
	}

}
