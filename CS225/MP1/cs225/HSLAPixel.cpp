#include "HSLAPixel.h"

namespace cs225{

  HSLAPixel::HSLAPixel(){
    h = 0.0;
    s = 0.0;
    l = 1.0;
    a = 1.0;
  }

  HSLAPixel::HSLAPixel(double hue, double saturation, double luminace){
    h = hue;
    s = saturation;
    l = luminace;
    a = 1.0;
  }
  HSLAPixel::HSLAPixel(double hue, double saturation, double luminace,double alpha){
    h = hue;
    s = saturation;
    l = luminace;
    a = alpha;
  }
}
