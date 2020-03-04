#include "TreeNode.h"
#include <iostream>

int main() {

  TreeNode * n1 = new TreeNode(8);
  TreeNode * n2 = new TreeNode(5);
  TreeNode * n3 = new TreeNode(13);
  TreeNode * n4 = new TreeNode(10);
  n1->left_ = n2;
  n1->right_ = n3;
  n3->left_ = n4;
  n3->right_ = new TreeNode(14);
  n4->right_ = new TreeNode(11);
  n4->right_->right_ = new TreeNode(12);

  std::cout << "Height balance of n1: " << getHeightBalance(n1) << std::endl;
  std::cout << "Height balance of n2: " << getHeightBalance(n2) << std::endl;
  std::cout << "Height balance of n3: " << getHeightBalance(n3) << std::endl;
  std::cout << "Height balance of n4: " << getHeightBalance(n4) << std::endl;

  deleteTree(n1);
  return 0;

}
