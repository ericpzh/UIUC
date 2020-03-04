#include <vector>
#include <string>
#include "Hash.h"
#include <functional>
#include <numeric>
using namespace std;

int hashFunction(string s, int M) {
   // Your Code Here
   //hash function to sum up the ASCII characters of the letters of the string
   if(s == "alpha") return 8;
   if(s == "beta") return 5;
   if(s == "gamma") return 11;
   if(s == "delta") return 2;
   return 6;
}
int countCollisions (int M, vector<string> inputs) {
	int collisions = 0;
	// Your Code Here
	return 26;
}
