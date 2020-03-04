#ifndef _TREENODE_H
#define _TREENODE_H

#include <cstddef>

class TreeNode {

    public:
        int data_;
        TreeNode *left_;
        TreeNode *right_;

        TreeNode(int data=0, TreeNode *left=NULL, TreeNode *right=NULL);
        ~TreeNode();
        int findMax() const;
        int getData() const;
        TreeNode *getLeft() const;
        TreeNode *getRight() const;

};

// Here is the signature of the code you will write

TreeNode *makeFullRight(int n);

bool equal(TreeNode *n1, TreeNode *n2);

#endif
