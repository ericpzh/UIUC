#ifndef THING_H
#define THING_H

#include <string>

namespace potd {
  class Thing {
  public:
    Thing(int size);
    Thing(const Thing & other);
    Thing & operator=(const Thing & other);
    ~Thing();

    int set_property(std::string prop,std::string value);
    std::string get_property(std::string prop);

  private:

    void _copy(const Thing &other);
    void _destroy();

    std::string *properties_;
    std::string *values_;
    int props_ct_;
    int props_max_;
  };
}
#endif
