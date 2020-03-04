#include "TreeNode.h"
#include <iostream>
#include <cmath>

// Part 1 code

void rotateRight(TreeNode *& root) {

    TreeNode *nuRoot = root->left_;
    root->left_ = nuRoot->right_;
    nuRoot->right_ = root;

    root = nuRoot;

    updateHeight(root);
    updateHeight(root->right_);
    return;
}


void rotateLeft(TreeNode *& root) {

    TreeNode *nuRoot = root->right_;
    root->right_ = nuRoot->left_;
    nuRoot->left_ = root;

    root = nuRoot;

    updateHeight(root);
    updateHeight(root->left_);
    return;
}

void rotateLeftRight(TreeNode* &root) {
    rotateLeft(root->left_);
    rotateRight(root);
}


void rotateRightLeft(TreeNode* &root) {
    rotateRight(root->right_);
    rotateLeft(root);
}

void swap(TreeNode* f, TreeNode* s){
  int temp = s->val_;
  s->val_ = f->val_;
  f->val_ = temp;

}
// Your Code

void remove(TreeNode* &root, int val) {
    // Your code here
    if(root == NULL) return;
    if(root->val_ < val){
      remove(root->right_,val);
      rebalance(root);
      updateHeight(root);
    }else if(root->val_ > val){
          remove(root->left_,val);
          rebalance(root);
          updateHeight(root);
    }else{
      if(root->left_ == NULL && root->right_ == NULL){
        root = NULL;
        //delete root;
      }else if(root->left_ != NULL && root->right_ != NULL){
        TreeNode* iop = root->left_;
        TreeNode* iopp = root;
        while(iop->right_ != NULL){
          iopp = iop;
          iop = iop->right_;
        }
        swap(iop,root);
        //TreeNode* node = iop;
        if(iop->left_ == NULL && iop->right_ == NULL){
          iop = NULL;
        }
        else if(iop -> left_ == NULL){
          iop = iop->right_;
        }else{
          iop = iop->left_;
        }
        //delete node;
        rebalance(root);
        updateHeight(root);
      }else{
        //TreeNode* node = root;
        if(root -> left_ == NULL){
          root = root->right_;
        }else{
          root = root->left_;
        }
        //delete node;
        rebalance(root);
        updateHeight(root);
      }
    }
}

void rebalance(TreeNode* &root) {
    // The following line is to silence compiler warnings.  Delete it.
    if(root == NULL) return;
    if(heightOrNeg1(root->left_)-heightOrNeg1(root->right_) == 2){
      if(root->left_ != NULL){
        if(heightOrNeg1(root->left_->left_)-heightOrNeg1(root->left_->right_) == 1){
          rotateRight(root);
        }else{
          rotateLeftRight(root);
        }
      }
    }else if(heightOrNeg1(root->left_)-heightOrNeg1(root->right_) == -2){
      if(root->right_ != NULL){
        if(heightOrNeg1(root->right_->left_)-heightOrNeg1(root->right_->right_) == -1){
            rotateLeft(root);
        }else{
          rotateRightLeft(root);
        }
      }
    }
}

// Helper functions

int heightOrNeg1(TreeNode *root) {
    if (root==NULL)
        return -1;
    else
        return root->height_;
}

void updateHeight(TreeNode * node) {
    node->height_ = 1 + std::max(heightOrNeg1(node->right_), heightOrNeg1(node->left_));
}

using namespace std;

void show(TreeNode *t1) {
    if (t1==NULL) {
        cout << "x";
        return;
    }

    if (t1->left_ == NULL && t1->right_==NULL) {
        cout << t1->val_;
        return;
    }

    cout << "(" << t1-> val_ << " ";
    show(t1->left_);
    cout << " ";
    show(t1->right_);
    cout << ")";
}

void deleteTree(TreeNode* root)
{
    if (root == NULL) return;
    deleteTree(root->left_);
    deleteTree(root->right_);
    delete root;
    root = NULL;
}
