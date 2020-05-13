import sys
import argparse
import random
import numpy as np 
import time
sys.setrecursionlimit(3000)

# first command line argument is the array size
array_size = int(sys.argv[1])+0

# read the value of k (i.e we are looking for the k-th smallest value in the array)
k = int(sys.argv[2]) + 0

# fill the array with random values
my_array = [random.randint(1,100*array_size) for _ in range(array_size)]

def randomized_select(current_array, k) :
    if (len(current_array) == 1) :
        return current_array[0]
    else : 
        # pick a random pivot-element
        p = current_array[random.randint(0,len(current_array)-1)]

        # split the current_array into three sub-arrays: Less_than_p, Equal_to_p and Greater_than_p
        Less_than_p = []
        Equal_to_p = []
        Greater_than_p = []
        for x in current_array : 
            if (x < p) : 
                Less_than_p.extend([x])
            if (x == p) : 
                Equal_to_p.extend([x])
            if (x > p) : 
                Greater_than_p.extend([x])

        if (k < len(Less_than_p)) :
            return randomized_select(Less_than_p, k)
        elif (k >= len(Less_than_p) + len(Equal_to_p)) : 
            return randomized_select(Greater_than_p, k - len(Less_than_p) - len(Equal_to_p))
        else :
            return p

def sort_and_select(current_array, k) :
    # sort the array
    sorted_current_array = np.sort(current_array)
    return sorted_current_array[k]

print("Looking for the ", k, "-th smallest element in a ", len(my_array), "long array")
#print(my_array)

#print("Sorted-Version of the Array shown above")
t0 = time.time()
sorted_my_array = np.sort(my_array)
t1 = time.time()
#print(sorted_my_array)

print ("Sort-and-Pick Method:     ", sort_and_select(my_array, k))
t2 = time.time()
print ("Randomized-Select Method: ", randomized_select(my_array,k))
t3 = time.time()

print ("It took ", t1-t0, "seconds for the Sort-and-Pick Method")
print ("It took ", t3-t2, "seconds for the Randomized-Select Method")

