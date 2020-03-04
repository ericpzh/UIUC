#ifndef MyColorPicker_H
#define MyColorPicker_H

#include "ColorPicker.h"
#include "../cs225/HSLAPixel.h"
#include "../Point.h"

using namespace cs225;

class MyColorPicker : public ColorPicker {
public:
  MyColorPicker(double i) : increment(i), h(0) , s(0) , l(0){};
  HSLAPixel getColor(unsigned x, unsigned y);

private:
  double increment;
  double h;
  double s;
  double l;
};

#endif
