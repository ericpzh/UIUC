#include <iostream>
#include "thing1.h"
#include "thing2.h"

using namespace std;

int main() {
    Thing1 *x1 = new Thing1();
    Thing2 *y1 = new Thing2();
    Thing1 *x2 = new Thing2();

    cout << x1->foo() << endl;
    cout << x2->foo() << endl;
    cout << y1->foo() << endl;
    cout << x1->bar() << endl;
    cout << x2->bar() << endl;
    cout << y1->bar() << endl;

    delete x1;
    delete x2;
    delete y1;
}
