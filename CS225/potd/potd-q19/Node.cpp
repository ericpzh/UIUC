#include "Node.h"

using namespace std;

void mergeList(Node *first, Node *second) {
  // your code here!
  if(first == NULL || second == NULL){
    return;
  }
  if(first->getNumNodes() > second->getNumNodes()){
    while(first != NULL){
      Node* fn = first->next_;
      if(second == NULL){
        first->next_ = fn;
        first = fn;
      }else{
        Node* sn = second->next_;
        first->next_ = second;
        second->next_ = fn;
        first = fn;
        second = sn;
      }
    }
  }else{
    while(second != NULL){
      Node* sn = second->next_;
      if(first == NULL){
        second->next_ = sn;
        second = sn;
      }else{
        Node* fn = first->next_;
        first->next_ = second;
        second->next_ = fn;
        first = fn;
        second = sn;
      }
    }
  }
}

Node::Node() {
    numNodes++;
}

Node::Node(Node &other) {
    this->data_ = other.data_;
    this->next_ = other.next_;
    numNodes++;
}

Node::~Node() {
    numNodes--;
}

int Node::numNodes = 0;
