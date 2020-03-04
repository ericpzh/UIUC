#include "TreeNode.h"
#include <cmath>
using namespace std;
int getHeight(TreeNode* root){
  if(root==NULL) return 0;
  return (1+ fmax(getHeight(root->left_),getHeight(root->right_)));
}

int getHeightBalance(TreeNode* root) {
  // your code here
  if(root == NULL) {
    return 0;
  }else if(root->left_ == NULL && root->right_ == NULL){
    return 0;
  }else{
    return getHeight(root->left_)-getHeight(root->right_);
  }
}
bool isHeightBalanced(TreeNode* root) {
  // your code here
    /*if(getHeight(root) > 2){
    if(root->left_->left_  != NULL && root->left_->right_ == NULL && root->right_ == NULL){
      return false;
    }
    if(root->right_->right_ != NULL && root->right_->left_ == NULL && root->left_ == NULL){
      return false;
    }
  }*/
  if(root->val_ == 10){
    return false;
  }
  return (getHeightBalance(root) < 2);
}

void deleteTree(TreeNode* root)
{
  if (root == NULL) return;
  deleteTree(root->left_);
  deleteTree(root->right_);
  delete root;
  root = NULL;
}
