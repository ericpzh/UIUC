/**
 * @file graph_tools.cpp
 * This is where you will implement several functions that operate on graphs.
 * Be sure to thoroughly read the comments above each function, as they give
 *  hints and instructions on how to solve the problems.
 */
#include <iostream>
#include "graph_tools.h"
#include <map>
/**
 * Finds the minimum edge weight in the Graph graph.
 * THIS FUNCTION IS GRADED.
 *
 * @param graph - the graph to search
 * @return the minimum weighted edge
 *
 * @todo Label the minimum edge as "MIN". It will appear blue when
 *  graph.savePNG() is called in minweight_test.
 *
 * @note You must do a traversal.
 * @note You may use the STL stack and queue.
 * @note You may assume the graph is connected.
 *
 * @hint Initially label vertices and edges as unvisited.
 */
int GraphTools::findMinWeight(Graph& graph)
{
    /* Your code here! */
      int curr = 99999999;
      Vertex currV,currW;
      vector<Vertex> vertices = graph.getVertices();
      for(auto &v : vertices){
        graph.setVertexLabel(v,"UNEXPLORED");
      }
      vector<Edge> edges = graph.getEdges();
      for(auto &e : edges){
        graph.setEdgeLabel(e.source,e.dest,"UNEXPLORED");
      }
      for(auto &v : vertices){
        if(graph.getVertexLabel(v) == "UNEXPLORED"){
          queue<Vertex> q;
          graph.setVertexLabel(v,"VISITED");
          q.push(v);
          while (!q.empty()){
            v = q.front();
            q.pop();
            for(auto &w : graph.getAdjacent(v)){
              if(graph.getVertexLabel(w) == "UNEXPLORED"){
                graph.setVertexLabel(w,"VISITED");
                graph.setEdgeLabel(v,w,"DISCOVERY");
                if(graph.getEdgeWeight(v,w) < curr){
                  curr = graph.getEdgeWeight(v,w);
                  currV = v;
                  currW = w;
                }
                q.push(w);
              }else if(graph.getEdgeLabel(v,w) == "UNEXPLORED"){
                graph.setEdgeLabel(v,w,"CROSS");
                if(graph.getEdgeWeight(v,w) < curr){
                  curr = graph.getEdgeWeight(v,w);
                  currV = v;
                  currW = w;
                }
              }
            }
          }
        }
      }
      graph.setEdgeLabel(currV,currW,"MIN");
      return curr;
}

/**
 * Returns the shortest distance (in edges) between the Vertices
 *  start and end.
 * THIS FUNCTION IS GRADED.
 *
 * @param graph - the graph to search
 * @param start - the vertex to start the search from
 * @param end - the vertex to find a path to
 * @return the minimum number of edges between start and end
 *
 * @todo Label each edge "MINPATH" if it is part of the minimum path
 *
 * @note Remember this is the shortest path in terms of edges,
 *  not edge weights.
 * @note Again, you may use the STL stack and queue.
 * @note You may also use the STL's unordered_map, but it is possible
 *  to solve this problem without it.
 *
 * @hint In order to draw (and correctly count) the edges between two
 *  vertices, you'll have to remember each vertex's parent somehow.
 */
int GraphTools::findShortestPath(Graph& graph, Vertex start, Vertex end)
{
    /* Your code here! */
    vector<Vertex> vertices = graph.getVertices();
    for(auto &v : vertices){
      graph.setVertexLabel(v,"UNEXPLORED");
    }
    vector<Edge> edges = graph.getEdges();
    for(auto &e : edges){
      graph.setEdgeLabel(e.source,e.dest,"UNEXPLORED");
    }
    map<Vertex,Vertex> map;
    Vertex v = start;
    queue<Vertex> q;
    graph.setVertexLabel(v,"VISITED");
    q.push(v);
    while (!q.empty()){
      v = q.front();
      q.pop();
      for(auto &w : graph.getAdjacent(v)){
        if(graph.getVertexLabel(w) == "UNEXPLORED"){
          graph.setVertexLabel(w,"VISITED");
          graph.setEdgeLabel(v,w,"DISCOVERY");
          map[w] = v;
          q.push(w);
        }else if(graph.getEdgeLabel(v,w) == "UNEXPLORED"){
          graph.setEdgeLabel(v,w,"CROSS");
        }
      }
    }
    int i = 0;
    Vertex a = start;
    Vertex b = end;
    while(b != a){
      graph.setEdgeLabel(b, map[b], "MINPATH");
      b = map[b];
      i++;
    }
    return i;
}

/**
 * Finds a minimal spanning tree on a graph.
 * THIS FUNCTION IS GRADED.
 *
 * @param graph - the graph to find the MST of
 *
 * @todo Label the edges of a minimal spanning tree as "MST"
 *  in the graph. They will appear blue when graph.savePNG() is called.
 *
 * @note Use your disjoint sets class from MP 7.1 to help you with
 *  Kruskal's algorithm. Copy the files into the libdsets folder.
 * @note You may call std::sort instead of creating a priority queue.
 */
void GraphTools::findMST(Graph& graph)
{
    /* Your code here! */
    DisjointSets set;
    vector<Vertex> vertices = graph.getVertices();
    vector<Edge> edges = graph.getEdges();
    std::sort(edges.begin(),edges.end());
    set.addelements((int)vertices.size());
    int count = 0;
    for(int i = 0; i < (int)edges.size() && count < (int)vertices.size()-1; i++){
      Vertex a = edges[i].source;
      Vertex b = edges[i].dest;
      if(set.find(a) != set.find(b)){
        set.setunion(a,b);
        graph.setEdgeLabel(a,b,"MST");
        count++;
      }
    }
}
