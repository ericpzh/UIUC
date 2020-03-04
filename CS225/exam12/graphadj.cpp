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
    for(unsigned i = 0; i < vertexList.size()*2+1 ; i++){
      matrix.push_back(NULL);
    }
  }
  void removeVertex(Vertex v){
    for(size_t i = 0; i < vertexList.size() ; i++){
      if(vertexList[i] == v){
        for(size_t j = 0; j < vertexList.size() ; j++){
          matrix[vertexList.size()*i+j] = NULL;
          matrix[vertexList.size()*j+i] = NULL;
        }
      }
    }
  }
  bool areAdjacent(Vertex v1, Vertex v2){
    int a,b;
    for(size_t i = 0; i < vertexList.size() ; i++){
      if(vertexList[i] == v1){
        a = i;
      }
      if(vertexList[i] == v2){
        b = i;
      }
    }
    return matrix[a*vertexList.size()+b] != NULL;
  }
  vector<Edge> incidentEdges(Vertex v){
    vector<Edge> ret;
    for(size_t i = 0; i < vertexList.size() ; i++){
      if(vertexList[i] == v){
        for(size_t j = 0; j < vertexList.size()/2 ; j++){
          if(matrix[i*vertexList.size()+j] != NULL){
            ret.push_back(*matrix[i*vertexList.size()+j]);
          }
        }
      }
    }
    return ret;
  }
private:
  vector<Edge*> matrix;
  vector<Vertex> vertexList;
  vector<Edge> edgeList;
};
#endif
int main(){
  return 0;
}
