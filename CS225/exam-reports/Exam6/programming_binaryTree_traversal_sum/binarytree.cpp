#define _BINARYTREE_CPP
#include "binarytree.h"
#include <iostream>
#include <cstdlib>

using namespace std;

template <typename T>
int BinaryTree<T>::sum(Node* root){
  if(root == NULL) return 0;
  if(root->left == NULL && root->right==NULL) return root->elem;
  return root->elem+sum(root->left)+sum(root->right);
}

template <typename T>
int BinaryTree<T>::sumNodes() {
  return sum(root);
}

/**
 * Constructor to create an empty tree.
 */
template <typename T>
BinaryTree<T>::BinaryTree() : root(NULL) {
  /* nothing */
}

/**
 * Inserts into the BinaryTree, using BST insert algorithm.
 * @param elem The element to insert
 */
template <typename T>
void BinaryTree<T>::insert(const T& elem) {
  insert(root, elem);
}

template <typename T>
void BinaryTree<T>::insert(Node*& node, const T& elem)
{
    // If we're at a NULL pointer, we can put our element here
    if (node == NULL)
        node = new Node(elem);

    // Go left or right
    else if (elem < node->elem)
        insert(node->left, elem);
    else
        insert(node->right, elem);
}
