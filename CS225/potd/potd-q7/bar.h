// your code here
#ifndef BAR_H
#define BAR_H
#include "foo.h"
#include <string>
using namespace std;
namespace potd{
  class bar{
  public:
    bar(string s);
    bar(const bar &);
    ~bar();
    bar & operator=(const bar &);
    string get_name();
    foo*  get_foo() const;
    void set_foo(foo* f);
  private:
    foo * f_;
  };
}
#endif
