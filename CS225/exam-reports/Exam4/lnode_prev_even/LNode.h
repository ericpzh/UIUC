// Your code here
#ifndef LNODE_H
#define LNODE_H

#include "Node.h"

class LNode : public Node{
public:
  void lookAtMeEven(Node **arr,int n);
  ~LNode();
};
#endif
