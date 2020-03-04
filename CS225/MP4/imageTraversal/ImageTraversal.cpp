#include <cmath>
#include <iterator>
#include <iostream>

#include "../cs225/HSLAPixel.h"
#include "../cs225/PNG.h"
#include "../Point.h"

#include "ImageTraversal.h"

#include <stack>
#include <queue>
#include <list>
#include <set>
using namespace cs225;
using namespace std;
/**
 * Calculates a metric for the difference between two pixels, used to
 * calculate if a pixel is within a tolerance.
 *
 * @param p1 First pixel
 * @param p2 Second pixel
 */
double ImageTraversal::calculateDelta(const HSLAPixel & p1, const HSLAPixel & p2) {
  double h = fabs(p1.h - p2.h);
  double s = p1.s - p2.s;
  double l = p1.l - p2.l;

  // Handle the case where we found the bigger angle between two hues:
  if (h > 180) { h = 360 - h; }
  h /= 360;

  return sqrt( (h*h) + (s*s) + (l*l) );
}

/**
 * Default iterator constructor.
 */
ImageTraversal::Iterator::Iterator() : pt(Point(0,0)){
  /** @todo [Part 1] */
  visit = new bool*[1];
  visit[0] = new bool[1];
  visit[0][0] = true;
  end = true;
}
ImageTraversal::Iterator::Iterator(bool endP){
  end = endP;
  visit = new bool*[1];
  visit[0] = new bool[1];
}
ImageTraversal::Iterator::Iterator(Point point, unsigned w, unsigned h, ImageTraversal* tr){
  pt = point;
  width = w;
  height = h;
  traversal = tr;
  visit = new bool*[w];
  for(unsigned i = 0; i < w; ++i){
    visit[i] = new bool[h];
  }
  for(unsigned i = 0; i < w ; i++){
    for(unsigned j =0 ;j < h ; j++){
      visit[i][j] = false;
    }
  }
}
ImageTraversal::Iterator::~Iterator(){
  for(unsigned i = 0; i < width; ++i) {
    delete [] visit[i];
  }
  delete [] visit;
}
/**
 * Iterator increment opreator.
 *
 * Advances the traversal of the image.
 */
ImageTraversal::Iterator & ImageTraversal::Iterator::operator++() {
  /** @todo [Part 1] */
  visit[pt.x][pt.y] = true;
  HSLAPixel *start = traversal->getStart();
  HSLAPixel *rightPixel,*downPixel,*leftPixel,*upPixel;
  double tolerance = traversal->getTol();

  if(pt.x < width-1 && !visit[pt.x+1][pt.y]){
    rightPixel = traversal->getPixel(pt.x+1,pt.y);
    if(calculateDelta(*start, *rightPixel) < tolerance){
      traversal->add(Point(pt.x+1,pt.y));
    }
  }
  if(pt.y < height-1 && !visit[pt.x][pt.y+1]){
    downPixel = traversal->getPixel(pt.x,pt.y+1);
    if(calculateDelta(*start, *downPixel) < tolerance ){
      traversal->add(Point(pt.x,pt.y+1));
    }
  }
  if(pt.x != 0 && !visit[pt.x-1][pt.y]){
    leftPixel = traversal->getPixel(pt.x-1,pt.y);
    if(calculateDelta(*start, *leftPixel) < tolerance ){
      traversal->add(Point(pt.x-1,pt.y));
    }
  }
  if(pt.y != 0 && !visit[pt.x][pt.y-1]){
    upPixel = traversal->getPixel(pt.x,pt.y-1);
    if(calculateDelta(*start, *upPixel) < tolerance ){
      traversal->add(Point(pt.x,pt.y-1));
    }
  }
  if(!traversal->empty()){
    while(visit[pt.x][pt.y] && !traversal->empty()){
      pt = traversal->pop();
    }
  }
  if(traversal->empty()){
    end = true;
  }
  return *this;
}

/**
 * Iterator accessor opreator.
 *
 * Accesses the current Point in the ImageTraversal.
 */
Point ImageTraversal::Iterator::operator*() {
  /** @todo [Part 1] */
  return pt;
}

/**
 * Iterator inequality operator.
 *
 * Determines if two iterators are not equal.
 */
bool ImageTraversal::Iterator::operator!=(const ImageTraversal::Iterator &other) {
  /** @todo [Part 1] */
  return !end;
}
