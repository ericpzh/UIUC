/**
 * @file list.cpp
 * Doubly Linked List (MP 3).
 */

/**
 * Destroys the current List. This function should ensure that
 * memory does not leak on destruction of a list.
 */
 #include <iostream>
template <class T>
List<T>::~List() {
  /// @todo Graded in MP3.1
  clear();
}

/**
 * Destroys all dynamically allocated memory associated with the current
 * List class.
 */
template <class T>
void List<T>::clear() {
  /// @todo Graded in MP3.1
  ListNode* curr = this->head_;
  while(curr != this->tail_){
    curr = curr->next;
    delete curr -> prev;
    curr -> prev = NULL;
  }
  delete curr;
  this->head_ = NULL;
  this->tail_ = NULL;
  this->length_ = 0;
}

/**
 * Inserts a new node at the front of the List.
 * This function **SHOULD** create a new ListNode.
 *
 * @param ndata The data to be inserted.
 */
template <class T>
void List<T>::insertFront(T const& ndata) {
  /// @todo Graded in MP3.1
  ListNode* newNode = new ListNode(ndata);
  ListNode* head = this->head_;
  if(this->length_ != 0){
    newNode->next = head;
    head->prev = newNode;
  }else{
    this->tail_ = newNode;
  }
  this->length_++;
  this->head_ = newNode;
}

/**
 * Inserts a new node at the back of the List.
 * This function **SHOULD** create a new ListNode.
 *
 * @param ndata The data to be inserted.
 */
template <class T>
void List<T>::insertBack(const T& ndata) {
  /// @todo Graded in MP3.1
  ListNode* newNode = new ListNode(ndata);
  ListNode* tail = this->tail_;
  if(this->length_ != 0){
    newNode->prev = tail;
    tail->next = newNode;
  }else{
    this->head_ = newNode;
  }
  this->length_++;
  this->tail_ = newNode;
}

/**
 * Reverses the current List.
 */
template <class T>
void List<T>::reverse() {
  reverse(head_, tail_);
}

/**
 * Helper function to reverse a sequence of linked memory inside a List,
 * starting at startPoint and ending at endPoint. You are responsible for
 * updating startPoint and endPoint to point to the new starting and ending
 * points of the rearranged sequence of linked memory in question.
 *
 * @param startPoint A pointer reference to the first node in the sequence
 *  to be reversed.
 * @param endPoint A pointer reference to the last node in the sequence to
 *  be reversed.
 */
template <class T>
void List<T>::reverse(ListNode*& startPoint, ListNode*& endPoint) {
  //  /// @todo Graded in MP3.1
  if(startPoint == NULL || endPoint == NULL || startPoint -> next == NULL || endPoint -> prev == NULL || startPoint == endPoint){
    return;
  }
  ListNode* tail = startPoint;
  ListNode* head = endPoint;
  bool stop = false;
  while(!stop){
    ListNode* startNext = startPoint->next;
    ListNode* startPrev = startPoint->prev;
    ListNode* endNext = endPoint->next;
    ListNode* endPrev = endPoint->prev;
    stop = (startNext == endPrev);
    if(startPoint->next == endPoint || endPoint->prev == startPoint){
      startPoint -> next = endNext;
      startPoint -> prev = endPoint;
      endPoint -> prev = startPrev;
      endPoint -> next = startPoint;
      startPrev -> next = endPoint;
      endNext -> prev = startPoint;
      break;
    }
    startPoint -> next = endNext;
    startPoint -> prev = endPrev;
    endPoint -> next = startNext;
    endPoint -> prev = startPrev;
    startNext -> prev = endPoint;
    endPrev -> next = startPoint;
    if(startPrev != NULL){
      startPrev->next = endPoint;
    }
    if(endNext != NULL){
      endNext->prev = startPoint;
    }
    startPoint = startNext;
    endPoint = endPrev;
  }
  startPoint = head;
  endPoint = tail;
}

/**
 * Reverses blocks of size n in the current List. You should use your
 * reverse( ListNode * &, ListNode * & ) helper function in this method!
 *
 * @param n The size of the blocks in the List to be reversed.
 */
template <class T>
void List<T>::reverseNth(int n) {
  /// @todo Graded in MP3.1
  if(n >= this->length_){
    this->reverse();
    return;
  }
  if(n <= 1){
    return;
  }
  ListNode* start = this->head_;
  ListNode* end = this->head_;
  int loop = 0;
  while(start != NULL){
    for(int j = 0; j < n-1 ; j++){
      if(end->next != NULL){
        end = end->next;
      }
    }
    this->reverse(start,end);
    if(loop == 0){
      this->head_ = start;
    }
    if(end == this->tail_){
      break;
    }
    start = end -> next;
    end = end -> next;
    loop++;
  }
}

/**
 * Modifies the List using the waterfall algorithm.
 * Every other node (starting from the second one) is removed from the
 * List, but appended at the back, becoming the new tail. This continues
 * until the next thing to be removed is either the tail (**not necessarily
 * the original tail!**) or NULL.  You may **NOT** allocate new ListNodes.
 * Note that since the tail should be continuously updated, some nodes will
 * be moved more than once.
 */
template <class T>
void List<T>::waterfall() {
  /// @todo Gradessd in MP3.1
  if(head_ != NULL){
    ListNode* head = head_->next;
    while(head != NULL && head->next != NULL){
      ListNode* headPrev = head -> prev;
      ListNode* headNext = head -> next;
      head -> next = NULL;
      head -> prev = tail_;
      tail_ -> next = head;
      tail_ = head;
      headNext -> prev = headPrev;
      headPrev -> next = headNext;
      head = headNext->next;
    }
  }
}

/**
 * Splits the given list into two parts by dividing it at the splitPoint.
 *
 * @param splitPoint Point at which the list should be split into two.
 * @return The second list created from the split.
 */
template <class T>
List<T> List<T>::split(int splitPoint) {
    if (splitPoint > length_)
        return List<T>();

    if (splitPoint < 0)
        splitPoint = 0;

    ListNode* secondHead = split(head_, splitPoint);

    int oldLength = length_;
    if (secondHead == head_) {
        // current list is going to be empty
        head_ = NULL;
        tail_ = NULL;
        length_ = 0;
    } else {
        // set up current lis
        tail_ = head_;
        while (tail_->next != NULL)
            tail_ = tail_->next;
        length_ = splitPoint;
    }

    // set up the returned list
    List<T> ret;
    ret.head_ = secondHead;
    ret.tail_ = secondHead;
    if (ret.tail_ != NULL) {
        while (ret.tail_->next != NULL)
            ret.tail_ = ret.tail_->next;
    }
    ret.length_ = oldLength - splitPoint;
    return ret;
}

/**
 * Helper function to split a sequence of linked memory at the node
 * splitPoint steps **after** start. In other words, it should disconnect
 * the sequence of linked memory after the given number of nodes, and
 * return a pointer to the starting node of the new sequence of linked
 * memory.
 *
 * This function **SHOULD NOT** create **ANY** new List objects!
 *
 * @param start The node to start from.
 * @param splitPoint The number of steps to walk before splitting.
 * @return The starting node of the sequence that was split off.
 */
template <class T>
typename List<T>::ListNode* List<T>::split(ListNode* start, int splitPoint) {
    /// @todo Graded in MP3.2
    if(splitPoint == 0 || start == NULL){
      return start;
    }
    for(int i = 0; i < splitPoint ; i++){
      start = start -> next;
    }
    start -> prev -> next = NULL;
    start -> prev = NULL;
    return start;
}

/**
 * Merges the given sorted list into the current sorted list.
 *
 * @param otherList List to be merged into the current list.
 */
template <class T>
void List<T>::mergeWith(List<T>& otherList) {
    // set up the current list

    head_ = merge(head_, otherList.head_);
    tail_ = head_;

    // make sure there is a node in the new list
    if (tail_ != NULL) {
        while (tail_->next != NULL)
            tail_ = tail_->next;
    }
    length_ = length_ + otherList.length_;

    // empty out the parameter list
    otherList.head_ = NULL;
    otherList.tail_ = NULL;
    otherList.length_ = 0;

}

/**
 * Helper function to merge two **sorted** and **independent** sequences of
 * linked memory. The result should be a single sequence that is itself
 * sorted.
 *
 * This function **SHOULD NOT** create **ANY** new List objects.
 *
 * @param first The starting node of the first sequence.
 * @param second The starting node of the second sequence.
 * @return The starting node of the resulting, sorted sequence.
 */
template <class T>
typename List<T>::ListNode* List<T>::merge(ListNode* first, ListNode* second) {
  /// @todo Graded in MP3.2
  ListNode* newHead;
  if(first->data < second -> data){
    newHead = first;
    first = first -> next;
  }else{
    newHead = second;
    second = second -> next;
  }
  ListNode* curr = newHead;
  bool stop = false;
  while(!stop){
    if(first == NULL && second == NULL){
      break;
    }
    if(first == NULL && second != NULL){
      curr->next = second;
      second->prev = curr;
      second = second -> next;
    }else if(second == NULL && first != NULL){
      curr->next = first;
      first->prev = curr;
      first = first->next;
    }else if(first -> data < second -> data){
      curr->next = first;
      first->prev = curr;
      first = first->next;
    }else{
      curr->next = second;
      second->prev = curr;
      second = second->next;
    }
    curr = curr -> next;
    if(first == NULL && second == NULL){
      stop = true;
    }
  }
  newHead->prev = NULL;
  return newHead;
}

/**
 * Sorts the current list by applying the Mergesort algorithm.
 */
template <class T>
void List<T>::sort() {
    if (empty())
        return;
    head_ = mergesort(head_, length_);
    tail_ = head_;
    while (tail_->next != NULL)
        tail_ = tail_->next;
}

/**
 * Sorts a chain of linked memory given a start node and a size.
 * This is the recursive helper for the Mergesort algorithm (i.e., this is
 * the divide-and-conquer step).
 *
 * @param start Starting point of the chain.
 * @param chainLength Size of the chain to be sorted.
 * @return A pointer to the beginning of the now sorted chain.
 */
template <class T>
typename List<T>::ListNode* List<T>::mergesort(ListNode* start, int chainLength) {
    /// @todo Graded in MP3.2
    if(chainLength == 1){
      return start;
    }else{
      ListNode* secondList = split(start,chainLength/2);
      return merge(mergesort(start,chainLength/2),mergesort(secondList,chainLength-chainLength/2));
    }
}
