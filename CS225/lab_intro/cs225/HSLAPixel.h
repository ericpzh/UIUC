#ifndef HSLAPixel_H
#define HSLAPixel_H

namespace cs225{

class HSLAPixel {
  public:
    HSLAPixel();
    HSLAPixel(double hue,double saturation, double luminace);
    HSLAPixel(double hue,double saturation, double luminace,double alpha);
    double h;
    double s;
    double l;
    double a;
  };
}
#endif
