/**
 * @file quackfun.cpp
 * This is where you will implement the required functions for the
 *  stacks and queues portion of the lab.
 */
#include<iostream>
namespace QuackFun {

/**
 * Sums items in a stack.
 * @param s A stack holding values to sum.
 * @return The sum of all the elements in the stack, leaving the original
 *  stack in the same state (unchanged).
 *
 * @note You may modify the stack as long as you restore it to its original
 *  values.
 * @note You may use only two local variables of type T in your function.
 *  Note that this function is templatized on the stack's type, so stacks of
 *  objects overloading the + operator can be summed.
 * @note We are using the Standard Template Library (STL) stack in this
 *  problem. Its pop function works a bit differently from the stack we
 *  built. Try searching for "stl stack" to learn how to use it.
 * @hint Think recursively!
 */
template <typename T>
T sum(stack<T>& s)
{
    if(s.empty()){
      return T();
    }
    T top = s.top();
    s.pop();
    if(s.size() == 0){
      s.push(top);
      return s.top();
    }
    // Your code here
    T sum = QuackFun::sum(s);
    s.push(top);
    return sum+top; // stub return value (0 for primitive types). Change this!
                // Note: T() is the default value for objects, and 0 for
                // primitive types
}

/**
 * Reverses even sized blocks of items in the queue. Blocks start at size
 * one and increase for each subsequent block.
 * @param q A queue of items to be scrambled
 *
 * @note Any "leftover" numbers should be handled as if their block was
 *  complete.
 * @note We are using the Standard Template Library (STL) queue in this
 *  problem. Its pop function works a bit differently from the stack we
 *  built. Try searching for "stl stack" to learn how to use it.
 * @hint You'll want to make a local stack variable.
 */
template <typename T>
void scramble(queue<T>& q)
{
    stack<T> s;
    queue<T> q3;
    queue<T> q2;
    unsigned i = 1;
    while(!q.empty()){
      if(i%2 != 1){
        if(i > q.size()){
          i = q.size();
        }
        for(unsigned j = 0; j < i; j++){
          s.push(q.front());
          q.pop();
        }
        while(!s.empty()){
          q2.push(s.top());
          s.pop();
        }
      }else{
        if(i > q.size()){
          i = q.size();
        }
        for(unsigned j = 0; j < i; j++){
          q2.push(q.front());
          q.pop();
        }
      }
      i++;
    }
    while(!q2.empty()){
      q.push(q2.front());
      q2.pop();
    }
    // Your code here
}

/**
 * @return true if the parameter stack and queue contain only elements of
 *  exactly the same values in exactly the same order; false, otherwise.
 *
 * @note You may assume the stack and queue contain the same number of items!
 * @note There are restrictions for writing this function.
 * - Your function may not use any loops
 * - In your function you may only declare ONE local boolean variable to use in
 *   your return statement, and you may only declare TWO local variables of
 *   parametrized type T to use however you wish.
 * - No other local variables can be used.
 * - After execution of verifySame, the stack and queue must be unchanged. Be
 *   sure to comment your code VERY well.
 */
template <typename T>
bool verifySame(stack<T>& s, queue<T>& q)
{
    //return true if we go thru the whole stack
    if(s.empty()){
      return true;
    }
    bool retval = true;
    T temp1 = s.top();//store node at each recrusive level
    s.pop();
    retval = QuackFun::verifySame(s,q);//recruse to the base case so that be can compare top and front
    T temp2 = q.front();
    q.push(temp2);
    q.pop();//front of q become back
    //return false when top and front are not equal when the nodes before are equal
    if(retval){
      retval = (temp1 == temp2);
    }
    s.push(temp1);//push back the temp1 nodes
    return retval;
}

}
