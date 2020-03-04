#include <iterator>
#include <cmath>
#include <list>
#include <stack>

#include "../cs225/PNG.h"
#include "../Point.h"

#include "ImageTraversal.h"
#include "DFS.h"

/**
 * Initializes a depth-first ImageTraversal on a given `png` image,
 * starting at `start`, and with a given `tolerance`.
 */
DFS::DFS(const PNG & png, const Point & start, double tolerance) :pic(png),st(start),tol(tolerance) {
  /** @todo [Part 1] */
}

/**
 * Returns an iterator for the traversal starting at the first point.
 */
ImageTraversal::Iterator DFS::begin() {
  /** @todo [Part 1] */
  DFS* dfs = new DFS(pic,st,tol);
  return ImageTraversal::Iterator(st,pic.width(),pic.height(),dfs);
}

/**
 * Returns an iterator for the traversal one past the end of the traversal.
 */
ImageTraversal::Iterator DFS::end() {
  /** @todo [Part 1] */
  return ImageTraversal::Iterator(true);
}

/**
 * Adds a Point for the traversal to visit at some point in the future.
 */
void DFS::add(const Point & point) {
  /** @todo [Part 1] */
  stk.push(point);
}

/**
 * Removes and returns the current Point in the traversal.
 */
Point DFS::pop() {
  /** @todo [Part 1] */
  Point ret = stk.top();
  stk.pop();
  return ret;
}

/**
 * Returns the current Point in the traversal.
 */
Point DFS::peek() const {
  /** @todo [Part 1] */
  return stk.top();
}

/**
 * Returns true if the traversal is empty.
 */
bool DFS::empty() const {
  /** @todo [Part 1] */
  return stk.empty();
}

double DFS::getTol() const{
  return tol;
}
HSLAPixel* DFS::getPixel(unsigned x,unsigned y) const{
  HSLAPixel* pixel = pic.getPixel(x,y);
  return pixel;
}
HSLAPixel* DFS::getStart() const{
  HSLAPixel* pixel = pic.getPixel(st.x,st.y);
  return pixel;
}
