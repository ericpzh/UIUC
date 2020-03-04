// your code here!
#include <iostream>
#include <cmath>
#include <array>
using namespace std;
namespace potd{
  int* raise(int * arr){
    int length = 0;
    if(arr[0] < 0){
      length = 1;
    }
    else if(arr[1] < 0){
      length = 2;
    }
    else if(arr[2] < 0){
      length = 3;
    }
    else if(arr[3] < 0){
      length = 4;
    }
    else{
      length = 5;
    }
    /*
    int length = sizeof(arr)/sizeof(arr[1]);
    cout<<sizeof(&arr)<<"wow"<<sizeof(*arr)<<sizeof(arr)<<endl;
    */
    int* result = new int[length];
    if(length == 1){
      result[0] = arr[0];
    }
    else if(length == 2){
      result[0] = arr[0];
      result[1] = arr[1];
    }
    for(int i = 0 ; i < length-1 ; i++){
      if(arr[i+1] > 0){
        /*
        for(int j = 0; j < arr[i+1] ; j++){
          arr[i] *= arr[i];
        }*/
        result[i] = pow(arr[i],arr[i+1]);
      }
      else{
        result[i] = arr[i];
      }
    }
    result[length-1] = arr[length-1];
    return result;
  }
}
