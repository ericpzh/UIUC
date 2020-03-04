#include "TreeNode.h"
#include <iostream>

TreeNode * min(TreeNode* node)
{
    TreeNode* current = node;
    while (current->left_ != NULL)
        current = current->left_;
    return current;
}

TreeNode * deleteNode(TreeNode* root, int key) {
  // your code here
  if (root == NULL)  return NULL;
  if (root->val_ < key) {
    root-> right_ =  deleteNode(root->right_,key);
  }
  else if (root->val_ > key){
    root-> left_ = deleteNode(root->left_,key);
  }else{
    if(root->right_ == NULL){
      TreeNode* ret = root->left_;
      free(root);
      return ret;
    }else if(root->left_ == NULL){
      TreeNode* ret = root->right_;
      free(root);
      return ret;
    }
    else{
      TreeNode* ret = min(root -> right_);
      root -> val_ = ret -> val_;
      root -> right_ = deleteNode(root -> right_, ret -> val_);
    }
  }
      return root;
}

void inorderPrint(TreeNode* node)
{
    if (!node)  return;
    inorderPrint(node->left_);
    std::cout << node->val_ << " ";
    inorderPrint(node->right_);
}

void deleteTree(TreeNode* root)
{
  if (root == NULL) return;
  deleteTree(root->left_);
  deleteTree(root->right_);
  delete root;
  root = NULL;
}
