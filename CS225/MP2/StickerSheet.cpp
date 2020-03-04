#include "StickerSheet.h"
#include "Image.h"
#include "cs225/PNG.h"
#include "cs225/HSLAPixel.h"
#include <iostream>
using namespace std;

StickerSheet::StickerSheet(const Image &picture, unsigned max){
  imageArray = new Image [max+1];
  imageArray[0] = picture;
  xArray = new unsigned [max+1];
  xArray[0] = 0;
  yArray = new unsigned [max+1];
  yArray[0] = 0;
  max_ = max+1;
  count_ = 1;
}
StickerSheet::~StickerSheet(){
  this->clear();
}
StickerSheet::StickerSheet(const StickerSheet &other){
  copy(other);
}
const StickerSheet & StickerSheet::operator= (const StickerSheet &other){
  if (this == &other) {
      return *this;
  }
  this->clear();
  copy(other);
  return *this;
}
void StickerSheet::changeMaxStickers(unsigned max){
  Image *temp = new Image[max+1];
  unsigned *tempx = new unsigned[max+1];
  unsigned *tempy = new unsigned[max+1];

  if(max <= this->getMax()){
    for (unsigned i = 0; i < max+1 ; i++){
      temp[i] = this->imageArray[i];
      tempx[i] = this->xArray[i];
      tempy[i] = this->yArray[i];
    }
    if(count_ > max+1){
      count_ = max+1;
    }
  }
  else{
    for (unsigned i = 0; i < this->getMax() ; i++){
      temp[i] = this->imageArray[i];
      tempx[i] = this->xArray[i];
      tempy[i] = this->yArray[i];
    }
  }
  delete[] imageArray;
  delete[] xArray;
  delete[] yArray;
  imageArray = new Image[max+1];
  xArray = new unsigned[max+1];
  yArray = new unsigned[max+1];
  for (unsigned i = 0; i < max+1 ; i++){
    this->imageArray[i] = temp[i];
    this->xArray[i] = tempx[i];
    this->yArray[i] = tempy[i];

  }
  this -> max_ = max+1;
  delete[] temp;
  delete[] tempx;
  delete[] tempy;
}
unsigned StickerSheet::addSticker(Image &sticker, unsigned x,unsigned y){
  for(unsigned i = 1; i < max_ ; i++){
    if(i > count_-1 || xArray[i] == UINT_MAX){
      imageArray[i] = sticker;
      xArray[i] = x;
      yArray[i] = y;
      count_++;
      return i;
    }
  }
  return -1;
}
bool StickerSheet::translate(unsigned index, unsigned x, unsigned y){
  if(index+1 > max_-1){
    return false;
  }
  else if(index+1 > count_-1 || xArray[index+1] == UINT_MAX){
    return false;
  }
  else{
    xArray[index+1] = x;
    yArray[index+1] = y;
    return true;
  }
}
void StickerSheet::removeSticker(unsigned index){
  if(index+1 < max_-1){

    if(index+1 < max_-2){
      if(index+1 < count_-1){
        //shift remaining image forward + delete last image
        for(unsigned i = index+1; i < max_-1 ; i++){
          if(i < count_-1){
            imageArray[i] = imageArray[i+1];
            xArray[i] = xArray[i+1];
           yArray[i] = yArray[i+1];
          }
          else{
            xArray[i] = UINT_MAX;
            yArray[i] = UINT_MAX;
            break;
          }
        }
      }
    }
    else{
      //delete image
      xArray[index+1] = UINT_MAX;
      yArray[index+1] = UINT_MAX;
    }
    count_ --;
  }
}
Image * StickerSheet::getSticker(unsigned index) const{
  if(index+1 > max_-1){
    return NULL;
  }
  else if(index+1 > count_-1 || xArray[index+1] == UINT_MAX){
    return NULL;
  }
  else{
    return &imageArray[index+1];
  }
}
Image StickerSheet::render() const{
  unsigned* maxx = new unsigned[count_];
  unsigned* maxy = new unsigned[count_];
  for(unsigned i = 0; i < count_ ; i++){
    maxx[i] = imageArray[i].width()+xArray[i];
    maxy[i] = imageArray[i].height()+yArray[i];
  }
  unsigned* maxX = max_element(maxx,maxx+count_);
  unsigned* maxY = max_element(maxy,maxy+count_);
  Image result = Image();
  result.resize(*maxX,*maxY);
  for(unsigned k = 0 ; k < count_ ; k++){
    for(unsigned i = 0 ; i < *maxX ; i++){
      for(unsigned j = 0 ; j < *maxY ; j++){
        if(i < imageArray[k].width() && j < imageArray[k].height() && xArray[k] < UINT_MAX){
          cs225::HSLAPixel* A = imageArray[k].getPixel(i,j);
          cs225::HSLAPixel* B = result.getPixel(i+xArray[k],j+yArray[k]);
          if(A->a > 0){
            B->h = A->h;
            B->s = A->s;
            B->l = A->l;
            B->a = A->a;
          }
        }
      }
    }
  }
  delete[] maxx;
  delete[] maxy;
  return result;
}
unsigned StickerSheet::getMax() const {
  return this->max_;
}
void StickerSheet::copy(const StickerSheet &other){
  imageArray = new Image[other.getMax()];
  xArray = new unsigned [other.getMax()];
  yArray = new unsigned [other.getMax()];
  max_ = other.getMax();
  count_ = other.count_;
  for(unsigned i = 0; i < other.max_ ; i++){
    this->imageArray[i] = other.imageArray[i];
    this->xArray[i] = other.xArray[i];
    this->yArray[i] = other.yArray[i];
  }
}
void StickerSheet::clear(){
  delete[] imageArray;
  delete[] xArray;
  delete[] yArray;
  max_ = -1;
  count_ = -1;
}
