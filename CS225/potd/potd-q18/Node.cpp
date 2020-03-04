#include "Node.h"
using namespace std;

void sortList(Node **head) {
  // your code here!
  if ((*head) != NULL){
    Node* curr = (*head);
    Node* prev = NULL;
    Node* temp = NULL;
    bool change = false;
    for (int i = 0; i < Node::getNumNodes(); i++){
      while (curr->next_ != NULL){
        temp = curr->next_;
        if (curr->data_ > temp->data_){
          change = true;
          curr->next_ = temp->next_;
          temp->next_ = curr;
          if (prev != NULL)
            prev->next_ = temp;
            prev = temp;
            if ((*head) == curr)
                (*head) = temp;
        }else{
          prev = curr;
          curr = curr->next_;
        }
      }
      if (change == false){
        break;
      }else{
        prev = NULL;
        curr = (*head);
        change = false;
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
