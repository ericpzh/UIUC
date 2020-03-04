#ifndef _EDGE_H_
#define _EDGE_H_

#include <string>
#include <limits.h>
#include "vertex.h"
using std::string;

class Edge
{
  public:
    Vertex source; /**< The source of the edge **/
    Vertex dest; /**< The destination of the edge **/
    int weight; /**< The edge weight (if in a weighed graph) **/
    string label; /**< The edge label **/
    Edge(Vertex u, Vertex v, string lbl)
        : source(u), dest(v), weight(-1), label(lbl)
    { /* nothing */
    }
    Edge(Vertex u, Vertex v, int w, string lbl)
        : source(u), dest(v), weight(w), label(lbl)
    { /* nothing */
    }
    Edge() : source(-1), dest(-1), weight(-1), label("")
    { /* nothing */
    }
    bool operator<(const Edge& other) const
    {
        return weight < other.weight;
    }
};

#endif
