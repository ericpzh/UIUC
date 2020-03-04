#include "Image.h"
#include "cs225/HSLAPixel.h"
#include "cs225/PNG.h"

void Image::lighten(){
  lighten(0.1);
}
void Image::lighten(double amount){
  for(unsigned i = 0 ; i < this->width(); i++){
    for(unsigned j = 0 ; j < this->height(); j++){
        cs225::HSLAPixel* now = this->getPixel(i,j);
        if(now->l + amount > 1){
          now->l = 1;
        }
        else{
          now->l += amount;
        }
    }
  }
}
void Image::darken(){
  darken(0.1);
}
void Image::darken(double amount){
  for(unsigned i = 0 ; i < this->width(); i++){
    for(unsigned j = 0 ; j < this->height(); j++){
        cs225::HSLAPixel* now = this->getPixel(i,j);
        if(now->l - amount < 0){
          now->l = 0;
        }
        else{
          now->l -= amount;
        }
    }
  }
}
void Image::saturate(){
  saturate(0.1);
}
void Image::saturate(double amount){
  for(unsigned i = 0 ; i < this->width(); i++){
    for(unsigned j = 0 ; j < this->height(); j++){
        cs225::HSLAPixel* now = this->getPixel(i,j);
        if(now->s + amount > 1){
          now->s = 1;
        }
        else{
          now->s += amount;
        }
    }
  }
}
void Image::desaturate(){
  desaturate(0.1);
}
void Image::desaturate(double amount){
  for(unsigned i = 0 ; i < this->width(); i++){
    for(unsigned j = 0 ; j < this->height(); j++){
        cs225::HSLAPixel* now = this->getPixel(i,j);
        if(now->s - amount < 0){
          now->s = 0;
        }
        else{
          now->s -= amount;
        }
    }
  }
}
void Image::grayscale(){
  desaturate(1);
}
void Image::illinify(){
  for (unsigned x = 0; x < this->width(); x++) {
    for (unsigned y = 0; y < this->height(); y++) {
      cs225::HSLAPixel *pixel = this->getPixel(x, y);
      if (pixel->h >= 0 && pixel->h <= 113) {
        pixel->h = 11;
      }
      else if(pixel->h >= 293 && pixel->h <= 360){
        pixel->h = 11;
      }
      else{
        pixel->h = 216;
      }
    }
  }
}
void Image::rotateColor(double degrees){
  for(unsigned i = 0 ; i < this->width(); i++){
    for(unsigned j = 0 ; j < this->height(); j++){
        cs225::HSLAPixel* now = this->getPixel(i,j);
        if(now->h + degrees > 360){
          now->h += (degrees - 360);
        }
        else if(now->h + degrees < 0){
          now-> h += (degrees +360);
        }
        else{
          now->h += degrees;
        }
    }
  }
}
void Image::scale(double factor){
  PNG* result = new PNG(this->width()*factor,this->height()*factor);
  for (unsigned x = 0; x < this->width()*factor; x++) {
    for (unsigned y = 0; y < this->height()*factor ; y++) {
        result->getPixel(x,y)->h = this->getPixel(x/factor,y/factor)->h;
        result->getPixel(x,y)->s = this->getPixel(x/factor,y/factor)->s;
        result->getPixel(x,y)->l = this->getPixel(x/factor,y/factor)->l;
        result->getPixel(x,y)->a = this->getPixel(x/factor,y/factor)->a;
    }
  }
  this->resize(this->width()*factor,this->height()*factor);
  for (unsigned x = 0; x < this->width(); x++) {
    for (unsigned y = 0 ;y < this->height() ; y++) {
      this->getPixel(x, y)->h = result->getPixel(x,y)->h;
      this->getPixel(x, y)->s = result->getPixel(x,y)->s;
      this->getPixel(x, y)->l = result->getPixel(x,y)->l;
      this->getPixel(x, y)->a = result->getPixel(x,y)->a;
    }
  }
  delete result;
}
void Image::scale(unsigned w, unsigned h){
  if(w/this->width() < h/this->height()){
    scale(w/(this->width()*1.00000000000000000000000));
  }
  else{
    scale(h/(this->height()*1.00000000000000000000000));
  }
  this->resize(w,h);
}
