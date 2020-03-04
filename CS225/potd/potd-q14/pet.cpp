// pet.cpp
#include "pet.h"
#include <string>
#include <iostream>
#include "animal.h"
Pet::Pet(): Animal("cat" , "fish"){
  name_ = "Fluffy";
  owner_name_ = "Cinda";
}
Pet::Pet(string type, string food, string name, string owner) : Animal(type,food){
  name_ = name;
  owner_name_ = owner;
}
string Pet::getName(){
  return name_;
}
void Pet::setName(string name){
  name_ = name;
}
string Pet::getOwnerName(){
  return owner_name_;
}
void Pet::setOwnerName(string name){
  owner_name_ = name;
}
string Pet::print(){
  string result = "My name is ";
  result += this->getName();
  return result;
}
