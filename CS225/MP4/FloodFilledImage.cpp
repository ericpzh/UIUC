#include "cs225/PNG.h"
#include <list>
#include <iostream>

#include "colorPicker/ColorPicker.h"
#include "imageTraversal/ImageTraversal.h"

#include "Point.h"
#include "Animation.h"
#include "FloodFilledImage.h"
#include <vector>
using namespace cs225;
using namespace std;
/**
 * Constructs a new instance of a FloodFilledImage with a image `png`.
 *
 * @param png The starting image of a FloodFilledImage
 */
FloodFilledImage::FloodFilledImage(const PNG & png) : pic(png) {
  /** @todo [Part 2] */

}

/**
 * Adds a flood fill operation to the FloodFillImage.  This function must store the operation,
 * which will be used by `animate`.
 *
 * @param traversal ImageTraversal used for this FloodFill operation.
 * @param colorPicker ColorPicker used for this FloodFill operation.
 */
void FloodFilledImage::addFloodFill(ImageTraversal & traversal, ColorPicker & colorPicker) {
  /** @todo [Part 2] */
  tv.push_back(&traversal);
  cv.push_back(&colorPicker);
}

/**
 * Creates an Animation of frames from the FloodFill operations added to this object.
 *
 * Each FloodFill operation added by `addFloodFill` is executed based on the order
 * the operation was added.  This is done by:
 * 1. Visiting pixels within the image based on the order provided by the ImageTraversal iterator and
 * 2. Updating each pixel to a new color based on the ColorPicker
 *
 * While applying the FloodFill to the image, an Animation is created by saving the image
 * after every `frameInterval` pixels are filled.  To ensure a smooth Animation, the first
 * frame is always the starting image and the final frame is always the finished image.
 *
 * (For example, if `frameInterval` is `4` the frames are:
 *   - The initial frame
 *   - Then after the 4th pixel has been filled
 *   - Then after the 8th pixel has been filled
 *   - ...
 *   - The final frame, after all pixels have been filed)
 */
Animation FloodFilledImage::animate(unsigned frameInterval) const {
  Animation animation;
  animation.addFrame(pic);
  for(int j = tv.size()-1; j >= 0 ; j--){
    unsigned i = 0;
    for (ImageTraversal::Iterator it = tv[j]->begin(); !it.end ; ++it) {
      Point p = *it;
      if(i >= frameInterval){
        animation.addFrame(pic);
        i = 0;
      }
      HSLAPixel* pixel = pic.getPixel(p.x,p.y);
      HSLAPixel color = (cv[j])->getColor(p.x,p.y);
      pixel->h =  color.h;
      pixel->s =  color.s;
      pixel->l =  color.l;
      pixel->a =  color.a;
      i++;
    }
  }
  animation.addFrame(pic);
  /** @todo [Part 2] */
  return animation;
}
