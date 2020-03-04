#include <iostream>
#include "TreeNode.h"



int main() {
  TreeNode * n1 = new TreeNode(8);
  TreeNode * n2 = new TreeNode(5);
  TreeNode * n3 = new TreeNode(13);
  TreeNode * n4 = new TreeNode(10);
  n1->left_ = n2;
  n1->right_ = n3;
  n3->left_ = n4;
  n3->right_ = new TreeNode(14);

  std::cout << "Example #1: isAVL(): " << isAVL(n1) << std::endl;

  n4->right_ = new TreeNode(11);
  n4->right_->right_ = new TreeNode(12);

  std::cout << "Example #2: isAVL(): " << isAVL(n1) << std::endl;

  deleteTree(n1);
  return 0;
}
