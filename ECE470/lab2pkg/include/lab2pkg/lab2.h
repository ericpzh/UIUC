#ifndef LAB2_H
#define LAB2_H

#include "ros/ros.h"
#include <string>
#include <iostream>
#include <cstdio>
#include <unistd.h>
#include <stdlib.h>

/** for custom msg and srv
 */

//messages for the simple_action_client
//uncomment if you uses trajClient

#include <sensor_msgs/JointState.h>
#include <control_msgs/JointTrajectoryControllerState.h>
#include <control_msgs/FollowJointTrajectoryAction.h>
#include <control_msgs/FollowJointTrajectoryActionGoal.h>
#include <actionlib/client/simple_action_client.h>
typedef actionlib::SimpleActionClient<control_msgs::FollowJointTrajectoryAction> TrajClient;


//messages for student to use.
#include <ece470_ur3_driver/command.h>
#include <ece470_ur3_driver/positions.h>

#include <ur_msgs/SetIO.h>
#include <ur_msgs/IOStates.h>

/** basic setting for using something like cout instead of std::cout everytime.
 */
using std::cout;
using std::endl;
using std::cin;
using std::string;


#endif
