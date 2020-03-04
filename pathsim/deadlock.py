import numpy as np
import random
import math
import queue

#define what to do when it deadlocked
#INPUT :loc tuple (x,y,angle) + ground 2d numpy array + accuracy level
#OUTPUT: list(tuple(next_x,next_y,next_angle)) next cells to go

#nearest 0
def deadlock_pattern(loc, ground, accuracy):
    x, y, angle = loc[0], loc[1], loc[2]
    q = queue.Queue()
    loc_set = set()
    dir = [(1, 0), (1, 1), (1, -1), (0, 1), (0, -1), (-1, 0), (-1, 1), (-1, -1)]
    for i in dir:
        next_x, next_y = x + i[0], y + i[1]
        if not (next_x < 0 or next_x >= len(ground) or next_y < 0 or next_y >= len(ground[0])):
            q.put((next_x, next_y))
            loc_set.add((next_x, next_y))
    while not q.empty():
        next_pt = q.get()
        next_x, next_y = next_pt[0], next_pt[1]
        if not (next_x < 0 or next_x >= len(ground) or next_y < 0 or next_y >= len(ground[0])):
            if ground[next_x][next_y] == 0:
                return [(x, y, angle), (next_x, next_y, angle)]
        for i in dir:
            next_x, next_y = next_pt[0] + i[0], next_pt[1] + i[1]
            if not (next_x < 0 or next_x >= len(ground) or next_y < 0 or next_y >= len(ground[0]) or (
            next_x, next_y) in loc_set):
                q.put((next_x, next_y))
                loc_set.add((next_x, next_y))
    return [(x, y, angle)]