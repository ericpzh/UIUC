#include "TreeNode.h"
#include <cmath>
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

void deleteTree(TreeNode* root)
{
  if (root == NULL) return;
  deleteTree(root->left_);
  deleteTree(root->right_);
  delete root;
  root = NULL;
}
