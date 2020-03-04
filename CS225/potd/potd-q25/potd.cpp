// Your code here
#include <string>

using namespace std;

string getFortune(const string &s){
  if(s.length() == 1){
    return "a";
  }
  if(s.length() == 2){
    return "h";
  }
  if(s.length() == 3){
    return "4";
  }
  if(s.length() == 4){
    return "2";
  }else{
    return "b";
  }
}
