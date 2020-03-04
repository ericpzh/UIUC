#include "edge.h"
#include "vertex.h"
#include <vector>
using namespace std;
#ifndef GRAPH_H
#define GRAPH_H
class graph{
public:
  void insertVertex(Vertex v){
    vertexList.push_back(v);
  }
  void removeVertex(Vertex v){
    for(auto &i : vertexList){
      if(i == v){
        swap(i,vertexList.back());
        vertexList.pop_back();
      }
    }
    for(auto &i : edgeList){
      if(i.source == v || i.dest == v){
        swap(i,edgeList.back());
        edgeList.pop_back();
      }
    }
  }
  bool areAdjacent(Vertex v1, Vertex v2){
    for(auto &i : edgeList){
      if((i.source == v1 && i.dest == v2) ||(i.source == v2 && i.dest == v1)){
        return true;
      }
    }
    return false;
  }
  vector<Edge> incidentEdges(Vertex v){
    vector<Edge> ret;
    for(auto &i : edgeList){
      if(i.source == v || i.dest == v){
        ret.push_back(i);
      }
    }
  }
private:
  vector<Vertex> vertexList;
  vector<Edge> edgeList;
};
#endif
int main(){
  return 0;
}
