#ifndef LAB3FUNC_H
#define LAB3FUNC_H


#include <ros/ros.h>
#include <string>
#include <iostream>
#include <cstdio>
#include <unistd.h>
#include <stdlib.h>
#include <eigen3/Eigen/Dense> //sudo apt-get install libeigen3-dev
#include <cmath>


/** for custom msg
 */

#include <ece470_ur3_driver/command.h>
#include <ece470_ur3_driver/positions.h>

#include <ur_msgs/SetIO.h>
#include <ur_msgs/IOStates.h>

#define PI 3.14159265359

using std::cout;
using std::endl;
using std::cin;
using std::string;
using std::atof;
using Eigen::Matrix4f;

using Eigen::Matrix4f;

//function declaration
Matrix4f DH2HT(float a, float alpha, float d, float theta);
std::vector<double> lab_fk(float theta1, float theta2, float theta3, float theta4, float theta5, float theta6);


#endif
