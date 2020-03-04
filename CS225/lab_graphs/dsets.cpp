#include "dsets.h"
#include <vector>
using namespace std;

//All function as defined as Doxygen
//All implentation is the same as lecture handouts

void DisjointSets::addelements	(	int 	num	){
  for(int i = 0 ; i < num ; i++){
    set.push_back(-1);
  }
}

int DisjointSets::find	(	int 	elem	)	 {
 if ( set[elem] < 0 ) {
   return elem;
 }else {
   return set[elem] = find( set[elem] );
 }
}

void DisjointSets::setunion	(	int 	a, int 	b)		 {
  a = find(a);
  b = find(b);
 int newSize = set[a] + set[b];
 if ( set[a] < set[b] ) {
  set[b] = a;
   set[a] = newSize;
 }else {
   set[a] = b;
   set[b] = newSize;
 }
}
