#include <iostream>
using namespace std;

#include "queen.h"
#include "piece.h"

void printPiece(piece *p) {
  cout << "In printPiece, printType() of the same memory address is: "
       << p->getType() << endl;
}

int main() {
  queen *q = new queen();

  cout << "In main, printType() of queen *q is: " 
       << q->getType() << endl;

  printPiece(q);

  delete q;  // Don't forget to free your memory! :)
  return 0;
}
