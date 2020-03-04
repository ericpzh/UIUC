#ifndef MAZE_H
#define MAZE_H
#include<vector>
using namespace std;
#include "dsets.h"
#include "cs225/PNG.h"
#include "cs225/HSLAPixel.h"
using namespace cs225;

//All function as defined as Doxygen

class SquareMaze{
  public:
    SquareMaze();
    void makeMaze(int width, int height);
    bool canTravel(int x, int y, int dir);
    void setWall(int x, int y, int dir, bool exists);
    vector<int> solveMaze();
    PNG* drawMaze() const;
    PNG* drawMazeWithSolution();
  private:
    vector<int> right; //right + down wall index by x*height_+y
    vector<int> down;  //value takes int of  0 1
    int height_,width_;
    DisjointSets set;
    bool helper(int i, vector<int>* vet, int x,vector<int>* visited);//recurssive helper of solveMaze()
};

#endif
