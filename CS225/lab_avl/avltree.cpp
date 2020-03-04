/**
 * @file avltree.cpp
 * Definitions of the binary tree functions you'll be writing for this lab.
 * You'll need to modify this file.
 */
#include <cmath>
using namespace std;
template <class K, class V>
V AVLTree<K, V>::find(const K& key) const
{
    return find(root, key);
}

template <class K, class V>
V AVLTree<K, V>::find(Node* subtree, const K& key) const
{
    if (subtree == NULL)
        return V();
    else if (key == subtree->key)
        return subtree->value;
    else {
        if (key < subtree->key)
            return find(subtree->left, key);
        else
            return find(subtree->right, key);
    }
}

template <class K, class V>
void AVLTree<K, V>::rotateLeft(Node*& t)
{
    functionCalls.push_back("rotateLeft"); // Stores the rotation name (don't remove this)
    // your code here
    Node* y = t->right;
    t->right = y->left;
    t->height = fmax(heightOrNeg1(t->left), heightOrNeg1(t->right))+1;
    y->left = t;
    t = y;
    t->height = fmax(heightOrNeg1(t->left), heightOrNeg1(t->right))+1;

}

template <class K, class V>
void AVLTree<K, V>::rotateLeftRight(Node*& t)
{
    functionCalls.push_back("rotateLeftRight"); // Stores the rotation name (don't remove this)
    // Implemented for you:
    rotateLeft(t->left);
    rotateRight(t);
}

template <class K, class V>
void AVLTree<K, V>::rotateRight(Node*& t)
{
    functionCalls.push_back("rotateRight"); // Stores the rotation name (don't remove this)
    // your code here
    Node* y = t->left;
    t->left = y->right;
    t->height = fmax(heightOrNeg1(t->left), heightOrNeg1(t->right))+1;
    y->right = t;
    t = y;
    t->height = fmax(heightOrNeg1(t->left), heightOrNeg1(t->right))+1;
}

template <class K, class V>
void AVLTree<K, V>::rotateRightLeft(Node*& t)
{
    functionCalls.push_back("rotateRightLeft"); // Stores the rotation name (don't remove this)
    // your code here
    rotateRight(t->right);
    rotateLeft(t);
}

template <class K, class V>
void AVLTree<K, V>::rebalance(Node*& subtree)
{
    // your code here
    if(subtree == NULL) return;
    int balance = heightOrNeg1(subtree->left) - heightOrNeg1(subtree->right);
    int rightBalance = 0;
    int leftBalance = 0;
    if(subtree->right != NULL){
      rightBalance = heightOrNeg1(subtree->right->left) - heightOrNeg1(subtree->right->right);
    }
    if(subtree->left != NULL){
      leftBalance = heightOrNeg1(subtree->left->left) - heightOrNeg1(subtree->left->right);
    }
    if(balance == -2){
        if(rightBalance > 0){
          rotateRightLeft(subtree);
        }
        else if(rightBalance < 0){
          rotateLeft(subtree);
        }
    }else if(balance == 2){
        if(leftBalance < 0){
          rotateLeftRight(subtree);
        }
        else if(leftBalance > 0){
          rotateRight(subtree);
        }
    }
}

template <class K, class V>
void AVLTree<K, V>::insert(const K & key, const V & value)
{
    insert(root, key, value);
}

template <class K, class V>
void AVLTree<K, V>::insert(Node*& subtree, const K& key, const V& value)
{
    // your code here
    if(subtree == NULL){
      Node* node = new Node(key,value);
      subtree = node;
      node->height = 0;
    }else if(key < subtree -> key){
      insert(subtree->left,key,value);
      rebalance(subtree);
    }else if(key > subtree -> key){
      insert(subtree->right,key,value);
      rebalance(subtree);
    }
    subtree->height = fmax(heightOrNeg1(subtree->left), heightOrNeg1(subtree->right))+1;
}

template <class K, class V>
void AVLTree<K, V>::remove(const K& key)
{
    remove(root, key);
}
template <class K, class V>
void AVLTree<K, V>::remove(Node*& subtree, const K& key)
{
    if (subtree == NULL)
        return;

    if (key < subtree->key) {
        // your code here
        remove(subtree->left,key);
        rebalance(subtree);
        subtree->height = fmax(heightOrNeg1(subtree->left), heightOrNeg1(subtree->right))+1;
    } else if (key > subtree->key) {
        // your code here
        remove(subtree->right,key);
        rebalance(subtree);
        subtree->height = fmax(heightOrNeg1(subtree->left), heightOrNeg1(subtree->right))+1;
    } else {
        if (subtree->left == NULL && subtree->right == NULL) {
            /* no-child remove */
            // your code here
            delete subtree;
            subtree = NULL;
            return;
        } else if (subtree->left != NULL && subtree->right != NULL) {
            /* two-child remove */
            // your code here
            Node* node = findNode(root, subtree);
            swap(subtree,node);
            Node* parent = search(root,subtree->key,key);
            if(parent!=NULL){
              if(parent -> right != NULL){
                if(parent->right->left == NULL && parent->right->right == NULL){
                    delete parent->right;
                    parent->right = NULL;
                }else{
                  Node* temp1 = parent->right;
                  if(parent->right -> left == NULL){
                    parent->right = parent->right->right;
                  }else{
                    parent->right = parent->right->left;
                  }
                  delete temp1;
                  temp1 = NULL;
                }
              }
            }
            rebalance(node);
            subtree->height = fmax(heightOrNeg1(subtree->left), heightOrNeg1(subtree->right))+1;

        } else {
            /* one-child remove */
            // your code here
            Node* node = subtree;
            if(subtree -> left == NULL && subtree->right != NULL){
              subtree = subtree->right;
            }else{
              subtree = subtree->left;
            }
            delete node;
            node = NULL;
            rebalance(subtree);
            subtree->height = fmax(heightOrNeg1(subtree->left), heightOrNeg1(subtree->right))+1;
        }
        // your code here
    }
}
