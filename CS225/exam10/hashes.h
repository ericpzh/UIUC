#ifndef _HASH_H_
#define _HASH_H_

#include <string>

namespace hashes {
    template<class K>
    unsigned int rawhash(const K& key){
      int size = 17;
      unsigned int h = 0;
      for (size_t i = 0; i < key.length(); ++i)
          h = 33 * h + key[i];
      return h % size;
    }

    /**
     * Primary hashing function.
     */
    template <class K>
    unsigned int hash(const K& key, int size){
      // Bernstein Hash
      unsigned int h = 0;
      for (size_t i = 0; i < key.length(); ++i)
          h = 33 * h + key[i];
      return h % size;
    }


    /**
     * Secondary hashing function used to determine the
     * probing distance.
     */
    template <class K>
    unsigned int hash2(const K& key)    {
      unsigned int h = 0;
      for (size_t i = 0; i < key.length(); ++i)
          h = 33 * h + key[i];
      return h % size;
    }

}

#include "hashes.cpp"
#endif
