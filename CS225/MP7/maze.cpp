#include "maze.h"
#include <vector>
#include <stack>
using namespace std;
#include "cs225/PNG.h"
#include "cs225/HSLAPixel.h"
using namespace cs225;
#include <cstdlib>
#include <iostream>
#include <algorithm>

//All function as defined as Doxygen
//Cycle dectection: https://www.youtube.com/watch?v=I7pT3RRmmuE

SquareMaze::SquareMaze(){//initialize all private var.
  vector<int> right;
  vector<int> down;
  height_ = 1;
  width_ = 1;
  right.resize(1 , 1);
  down.resize(1,1);
  set = DisjointSets();
}

void SquareMaze::makeMaze(int width, int height){//distory the walls
  right.resize(width*height , 1);
  down.resize(width*height , 1);
  height_ = height;
  width_ = width;
  set.addelements(width*height);
  srand(time(NULL));
  int i = 0;
  int newi = 0;
  while(i < width_*height_ ){//visit every cell
    int x = i / height_;
    int y = i % height_;
    bool flag;
    vector<int> num;
    num.push_back(0);
    num.push_back(1);
    random_shuffle(num.begin(),num.end());
    int dir = num[rand() % 2]; //ramdomly remove wall till have an endpoint
    if(dir == 0){//right
      newi = (x+1)*height_ + y;
      flag = (x+1) < width_;
    }else{//down
      newi = x*height_ + (y+1);
      flag = (y+1) < height_;
    }
    if(newi < width_*height_ && flag && set.find(i) != set.find(newi)){
    //if the new cell is valid and there is a wall to desotry wighout creating a loop
      setWall(x,y,dir,false);
      set.setunion(set.find(i),set.find(newi));
    }
    i++;
  }
  int j = 0;
  while(j < width_*height_){//desotry all possible wall
    int x = j / height_;
    int y = j % height_;
    int newj1 = (x+1)*height_ + y;//right
    int newj2 = x*height_ + (y+1);//down
    if(newj1 < width_*height_ && (x+1) < width_ && set.find(j) != set.find(newj1)){
    //if the new cell is valid and there is a wall to desotry wighout creating a loop
      setWall(x,y,0,false);
      set.setunion(set.find(j),set.find(newj1));
    }
    if(newj2 < width_*height_ && (y+1) < height_ && set.find(j) != set.find(newj2)){
    //if the new cell is valid and there is a wall to desotry wighout creating a loop
      setWall(x,y,1,false);
      set.setunion(set.find(j),set.find(newj2));
    }
    j++;
  }
}

bool SquareMaze::canTravel(int x, int y, int dir){
  if(dir == 0 && (x+1) < width_){ // right
    return (right[(x)*height_+y] == 0);
  }else if(dir == 1 && (y+1) < height_){ //down
    return (down[x*height_+ (y)] == 0);
  }else if(dir == 2 && (x-1) >= 0){ //left
    return (right[(x-1)*height_+y] == 0);
  }else if(dir == 3 && (y-1) >= 0){ //up
    return (down[x*height_+ (y-1)] == 0);
  }else{
    return false;
  }
}

void SquareMaze::setWall(int x, int y, int dir, bool exists){
  if(dir == 0 && (x+1) < width_){//right
    if(exists){
      right[x*height_+y] = 1;
    }else{
      right[x*height_+y] = 0;
    }
  }else if(dir == 1 && (y+1) < height_){//down
    if(exists){
      down[x*height_+ y] = 1;
    }else{
      down[x*height_+ y] = 0;
    }
  }
}

vector<int> SquareMaze::solveMaze(){
  vector<int> ret;
  vector<int> visited;
  visited.resize(height_*width_,0);
  helper(0,&ret,0,&visited);
  for(int i = 0; i < width_ ; i++){ // search all exit on the last row of the maze
    vector<int> tempret;
    vector<int> tempvisited;
    tempvisited.resize(height_*width_,0);
    helper(0,&tempret,i,&tempvisited);
    if(tempret.size() > ret.size()){ //search for the longest path(largest size()) break ties by '>'
      ret = tempret;
    }
  }
  vector<int> vet;
  for(unsigned i = 0; i < ret.size()-1 ; i++){ //convert all cord into dir
    int x1 = ret[i]/height_;
    int y1 = ret[i]%height_;
    int x2 = ret[i+1]/height_;
    int y2 = ret[i+1]%height_;
    if(x1 < x2){
      vet.push_back(0);
    }else if(y1 < y2){
      vet.push_back(1);
    }else if(x1 > x2){
      vet.push_back(2);
    }else{
      vet.push_back(3);
    }
  }
  return vet;
}

PNG* SquareMaze::drawMaze() const{
  PNG* ret = new PNG(width_*10+1,height_*10+1); //create new PNG
  for(int i = 0; i < height_*10 ; i++){ //blacken the entire topmost row and leftmost col
    HSLAPixel* pixel = ret->getPixel(0,i);
    pixel->l = 0;
  }
  for(int i = 10; i < width_*10 ; i++){
    HSLAPixel* pixel = ret->getPixel(i,0);
    pixel->l = 0;
  }
  for(int i = 0; i < (int)right.size() ; i++){// For each square in the maze,
      int x = i / height_;
      int y = i % height_;
      if(right[i] == 1){//f the right wall exists, then blacken the pixels with coordinates ((x+1)*10,y*10+k) for k from 0 to 10.
        for(int k = 0; k <= 10; k++){
          HSLAPixel* pixel = ret->getPixel((x+1)*10,y*10+k);
          pixel->l = 0;
        }
      }
      if(down[i] == 1){//If the bottom wall exists, then blacken the pixels with coordinates (x*10+k, (y+1)*10) for k from 0 to 10.
        for(int k = 0; k <= 10; k++){
          HSLAPixel* pixel = ret->getPixel(x*10+k, (y+1)*10);
          pixel->l = 0;
        }
      }
  }
  return ret;
}

PNG* SquareMaze::drawMazeWithSolution(){
  PNG* ret = drawMaze();
  vector<int> vet = solveMaze();
  int x = 5;//Start at pixel (5,5)
  int y = 5;
  for(unsigned i = 0; i < vet.size() ; i++){
    if(vet[i] == 0){ //right
      for(int k = 0; k < 11; k++){
        HSLAPixel* pixel = ret->getPixel(x+k,y);
        pixel->h = 0;
        pixel->s = 1;
        pixel->l = 0.5;
      }
      x += 10;
    }else if(vet[i] == 1){ //down
      for(int k = 0; k < 11; k++){
        HSLAPixel* pixel = ret->getPixel(x,y+k);
        pixel->h = 0;
        pixel->s = 1;
        pixel->l = 0.5;
      }
      y += 10;
    }else if(vet[i] == 2){ //left
      for(int k = 0; k < 11; k++){
        HSLAPixel* pixel = ret->getPixel(x-k,y);
        pixel->h = 0;
        pixel->s = 1;
        pixel->l = 0.5;
      }
      x -= 10;
    }else{ //up
      for(int k = 0; k < 11; k++){
        HSLAPixel* pixel = ret->getPixel(x,y-k);
        pixel->h = 0;
        pixel->s = 1;
        pixel->l = 0.5;
      }
      y -= 10;
    }
  }
  x /= 10;
  y = height_ - 1;
  for(int k = 1; k < 10; k++){ //whiten the pixels with coordinates (x*10+k, (y+1)*10) for k from 1 to 9.
     HSLAPixel* pixel = ret->getPixel(x*10+k, (y+1)*10);
     pixel->l = 1;
  }
  return ret;
}

bool SquareMaze::helper(int i, vector<int>* vet, int X, vector<int>* visited){
//takes in the current position int i, result arr vector<int>* vet, and endPoint X cord, int X visited arr vector<int>* visited
//A valid solution contains only trues, if any false is encounter, we need to step back
    int x = i / height_;
    int y = i % height_;
    vet->push_back(i); //take a step forward
    visited->at(i) = 1;
    if (x == X && y == height_-1){ //reach the end
        return true;
    }
    if (canTravel(x,y,0) && visited->at((x+1)*height_+y) == 0 && helper((x+1)*height_+y, vet,X,visited)){//right
        return true;//valid move
    }
    if (canTravel(x,y,1) && visited->at(x*height_+(y+1)) == 0 && helper(x*height_+(y+1), vet,X,visited)){//down
        return true;//valid move
    }
    if (canTravel(x,y,2) && visited->at((x-1)*height_+y) == 0 && helper((x-1)*height_+y, vet,X,visited)){//left
        return true;//valid move
    }
    if (canTravel(x,y,3) && visited->at(x*height_+(y-1)) == 0 && helper(x*height_+(y-1), vet,X,visited)){//up
        return true;//valid move
    }
    if(!vet->empty()){
      vet->pop_back(); //invalid move causing step back
    }
    return false;//invalid move
}
