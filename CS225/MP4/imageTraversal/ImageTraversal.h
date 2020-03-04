#ifndef IMAGETRAVERSAL_H
#define IMAGETRAVERSAL_H

#include <iterator>
#include "../cs225/HSLAPixel.h"
#include "../cs225/PNG.h"
#include "../Point.h"
#include <stack>
#include <queue>
#include <list>
#include <set>
using namespace cs225;
using namespace std;

/**
 * A base class for traversal algorithms on images.
 *
 * Each derived class must maintain an ordering of points on an image,
 * through calls to the virtual member functions `add` and `pop`.
 *
 * A derived class provides a traversal by returning instances of
 * ImageTraversal::Iterator.
 */
class ImageTraversal {
public:
  /**
   * A forward iterator through an ImageTraversal.
   */
  class Iterator : std::iterator<std::forward_iterator_tag, Point> {
  public:
    Iterator();
    Iterator(bool endP);
    Iterator(Point point, unsigned w, unsigned h, ImageTraversal* tr);

    ~Iterator();

    Iterator & operator++();
    Point operator*();
    bool operator!=(const Iterator &other);
    bool end = false;

  private:
    Point pt;
    unsigned width,height;
    bool** visit;
    ImageTraversal* traversal;
  };

  virtual double getTol() const = 0;
  virtual HSLAPixel* getPixel(unsigned x,unsigned y) const = 0;
  virtual HSLAPixel* getStart() const = 0;
  virtual Iterator begin() = 0;
  virtual Iterator end() = 0;

  virtual void add(const Point & t) = 0;
  virtual Point pop() = 0;
  virtual Point peek() const = 0;
  virtual bool empty() const = 0;

  private:
    static double calculateDelta(const HSLAPixel & p1, const HSLAPixel & p2);
};




#endif
