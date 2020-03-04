#ifndef DFS_H
#define DFS_H

#include <iterator>
#include <cmath>
#include <list>
#include <stack>

#include "../cs225/PNG.h"
#include "../Point.h"

#include "ImageTraversal.h"

using namespace cs225;

/**
 * A depth-first ImageTraversal.
 */
class DFS : public ImageTraversal {
public:
  DFS(const PNG & png, const Point & start, double tolerance);

  ImageTraversal::Iterator begin();
  ImageTraversal::Iterator end();

  void add(const Point & point);
  Point pop();
  Point peek() const;
  bool empty() const;
  double getTol() const;
  HSLAPixel* getPixel(unsigned x,unsigned y) const;
  HSLAPixel* getStart() const;
private:
  PNG pic;
  Point st;
  double tol;
  std::stack<Point> stk;
};

#endif
