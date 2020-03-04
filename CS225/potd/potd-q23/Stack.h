#ifndef _STACK_H
#define _STACK_H

#include <cstddef>

class Stack {
public:
  Stack();
  int size() const;
  bool isEmpty() const;
  void push(int value);
  int pop();
private:
  int size_,head_;
  int* arr;
};

#endif
