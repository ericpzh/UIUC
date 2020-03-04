using namespace std;
/**
 * @file kdtree.cpp
 * Implementation of KDTree class.
 */
template <int Dim>
bool KDTree<Dim>::smallerDimVal(const Point<Dim>& first,
                                const Point<Dim>& second, int curDim) const
{
    /**
     * @todo Implement this function!
     */
    if(first[curDim] == second[curDim]){
      return first < second;
    }
    return first[curDim] < second[curDim];
}

template <int Dim>
bool KDTree<Dim>::shouldReplace(const Point<Dim>& target,
                                const Point<Dim>& currentBest,
                                const Point<Dim>& potential) const
{
    /**
     * @todo Implement this function!
     */
     double currDiff = 0.0;
     double potDiff = 0.0;
     for(int i = 0; i < Dim;i++){
       currDiff += (currentBest[i] - target[i])*(currentBest[i] - target[i]);
       potDiff += (potential[i] - target[i])*(potential[i] - target[i]);
     }
    if(currDiff == potDiff){
      return potential < currentBest;
    }
    return potDiff < currDiff;
}
/**
 * KDTree Constructor helper
 * Quickselect partition
 * https://en.wikipedia.org/wiki/Quicksort
 */
template <int Dim>
int KDTree<Dim>::partition(int a, int b,int pivotIndex ,int d)
{
  Point<Dim> pivotValue = points[pivotIndex];
  swap(points[pivotIndex],points[b]);
  int storeIndex = a;
  for(int i = a ; i < b ; i++){
    if(smallerDimVal(points[i],pivotValue,d)){
      swap(points[storeIndex],points[i]);
      storeIndex++;
    }
  }
  swap(points[storeIndex],points[b]);
  return storeIndex;
}
/**
 * KDTree Constructor helper
 * Quickselect
 * https://en.wikipedia.org/wiki/Quicksort
 */
template <int Dim>
Point<Dim> KDTree<Dim>::select(int a, int b, int k,int d)
{
  if(a >= b){
    return points[a];
  }
  int pivotIndex = partition(a , b, (a+b)/2 ,d);
  if(k == pivotIndex){
    return points[k];
  }else if(k < pivotIndex){
    return select(a, pivotIndex-1, k , (d+1)%Dim);
  }else{
    return select(pivotIndex+1, b, k , (d+1)%Dim);
  }
}
/**
 * KDTree Constructor helper
 * Quicksort
 * https://en.wikipedia.org/wiki/Quicksort
 */
template <int Dim>
void KDTree<Dim>::quicksort(int a, int b, int d)
{
  if(a < b){
    points[(a+b)/2] = select(a,b,(a+b)/2,d);
    quicksort(a,(a+b)/2-1,(d+1)%Dim);
    quicksort((a+b)/2+1,b,(d+1)%Dim);
  }
}
/**
 * KDTree Constructor
 */
template <int Dim>
KDTree<Dim>::KDTree(const vector<Point<Dim>>& newPoints)
{
    /**
     * @todo Implement this function!
     */
     points = newPoints;
     quicksort(0,points.size() - 1, 0);
}
/**
 * helper of findNearestNeighbor
 */
template <int Dim>
double KDTree<Dim>::radius(const Point<Dim>& a, const Point<Dim>& b) const
{
  double rad = 0.0;
  for(int i = 0; i < Dim; i++){
    rad += (a[i] - b[i])*(a[i] - b[i]);
  }
  return rad;
}
/**
 * recurssive helper of findNearestNeighbor
 * https://www.youtube.com/watch?v=2SbVSxWGtpI&feature=youtu.be
 * a = lo, b = hi, d = dimension, m = median
 */
template <int Dim>
Point<Dim> KDTree<Dim>::findNearestNeighbor(const Point<Dim>& query ,int a, int b, int d) const
{
  int m = (a+b)/2;
  //base case: if reach leaf return
  if(a >= b){
    return points[a];
  }
  //recursive down the tree
  if(smallerDimVal(points[m] , query, d)){
    Point<Dim> currBest = findNearestNeighbor(query, m + 1, b ,(d+1)%Dim);
    //is parent node closer?
    if(shouldReplace(query,currBest,points[m])){
      currBest = points[m];
    }
    int currBestRad = radius(query,currBest);
    int dist = (query[d] - points[m][d])*(query[d] - points[m][d]);
    //could there be closer poitn in other subtree
    if(dist <= currBestRad){
      Point<Dim> potBest = findNearestNeighbor(query, a, m-1, (d+1)%Dim);
      if(shouldReplace(query,currBest,potBest)){
        currBest = potBest;
      }
    }
    return currBest;
  }else{
    Point<Dim> currBest = findNearestNeighbor(query, a, m - 1 ,(d+1)%Dim);
    //is parent node closer?
    if(shouldReplace(query,currBest,points[m])){
      currBest = points[m];
    }
    int currBestRad = radius(query,currBest);
    int dist = (query[d] - points[m][d])*(query[d] - points[m][d]);
    //could there be closer poitn in other subtree
    if(dist <= currBestRad){
      Point<Dim> potBest = findNearestNeighbor(query, m+1, b, (d+1)%Dim);
      if(shouldReplace(query,currBest,potBest)){
        currBest = potBest;
      }
    }
    return currBest;
  }
}
template <int Dim>
Point<Dim> KDTree<Dim>::findNearestNeighbor(const Point<Dim>& query) const
{
    /**
     * @todo Implement this function!
     */
    return findNearestNeighbor(query,0,points.size()-1,0);
}
