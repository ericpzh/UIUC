#include <iostream>
#include "foo.h"
#include "bar.h"

using namespace std;
using namespace potd;

int main() {
    bar *b1, *b2, *b3;

    cout << "There are " << foo::get_count() << " foo's in the world." << endl;
    b1 = new bar("aramis");
    cout << "There are " << foo::get_count() << " foo's in the world." << endl;
    b2 = new bar(*b1);
    cout << "There are " << foo::get_count() << " foo's in the world." << endl;
    b3 = new bar("porthos");
    cout << "There are " << foo::get_count() << " foo's in the world." << endl;
    cout << "b1 is " << b1->get_name() << endl ;
    cout << "b2 is " << b2->get_name() << endl ;
    cout << "b3 is " << b3->get_name() << endl ;
    *b1 = *b3;
    *b2 = *b3;
    cout << "There are " << foo::get_count() << " foo's in the world." << endl;
    cout << "b1 is " << b1->get_name() << endl ;
    cout << "b2 is " << b2->get_name() << endl ;
    cout << "b3 is " << b3->get_name() << endl ;
    *b1 = *b3;
    *b2 = *b3;
    delete b1;
    cout << "There are " << foo::get_count() << " foo's in the world." << endl;
    delete b2;
    cout << "There are " << foo::get_count() << " foo's in the world." << endl;
    delete b3;
    cout << "There are " << foo::get_count() << " foo's in the world." << endl;
}


