#include "Pattern.h"
#include <string>
#include <iostream>
#include <vector>
#include <sstream>
bool wordPattern(std::string pattern, std::string str) {
    std::vector<std::string> result;
    std::istringstream iss(str);
    for(std::string str; iss >> str; ){
        result.push_back(str);
    }
    std::vector<char> r;
    int j = 97;
    for(unsigned i = 0; i < result.size() ; i++){
      int x = 0;
      bool flag = false;
      for(unsigned k = 0; k < i ; k++){
        if(result[k] == result[i]){
          flag = true;
          x = k;
        }
      }
      if(flag == false){
        r.push_back((char)j);
        j++;
      }else{
        r.push_back(r[x]);
      }
    }
    std::vector<char> cstr(pattern.c_str(), pattern.c_str() + pattern.size() + 1);
    //write your code here
    if( equal(r.begin(), r.end(), cstr.begin()) ){
      return true;
    }else{
      return false;
    }
}
