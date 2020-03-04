#include "Node.h"
#include <iostream>

using namespace std;

Node *listUnion(Node *first, Node *second) {
  // your code here
  if(first == NULL && second == NULL){
    return NULL;
  }
  else if(first == NULL){
    return second;
  }
  else if(second == NULL){
    return first;
  }
  Node* temp1 = first;
  Node* temp2 = second;
  bool duplicate = false;
  while (temp2 != NULL){
    cout<<duplicate<<endl;
    duplicate = false;
    while (temp1->next_ != NULL){
      if (temp2 -> data_ == temp1 -> data_){
        duplicate = true;
      }
      temp1 = temp1 -> next_;
      if(temp1->next_ == NULL && temp2 -> data_ == temp1 -> data_){
        duplicate = true;
      }
    }
    if (duplicate == false){
      Node* newNode = new Node();
      newNode -> data_ = temp2 -> data_;
      newNode -> next_ = NULL;
      temp1 -> next_ = newNode;
    }
    temp1 = first;
    temp2 = temp2 -> next_;
  }
  return first;
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
