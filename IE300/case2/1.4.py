import numpy as np
import matplotlib.pyplot as plt
import scipy.misc
import matplotlib.patches as mpatches
#create plot
plt.figure(figsize=(8, 6), dpi=80)
plt.subplot(1, 1, 1)
#calculate for each n
n1 = 1
n2 = 2
n10 = 10
nn = 100
xvals = np.linspace(-3, 3, 256, endpoint=True)
yvals1 = n1**(n1-1/2)/scipy.misc.factorial(n1-1)*(1+xvals/n1**0.5)**(n1-1)*np.exp(-n1*(1+xvals/n1**0.5))
yvals2 = n2**(n2-1/2)/scipy.misc.factorial(n2-1)*(1+xvals/n2**0.5)**(n2-1)*np.exp(-n2*(1+xvals/n2**0.5))
yvals10 = n10**(n10-1/2)/scipy.misc.factorial(n10-1)*(1+xvals/n10**0.5)**(n10-1)*np.exp(-n10*(1+xvals/n10**0.5))
yvalsn = nn**(nn-1/2)/scipy.misc.factorial(nn-1)*(1+xvals/nn**0.5)**(nn-1)*np.exp(-nn*(1+xvals/nn**0.5))
#plot it
plt.plot(xvals, yvals1,color="blue", linewidth=1.0, linestyle="-")
plt.plot(xvals, yvals2,color="green", linewidth=1.0, linestyle="-")
plt.plot(xvals, yvals10,color="red", linewidth=1.0, linestyle="-")
plt.plot(xvals, yvalsn,color="black", linewidth=2.0, linestyle="-")
#add legend
red_patch = mpatches.Patch(color='red', label='n = 10')
blue_patch = mpatches.Patch(color='blue', label='n = 1')
green_patch = mpatches.Patch(color='green', label='n = 2')
black_patch = mpatches.Patch(color='black', label='normal distribution')
plt.legend(handles=[blue_patch,green_patch,red_patch,black_patch])
#add title
plt.suptitle('1.4 Plot', fontsize=14, fontweight='bold')
#x,y limit
plt.xlim(-3.0, 3.0)
plt.xticks(np.linspace(-3, 3, 9, endpoint=True))
plt.ylim(0, 1.0)
plt.yticks(np.linspace(0, 1, 5, endpoint=True))
#save it
plt.savefig("1.4.png", dpi=72)
plt.show()