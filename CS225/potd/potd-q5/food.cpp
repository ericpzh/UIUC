#include "food.h"
#include <string>
using namespace std;

food::food(){
  name_="";
  quantity_=0;
}
string food::get_name(){
  return name_;
}
int food::get_quantity(){
  return quantity_;
}
void food::set_name(string s){
  name_ = s;
}
void food::set_quantity(int i){
  quantity_ = i;
}
