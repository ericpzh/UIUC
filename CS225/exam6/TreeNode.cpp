#include "TreeNode.h"
#include <iostream>
#include <cmath>
#include <stdlib.h>
#include <queue>
using namespace std;
int height(TreeNode* root){
  if(root == NULL) return -1;
  return 1+fmax(height(root->left_),height(root->right_));
}
int sum(TreeNode* root){
  if(root == NULL) return 0;
  return root->val_+sum(root->left_)+sum(root->right_);
}
int leaf(TreeNode* root){
  if(root->left_ == NULL && root->right_ == NULL) return 1;
  return leaf(root->left_)+leaf(root->right_);
}
int number(TreeNode* root , int i){
  if(root == NULL) return 0;
  if(root->left_ == NULL && root->right_ == NULL){
    if(root->val_ == i) return 1;
    else return 0;
  }else{
    if(root->val_ == i) return 1+number(root->left_,i)+number(root->right_,i);
    else return 0+number(root->left_,i)+number(root->right_,i);
  }
}
TreeNode* full(int i){
  if(i<1) return NULL;
  TreeNode* root = new TreeNode(1);
  if(i==1) return root;
  for(int j = 1;j<i;j+=2){
    TreeNode* one = new TreeNode(j+1);
    TreeNode* two = new TreeNode(j+2);
    TreeNode* now = root;
    int k = rand();
    while(now->left_ != NULL && now->right_ != NULL){
      if(k % 2 == 1){
        now = now->left_;
      }else{
        now = now ->right_;
      }
    }
    now->left_ = one;
    now->right_ = two;
  }
  return root;
}
void insert(TreeNode** root, int i , queue<TreeNode*> *q){
  TreeNode* node = new TreeNode(i);
  if(*root == NULL){
    *root = node;
  }else{
    TreeNode* temp = q->front();
    if(temp->left_ == NULL){
      temp->left_ = node;
    }else if(temp->right_ == NULL){
      temp->right_ = node;
    }
    if(temp -> left_ != NULL && temp->right_ != NULL){
      q->pop();
    }
  }
  q->push(node);
}
TreeNode* complete(int i){
  queue<TreeNode*> q;
  TreeNode* root;
  for(int j = 1 ; j <= i ; j++){
    insert(&root,j,&q);
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
