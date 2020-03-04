#include "lab56pkg/lab56.h"
#include <iostream>
ImageConverter* ic_ptr;




int main(int argc, char** argv)
{ //initialize ros node, don't change this
  ros::init(argc, argv, "lab56node");
  ros::NodeHandle nh_command;





  //initialize video window names
  namedWindow("Image window");
  namedWindow("associate objects");
  namedWindow("gray_scale");
  namedWindow("black and white");

  //declare the class & and assign its address to global pointer
  ImageConverter ic;
  ic_ptr= &ic;

  //initialize the mouse callback
  setMouseCallback("Image window", onMouse);
  setMouseCallback("associate objects", onMouse);
  setMouseCallback("black and white", onMouse);
  setMouseCallback("gray_scale", onMouse);

 // while(ros::ok()){
  ros::spin();

 // }

  return 0;
}

