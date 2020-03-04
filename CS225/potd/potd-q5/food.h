// Your code here
#ifndef FOOD_H
#define FOOD_H
#include <string>
using namespace std;
class food {
  public:
    food();
    string get_name();
    int get_quantity();
    void set_name(string s);
    void set_quantity(int i);
  private:
    string name_;
    int quantity_;
};

#endif
