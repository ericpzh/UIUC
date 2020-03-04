#include "Node.h"
#include <iostream>
using namespace std;

Node *listIntersection(Node *first, Node *second) {
  // your code here
  if(first == NULL || second == NULL){
    return NULL;
  }
  Node* ret = NULL;
  Node* head = NULL;
  int i = 0;
  while(second!=NULL){

    bool flag = false;
    Node* f = first;
    while(f != NULL){
      if(f->data_ == second->data_){
        flag = true;
      }
      f = f->next_;
    }
    if(flag){
      Node* n = new Node();
      n->data_ = second->data_;
      n->next_ = NULL;
      if(i == 0){
        ret = n;
        head = n;
        i++;
      }else{
      ret->next_ = n;
      ret = ret->next_;
    }

    }
    second = second->next_;
    cout<<"i"<<endl;
  }
  return head;
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
