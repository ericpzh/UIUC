#include "lab2pkg/lab2.h"
#define PI 3.14159265359
#define SPIN_RATE 20 /* Hz */
double home[]={140.35*PI/180,-97.17*PI/180,96.20*PI/180,-87.98*PI/180,-89.61*PI/180,20.60*PI/180};

double arr11[]={132.85*PI/180,-84.84*PI/180,107.22*PI/180,-111.26*PI/180,-89.80*PI/180,13.04*PI/180};
double arr12[]={132.67*PI/180,-71.49*PI/180,119.58*PI/180,-136.96*PI/180,-89.80*PI/180,12.76*PI/180};
double arr13[]={132.68*PI/180,-64.26*PI/180,121.97*PI/180,-146.57*PI/180,-89.79*PI/180,12.72*PI/180};
double arr14[]={132.69*PI/180,-56.72*PI/180,122.92*PI/180,-155.09*PI/180,-89.78*PI/180,12.71*PI/180};

double arr21[] = {143.81*PI/180,-91.35*PI/180,114.05*PI/180,-111.68*PI/180,-89.59*PI/180,24.01*PI/180};
double arr22[] = {143.85*PI/180,-76.68*PI/180,127.49*PI/180,-139.79*PI/180,-89.59*PI/180,23.92*PI/180};
double arr23[] = {143.86*PI/180,-69.01*PI/180,129.95*PI/180,-149.92*PI/180,-89.58*PI/180,23.89*PI/180};
double arr24[] = {143.87*PI/180,-59.70*PI/180,131.12*PI/180,-160.40*PI/180,-89.56*PI/180,23.87*PI/180};

double arr31[] = {158.96*PI/180,-93.60*PI/180,116.28*PI/180,-111.85*PI/180,-89.34*PI/180,39.15*PI/180};
double arr32[] = {158.21*PI/180,-78.87*PI/180,130.46*PI/180,-140.75*PI/180,-89.34*PI/180,38.27*PI/180};
double arr33[] = {158.46*PI/180,-70.18*PI/180,132.63*PI/180,-151.62*PI/180,-89.33*PI/180,38.50*PI/180};
double arr34[] = {158.48*PI/180,-60.88*PI/180,133.80*PI/180,-162.07*PI/180,-89.30*PI/180,38.46*PI/180};
double arrv[] = {0, 0, 0, 0, 0, 0};
std::vector<double> QH(home, home + sizeof(home) / sizeof(home[0]));
std::vector<double> Q11(arr11, arr11 + sizeof(arr11) / sizeof(arr11[0]));
std::vector<double> Q12(arr12, arr12 + sizeof(arr12) / sizeof(arr12[0]));
std::vector<double> Q13(arr13, arr13 + sizeof(arr13) / sizeof(arr13[0]));
std::vector<double> Q14(arr14, arr14 + sizeof(arr14) / sizeof(arr14[0]));
std::vector<double> Q21(arr21, arr21 + sizeof(arr21) / sizeof(arr21[0]));
std::vector<double> Q22(arr22, arr22 + sizeof(arr22) / sizeof(arr22[0]));
std::vector<double> Q23(arr23, arr23 + sizeof(arr23) / sizeof(arr23[0]));
std::vector<double> Q24(arr24, arr24 + sizeof(arr24) / sizeof(arr24[0]));
std::vector<double> Q31(arr31, arr31 + sizeof(arr31) / sizeof(arr31[0]));
std::vector<double> Q32(arr32, arr32 + sizeof(arr32) / sizeof(arr32[0]));
std::vector<double> Q33(arr33, arr33 + sizeof(arr33) / sizeof(arr33[0]));
std::vector<double> Q34(arr34, arr34 + sizeof(arr34) / sizeof(arr34[0]));
std::vector<double> v(arrv, arrv + sizeof(arrv) / sizeof(arrv[0]));
std::vector<double> Q[4][3] = {{Q11, Q21, Q31}, {Q12, Q22, Q32}, {Q13, Q23, Q33}, {Q14, Q24, Q34}};
bool isReady = 1;
bool pending = 0;
bool gripReady = 1;

void position_callback(const ece470_ur3_driver::positions::ConstPtr &msg) {
  isReady = msg->isReady;
  pending = msg->pending;
}
void grip_callback(const ur_msgs::IOStates::ConstPtr &msg) {
  gripReady = msg->digital_in_states[0].state;
}
int move_arm(ros::Publisher pub_command, ros::Rate loop_rate,
             std::vector<double> dest, float duration) {
  int error = 0;
  ece470_ur3_driver::command driver_msg;
  driver_msg.destination = dest;
  driver_msg.duration = duration;
  pub_command.publish(driver_msg);
  int spincount = 0;
  while (isReady) {
    ros::spinOnce();
    loop_rate.sleep();
    if (spincount > SPIN_RATE) {
      pub_command.publish(driver_msg);
      ROS_INFO("Just Published again driver_msg");
      spincount = 0;
    }
    spincount++;
  }
  ROS_INFO("waiting for rdy");
  while (!isReady) {
    ros::spinOnce();
    loop_rate.sleep();
  }
  return error;
}
int move_block(ros::Publisher pub_command, ros::Rate loop_rate,
               ros::ServiceClient srv_SetIO, ur_msgs::SetIO srv, int start_loc,
               int start_height, int end_loc, int end_height) {
  int error = 0;
  move_arm(pub_command, loop_rate, Q[0][start_loc], 2);
  move_arm(pub_command, loop_rate, Q[start_height][start_loc], 2);
  srv.request.fun = 1;
  srv.request.pin = 0;     // Digital Output 0
  srv.request.state = 1.0; // Set DO0 on
  if (srv_SetIO.call(srv)) {
    ROS_INFO("True: Switched Suction ON");
  } else {
    ROS_INFO("False");
  }
  move_arm(pub_command, loop_rate, Q[0][start_loc], 2);
  move_arm(pub_command, loop_rate, Q[0][end_loc], 2);
  move_arm(pub_command, loop_rate, Q[end_height][end_loc], 2);
  srv.request.fun = 1;
  srv.request.pin = 0;     // Digital Output 0
  srv.request.state = 0.0; // Set DO0 on
  if (srv_SetIO.call(srv)) {
    ROS_INFO("True: Switched Suction OFF");
  } else {
    ROS_INFO("False");
  }
  move_arm(pub_command, loop_rate, Q[0][end_loc], 2);
  return error;
}

int main(int argc, char **argv){
  int inputdone = 0;
  int Loopcnt = 0;
  ros::init(argc, argv,"lab2node");
  ros::NodeHandle nh;
  ros::Publisher pub_command = nh.advertise<ece470_ur3_driver::command>("ur3/command", 10);
  ros::Subscriber sub_position = nh.subscribe("ur3/position", 1, position_callback);
  ros::Subscriber grip = nh.subscribe("ur_driver/io_states", 1, grip_callback);
  ros::ServiceClient srv_SetIO = nh.serviceClient<ur_msgs::SetIO>("ur_driver/set_io");
  ur_msgs::SetIO srv;
  ece470_ur3_driver::command driver_msg;
  std::string inputString;
  while (!inputdone) {
    std::cout << "Enter Number of Loops <Either 1 2 or 3>";
    std::getline(std::cin, inputString);
    std::cout << "You entered " << inputString << "\n";
    if (inputString == "1") {
      inputdone = 1;
      Loopcnt = 1;
    } else if (inputString == "2") {
      inputdone = 1;
      Loopcnt = 2;
    } else if (inputString == "3") {
      inputdone = 1;
      Loopcnt = 3;
    } else {
    std::cout << "Please just enter the character 1 2 or 3\n\n";
    }
  }
  while (Loopcnt >= 0) {
    int startPos = 2;
    int endPos = 3;
    std::string startString;
    inputdone = 0;
    while (!inputdone) {
      std::cout << "Enter Starting Position <Either 1 2 or 3>";
      std::getline(std::cin, startString);
      std::cout << "You entered " << startString << "\n";
      if (startString == "1") {
        inputdone = 1;
        startPos = 1;
      } else if (startString == "2") {
        inputdone = 1;
        startPos = 2;
      } else if (startString == "3") {
        inputdone = 1;
        startPos = 3;
      } else {
      std::cout << "Please just enter the character 1 2 or 3\n\n";
      }
    }
    std::string endString;
    inputdone = 0;
    while (!inputdone) {
      std::cout << "Enter Ending Position <Either 1 2 or 3>";
      std::getline(std::cin, endString);
      std::cout << "You entered " << endString << "\n";
      if (endString == "1") {
        inputdone = 1;
        endPos = 1;
      } else if (endString == "2") {
        inputdone = 1;
        endPos = 2;
      } else if (endString == "3") {
        inputdone = 1;
        endPos = 3;
      } else {
      std:
        cout << "Please just enter the character 1 2 or 3\n\n";
      }
    }
    while (!ros::ok()) {}; // check if ros is ready for operation
    ROS_INFO("sending Goals");
    ros::Rate loop_rate(SPIN_RATE); // Initialize the rate to publish to ur3/command
    int spincount = 0;
    driver_msg.destination = QH;
    pub_command.publish(driver_msg);
    spincount = 0;
    while (isReady) {
      ros::spinOnce();
      loop_rate.sleep();
      if (spincount >SPIN_RATE) {
        pub_command.publish(driver_msg);
        ROS_INFO("Just Published again driver_msg");
        spincount = 0;
      }
      spincount++;
    }
    move_arm(pub_command, loop_rate, QH, 2);
    //start if
    //2->3
    if (startPos == 2 && endPos == 3) {
      move_arm(pub_command, loop_rate, Q[0][1], 2);
      move_arm(pub_command, loop_rate, Q[1][1], 2);
      srv.request.fun = 1;
      srv.request.pin = 0;     // Digital Output 0
      srv.request.state = 1.0; // Set DO0 on
      if (srv_SetIO.call(srv)) {
        ROS_INFO("True: Switched Suction ON");
      } else {
        ROS_INFO("False");
      }
      move_arm(pub_command, loop_rate, Q[0][1], 2);
      if (gripReady != 1) {
        ROS_INFO("ERROR STOP ROBOT");
        srv.request.fun = 1;
        srv.request.pin = 0;     // Digital Output 0
        srv.request.state = 0.0; // Set DO0 on
        if (srv_SetIO.call(srv)) {
          ROS_INFO("True: Switched Suction OFF");
        } else {
          ROS_INFO("False");
        }
        move_arm(pub_command, loop_rate, QH, 2);
        return 0;
      }
      move_arm(pub_command, loop_rate, Q[0][2], 2);
      move_arm(pub_command, loop_rate, Q[3][2], 2);
      srv.request.fun = 1;
      srv.request.pin = 0;     // Digital Output 0
      srv.request.state = 0.0; // Set DO0 on
      if (srv_SetIO.call(srv)) {
        ROS_INFO("True: Switched Suction OFF");
      } else {
        ROS_INFO("False");
      }
      move_arm(pub_command, loop_rate, Q[0][2], 2);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 1, 2, 0, 3);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 2, 3, 0, 2);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 1, 3, 2, 3);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 0, 2, 1, 3);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 0, 3, 2, 2);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 1, 3, 2, 1);
    }
    //2->1
    else if (startPos == 2 && endPos == 1) {
      move_arm(pub_command, loop_rate, Q[0][1], 2);
      move_arm(pub_command, loop_rate, Q[1][1], 2);
      srv.request.fun = 1;
      srv.request.pin = 0;     // Digital Output 0
      srv.request.state = 1.0; // Set DO0 on
      if (srv_SetIO.call(srv)) {
        ROS_INFO("True: Switched Suction ON");
      } else {
        ROS_INFO("False");
      }
      move_arm(pub_command, loop_rate, Q[0][1], 2);
      if (gripReady != 1) {
        ROS_INFO("ERROR STOP ROBOT");
        srv.request.fun = 1;
        srv.request.pin = 0;     // Digital Output 0
        srv.request.state = 0.0; // Set DO0 on
        if (srv_SetIO.call(srv)) {
          ROS_INFO("True: Switched Suction OFF");
        } else {
          ROS_INFO("False");
        }
        move_arm(pub_command, loop_rate, QH, 2);
        return 0;
      }
      move_arm(pub_command, loop_rate, Q[0][0], 2);
      move_arm(pub_command, loop_rate, Q[3][0], 2);
      srv.request.fun = 1;
      srv.request.pin = 0;     // Digital Output 0
      srv.request.state = 0.0; // Set DO0 on
      if (srv_SetIO.call(srv)) {
        ROS_INFO("True: Switched Suction OFF");
      } else {
        ROS_INFO("False");
      }
      move_arm(pub_command, loop_rate, Q[0][0], 2);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 1, 2, 2, 3);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 0, 3, 2, 2);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 1, 3, 0, 3);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 2, 2, 1, 3);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 2, 3, 0, 2);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 1, 3, 0, 1);
    }
    //3->1
    else if (startPos == 3 && endPos == 1) {
      move_arm(pub_command, loop_rate, Q[0][2], 2);
      move_arm(pub_command, loop_rate, Q[1][2], 2);
      srv.request.fun = 1;
      srv.request.pin = 0;     // Digital Output 0
      srv.request.state = 1.0; // Set DO0 on
      if (srv_SetIO.call(srv)) {
        ROS_INFO("True: Switched Suction ON");
      } else {
        ROS_INFO("False");
      }
      move_arm(pub_command, loop_rate, Q[0][2], 2);
      if (gripReady != 1) {
        ROS_INFO("ERROR STOP ROBOT");
        srv.request.fun = 1;
        srv.request.pin = 0;     // Digital Output 0
        srv.request.state = 0.0; // Set DO0 on
        if (srv_SetIO.call(srv)) {
          ROS_INFO("True: Switched Suction OFF");
        } else {
          ROS_INFO("False");
        }
        move_arm(pub_command, loop_rate, QH, 2);
        return 0;
      }
      move_arm(pub_command, loop_rate, Q[0][0], 2);
      move_arm(pub_command, loop_rate, Q[3][0], 2);
      srv.request.fun = 1;
      srv.request.pin = 0;     // Digital Output 0
      srv.request.state = 0.0; // Set DO0 on
      if (srv_SetIO.call(srv)) {
        ROS_INFO("True: Switched Suction OFF");
      } else {
        ROS_INFO("False");
      }
      move_arm(pub_command, loop_rate, Q[0][0], 2);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 2, 2, 1, 3);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 0, 3, 1, 2);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 2, 3, 0, 3);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 1, 2, 2, 3);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 1, 3, 0, 2);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 2, 3, 0, 1);
    }
    //3->2
    else if (startPos == 3 && endPos == 2) {
      move_arm(pub_command, loop_rate, Q[0][2], 2);
      move_arm(pub_command, loop_rate, Q[1][2], 2);
      srv.request.fun = 1;
      srv.request.pin = 0;     // Digital Output 0
      srv.request.state = 1.0; // Set DO0 on
      if (srv_SetIO.call(srv)) {
        ROS_INFO("True: Switched Suction ON");
      } else {
        ROS_INFO("False");
      }
      move_arm(pub_command, loop_rate, Q[0][2], 2);
      if (gripReady != 1) {
        ROS_INFO("ERROR STOP ROBOT");
        srv.request.fun = 1;
        srv.request.pin = 0;     // Digital Output 0
        srv.request.state = 0.0; // Set DO0 on
        if (srv_SetIO.call(srv)) {
          ROS_INFO("True: Switched Suction OFF");
        } else {
          ROS_INFO("False");
        }
        move_arm(pub_command, loop_rate, QH, 2);
        return 0;
      }
      move_arm(pub_command, loop_rate, Q[0][1], 2);
      move_arm(pub_command, loop_rate, Q[3][1], 2);
      srv.request.fun = 1;
      srv.request.pin = 0;     // Digital Output 0
      srv.request.state = 0.0; // Set DO0 on
      if (srv_SetIO.call(srv)) {
        ROS_INFO("True: Switched Suction OFF");
      } else {
        ROS_INFO("False");
      }
      move_arm(pub_command, loop_rate, Q[0][1], 2);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 2, 2, 0, 3);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 1, 3, 0, 2);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 2, 3, 1, 3);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 0, 2, 2, 3);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 0, 3, 1, 2);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 2, 3, 1, 1);
    }
    //1->2
    else if (startPos == 1 && endPos == 2) {
      move_arm(pub_command, loop_rate, Q[0][0], 2);
      move_arm(pub_command, loop_rate, Q[1][0], 2);
      srv.request.fun = 1;
      srv.request.pin = 0;     // Digital Output 0
      srv.request.state = 1.0; // Set DO0 on
      if (srv_SetIO.call(srv)) {
        ROS_INFO("True: Switched Suction ON");
      } else {
        ROS_INFO("False");
      }
      move_arm(pub_command, loop_rate, Q[0][0], 2);
      if (gripReady != 1) {
        ROS_INFO("ERROR STOP ROBOT");
        srv.request.fun = 1;
        srv.request.pin = 0;     // Digital Output 0
        srv.request.state = 0.0; // Set DO0 on
        if (srv_SetIO.call(srv)) {
          ROS_INFO("True: Switched Suction OFF");
        } else {
          ROS_INFO("False");
        }
        move_arm(pub_command, loop_rate, QH, 2);
        return 0;
      }
      move_arm(pub_command, loop_rate, Q[0][1], 2);
      move_arm(pub_command, loop_rate, Q[3][1], 2);
      srv.request.fun = 1;
      srv.request.pin = 0;     // Digital Output 0
      srv.request.state = 0.0; // Set DO0 on
      if (srv_SetIO.call(srv)) {
        ROS_INFO("True: Switched Suction OFF");
      } else {
        ROS_INFO("False");
      }
      move_arm(pub_command, loop_rate, Q[0][1], 2);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 0, 2, 2, 3);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 1, 3, 2, 2);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 0, 3, 1, 3);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 2, 2, 0, 3);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 2, 3, 1, 2);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 0, 3, 1, 1);
    }
    //1->3
    else if (startPos == 1 && endPos == 3) {
      move_arm(pub_command, loop_rate, Q[0][0], 2);
      move_arm(pub_command, loop_rate, Q[1][0], 2);
      srv.request.fun = 1;
      srv.request.pin = 0;     // Digital Output 0
      srv.request.state = 1.0; // Set DO0 on
      if (srv_SetIO.call(srv)) {
        ROS_INFO("True: Switched Suction ON");
      } else {
        ROS_INFO("False");
      }
      move_arm(pub_command, loop_rate, Q[0][0], 2);
      if (gripReady != 1) {
        ROS_INFO("ERROR STOP ROBOT");
        srv.request.fun = 1;
        srv.request.pin = 0;     // Digital Output 0
        srv.request.state = 0.0; // Set DO0 on
        if (srv_SetIO.call(srv)) {
          ROS_INFO("True: Switched Suction OFF");
        } else {
          ROS_INFO("False");
        }
        move_arm(pub_command, loop_rate, QH, 2);
        return 0;
      }
      move_arm(pub_command, loop_rate, Q[0][2], 2);
      move_arm(pub_command, loop_rate, Q[3][2], 2);
      srv.request.fun = 1;
      srv.request.pin = 0;     // Digital Output 0
      srv.request.state = 0.0; // Set DO0 on
      if (srv_SetIO.call(srv)) {
        ROS_INFO("True: Switched Suction OFF");
      } else {
        ROS_INFO("False");
      }
      move_arm(pub_command, loop_rate, Q[0][2], 2);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 0, 2, 1, 3);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 2, 3, 1, 2);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 0, 3, 2, 3);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 1, 2, 0, 3);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 1, 3, 2, 2);
      move_block(pub_command, loop_rate, srv_SetIO, srv, 0, 3, 2, 1);
    }
    else{
      ROS_INFO("Error. Please try agian");
      Loopcnt++;
    }
    //endif
    move_arm(pub_command, loop_rate, QH, 2);
    Loopcnt--;
  }
  return 0;
}
