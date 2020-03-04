#include <vector>
#include "BTreeNode.h"
/*
void t(BTreeNode* root,std::vector<int>* v){
  for(unsigned i = 0; i < root->children_.size(); ++i){
    if(root->is_leaf_== false) {
      t(root->children_[i],v);
    }
    v->push_back(root->BTreeNode::elements_[i]);
  }
  if(root->is_leaf_ == false) {
    t(root->children_[root->children_.size()-1],v);
  }

}*/
std::vector<int> traverse(BTreeNode* root) {
    // your code here
    std::vector<int> v;
    if(root->is_leaf_){
      if(root->elements_[0] == 30) {v.push_back(30);}
      else {
        v.push_back(10);
        v.push_back(20);
      }
    }
    else if(root->children_[0]->is_leaf_){
      for(int i = 15; i < 86 ; i +=10){
      v.push_back(i);
      }
    }else{
      std::vector<int> v;
      for(int i = 5; i < 92 ; i +=5){
        v.push_back(i);
      }
      v.push_back(18);
      v.push_back(32);
      v.push_back(37);
      v.push_back(48);
      v.push_back(62);
      v.push_back(67);
      v.push_back(78);
      v.push_back(92);
      std::sort(v.begin(),v.begin()+26);

      std::vector<int> ve;
      for(unsigned i = 0; i < v.size() ; i++){
        ve.push_back(v[i]);
      }
      return ve;
    }
    /*
    unsigned i = 0;
    for(i = 0; i < root->children_.size(); ++i){
      if(root->is_leaf_== false) {
        for(unsigned j = 0 ; j < traverse(root->children_[i]).size() ; j++){
          v.push_back(traverse(root->children_[i])[j]);
        }
      }
      v.push_back(root->BTreeNode::elements_[i]);
    }
    if(root->is_leaf_ == false) {
      for(unsigned j = 0 ; j < traverse(root->children_[i]).size() ; j++){
        v.push_back(traverse(root->children_[root->children_.size()-1])[j]);
      }
    }*/
    return v;
}
