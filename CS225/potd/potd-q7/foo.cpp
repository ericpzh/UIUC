#include "foo.h"
#include <string>

using namespace potd;
using namespace std;

int foo::count_ = 0;

foo::foo(string name) {
    this->name_ = name;
    foo::count_++;
}

foo::foo(const foo &that) {
    this->name_ = that.name_;
    foo::count_++;
}

foo::~foo() {
    foo::count_--;
}

foo & foo::operator=(const potd::foo &that) {
    this->name_ = that.name_;
    foo::count_++;
    return *this;
}

int foo::get_count() {
    return foo::count_;
}

string foo::get_name() {
    return name_;
}
