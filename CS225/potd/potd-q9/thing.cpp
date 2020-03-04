#include "thing.h"
#include <string>

using namespace potd;

Thing::Thing(int size){
  props_max_ = size;
  props_ct_ = 0;
  properties_ = new std::string[size];
  values_ = new std::string[size];
}
Thing::Thing(const Thing & other){
  _copy(other);
}
Thing & Thing::operator=(const Thing &other){
  if(this == &other){
     return *this;
   }
    _destroy();
 _copy(other);

 return *this;
    }
Thing::~Thing(){
  _destroy();
}
int Thing::set_property(std::string prop,std::string value){
  for(int i = 0; i < props_max_;i++){
    if(this->properties_[i] == "" || this->properties_[i] == prop || this->value_[i] == value ){
      this->properties_[i] = prop;
      this->values_[i] = value;
      this->props_ct_++;
      return i;
    }
  }
    return -1;
}
std::string Thing::get_property(std::string prop){
  for(int i = 0; i < props_max_ ; i++){
    if(this->properties_[i].compare(prop) == 0){
      return values_[i];
    }
  }
  return "";
}
void Thing::_copy(const Thing & other){
    props_max_ = other.props_max_;
    props_ct_ = other.props_ct_;
    properties_ = new std::string[props_max_];
    values_ = new std::string[props_max_];
    for(int i = 0; i < props_max_ ; i++){
      this->properties_[i] = other.properties_[i];
      this->values_[i] = other.values_[i];
    }
}
void Thing::_destroy(){
  delete[] values_;
  delete[] properties_;
  props_ct_ = 0;
}
// Your code here!
