#ifndef STICKERSHEET_H
#define STICKERSHEET_H

#include "Image.h"
#include "cs225/PNG.h"
#include "cs225/HSLAPixel.h"

using namespace std;

class StickerSheet{
  public:
  StickerSheet(const Image &picture, unsigned max);
  ~StickerSheet();
  StickerSheet(const StickerSheet &other);
  const StickerSheet & operator= (const StickerSheet &other);
  void changeMaxStickers(unsigned max);
  unsigned addSticker(Image &sticker, unsigned x,unsigned y);
  bool translate(unsigned index, unsigned x, unsigned y);
  void removeSticker(unsigned index);
  Image * getSticker(unsigned index) const;
  Image render() const;
  unsigned getMax() const;
  private:
    Image* imageArray ;
    unsigned* xArray;
    unsigned* yArray;
    void copy(const StickerSheet &other);
    void clear();
    unsigned max_;
    unsigned count_;
};
#endif
