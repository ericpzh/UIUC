#ifndef VERTEX_H
#define VERTEX_H
#include <string>
using namespace std;
class Vertex{
public:
  size_t label;
  Vertex(size_t i){
    label = i;
  }
  Vertex(){
    label = -1;
  }
  bool operator==(const Vertex& other) const
  {
      return label == other.label;
  }
};
#endif
