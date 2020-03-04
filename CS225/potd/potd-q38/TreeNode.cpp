#include "TreeNode.h"
#include <algorithm>


void rightRotate(TreeNode* root) {

  TreeNode* y = root->left_;
  TreeNode* a = y->right_;
  TreeNode* b = root;
  TreeNode* c = b->parent_;
  root = y;
  y->right_ = b;
  b->left_ = a;
  b->parent_ = y;
  y->parent_ = c;
  a->parent_ = b;

}


void leftRotate(TreeNode* root) {

  TreeNode* y = root->right_;
  TreeNode* a = y->left_;
  TreeNode* b = root;
  TreeNode* c = b->parent_;
  root = y;
  y->left_ = b;
  b->right_ = a;
  b->parent_ = y;
  y->parent_ = c;
  a->parent_ = b;
    // your code here
}



void deleteTree(TreeNode* root)
{
  if (root == NULL) return;
  deleteTree(root->left_);
  deleteTree(root->right_);
  delete root;
  root = NULL;
}
