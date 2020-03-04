#include "Image.h"
#include "StickerSheet.h"

using namespace cs225;

int main() {
  Image base;
  base.readFromFile("base.png");

  Image head;
  head.readFromFile("head.png");

  Image cs;
  cs.readFromFile("cs.png");

  Image i;
  i.readFromFile("i.png");

  StickerSheet sheet(base, 5);
  sheet.addSticker(head, 695, 0);
  sheet.addSticker(cs, 0, 700);
  sheet.addSticker(i, 25, 50);



  sheet.render().writeToFile("myImage.png");
  return 0;
}
