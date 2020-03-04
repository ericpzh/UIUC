#ifndef _TREENODE_H
#define _TREENODE_H

#include <cstddef>
#include <queue>
using namespace std;
// Definition for a binary tree node.
struct TreeNode {
    int val_;
    TreeNode *left_;
    TreeNode *right_;
    TreeNode(int x) {
      left_ = NULL;
      right_ = NULL;
      val_ = x;
    }
};
TreeNode* full(int i);
TreeNode* complete(int i);
int height(TreeNode* root);
int sum(TreeNode* root);
int leaf(TreeNode* root);
int number(TreeNode* root, int i);
void inorderPrint(TreeNode* node);
//void insert(TreeNode* root,int i ,queue q);
void deleteTree(TreeNode* root);

#endif
