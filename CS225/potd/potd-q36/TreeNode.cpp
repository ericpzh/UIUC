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
  return (abs(getHeightBalance(root)) < 2);
}
bool isAVL(TreeNode* root) {
  if(root == NULL) return true;
  if(root -> left_ != NULL && root->right_ != NULL){
    return (isHeightBalanced(root->left_)&&isHeightBalanced(root)&&isHeightBalanced(root->right_));
  }else if(root->right_ != NULL){
    return (isHeightBalanced(root)&&isHeightBalanced(root->right_));
  }else if(root->left_ != NULL){
    return (isHeightBalanced(root->left_)&&isHeightBalanced(root));
  }else{
    return isHeightBalanced(root);
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