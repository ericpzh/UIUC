#ifndef LAB56FUNC_H
#define LAB56FUNC_H

#include "lab4pkg/lab4.h"
//opencv msg types
#include <image_transport/image_transport.h>
#include <cv_bridge/cv_bridge.h>
#include <sensor_msgs/image_encodings.h>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/highgui/highgui.hpp>
//basic ros msg type
#include <geometry_msgs/Point.h>

#include <vector>
#include <utility>
using namespace std;

// -----------------------------------------------------------------------------------------
// You will edit the two functions in this file (thresholdImage and associateObjects)
// for lab 5.  The functions at the bottom (onMouse function)of this file are not to be edited until lab 6.
// -----------------------------------------------------------------------------------------

static const std::string OPENCV_WINDOW = "Image window";
using namespace cv;

//callback function for opencv onMouse click.
void onMouse(int event, int x, int y, int flags, void* userdata);
/*****************************************************
	*Class defined for both Lab5 and Lab6
 * **************************************************/
class ImageConverter
{
		//node handle
		ros::NodeHandle nh_;
		// initialize cv bridge sub and pub
		image_transport::ImageTransport it_;
		image_transport::Subscriber image_sub_;
		image_transport::Publisher image_pub_; 
		ros::Publisher pub_command;
		ros::Subscriber sub_position;
		ece470_ur3_driver::command driver_msg;
		ros::Subscriber sub_io_states;
		ros::ServiceClient srv_SetIO;
		ur_msgs::SetIO srv;
		int spincount;

	public: 
		//constructor(don't modify)
		ImageConverter();
	  
		//destructor(don't modify)
		~ImageConverter();
	  
		//Subscriber callback function, will be called when there is a new image read by camera
		void imageCb(const sensor_msgs::ImageConstPtr& msg);

		void position_callback(const ece470_ur3_driver::positions::ConstPtr& msg);
		void suction_callback(const ur_msgs::IOStates::ConstPtr& msg);
		//class function for onMouse click
	  	void onClick(int event,int x, int y, int flags, void* userdata);

	private:
	
		//lab5's function, thresholding the image, input a grayscale image and returns a threshholded image
		Mat thresholdImage(Mat gray_img);
	
		//lab5's function, associating image, input a black and white(binary) image and returns an associated image
		Mat associateObjects(Mat bw_img);
		
		void getNumber(Mat bw_img);
		
		int ** pixellabel;
		
		vector<pair<int,int> > centroid;
		
		double height_;
		double width_;
		
};

#endif
