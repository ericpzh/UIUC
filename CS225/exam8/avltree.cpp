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
    if(heightOrNeg1(subtree->left)-heightOrNeg1(subtree->right) == 2){
      if(subtree->left != NULL){
        if(heightOrNeg1(subtree->left->left)-heightOrNeg1(subtree->left->right) == 1){
          rotateRight(subtree);
        }else{
          rotateLeftRight(subtree);
        }
      }
    }else if(heightOrNeg1(subtree->left)-heightOrNeg1(subtree->right) == -2){
      if(subtree->right != NULL){
        if(heightOrNeg1(subtree->right->left)-heightOrNeg1(subtree->right->right) == 1){
          rotateRightLeft(subtree);
        }else{
          rotateLeft(subtree);
        }
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
      subtree =  new Node(key,value);
    }else if(subtree->key < key){
      insert(subtree->right,key,value);
    }else{
      insert(subtree->left,key,value);
    }
    rebalance(subtree);
    subtree->height = 1+fmax(heightOrNeg1(subtree->left),heightOrNeg1(subtree->right));
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
        subtree->height = 1+fmax(heightOrNeg1(subtree->left),heightOrNeg1(subtree->right));
    } else if (key > subtree->key) {
        // your code here
        remove(subtree->right,key);
        rebalance(subtree);
        subtree->height = 1+fmax(heightOrNeg1(subtree->left),heightOrNeg1(subtree->right));

    } else {
        if (subtree->left == NULL && subtree->right == NULL) {
            /* no-child remove */
            // your code here
            delete subtree;
            subtree = NULL;

        } else if (subtree->left != NULL && subtree->right != NULL) {
            /* two-child remove */
            // your code here

            Node* iopp = subtree;
            Node* iop = subtree->left;
            while(iop->right != NULL){
              iopp = iop;
              iop = iop->right;
            }
            swap(iop,subtree);
            if(iop->left == NULL){
              iopp->right =  NULL;
              delete iop;
            }else{
              Node* temp = iop;
              iopp->right = iop->left;
              delete temp;
            }
            rebalance(subtree);
            subtree->height = 1+fmax(heightOrNeg1(subtree->left),heightOrNeg1(subtree->right));

        } else {
            /* one-child remove */
            // your code here

            Node* temp = subtree;
            if(subtree->left == NULL){
                subtree = subtree->right;
            }else{
              subtree = subtree->left;
            }
            delete temp;
            rebalance(subtree);
            subtree->height = 1+fmax(heightOrNeg1(subtree->left),heightOrNeg1(subtree->right));
        }
        // your code here

    }
}
