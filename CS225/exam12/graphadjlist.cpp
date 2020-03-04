#include "edge.h"
#include "vertex.h"
#include <vector>
#include <utility>
using namespace std;
#ifndef GRAPH_H
#define GRAPH_H
class graph{
public:
  void insertVertex(Vertex v){
    vector<Edge*> list;
    vertexList.push_back(pair<Vertex,vector<Edge*> >(v,list));
  }
  void removeVertex(Vertex v){
    for(size_t i = 0 ; i < vertexList.size() ; i++){
      if(vertexList[i].first == v){
        for(auto& i : vertexList[i].second){
          i = NULL;
        }
        swap(vertexList[i],vertexList[vertexList.size()-1]);
        vertexList.pop_back();
      }
    }
  }
  bool areAdjacent(Vertex v1, Vertex v2){
    for(size_t i = 0 ; i < vertexList.size() ; i++){
      if(vertexList[i].first == v1){
        for(auto& i : vertexList[i].second){
          if((i->source == v1 && i->dest == v2)||(i->source == v2 && i->dest == v1)){
            return true;
          }
        }
      }
    }
    return false;
  }
  vector<Edge> incidentEdges(Vertex v){
    vector<Edge> ret;
    for(size_t i = 0 ; i < vertexList.size() ; i++){
      if(vertexList[i].first == v){
        for(auto& i : vertexList[i].second){
          ret.push_back(*i);
        }
      }
    }
    return ret;
  }
private:
  vector<pair<Vertex,vector<Edge*> > > vertexList;
  vector<Edge> edgeList;
};
#endif
int main(){
  return 0;
}
