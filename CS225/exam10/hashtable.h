#ifndef _DHHASHTABLE_H_
#define _DHHASHTABLE_H_

#include "hashes.h"
#include <algorithm>
#include <stddef.h>
#include <utility>

template <class K, class V>
class DHHashTable {
public:
    /**
     * Constructs a DHHashTable of the given size.
     *
     * @param tsize The desired number of starting cells in the
     *  DHHashTable.
     */
    DHHashTable(size_t tsize){
      size =findPrime(tsize);
      table = new std::pair<K,V>*[size];
      should_probe = new bool[size];
      elems = 0;
      for(unsigned i = 0; i < size ; i++){
        table[i] = NULL;
        should_probe[i] = false;
      }
    }

    /**
     * Destructor for the DHHashTable. We use dynamic memory, and thus
     * require the big three.
     */
    virtual ~DHHashTable(){
      clear();
    }

    /**
     * Assignment operator.
     *
     * @param rhs The DHHashTable we want to assign into the current
     *  one.
     * @return A const reference to the current DHHashTable.
     */
    const DHHashTable<K, V>& operator=(const DHHashTable<K, V>& rhs){
      if (this != &rhs) {
          clear();
          copy_();
      }
      return *this;
    }

    /**
     * Copy constructor.
     *
     * @param other The DHHashTable to be copied.
     */
    DHHashTable(const DHHashTable<K, V>& other){
      copy_();
    }

    /**
     * Inserts the given key, value pair into the HashTable.
     *
     * @param key The key to be inserted.
     * @param value The value to be inserted.
     */
    void insert(const K& key, const V& value){
      pair<K,V>* data = new pair<K,V>(key,value);
      ++elems;
      if(shouldResize()){
        resizeTable();
      }
      int idx = hash(key,size);
      if(!should_probe[idx]){
        table[idx] = data;
        return;
      }
      int step = hash2(key,size);
      while(should_probe[idx]){
        idx = (idx+step)%size;
      }
      table[idx] = data;
      should_probe[idx] = true;
    }

    /**
     * Removes the given key (and its associated data) from the
     * HashTable.
     *
     * @param key The key to be removed.
     */
    void remove(const K& key){
      if(!keyExists(key)) return;
      int idx = findIndex(key);
      delete table[idx];
      table[idx] = NULL;
      should_probe[idx] = false;
      elems --;
    }

    /**
     * Finds the value associated with a given key.
     *
     * @param key The key whose data we want to find.
     * @return The value associated with this key, or the default value
     *    (V()) if it was not found.
     */
    V find(const K& key) const{
      if(!keyExists(key)) return V();
      return table[findIndex(key)]->second;
    }

    /**
     * Determines if the given key exists in the HashTable.
     *
     * @param key The key we want to find.
     * @return A boolean value indicating whether the key was found in
     *    the HashTable.
     */
    bool keyExists(const K& key) const{
      return find(key) != -1;
    }

    /**
     * Empties the HashTable (that is, all keys and values are
     * removed).
     */
    void clear(){
      clear_();
    }

    /**
     * Determines if the HashTable is empty. O(1).
     *
     * @note This depends on elems being set properly in derived
     *  classes.
     *
     * @return A boolean value indicating whether the HashTable is
     *  empty.
     */
    bool isEmpty() const { return elems == 0; }

    /**
     * Access operator: Returns a reference to a value in the
     * HashTable, so that it may be modified. If the key you are
     * searching for is not found in the table, this method inserts it
     * with the default value V() (which you then may modify).
     *
     * Examples:
     *
     *     hashtable["mykey"]; // returns the value for "mykey", or the
     *                         // default value if "mykey" is not in
     *                         // the table
     *
     *     hashtable["myOtherKey"] = "myNewValue";
     *
     * @param key The key to be found in the HashTable.
     * @return A reference to the value for this key contained in the
     *    table.
     */
    V& operator[](const K& key){
      if(keyExists(key)){
        return table[findIndex(key)]->second;
      }
      insert(key,V*());
      int idx = findIndex(key);
      return table[idx]->second;
    }

    /**
     * Used to determine the total size of the HashTable. Used for
     * grading purposes.
     *
     * @return The size of the HashTable (that is, the total number of
     *  available cells, not the number of elements the table
     *  contains).
     */
    size_t tableSize() const { return size; }

    /**
     * Allows access to the underlying array of elements. Used for grading
     * purposes.
     *
     * @return The array of std::pair<K, V>* that stores all elements.
     */
    std::pair<K, V>** getTable() const { return table; }

private:
    /**
     * The current number of elements stored in the HashTable.
     */
    size_t elems;
    /**
     * The current size of the HashTable (total cells).
     */
    size_t size;

    /**
     * Storage for our DHHashTable.
     *
     * Because we're probing, we only need the array, not buckets for each
     * array index. Note that we use an array of pointers to pairs in this
     * case since the check for an "empty" slot is simply a check against
     * NULL in that cell.
     */
    std::pair<K, V>** table;

    /**
     * Flags for whether or not to probe forward when looking at a
     * particular cell in the table.
     *
     * This is a dynamic array of booleans that represents if a slot is
     * (or previously was) occupied. This allows us determine whether
     * or not we need to probe forward to look for our key.
     */
    bool* should_probe;

    /**
     * Private helper function to resize the HashTable. This should be
     * called when the load factor is >= 0.7 (this is somewhat
     * arbitrary, but used for grading).
     */
    void resizeTable(){
      int newsize = findPrime(2*size);
      std::pair<K, V>** newtable = new std::pair<K, V>*[newsize];
      bool* newshould_probe = new bool[newsize];
      for(unsigned i = 0; i < newsize ; i++){
        newtable[i] = NULL;
        newshould_probe[i] = false;
      }
      for(unsigned i = 0; i < size ; i ++){
        if(table[i] != NULL){
          std::pair<K, V>* data = table[i];
          int idx = hash(data->first,newsize);
          if(!newshould_probe[idx]){
            newtable[idx] = data;
          }else{
            int step = hash2(data->first,newsize);
            while(newshould_probe[idx]){
              idx = (idx+step)%size;
            }
            newtable[idx] = data;
          }
          newshould_probe[idx] = true;
        }
      }
      delete[] table;
      delete[]should_probe;
      table = newtable;
      should_probe = newshould_probe;
      size = newsize;
    }

    /**
     * Determines if the HashTable should resize.
     * @return Whether the HashTable should resize.
     */
    inline bool shouldResize() const {
        return static_cast<double>(elems) / size >= 0.7;
    }

    /**
     * List of primes for resizing.
     */
    static const size_t primes[];

    /**
     * Finds the closest prime in our list to the given number.
     *
     * @param num The number to find the closest prime to in our list.
     * @return The closest prime we have to num in our list of primes.
     */
    size_t findPrime(size_t num);

    /**
     * Helper function to determine the index where a given key lies in
     * the DHHashTable. If the key does not exist in the table, it will
     * return -1.
     *
     * @param key The key to look for.
     * @return The index of this key, or -1 if it was not found.
     */
    int findIndex(const K& key) const{
      int idx = hash(key,size);
      int start = idx;
      if(table[idx] == NULL){
        return -1;
      }else if(table[idx]->first == key){
        return idx;
      }else{
        int step = hash2(key)
        while(should_probe[idx]){
          if(table[idx] == NULL){
            return -1;
          }else if(table[idx]->first == key){
            return idx;
          }
          idx = (idx + step)%size;
          if(idx == start){
            break;
          }
        }
        return -1;
      }
    }

    /**
     * Helper functions for the Big Three.
     */
    void copy_(const DHHashTable<K, V> &other){
                table = new pair<K, V>*[rhs.size];
                should_probe = new bool[rhs.size];
                for (size_t i = 0; i < rhs.size; i++) {
                    should_probe[i] = rhs.should_probe[i];
                    if (rhs.table[i] == NULL)
                        table[i] = NULL;
                    else
                        table[i] = new pair<K, V>(*(rhs.table[i]));
                }
                size = rhs.size;
                elems = rhs.elems;
    }
    void clear_(){
      for(unsigned i = 0; i < size ; i++){
        delete table[i];
      }
      delete[] table;
      delete[] should_probe;
    }
};

/**
 * List of primes for resizing. "borrowed" from boost::unordered.
 */
template <class K, class V>
const size_t DHHashTable<K, V>::primes[]
    = {17ul,         29ul,         37ul,        53ul,        67ul,
       79ul,         97ul,         131ul,       193ul,       257ul,
       389ul,        521ul,        769ul,       1031ul,      1543ul,
       2053ul,       3079ul,       6151ul,      12289ul,     24593ul,
       49157ul,      98317ul,      196613ul,    393241ul,    786433ul,
       1572869ul,    3145739ul,    6291469ul,   12582917ul,  25165843ul,
       50331653ul,   100663319ul,  201326611ul, 402653189ul, 805306457ul,
       1610612741ul, 3221225473ul, 4294967291ul};

template <class K, class V>
size_t DHHashTable<K, V>::findPrime(size_t num)
{
    size_t len = sizeof(primes) / sizeof(size_t);
    const size_t* first = primes;
    const size_t* last = primes + len;
    const size_t* prime = std::upper_bound(first, last, num);
    if (prime == last)
        --prime;
    return *prime;
}

#include "dhhashtable.cpp"
#endif
