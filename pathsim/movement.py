import numpy as np
import random
import math

#define what to do when no obstacle
#INPUT : loc tuple (x,y,angle) + ground 2d numpy array + accuracy level
#OUTPUT: list(tuple(next_x,next_y,next_angle)) next cells to go

#Follow excaly the prev angle
def movement_pattern(loc, ground, accuracy):
    x, y, angle = loc[0], loc[1], loc[2]
    next_x, next_y, next_angle = x + int(np.round(math.cos(angle) / accuracy)), y + int(
        np.round(math.sin(angle) / accuracy)), angle
    try:
        slope = (y - next_y) / (x - next_x)
        intercept = y - x * slope
        next_locs = []
        dir = 2 * int(x < next_x) - 1
        for curr_x in range(x, next_x, dir):
            for i in range(int(1 / accuracy)):
                for j in range(int(1 / accuracy)):
                    next_locs.append((curr_x + int(j - 1 / accuracy / 2),
                                      int(curr_x * slope + intercept + int(i - 1 / accuracy / 2)), angle))
    except:
        slope = (x - next_x) / (y - next_y)
        intercept = x - y * slope
        next_locs = []
        dir = 2 * int(y < next_y) - 1
        for curr_y in range(y, next_y, dir):
            for i in range(int(1 / accuracy)):
                for j in range(int(1 / accuracy)):
                    next_locs.append((curr_y + int(j - 1 / accuracy / 2),
                                      int(curr_y * slope + intercept + int(i - 1 / accuracy / 2)), angle))
    next_locs.append((next_x, next_y, next_angle))
    return next_locs