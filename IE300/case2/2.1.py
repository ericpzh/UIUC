from scipy import stats
import numpy as np
import scipy.misc
import math
import matplotlib.patches as mpatches
from matplotlib import pyplot as plt
from scipy.stats import norm
import random

#U(0,1) plot
xlist = []
for i in range(500):
    xlist.append(random.uniform(0, 1))
xlist.sort()
#create plot
plt.figure(figsize=(8, 6), dpi=80)
plt.subplot(1, 1, 1)
#calculate for each n
xvals = np.linspace(0, 500, num=500, endpoint=False)
yvals = -np.log((500 - (xvals-0.5))/500)
#plot it
plt.plot(yvals, xlist, "o")
plt.plot(np.unique(yvals), np.poly1d(np.polyfit(yvals, xlist, 1))(np.unique(yvals)),color="red", linewidth=1.0, linestyle="-")
#add legend
red_patch = mpatches.Patch(color='red', label='line of best fit')
plt.legend(handles=[red_patch])
#add title
plt.suptitle('QQPlot Against U(0,1)', fontsize=14, fontweight='bold')
#x,y limit
plt.ylim(0, 2)
plt.yticks(np.linspace(0, 2, 9, endpoint=True))
plt.xlim(0, 6.0)
plt.xticks(np.linspace(0, 6, 5, endpoint=True))
#save it
plt.savefig("2.1exp.png", dpi=72)
plt.show()

#exp plot
xlist = []
for i in range(500):
    xlist.append(np.random.exponential(1))
xlist.sort()

#create plot
plt.figure(figsize=(8, 6), dpi=80)
plt.subplot(1, 1, 1)
#calculate for each n
xvals = np.linspace(0, 500, num=500, endpoint=False)
yvals = -np.log((500 - (xvals-0.5))/500)
#plot it
plt.plot(yvals, xlist, "o")
plt.plot(np.unique(yvals), np.poly1d(np.polyfit(yvals, xlist, 1))(np.unique(yvals)),color="red", linewidth=1.0, linestyle="-")
#add legend
red_patch = mpatches.Patch(color='red', label='line of best fit')
plt.legend(handles=[red_patch])
#add title
plt.suptitle('QQPlot Against exp(1)', fontsize=14, fontweight='bold')
#x,y limit
plt.ylim(0, 4)
plt.yticks(np.linspace(0, 4, 9, endpoint=True))
plt.xlim(0, 4.0)
plt.xticks(np.linspace(0, 4, 5, endpoint=True))
#save it
plt.savefig("2.1exp-1.png", dpi=72)
plt.show()
