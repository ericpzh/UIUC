// pet.h
#ifndef PET_H
#define PET_H
#include <string>
#include <iostream>
#include "animal.h"
using namespace std;
class Pet : public Animal{
  public:
    Pet();/*{
      type_ = "cat";
      food_ = "fish";
      name_ = "Fluffy";
      owner_name_ = "Cinda";
    }*/
    Pet(string type, string food, string name, string owner);/*{
      type_ = type;
      food_ = food;
      name_ = name;
      owner_name_ = owner;
    }*/
    string getName();/*{
      return name_;
    }*/
    void setName(string name);/*{
      name_ = name;
    }*/
    string getOwnerName();/*{
      return owner_name_;
    }*/
    void setOwnerName(string name);/*{
      owner_name_ = name;
    }*/
    string print();/*{
      string result = "My name is ";
      result += this->getName();
      return result;
    }*/
  private:
    string name_;
    string owner_name_;
};
#endif
