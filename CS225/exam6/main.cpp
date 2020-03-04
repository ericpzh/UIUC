#include "TreeNode.h"
#include <iostream>
using namespace std;

int main() {

  TreeNode * root = complete(5);
  cout<<"height"<<height(root)<<endl;
  cout<<"sum"<<sum(root)<<endl;
  cout<<"leaf"<<leaf(root)<<endl;
  cout<<"number"<<number(root,6)<<endl;
  cout << "Tree" << endl;
  inorderPrint(root);
  cout << endl;

  deleteTree(root);
  return 0;

}
