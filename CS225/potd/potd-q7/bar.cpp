// your code here
#include "bar.h"
#include "foo.h"
#include <string>

using namespace potd;
using namespace std;

bar::bar(string s){
  this->f_ = new foo(s);
}
bar::bar(const bar &that){
    this->set_foo(new foo(that.get_foo()->get_name()));
}
bar::~bar(){
  delete this->f_;
}
bar & bar::operator=(const bar & that){
  delete f_;
  foo* re = new foo(that.get_foo()->get_name());
  this->set_foo(re);
    return *this;
    //this->set_foo(that.get_foo());
  //  return *this;
}
string bar::get_name(){
  return f_->get_name();
}
foo* bar::get_foo() const {
  return f_;
}
void bar::set_foo(foo* f){
  this->f_ = f;
}
