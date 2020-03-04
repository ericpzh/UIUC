#include "TreeNode.h"

#include <cstddef>
#include <iostream>
using namespace std;

TreeNode::TreeNode() : left_(NULL), right_(NULL) { }
int findHeight(TreeNode* root){
  if(root == NULL){
    return -1;
  }
  int left = findHeight(root->left_);
  int right = findHeight(root->right_);
  if(left>right){
    return left+1;
  }else{
    return right +1;
  }
}
int TreeNode::getHeight() {
  return findHeight(this);
}
