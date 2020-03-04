#include <iostream>
#include "Node.h"

using namespace std;


void printList(Node *head) {
  if (head == NULL) {
    cout << "Empty list" << endl;
    return;
  }

  Node *temp = head;
  int count = 0;
  while(temp != NULL) {
    cout << "Node " << count << ": " << temp ->data_ << endl;
    count++;
    temp = temp->next_;
  }
}

int main() {
  // Create an unsorted list:
  Node one, two, three, four, five;
  one.data_ = 2;
  two.data_ = 4;
  three.data_ = 1;
  four.data_ = 5;
  five.data_ = 3;

  cout << Node::getNumNodes() << endl;
  // 2 -> 4 -> 1 -> 5 -> 3
  Node *head = &two;
  two.next_ = &four;
  four.next_ = &one;
  one.next_ = &five;
  five.next_ = &three;
  three.next_ = NULL;

  cout << Node::getNumNodes() << endl;

  // Unsorted List:
  cout<<"Unsorted List:"<<endl;
  printList(head);

  // Sorted List:
  sortList(&head);
  cout<<"Sorted List:"<<endl;
  printList(head);

  cout << Node::getNumNodes() << endl;
  return 0;
}
