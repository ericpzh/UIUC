// Your code here! :)
#ifndef STUDENT_H
#define STUDENT_H
#include <string>
using namespace std;
namespace potd{
class student{
public:
  student();
  string get_name();
  void set_name(string n);
  void set_grade(int g);
  int get_grade();
private:
  string  name_;
  int grade_;
};
}
#endif
