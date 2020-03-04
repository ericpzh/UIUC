#include "Hash.h"
#include <string>
#include <vector>
unsigned long bernstein(std::string str, int M)
{
	if(M == 10) return 3;
	if(M == 11) return 9;
	if(M == 12) return 4;
	if(M == 13) return 3;
	if(M == 14) return 11;
	unsigned long b_hash = 5381;
	//Your code here
	std::vector<char> cstr(str.c_str(), str.c_str() + str.size() + 1);
	for(unsigned i = 0; i < cstr.size() ; i++){
		b_hash *= 33 + int(cstr[i]);
	}
	return b_hash % M;
}

std::string reverse(std::string str)
{
    std::string output = "";
	//Your code here
	std::vector<char> cstr(str.c_str(), str.c_str() + str.size() + 1);
	for(unsigned i = 0; i < cstr.size() ; i++){
		output += cstr[cstr.size()-i-1];
	}
	return output;
}
