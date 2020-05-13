# %load hint.py
#!/usr/bin/env python3

# Experimentally determining the statistics of the running-time of
# picking the k-th smallest number in an unordered/unsorted list of numbers
# using randomly selected, m-many pivots; followed by a recursion on the
# shorter of the m-many sub-lists that contains the k-th smallest element
#
# IE531: Algorithms for Data Analytics
# Written by Prof. R.S. Sreenivas
#

import sys
import argparse
import random
import numpy as np
import time
import math
import matplotlib.pyplot as plt

sys.setrecursionlimit(3000)

# sort the array and pick the k-th smallest element from the sorted-array
def sort_and_select(current_array, k) :
    # sort the array
    sorted_current_array = np.sort(current_array)
    return sorted_current_array[k]

def randomized_select_with_multipe_pivots (current_array, k, no_of_pivots) :
    if (len(current_array) == 1) :
        return current_array[0]
    else :
        best_Less_than_p = [i for i in range(len(current_array))]
        best_Equal_to_p = [i for i in range(len(current_array))]
        best_Greater_than_p = [i for i in range(len(current_array))]
        coresponding_Less_than_p = []
        coresponding_Equal_to_p = []
        for i in range(no_of_pivots):
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
            if(len(best_Less_than_p) > len(Less_than_p)):
                best_Less_than_p = Less_than_p
        elif (k >= len(Less_than_p) + len(Equal_to_p)) :
            if(len(best_Greater_than_p) > len(Greater_than_p)):
                best_Greater_than_p = Greater_than_p
                coresponding_Less_than_p = Less_than_p
                coresponding_Equal_to_p = Equal_to_p
        else :
            return p

        if (len(best_Less_than_p) < len(best_Greater_than_p)) :
            return randomized_select_with_multipe_pivots(best_Less_than_p, k, no_of_pivots)
        else:
            return randomized_select_with_multipe_pivots(best_Greater_than_p, k - len(coresponding_Less_than_p) - len(coresponding_Equal_to_p), no_of_pivots)



# Maximum #pivots
max_no_of_pivots = 15

# Number of Trials
number_of_trials = 1000

# We are going to see if there is any observable difference in the slope of the Linear Regressor
# for the Mean (resp. Standard-Deviation) of the Running Time
# and the slope of standard-deviation-regressor as the number of pivots are increased
slope_of_mean_regressor_as_a_function_of_no_of_pivots = []
slope_of_std_dev_regressor_as_a_function_of_no_of_pivots = []

# I am going to plot a lot of things
# I found the stuff here -- https://matplotlib.org/gallery/subplots_axes_and_figures/figure_title.html
# to be useful. Instead, I just used what got from here to get the subplots not to get squished down --
# https://stackoverflow.com/questions/41530975/set-size-of-subplot-in-matplotlib
fig = plt.figure(figsize=(8, 20))

# try #pivots = 1,2,3,4 and see if having more pivots is helping with the run-time
for number_of_pivots in range(1, max_no_of_pivots+1) :

    # arrays containing mean- and std-dev of running time as a function of
    # array size starting from 100 to 4000 in steps of 100
    mean_running_time = []
    std_dev_running_time = []

    # cycle through a bunch of array sizes, where "k" is randomly chosen
    for j in range(1, 40) :
        array_size = 100*j
        # let is pick k to be (close to) the median
        k = math.ceil(array_size/2)
        # fill the array with random values
        my_array = [random.randint(1,100*array_size) for _ in range(array_size)]

        # run a bunch of random trials and get the algorithm's running time
        running_time = []
        for i in range(1, number_of_trials) :
            t1 = time.time()
            answer1 = randomized_select_with_multipe_pivots(my_array,k,number_of_pivots)
            t2 = time.time()
            running_time.extend([t2-t1])
            # uncomment the lines below to verify the solution of randomized_select_with_pivots
            #answer2 = sort_and_select(my_array, k)
            #if (answer1 != answer2) :
                #print ("Something went wrong")
                #exit()

        mean_running_time.extend([np.mean(running_time)])
        std_dev_running_time.extend([np.std(running_time)])

    # linear fit (cf. https://docs.scipy.org/doc/numpy-1.15.0/reference/generated/numpy.polyfit.html)
    t = np.arange(100, 4000, 100)
    z1 = np.polyfit(t, mean_running_time, 1)
    p1 = np.poly1d(z1)
    z2 = np.polyfit(t, std_dev_running_time, 1)
    p2 = np.poly1d(z2)

    print("#Pivots = ", number_of_pivots, "; Mean-Regressor's slope = ", z1[0], "; Std-Dev-Regressor's slope = ", z2[0])
    slope_of_mean_regressor_as_a_function_of_no_of_pivots.extend([z1[0]])
    slope_of_std_dev_regressor_as_a_function_of_no_of_pivots.extend([z2[0]])

    # plot the mean and standard deviation of the running-time as a function of array-size
    axs = fig.add_subplot(5, 3, number_of_pivots)
    plt.plot(t, mean_running_time, 'r', t, std_dev_running_time, 'g', t, p1(t), 'r-', t, p2(t), 'g-')
    axs.set_title('#Pivots =' + str(number_of_pivots))

plt.savefig("fig2.pdf", bbox_inches='tight')
plt.show()

# plot the slope of the two regressors as a function of #pivots
x = [i for i in range(1, max_no_of_pivots+1)]
plt.plot(x, slope_of_mean_regressor_as_a_function_of_no_of_pivots, 'r', x, slope_of_std_dev_regressor_as_a_function_of_no_of_pivots, 'g')
plt.title('Linear Regressor Slope for Mean- and Std-Dev vs. #Pivots')
plt.xlabel('#Pivots')
plt.ylabel('Seconds/Length')
plt.savefig("fig1.pdf", bbox_inches='tight')
plt.show()

# Checking if increasing the number of pivots is helping with the runtime in any significant manner...
z3 = np.polyfit(x, slope_of_mean_regressor_as_a_function_of_no_of_pivots, 1)
z4 = np.polyfit(x, slope_of_std_dev_regressor_as_a_function_of_no_of_pivots, 1)
print("Sensitivity of the Slope of the Linear Regressor of the Mean to the #Pivots    = ", z3[0])
print("Sensitivity of the Slope of the Linear Regressor of the Std-Dev to the #Pivots = ", z4[0])
