#ifndef QUEEN_H
#define QUEEN_H
#include <string>
#include"piece.h"

using namespace std;

class queen : public piece {
  public:
    string getType();
};

#endif
