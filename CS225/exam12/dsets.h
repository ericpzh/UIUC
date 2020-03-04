#ifndef DSETs_H
#define DSETs_H
#include <vector>
#include <cmath>
using namespace std;
class dsets{
public:
  void addelements(int num){
    for(int i = 0 ;i < num; i++){
      set.push_back(-1);
    }
  }
  int find(int elem){
    if(set[elem] < 0) return elem;
    else return set[elem] = find(set[elem]);
  }
  void setunion(int a, int b){
    a = find(a);
    b = find(b);
    if(abs(set[a]) > abs(set[b])){
      set[a] = a+b;
      set[b] = a;
    }else{
      set[b] = a+b;
      set[a] = b;
    }
  }
private:
  vector<int> set;
};
#endif
