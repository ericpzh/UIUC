#include <iostream>
#include "circle.h"
#include "q4.h"

using namespace std;

int main() {
  circle c;
  c.setRadius(5);

  circle *ptr = &c;

  cout<<"In main, the memory address of c is: "<<(&c)<<endl;

  pass_by_value(c);
  pass_by_pointer(ptr);
  pass_by_ref(c);

  return 0;
}
