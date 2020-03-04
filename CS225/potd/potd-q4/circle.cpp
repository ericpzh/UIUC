#include "circle.h"

double circle::getArea() {
  return 3.14 * r * r;
}

void circle::setRadius(double val) {
  r = val;
}
