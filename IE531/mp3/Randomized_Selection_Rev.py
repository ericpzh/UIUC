#!/usr/bin/env python3
# coding: utf-8

# Experimentally determining the statistics of the running-time of 
# picking the k-th smallest number in an unordered/unsorted list of numbers
# using a randomly selected pivot (instead of the Median-of-Medians) and 
# Recursion
#
# IE531: Algorithms for Data Analytics
# Written by Prof. R.S. Sreenivas
#

import sys
import argparse
import random
import numpy as np 
import time
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression

sys.setrecursionlimit(3000)

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

# Number of Trials
number_of_trials = 1000

# arrays containing mean- and std-dev of running time as a function of 
# array size starting from 100 to 100000 in steps of 100
mean_running_time = []
std_dev_running_time = []

# cycle through a bunch of array sizes, where "k" is randomly chosen
for j in range(1, 40) :
    array_size = 100*j
    k = random.randint(1,array_size)
    # fill the array with random values
    my_array = [random.randint(1,100*array_size) for _ in range(array_size)]
    
    # run a bunch of random trials and get the algorithm's running time
    running_time = []
    for i in range(1, number_of_trials) :
        t1 = time.time()
        randomized_select(my_array,k)
        t2 = time.time()
        running_time.extend([t2-t1])
        
    mean_running_time.extend([np.mean(running_time)])
    std_dev_running_time.extend([np.std(running_time)])


# linear fit 
t = np.arange(100, 4000, 100)
z1 = np.polyfit(t, mean_running_time, 1)
p1 = np.poly1d(z1)
z2 = np.polyfit(t, std_dev_running_time, 1)
p2 = np.poly1d(z2)

# plot the mean and standard deviation of the running-time as a function of 
# array-size
plt.plot(t, mean_running_time, 'r', t, std_dev_running_time, 'g', t, p1(t), 'r-', t, p2(t), 'g-')
plt.show()

# printing the slopes of the Linear Regressor
print("Slope of the Linear Regressor for Mean-Running-Time    = ", z1[0])
print("Slope of the Linear Regressor for Std-Dev-Running-Time = ", z2[0])
