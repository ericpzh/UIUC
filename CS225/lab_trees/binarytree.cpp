/**
 * @file binarytree.cpp
 * Definitions of the binary tree functions you'll be writing for this lab.
 * You'll need to modify this file.
 */
 #include <stack>
/**
 * @return The height of the binary tree. Recall that the height of a binary
 *  tree is just the length of the longest path from the root to a leaf, and
 *  that the height of an empty tree is -1.
 */
template <typename T>
int BinaryTree<T>::height() const
{
    // Call recursive helper function on root
    return height(root);
}

/**
 * Private helper function for the public height function.
 * @param subRoot
 * @return The height of the subtree
 */
template <typename T>
int BinaryTree<T>::height(const Node* subRoot) const
{
    // Base case
    if (subRoot == NULL)
        return -1;

    // Recursive definition
    return 1 + max(height(subRoot->left), height(subRoot->right));
}

/**
 * Prints out the values of the nodes of a binary tree in order.
 * That is, everything to the left of a node will be printed out before that
 * node itself, and everything to the right of a node will be printed out after
 * that node.
 */
template <typename T>
void BinaryTree<T>::printLeftToRight() const
{
    // Call recursive helper function on the root
    printLeftToRight(root);

    // Finish the line
    cout << endl;
}

/**
 * Private helper function for the public printLeftToRight function.
 * @param subRoot
 */
template <typename T>
void BinaryTree<T>::printLeftToRight(const Node* subRoot) const
{
    // Base case - null node
    if (subRoot == NULL)
        return;

    // Print left subtree
    printLeftToRight(subRoot->left);

    // Print this node
    cout << subRoot->elem << ' ';

    // Print right subtree
    printLeftToRight(subRoot->right);
}
/**
 * Flips the tree over a vertical axis, modifying the tree itself
 *  (not creating a flipped copy).
 */
template <typename T>
void BinaryTree<T>::mirror()
{
    //your code here
    mirror(root);
}
template <typename T>
void BinaryTree<T>::mirror(Node* curr){
  if(curr->left == NULL && curr->right == NULL){
    return;
  }else if(curr->left == NULL){
    curr->left = curr->right;
    curr->right = NULL;
    return;
  }else if(curr->right == NULL){
    curr->right = curr->left;
    curr->left = NULL;
    return;
  }
  else if(curr->left->left == NULL && curr->left->right == NULL &&curr->right->left == NULL &&curr->right->right == NULL){
    Node* temp = curr->left;
    curr->left = curr->right;
    curr->right = temp;
    return;
  }
  else{
    Node* temp = curr->left;
    curr->left = curr->right;
    curr->right = temp;
    mirror(curr->left);
    mirror(curr->right);
  }
}
/**
 * @return True if an in-order traversal of the tree would produce a
 *  nondecreasing list output values, and false otherwise. This is also the
 *  criterion for a binary tree to be a binary search tree.
 */
template <typename T>
bool BinaryTree<T>::isOrdered() const
{
    // your code here
    Node* curr = root;
    stack<Node*> s;
    stack<T> st;
    bool stop = false;
    while(!stop){
      if(curr != NULL){
        s.push(curr);
        curr = curr->left;
      }else if(!s.empty()){
          curr = s.top();
          st.push(curr->elem);
          s.pop();
          curr = curr->right;
      }else{
          stop = true;
      }
    }
    T* arr = new T[st.size()];
    int size = st.size();
    int i = size;
    while(!st.empty()){
      arr[--i] = st.top();
      st.pop();
    }
    for(int i = 1; i < size ; i++){
      if(arr[i-1] > arr[i]){
        return false;
      }
    }
    return true;
}

/**
 * creates vectors of all the possible paths from the root of the tree to any leaf
 * node and adds it to another vector.
 * Path is, all sequences starting at the root node and continuing
 * downwards, ending at a leaf node. Paths ending in a left node should be
 * added before paths ending in a node further to the right.
 * @param paths vector of vectors that contains path of nodes
 */
template <typename T>
void BinaryTree<T>::printPaths(vector<vector<T> > &paths) const
{
    // your code here
    vector<T> path;
    printPaths(paths,path,root);
}
template <typename T>
void BinaryTree<T>::printPaths(vector<vector<T> > &paths,vector<T> path, Node* curr) const{
  if(curr == NULL){
    return;
  }
  path.push_back(curr->elem);
  if(curr->left == NULL && curr->right == NULL){
    paths.push_back(path);
  }else{
    printPaths(paths,path,curr->left);
    printPaths(paths,path,curr->right);
  }
}
/**
 * Each node in a tree has a distance from the root node - the depth of that
 * node, or the number of edges along the path from that node to the root. This
 * function returns the sum of the distances of all nodes to the root node (the
 * sum of the depths of all the nodes). Your solution should take O(n) time,
 * where n is the number of nodes in the tree.
 * @return The sum of the distances of all nodes to the root
 */
 template <typename T>
 int BinaryTree<T>::sumDistances(Node* curr) const{
   // your code here
   if(curr == NULL){
     return 0;
   }
   else if(curr->left == NULL && curr->right == NULL){
     return 1;
   }
   else{
     return sumDistances(curr->left)+sumDistances(curr->right)+2;
   }
 }

template <typename T>
int BinaryTree<T>::sumDistances() const
{

    return sumDistances(root);
}
