#include <string>
#include "pet.h"

using namespace std;

// Put your constructor code here!
Pet::Pet(){
  name = "Rover";
  birth_year = 2017;
  type = "dog";
  owner_name = "Cinda";
}
Pet::Pet(string n, int b,string t,string o){
  name = n;
  birth_year = b;
  type = t;
  owner_name = o;
}
void Pet::setName(string newName) {
  name = newName;
}

void Pet::setBY(int newBY) {
  birth_year = newBY;
}

void Pet::setType(string newType) {
  type = newType;
}

void Pet::setOwnerName(string newName) {
  owner_name = newName;
}

string Pet::getName() {
  return name;
}

int Pet::getBY() {
  return birth_year;
}

string Pet::getType() {
  return type;
}

string Pet::getOwnerName() {
  return owner_name;
}
