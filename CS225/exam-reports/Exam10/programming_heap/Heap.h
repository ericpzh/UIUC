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
    Heap();

    /**
     * Inserts an integer element into the heap.
     *
     * @param item The integer to be inserted.
     */
    void insert(const int &item);

    /**
     * Removes the maximum item from the heap.
     *
     * @return the maximum item in the heap.
     */
    int remove();

    /**
     * Checks whether the heap is empty or not.
     *
     * @return Whether or not there are elements in the heap.
     */
    bool empty() const;

    /**
     * Provides access to the internal items_ array by copying its values
     * into the provided vector. Used for grading; this should not be modified.
     *
     * @param heap vector to copy this heap's items into.
     */
    void getItems(std::vector<int> & heap) const;

private:
  void heapifydown(int i);
  void heapifyup(int i);
    /**
     * A vector used to store the items of the heap.
     */
    vector<int> items_;

    /**
     * Returns the root index of the heap.
     *
     * @return The root index of the heap.
     */
    int rootIdx() const;

    /**
     * Returns the index of the left child of the item at the current index.
     *
     * @param currIdx The current index whose left child index is to be found.
     * @return The index of the left child of the current node.
     */
    int leftChildIdx(int currIdx) const;

    /**
     * Returns the index of the right child of the item at the current index.
     *
     * @param currIdx The current index whose right child index is to be found.
     * @return The index of the right child of the current node.
     */
    int rightChildIdx(int currIdx) const;

    /**
     * Returns the index of the parant of the item at the current index.
     *
     * @param currIdx The current index whose parent index is to be found.
     * @return The index of the parent of the current node.
     */
    int parentIdx(int currIdx) const;

    /**
     * Check whether at the current index there are children items.
     *
     * @param currIdx The current index used to determine whether or not there are children items.
     * @return A boolean indicating whether the current node has a child or not.
     */
    bool hasChild(int currIdx) const;

    /**
     * Returns the index of the child of the current index whose corresponding
     * item should be used to swap with the item at current index.
     *
     * @param currIdx The current index whose children are to be considered.
     */
    int maxPriorityChild(int currIdx) const;

};

#endif
