#include "Node.h"
#include <iostream>
using namespace std;
void push (Node** head_ref, int new_data)
{
    Node* new_node =  (Node*) malloc(sizeof(Node));
    new_node->data_ = new_data;
    new_node->next_ = (*head_ref);
    (*head_ref) = new_node;
}

bool isPresent (Node *head, int data){
    Node *t = head;
    while (t != NULL)
    {
        if (t->data_ == data)
            return 1;
        t = t->next_;
    }
    return 0;
}
Node *listSymmetricDifference(Node *first, Node *second) {
  // your code here
  if(first == NULL && second == NULL){
    return NULL;
  }
  if(first == NULL){
    return second;
  }
  if(second == NULL){
    return first;
  }
  if(first->next_ == NULL && second->next_ == NULL){
    if(first->data_ != second->data_){
      first->next_ = second;
      return first;
    }
    else
      return NULL;
  }

    Node *u = NULL;
    Node *f = first;
    Node *s = second;
    while (f != NULL){
        if (!isPresent(u, f->data_))
          push(&u, f->data_);
        f = f->next_;
    }
    while (s != NULL){
        if (!isPresent(u, s->data_))
            push(&u, s->data_);
        s = s->next_;
    }

    Node *i = NULL;
    f = first;
    while (f != NULL){
        if (isPresent(second, f->data_))
            push (&i, f->data_);
        f = f->next_;
    }

    int j = 0;
    Node* ret = new Node();
    Node* result = ret;
    Node* r = u;
    while(r!=NULL){
      if(!isPresent(i,r->data_)){
        if(j == 0){
          ret -> data_ = r -> data_;
          j++;
        }else{
          Node* n = new Node();
          n->data_ = r -> data_;
          ret -> next_ = n;
          ret = ret->next_;
        }
      }
      r = r->next_;
    }
    return result;
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
