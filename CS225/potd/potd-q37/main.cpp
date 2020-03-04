#include <iostream>
#include <stdlib.h>
#include "TreeNode.h"

int main() {

  TreeNode * n1 = new TreeNode(4);
  TreeNode * n2 = new TreeNode(7);
  TreeNode * n3 = new TreeNode(11);
  TreeNode * n4 = new TreeNode(13);
  TreeNode * n5 = new TreeNode(14);
  TreeNode * n6 = new TreeNode(22);

  n5->left_ = n3;
  n5->right_ = n6;
  n3->right_ = n4;
  n3->left_ = n2;
  n2->left_ = n1;
  
  std::cout << "Last Unbalanced Node: " << (findLastUnbalanced(n5))->val_ << std::endl;

  deleteTree(n5);

  return 0;

}
