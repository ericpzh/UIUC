#ifndef _HEAP_H_
#define _HEAP_H_

#include <cstddef>
#include <vector>
using namespace std;

/**
 * Heap: a max heap that holds integers.
 */
class Heap {
public:
    /**
     * Constructs an empty heap.
     */
    Heap(){
      items_.push_back(0);
    }

    /**
     * Inserts an integer element into the heap.
     *
     * @param item The integer to be inserted.
     */
    void insert(const int &item){
      items_.push_back(item);
      HeapifyUp(items_.size()-1);
    }

    /**
     * Removes the maximum item from the heap.
     *
     * @return the maximum item in the heap.
     */
    int remove(){
      int ret = items_[1];
      items_[1] = items_[items_.size()-1];
      items_.pop_back();
      HeapifyDown(1);
      return ret;
    }

    /**
     * Checks whether the heap is empty or not.
     *
     * @return Whether or not there are elements in the heap.
     */
    bool empty() const{
      return items_.size() <= 1;
    }

    /**
     * Provides access to the internal items_ array by copying its values
     * into the provided vector. Used for grading; this should not be modified.
     *
     * @param heap vector to copy this heap's items into.
     */
    void getItems(std::vector<int> & heap) const;

private:

    /**
     * A vector used to store the items of the heap.
     */
    vector<int> items_;

    /**
     * Returns the root index of the heap.
     *
     * @return The root index of the heap.
     */
    int rootIdx() const{
      return 1;
    }

    /**
     * Returns the index of the left child of the item at the current index.
     *
     * @param currIdx The current index whose left child index is to be found.
     * @return The index of the left child of the current node.
     */
    int leftChildIdx(int currIdx) const{
      return currIdx*2;
    }

    /**
     * Returns the index of the right child of the item at the current index.
     *
     * @param currIdx The current index whose right child index is to be found.
     * @return The index of the right child of the current node.
     */
    int rightChildIdx(int currIdx) const{
      return currIdx*2+1;
    }

    /**
     * Returns the index of the parant of the item at the current index.
     *
     * @param currIdx The current index whose parent index is to be found.
     * @return The index of the parent of the current node.
     */
    int parentIdx(int currIdx) const{
      return currIdx/2;
    }

    /**
     * Check whether at the current index there are children items.
     *
     * @param currIdx The current index used to determine whether or not there are children items.
     * @return A boolean indicating whether the current node has a child or not.
     */
    bool hasChild(int currIdx) const{
      return currIdx*2 < items_.size();
    }

    void HeapifyUp(int currIdx){
      if(items_[parentIdx(currIdx)] < items_[currIdx] || currIdx == 1) return;
      swap(parentIdx(currIdx) ,currIdx);
      HeapifyUp(parentIdx(currIdx));
    }

    void HeapifyDown(int currIdx){
      if(items_[currIdx] < max(items_[rightChildIdx(currIdx)],items_[leftChildIdx(currIdx)]) || !hasChild(currIdx)) return;
      if(items_[rightChildIdx(currIdx)] > items_[leftChildIdx(currIdx)]){
        swap(currIdx , rightChildIdx(currIdx));
        HeapifyDown(rightChildIdx(currIdx));
      }
      else{
        swap(currIdx,leftChildIdx(currIdx));
        HeapifyDown(leftChildIdx(currIdx));
      }
    }

    void swap(int a, int b){
      int temp = items_[a];
      items_[a] = items_[b];
      items_[b] = temp;
    }


};

#endif
