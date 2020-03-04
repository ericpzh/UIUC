#include <string>
#include "cs225/PNG.h"
#include "cs225/HSLAPixel.h"
void rotate(std::string inputFile, std::string outputFile) {
  cs225::PNG* origin = new cs225::PNG();
  cs225::PNG* result = new cs225::PNG();
  origin->readFromFile(inputFile);
  result->resize(origin->width(),origin->height());
  for (unsigned i = 0; i < origin->width() ; i++){
    for(unsigned j = 0; j < origin->height() ; j++){
      cs225::HSLAPixel* ori = origin->getPixel(origin->width()-i-1,origin->height()-j-1);
      cs225::HSLAPixel* res = result->getPixel(i,j);
      res -> h = ori -> h;
      res -> s = ori -> s;
      res -> l = ori -> l;
      res -> a = ori -> a;
    }
  }
  result->writeToFile(outputFile);
  delete origin;
  delete result;
}
