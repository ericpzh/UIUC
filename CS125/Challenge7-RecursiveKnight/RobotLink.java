/**
 * 
 * @author 233
 *
 */
public class RobotLink {

	private RobotLink next; 	
	private final Robot robot;

	public Robot getRobot() {
		return robot;
	}
	/** Constructs this link.
	 * @param next ; the next item in the chain (null, if there are no more items).
	 * @param robot ; a single robot (never null).
	 */
	public RobotLink(RobotLink next,Robot robot) {
		this.robot = robot;
		this.next = next;
	}

	/**
	 * Returns the number of entries in the linked list.
	 * @return number of entries.
	 */
	public int count() {
		// BASE CASE; no more recursion required!
		if(next==null) return 1;
		// Recursive case:
		return next.count()+1;
	}
	/**
	 * Counts the number of flying robots in the linked list.
	 * Hint: robot.isFlying is useful here.
	 */
	public int countFlyingRobots() {
		if(next==null && robot.isFlying()) return 1;
		if(next==null) return 0;
		if(robot.isFlying()) return 1+next.countFlyingRobots();
		else return next.countFlyingRobots();
	}
	/**
	 * Counts the number of flying robots upto and excluding a sad robot.
	 * (i.e. do not count the sad robot if it is flying).
	 * If there are no sad robots this function will return the same value as countFlyingRobots().
	 * Hint: robot.isHappy() is useful.
	 */
	public int countFlyingRobotsBeforeSadRobot() {
		if(next==null) {
			if(robot.isHappy()&&robot.isFlying()) return 1;
			else return 0;
		}
		if(!next.robot.isHappy()){
			if(next.robot.isFlying()) return 1;
			else return 0;
		}
		if(robot.isFlying()&&robot.isHappy()) return 1+next.countFlyingRobotsBeforeSadRobot();
		else return next.countFlyingRobotsBeforeSadRobot();
		//TODO zhe shi yi dao ying yu ti
	}
	/** Creates a new LinkedList entry at the end of this linked list.
	 * Recursively finds the last entry then adds a new link to the end.
	 * @param robot - the robot value of the new last link
	 */
	public void append(Robot robot) {
		if(next == null) {
			next = new RobotLink(null,robot);//TODO love love chuan chuan
		}
		else next.append(robot);
	}
	/**
	 * Returns the first flying unhappy robot, or null
	 * if there are not robots that are flying and unhappy.
	 * @return
	 */
	public Robot getFirstFlyingUnhappyRobot() {
		if(robot.isFlying()&&!robot.isHappy()) return robot;
		//if(next==null&&robot.isFlying()&&!robot.isHappy()) return robot;
		if(next==null) return null;
		return next.getFirstFlyingUnhappyRobot();
		
	}
	/**
	 * Returns the last flying unhappy robotn the linked list, or null
	 * if there are not robots that are flying and unhappy.
	 * @return
	 */
	public Robot getLastFlyingUnhappyRobot() {
		if(next==null){
		if(robot.isFlying()&&!robot.isHappy()) return robot;
		else return null;
		}
		if(robot.isFlying()&&!robot.isHappy()&&next.getLastFlyingUnhappyRobot()==null) return robot;
		return next.getLastFlyingUnhappyRobot();
	}
	/**
	 * Returns a reference to the happy most distant explorer.
	 * Returns null if there are no happy robots
	 * @return reference to the most distant happy robot
	 */
	public Robot findHappyRobotFurthestFromHome() {
		if(next==null){
			if(!robot.isHappy()) return null;
		}
		if(robot.isHappy()&&robot.getDistanceFromHome()>=next.robot.getDistanceFromHome()) return robot;
		return next.findHappyRobotFurthestFromHome();
	}
	/**
	 * Returns true if linked list contains the robot.
	 * Hint: Use robot.equals(other).
	 * @param robot
	 * @return
	 */
	public boolean contains(Robot other) {
		if(robot.equals(other)) return true;
		if(next == null) return false;
		return next.contains(other);
	}

	
}
