#ifndef DEST_H
#define DEST_H
#include <vector>
using namespace std;

//All function as defined as Doxygen
//All implentation is the same as lecture handouts

class DisjointSets{
  public:
    void 	addelements (int num);
    int 	find (int elem);
    void 	setunion (int a, int b);
  private:
    vector<int> set;
};

#endif
