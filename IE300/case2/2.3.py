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
#largest 270
list.sort(key=lambda x: x[1], reverse=True)
list = list[:270]
xlist = []
for i in list:
    xlist.append(i[1])
xlist.sort()
#exp plot
#create plot
plt.figure(figsize=(8, 6), dpi=80)
plt.subplot(1, 1, 1)
#calculate for each n
xvals = np.linspace(0, 270, num=270, endpoint=False)
yvals = -np.log((270 - (xvals-0.5))/270)
#plot it
plt.plot(yvals, xlist, "o")
plt.plot(np.unique(yvals), np.poly1d(np.polyfit(yvals, xlist, 1))(np.unique(yvals)),color="red", linewidth=1.0, linestyle="-")
#add legend
red_patch = mpatches.Patch(color='red', label='line of best fit')
plt.legend(handles=[red_patch])
#add title
plt.suptitle('QQPlot Against exp(270)', fontsize=14, fontweight='bold')
#x,y limit
plt.ylim(1500000, 8000000)
plt.yticks(np.linspace(1500000, 8000000, 9, endpoint=True))
plt.xlim(0, 6.0)
plt.xticks(np.linspace(0, 6, 5, endpoint=True))
#save it
plt.savefig("2.3exp.png", dpi=72)
plt.show()

#normal plot:
#create plot
plt.figure(figsize=(8, 6), dpi=80)
plt.subplot(1, 1, 1)
#calculate for each n
xvals = np.linspace(1, 270, num=270, endpoint=False)
yvals = norm.ppf((xvals-0.5)/270)
#plot it
plt.plot(yvals, xlist, "o")
plt.plot(np.unique(yvals), np.poly1d(np.polyfit(yvals, xlist, 1))(np.unique(yvals)),color="red", linewidth=1.0, linestyle="-")
#add legend
red_patch = mpatches.Patch(color='red', label='line of best fit')
plt.legend(handles=[red_patch])
#add title
plt.suptitle('QQPlot Against N~(0,1)', fontsize=14, fontweight='bold')
#x,y limit
plt.ylim(1500000, 8000000)
plt.yticks(np.linspace(1500000, 8000000, 9, endpoint=True))
plt.xlim(-3, 3.0)
plt.xticks(np.linspace(-3, 3, 5, endpoint=True))
#save it
plt.savefig("2.3nor.png", dpi=72)
plt.show()