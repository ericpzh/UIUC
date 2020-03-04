#include <iostream>
using namespace std;

#include "animal.h"

int main() {
  animal a;
  cout<<"Default constructor:"<<endl;
  cout<<"  Type: "<<a.getType()<<", Food: "<<a.getFood()<<endl;
  a.print();
  cout<<endl;

  animal b("horse", "hay");
  cout<<"Two parameter constructor:"<<endl;
  cout<<"  Type: "<<b.getType()<<", Food: "<<b.getFood()<<endl;
  b.print();
  cout<<endl;

  b.setType("snake");
  b.setFood("mouse");
  cout<<"After using setters:"<<endl;
  cout<<"  Type: "<<b.getType()<<", Food: "<<b.getFood()<<endl;
  b.print();
  cout<<endl;

  return 0;
}
