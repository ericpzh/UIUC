#include "Queue.h"
#include <queue>
using namespace std;
Queue::Queue() {}

// `int size()` - returns the number of elements in the stack (0 if empty)
int Queue::size() const {
  return q.size();
}

// `bool isEmpty()` - returns if the list has no elements, else false
bool Queue::isEmpty() const {
  return q.empty();
}

// `void enqueue(int val)` - enqueue an item to the queue in O(1) time
void Queue::enqueue(int value) {
  q.push(value);
}

// `int dequeue()` - removes an item off the queue and returns the value in O(1) time
int Queue::dequeue() {
  int top = q.front();
  q.pop();
  return top;
}
