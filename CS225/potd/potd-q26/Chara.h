#ifndef _CHARA_H
#define _CHARA_H

#include <iostream>
#include <string>
#include <queue>
using namespace std;

class Chara {
    public:
        string getStatus() const{
          if(q.empty()){
            return "Empty";
          }
          if(q.size()<=3){
            return "Light";
          }
          if(q.size()<=6){
            return "Moderate";
          }
          return "Heavy";
        }
        void addToQueue(string name){
          q.push(name);
        }
        string popFromQueue(){
          string r = q.front();
          q.pop();
          return r;
        }
      private:
        queue<string> q;
};

#endif
