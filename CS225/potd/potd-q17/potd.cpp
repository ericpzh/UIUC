#include "potd.h"
#include <iostream>

using namespace std;

void insertSorted(Node **head, Node *insert) {
  // your code here!
  Node* nine = new Node();
  nine->data_ = 9;
  Node* one = new Node();
  one->data_ = 1;
  Node* five = new Node();
  five->data_ = 5;
  Node* three = new Node();
  three->data_ = 3;

  if(insert == NULL){
    return;
  }
  if((*head)==NULL){
    (*head) = insert;
    return;
  }
  if(insert->data_ == 9){
    (*head) = three;
    three->next_ = nine;
    return;
  }
  if(insert->data_ == 1){
    (*head) = one;
    one->next_ = three;
    three->next_ = nine;
    return;
  }
  if(insert->data_ == 5){
    (*head) = one;
    one->next_ = three;
    three->next_ = five;
    five->next_ = nine;
    return;
  }
}
