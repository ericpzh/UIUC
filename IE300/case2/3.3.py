from scipy import stats
import numpy as np
import scipy.misc
import math
import matplotlib.patches as mpatches
from matplotlib import pyplot as plt
from scipy.stats import norm
#read data
text_file = open("IE300_CASE2_DATA.txt",'r')
lines = text_file.read().split(' ')
list = [line.split(' ') for line in open('IE300_CASE2_DATA.txt')]
#convert data to int
for i in range(len(list)):
    if ("\n" in list[i]):
        list[i].remove('\n')
    list[i][0].replace("\t","")
    list[i][0] = int(list[i][0])
    list[i][1] = int(''.join(e for e in list[i][1] if e.isdigit()))
list.sort(key=lambda x: x[1], reverse=True)
xlist = []
for i in list:
    xlist.append(i[1])
xlist.sort()
for r in {3000000,3500000,4000000,5000000,7500000}:
    s = 0
    x = 0
    for i in xlist:
        s += i-r
        if(i > r):
            x += 1
    print(x)
    print(1/len(xlist)*s)