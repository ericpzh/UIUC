// Your code here! :)
#include "student.h"
using namespace std;
namespace potd{
student::student(){
  name_ = "";
  grade_ = 0;
}
string student::get_name(){
  return name_;
}
void student::set_name(string n){
  name_ = n;
}
void student::set_grade(int g){
  grade_ = g;
}
int student::get_grade(){
  return grade_;
}
}
