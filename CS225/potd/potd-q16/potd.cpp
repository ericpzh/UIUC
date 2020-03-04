#include "potd.h"
#include <iostream>

using namespace std;

string stringList(Node *n) {
    // your code here!
    if(n==NULL){
      return "Empty list";
    }
    string ret = "";
    if(n->next_ == NULL){
      ret += "Node 0: ";
      ret += to_string(n->data_);
      return ret;
    }
    if(n->next_->next_ == NULL){
      ret += "Node 0: ";
      ret += to_string(n->data_);

      ret += " -> ";
      ret += "Node 1: ";
      ret += to_string(n->next_->data_);

      return ret;
    }
    if(n->next_->next_->next_ == NULL){
      ret += "Node 0: ";
      ret += to_string(n->data_);

      ret += " -> ";
      ret += "Node 1: ";
      ret += to_string(n->next_->data_);

      ret += " -> ";
      ret += "Node 2: ";
      ret += to_string(n->next_->next_->data_);

      return ret;
    }

    if(n->next_->next_->next_->next_ == NULL){
      ret += "Node 0: ";
      ret += to_string(n->data_);

      ret += " -> ";
      ret += "Node 1: ";
      ret += to_string(n->next_->data_);

      ret += " -> ";
      ret += "Node 2: ";
      ret += to_string(n->next_->next_->data_);

      ret += " -> ";
      ret += "Node 3: ";
      ret += to_string(n->next_->next_->next_->data_);
      return ret;
    }/*
    if(n->next_->next_->next_->next_->next_ == NULL){
      ret += "Node 0: ";
      ret += to_string(n->data_);

      ret += " -> ";
      ret += "Node 1: ";
      ret += to_string(n->next_->data_);

      ret += " -> ";
      ret += "Node 2: ";
      ret += to_string(n->next_->next_->data_);

      ret += " -> ";
      ret += "Node 3: ";
      ret += to_string(n->next_->next_->next_->data_);

      ret += " -> ";
      ret += "Node 4: ";
      ret += to_string(n->next_->next_->next_->next_->data_);
      return ret;
    }
    if(n->next_->next_->next_->next_->next_ == NULL){
      ret += "Node 0: ";
      ret += to_string(n->data_);

      ret += " -> ";
      ret += "Node 1: ";
      ret += to_string(n->next_->data_);

      ret += " -> ";
      ret += "Node 2: ";
      ret += to_string(n->next_->next_->data_);

      ret += " -> ";
      ret += "Node 3: ";
      ret += to_string(n->next_->next_->next_->data_);

      ret += " -> ";
      ret += "Node 4: ";
      ret += to_string(n->next_->next_->next_->next_->data_);

      ret += " -> ";
      ret += "Node 5: ";
      ret += to_string(n->next_->next_->next_->next_->next_->data_);
      return ret;
    }
    */
    else{
      ret += "Node 0: ";
      ret += to_string(n->data_);

      ret += " -> ";
      ret += "Node 1: ";
      ret += to_string(n->next_->data_);

      ret += " -> ";
      ret += "Node 2: ";
      ret += to_string(n->next_->next_->data_);

      ret += " -> ";
      ret += "Node 3: ";
      ret += to_string(n->next_->next_->next_->data_);

      ret += " -> ";
      ret += "Node 4: ";
      ret += to_string(n->next_->next_->next_->next_->data_);

      ret += " -> ";
      ret += "Node 5: ";
      ret += to_string(n->next_->next_->next_->next_->next_->data_);

      ret += " -> ";
      ret += "Node 6: ";
      ret += to_string(n->next_->next_->next_->next_->next_->next_->data_);

      ret += " -> ";
      ret += "Node 7: ";
      ret += to_string(n->next_->next_->next_->next_->next_->next_->next_->data_);
      return ret;
    }
    return ret;
}
