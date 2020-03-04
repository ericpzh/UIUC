#include "lab4pkg/lab4.h"
#include <cmath>
#include <math.h>
using namespace std;
/** 
 * function that calculates an elbow up Inverse Kinematic solution for the UR3
 */
std::vector<double> lab_invk(float xWgrip, float yWgrip, float zWgrip, float yaw_WgripDegree)
{

	double xcen,ycen,zcen,theta6,theta5,theta4,theta3,theta2,theta1,x3end,y3end,z3end; 
	double xgrip,ygrip,zgrip;
	double a1,a2,a3,a4,a5,a6;
	double d1,d2,d3,d4,d5,d6;

	a1 = 0;
	d1 = 0.152;
	a2 = 0.244;
	d2 = 0.120;
	a3 = 0.213;
	d3 = -0.093;
	a4 = 0;
	d4 = 0.083;
	a5 = 0;
	d5 = 0.083;
	a6 = 0.0535;
	d6 = (0.082+0.056);

	xgrip = xWgrip + 0.15;
	ygrip = yWgrip - 0.15;
	zgrip = zWgrip - 0.013;

    xcen= xgrip-a6*cos((yaw_WgripDegree)*PI/180);
    ycen= ygrip-a6*sin((yaw_WgripDegree)*PI/180);
    zcen= zgrip;
    
    theta1 = atan(ycen/xcen)-asin((d2+d3+d4)/sqrt(xcen*xcen+ycen*ycen));
    theta6 = PI-((PI/2-theta1)+(yaw_WgripDegree)*PI/180);


    x3end = cos(-(PI-theta1))*d5-sin(-(PI-theta1))*(d2+d3+d4)+xcen;
    y3end = sin(-(PI-theta1))*d5+cos(-(PI-theta1))*(d2+d3+d4)+ycen;
    z3end = zcen+d6;

    
    double gamma = acos(((x3end*x3end+y3end*y3end+(z3end-d1)*(z3end-d1)-a3*a3-a2*a2)/(-2*a2*a3)));
    theta3 = PI-gamma;
    double beta = asin(sin(theta3)*a3/sqrt(x3end*x3end+y3end*y3end+(z3end-d1)*(z3end-d1)));
    double alpha = atan((z3end-d1)/sqrt(x3end*x3end+y3end*y3end));
    theta2 = -(beta+alpha);
    theta4= -2*PI+PI+(PI-(2*PI-(PI-theta3)-(-theta2)-PI/2));
	theta5=-PI/2;  

	// View values
	//use cout
	cout<<"theta1: "<< theta1<<endl;
	cout<<"theta2: "<< theta2<<endl;
	cout<<"theta3: "<< theta3<<endl;
	cout<<"theta4: "<< theta4<<endl;
	cout<<"theta5: "<< theta5<<endl;
	cout<<"theta6: "<< theta6<<endl;


	// check that your values are good BEFORE sending commands to UR3
	//lab_fk calculates the forward kinematics and convert it to std::vector<double>
	return lab_fk((float)theta1,(float)theta2,(float)theta3,(float)theta4,(float)theta5,(float)theta6);
}
