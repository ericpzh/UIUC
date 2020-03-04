#include "TreeNode.h"

// Your function here
TreeNode *makeFullRight(int n) {
  if(n < 1) return NULL;
  TreeNode* root = new TreeNode(1);
  for(int i = 2 ; i <= n ; i+=2){
    TreeNode* one = new TreeNode(i);
    TreeNode* two = new TreeNode(i+1);
    TreeNode* temp = root;
    while(temp -> left_ != NULL && temp->right_ != NULL){
      temp = temp->right_;
    }
    temp->left_ = one;
    temp->right_ = two;
  }
  return root;
}

// Methods and functions we provide following.
// You should not need to edit this code.

TreeNode::TreeNode(int data, TreeNode *left, TreeNode *right) :
    data_(data), left_(left), right_(right) {}

TreeNode::~TreeNode() {
    if (left_ != NULL)
        delete left_;
    if (right_ != NULL)
        delete right_;
}

bool equal(TreeNode *n1, TreeNode *n2) {
    if (n1 == NULL)
        return n2 == NULL;

    if (n2==NULL)
        return false;

    return (n1->getData() == n2->getData() &&
            equal(n1->getLeft(),n2->getLeft()) &&
            equal(n1->getRight(),n2->getRight()));
}

int TreeNode::getData() const {
    return data_;
}

TreeNode *TreeNode::getLeft() const {
    return left_;
}

TreeNode *TreeNode::getRight() const {
    return right_;
}
