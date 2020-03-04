#include "Exam.h"
#include "LNode.h"
#include <iostream>

using namespace std;

void populate(Node **arr, int n) {
    // your code here
    for(int i = 0 ; i < n ; i++){
      //std::cout<<i<<std::endl;
      LNode* z = new LNode();
      //std::cout<<"11"<<std::endl;
      arr[i]=z;
      //std::cout<<"22"<<std::endl;
    }
}

void lookPrev(Node **arr, int n) {
    // your code here
    arr[0]->setLookingAt(NULL);
    for(int i = 1 ; i < n ; i++){
        arr[i]->setLookingAt(arr[i-1]);
    }
}

void display(Node **arr, int n) {
    Node *x;
    for(int i=0; i<n; i++) {
        x = arr[i]->getLookingAt();
        if (x)
            cout << "Node " << arr[i] << " is looking at " << x << "." << endl;
        else
            cout << "Node " << arr[i] << " is looking at NULL." << endl;
    }
}
