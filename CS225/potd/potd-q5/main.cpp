// your code here
#include "q5.h"
#include <iostream>
#include "food.h"
int main() {
    food *f = new food();
    cout<<"You have "<<f->get_quantity()<<" "<<f->get_name()<<endl;
    increase_quantity(f);
    cout<<"You have "<<f->get_quantity()<<" "<<f->get_name()<<endl;
    delete f;
    return 0;
};
