#ifndef TreeNode_H
#define TreeNode_H

#include <cstddef>

// Definition for a binary tree node.
struct TreeNode {
    int val_;
    int height_;
    TreeNode *left_;
    TreeNode *right_;
    TreeNode(int x) {
      left_ = NULL;
      right_ = NULL;
      val_ = x;
      height_ = 0;
    }
};

// Part 1 code

void rotateRight(TreeNode* &root);
void rotateLeft(TreeNode* &root);

// Part 2 code

void remove(TreeNode* &root, int val);

void rebalance(TreeNode * &root);

// Helper functions

int heightOrNeg1(TreeNode *root);
void updateHeight(TreeNode *root);
void show(TreeNode *t1);

void deleteTree(TreeNode* root);

#endif
