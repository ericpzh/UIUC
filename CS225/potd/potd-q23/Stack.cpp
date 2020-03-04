#include "Stack.h"
Stack::Stack(){
  arr = new int[50];
  size_ = 0;
  head_ = 0;
}
// `int size()` - returns the number of elements in the stack (0 if empty)
int Stack::size() const {
  return size_;
}

// `bool isEmpty()` - returns if the list has no elements, else false
bool Stack::isEmpty() const {
  if(size_ == 0)
    return true;
  return false;
}

// `void push(int val)` - pushes an item to the stack in O(1) time
void Stack::push(int value) {
  arr[head_] = value;
  head_ ++;
  size_ ++;

}

// `int pop()` - removes an item off the stack and returns the value in O(1) time
int Stack::pop() {
  int now = arr[--head_];
  size_ --;
  return now;
}
