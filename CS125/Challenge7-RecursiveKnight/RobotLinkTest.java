import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import junit.framework.TestCase;
/**
 * You do not need to modify these tests
 * @author angrave
 */

public class RobotLinkTest extends TestCase {

	private RobotLink robot3, robot2, robot4, fourHappyFlyingRobots;
	private RobotLink robFlyingHappy, robFlying, robGrounded, mixedRobots,
			robGroundSadEnd, robFlyingSadSecond, robFlyingSadFirst;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		checkRobotUnmodified();
		robot4 = new RobotLink(null, new Robot(1, 5, true));
		robot3 = new RobotLink(robot4, new Robot(2, 5, true));
		robot2 = new RobotLink(robot3, new Robot(3, 5, true));
		fourHappyFlyingRobots = new RobotLink(robot2, new Robot(4, 5, true));

		robGroundSadEnd = new RobotLink(null, new Robot(1, -1, false));
		robFlyingSadSecond = new RobotLink(robGroundSadEnd, new Robot(1, 3, false));
		robFlyingHappy = new RobotLink(robFlyingSadSecond, new Robot(1, 5, true));
		robFlyingSadFirst = new RobotLink(robFlyingHappy, new Robot(2, 5, false));
		robFlying = new RobotLink(robFlyingSadFirst, new Robot(3, 2, true));
		robGrounded = new RobotLink(robFlying, new Robot(3, 0, true));
		mixedRobots = new RobotLink(robGrounded, new Robot(0, 1, true));
	}

	/** Checks that Robot has not been modified.*/
	@SuppressWarnings({  "rawtypes" })
	private void checkRobotUnmodified() {
		Field[] fields = Robot.class.getDeclaredFields();
		Method[] methods = Robot.class.getDeclaredMethods();
		Constructor[] ctors = Robot.class.getDeclaredConstructors();
		String msg = "Replace Robot with the original version! (you may copy the contents of Robot_originalVersion.txt)";
		
		assertEquals(msg,3,fields.length);
		assertEquals(msg,6,methods.length);
		assertEquals(msg,2,ctors.length);
		for(int i =0;i< fields.length;i++) {
			Field f = fields[i];
			assertTrue(msg,Modifier.isPrivate( f.getModifiers()));
		}
		String[] methodNames = {"flipMood","isHappy","isFlying","getDistanceFromHome","toString","equals"};
		for(int i=0;i<methods.length;i++) {
			boolean found = false;
			String name = methods[i].getName();
			for(int j =0;j<methodNames.length && !found;j++) {
				
				if(name.equals(methodNames[j])) found=true;
			}
			if(!found) 
				fail(msg+" "+name+" not allowed");
		}
	}


	public void testCount() {
		assertEquals(4, fourHappyFlyingRobots.count());
		assertEquals(7, mixedRobots.count());
	}

	public void testCountFlyingRobots() {
		assertEquals(4, fourHappyFlyingRobots.countFlyingRobots());
		assertEquals(5, mixedRobots.countFlyingRobots());
	}

	public void testCountFlyingRobotsBeforeSadRobot() {
		assertEquals(4, fourHappyFlyingRobots.countFlyingRobotsBeforeSadRobot());

		assertEquals(2, mixedRobots.countFlyingRobotsBeforeSadRobot());
	}

	public void testAppend() {
		Robot newEnd = new Robot(8, 9, true);
		fourHappyFlyingRobots.append(newEnd);
		assertEquals(5, fourHappyFlyingRobots.count());
	}

	public void testGetFirstFlyingUnhappyRobot() {
		assertNull(fourHappyFlyingRobots.getFirstFlyingUnhappyRobot());
		assertEquals(robFlyingSadFirst.getRobot(), mixedRobots.getFirstFlyingUnhappyRobot());
	}

	public void testGetLastFlyingUnhappyRobot() {
		assertEquals(robFlyingSadSecond.getRobot(), mixedRobots.getLastFlyingUnhappyRobot());
		assertNull(fourHappyFlyingRobots.getLastFlyingUnhappyRobot());
	}

	public void testFindHappyRobotFurthestFromHome() {
		assertEquals(new Robot(4, 5, true), fourHappyFlyingRobots
				.findHappyRobotFurthestFromHome());
	}

	public void testContains() {
		Robot r4dup = new Robot(1, 5, true);
		Robot r1dup = new Robot(4, 5, true);
		Robot nope = new Robot(99, 98, false);
		assertTrue(fourHappyFlyingRobots.contains(r1dup));
		assertTrue(fourHappyFlyingRobots.contains(r4dup));
		assertFalse(fourHappyFlyingRobots.contains(nope));
	}

}
