#include <vector>
#include "BTreeNode.h"

BTreeNode* find(BTreeNode* root, int key) {
    if(root->is_leaf_){
      for(int i = 0 ; i < root->elements_.size() ; i++){
        if(root->elements_[i] == key){
          return root;
        }
      }
      return NULL;
    }else if(root->children_[0]->is_leaf_){
      for(int i = 0 ; i < root->elements_.size() ; i++){
        if(root->elements_[i] == key){
          return root;
        }
      }
      for(int i = 0 ; i < root->children_[0]->elements_.size() ; i++){
        if(root->children_[0]->elements_[i] == key){
          return root->children_[0];
        }
      }
      if(root->children_.size() > 0){
        for(int i = 0 ; i < root->children_[1]->elements_.size() ; i++){
          if(root->children_[1]->elements_[i] == key){
            return root->children_[1];
          }
        }
      }
      if(root->children_.size() > 1){
        for(int i = 0 ; i < root->children_[2]->elements_.size() ; i++){
          if(root->children_[2]->elements_[i] == key){
            return root->children_[2];
          }
        }
      }
      return NULL;
    }else{
      for(int i = 0 ; i < root->elements_.size() ; i++){
        if(root->elements_[i] == key){
          return root;
        }
      }
      for(int x = 0; x < root->children_.size() ; x++){
        for(int i = 0 ; i < root->children_[x]->elements_.size() ; i++){
          if(root->children_[x]->elements_[i] == key){
            return root->children_[x];
          }
          for(int j = 0 ; j < root->children_[x]->children_.size() ; j++){
            for(int k = 0; k < root->children_[x]->children_[j]->elements_.size();k++){
              if(root->children_[x]->children_[j]->elements_[k] == key){
                return root->children_[x]->children_[j];
              }
            }
          }
        }
      }
      return NULL;
    }
    // Your Code Here
    /*
    int i = 0;
    while( i < root->children_.size() && key > root->elements_[i]){
      i++;
    }
    if(root->elements_[i] == key){
      return root;
    }
    if(root->is_leaf_){
      return NULL;
    }
    return find(root->children_[i],key);
    */
}
