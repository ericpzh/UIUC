#include "../cs225/HSLAPixel.h"
#include "../Point.h"

#include "ColorPicker.h"
#include "MyColorPicker.h"

using namespace cs225;

/**
 * Picks the color for pixel (x, y).
 */
HSLAPixel MyColorPicker::getColor(unsigned x, unsigned y) {
  HSLAPixel pixel(h,s,l);
  h+=increment;
  s+=increment/360.0;
  l+=increment/360.0;
  if(h >= 360) {h -= 360;}
  if(s >= 1) {s -= 1;}
  if(l >= 1) {l -= 1;}
  /* @todo [Part 3] */
  return pixel;
}
