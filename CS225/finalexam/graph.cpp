#include "graph.h"
#include "dset.h"
#include <queue>
#include <vector>
using namespace std;

void BFS(Graph g){
  for (auto & i : g.getVertices()){
    g.setVertexLabel(i,"UE");
  }
  for (auto & i : g.getEdges()){
    g.setEdgeLabel(i.source,i.dest,"UE");
  }
  for (auto & i : g.getVertices()){
    if(g.getVertexLabel(i) == "UE"){
      BFS(g,i);
    }
  }
}
void BFS(Graph g, Vertex v){
  queue<Vertex> q;
  q.push(v);
  while(!q.empty()){
    Vertex a = q.top();
    q.pop();
    g.setVertexLabel(a,"V");
    for(auto & i : g.getAdjacent(a)){
      if(g.getVertexLabel(i) == "UE"){
        q.push(i);
        g.setEdgeLabel(a,i,"D");
      }else if(g.getEdgeLabel(a,i) == "UE"){
        g.setEdgeLabel(a,i) = "C";
      }
    }
  }
}
void DFS(Graph g){
  for (auto & i : g.getVertices()){
    g.setVertexLabel(i,"UE");
  }
  for (auto & i : g.getEdges()){
    g.setEdgeLabel(i.source,i.dest,"UE");
  }
  for (auto & i : g.getVertices()){
    if(g.getVertexLabel(i) == "UE"){
      DFS(g,i);
    }
  }
}
void DFS(Graph g, Vertex v){
    g.setVertexLabel(a,"V");
    for(auto & i : g.getAdjacent(a)){
      if(g.getVertexLabel(i) == "UE"){
        g.setEdgeLabel(a,i,"D");
        DFS(g,i);
      }else if(g.getEdgeLabel(a,i) == "UE"){
        g.setEdgeLabel(a,i) = "B";
      }
    }
}

void KruskalMST(Graph g){
  DisjointSets set;
  set.addelements(g.getVertices().size());
  vector<Edge> e = g.getEdges();
  std::sort(e.begin(),e.end());
  g.setVertexLabel(e[0].source, "V");
  int i = 0;
  size_t count = 1;
  while(count < g.getVertices().size()){
    if(set.find(e[i].source) != set.find(e[i].dest)){
      g.setEdgeLabel(e[i].source,e[i].dest,"MST");
      set.setunion(e[i].source,e[i].dest);
      count++;
    }
    i++;
  }
}

void PrimMST(Graph g, Vertex start, Vertex end){
  vector<Edge> e = g.getEdges();
  std::sort(e.begin(),e.end());
  Vertex start = e[0].source;
  for(auto & i : g.getVertices()){
    g.setVertexLabel(i,"B");
  }
  vector<int> d;
  vector<Vertex> p;
  d.resize(g.getVertices().size(),99999);
  p.resize(g.getVertices().size(),99999);
  d[start] = 0;
  vector<Vertex> q = g.getVertices();
  while(!q.empty()){
    int curr = 999999;
    unsigned idx = 0;
    for(unsigned j = 0; j < q.size() ; j++){
      if(d[q[j]] < curr){
        curr = d[q[j]];
        idx = j;
      }
    }
    Vertex m = q[idx];
    g.setVertexLabel(m,"A");
    for(auto & j : g.getAdjacent(m)){
      if(g.getVertexLabel(j) == "B"){
        if(g.getEdgeWeight(m,j) < d[j]){
          d[j] = g.getEdgeWeight(m,j);
          p[j] = m;
        }
      }
    }
    std::swap(q[idx],q[q.size()-1]);
    q.pop_back();
  }
  for(unsigned i = 0 ; i < p.size() ; i++){
    if(p[i] != 99999)
      g.setEdgeLabel(i,p[i],"MST");
  }
}

int Dis(Graph g, Vertex start, Vertex end){
  for(auto & i : g.getVertices()){
    g.setVertexLabel(i,"B");
  }
  vector<int> d;
  vector<Vertex> p;
  d.resize(g.getVertices().size(),99999);
  p.resize(g.getVertices().size(),99999);
  d[start] = 0;
  vector<Vertex> q = g.getVertices();
  while(!q.empty()){
    int curr = 999999;
    unsigned idx = 0;
    for(unsigned j = 0; j < q.size() ; j++){
      if(d[q[j]] < curr){
        curr = d[q[j]];
        idx = j;
      }
    }
    Vertex m = q[idx];
    g.setVertexLabel(m,"A");
    for(auto & j : g.getAdjacent(m)){
      if(g.getVertexLabel(j) == "B"){
        if(1 + d[m] < d[j]){
          d[j] = 1 + d[m];
          p[j] = m;
        }
      }
    }
    std::swap(q[idx],q[q.size()-1]);
    q.pop_back();
  }
  Vertex i = end;
  while(p[i] != 99999){
    g.setEdgeLabel(i,p[i],"MINPATH");
    i = p[i];
  }
  return d[end];
}
