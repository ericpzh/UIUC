import numpy as np
import random
import math

#define what to do when hit a wall
#INPUT :loc tuple (x,y,angle) + ground 2d numpy array + accuracy level
#OUTPUT: list(tuple(next_x,next_y,next_angle)) next cells to go

#random shuffle between 0, 90 ,180 ,279 deg
def wall_pattern(loc, ground, accuracy):
    x, y, angle = loc[0], loc[1], loc[2]
    for angle_deg in sorted([0, 90, 180, 270], key=lambda k: random.random()):  # [0,45,90,135,180,225,270,315]
        next_angle = math.radians(angle_deg)
        next_x, next_y = x + int(np.round(math.cos(next_angle) / accuracy)), y + int(
            np.round(math.sin(next_angle) / accuracy))
        if not next_angle == angle:
            if next_angle == angle + math.pi or next_angle == angle - math.pi:
                if ground[x][y + 1] == 0:
                    return [(x, y, angle), (x, y + 1, next_angle)]
                elif ground[x][y - 1] == 0:
                    return [(x, y, angle), (x, y - 1, next_angle)]
                elif ground[x + 1][y] == 0:
                    return [(x, y, angle), (x + 1, y, next_angle)]
                elif ground[x - 1][y] == 0:
                    return [(x, y, angle), (x - 1, y, next_angle)]
                elif ground[x][y + 1] < 1:
                    return [(x, y, angle), (x, y + 1, next_angle)]
                elif ground[x][y - 1] < 1:
                    return [(x, y, angle), (x, y - 1, next_angle)]
                elif ground[x + 1][y] < 1:
                    return [(x, y, angle), (x + 1, y, next_angle)]
                elif ground[x - 1][y] < 1:
                    return [(x, y, angle), (x - 1, y, next_angle)]
            else:
                if ground[next_x][next_y] < 1:
                    return [(x, y, angle), (next_x, next_y, next_angle)]
    return [(x, y, angle)]

#always 0 -> 180 -> 90 -> 270 deg
def wall_pattern(loc, ground, accuracy):
    x, y, angle = loc[0], loc[1], loc[2]
    for angle_deg in [0, 180, 90, 270]:
        next_angle = math.radians(angle_deg)
        next_x, next_y = x + int(np.round(math.cos(next_angle) / accuracy)), y + int(
            np.round(math.sin(next_angle) / accuracy))
        if not next_angle == angle:
            if next_angle == angle + math.pi or next_angle == angle - math.pi:
                if ground[x][y + 1] == 0:
                    return [(x, y, angle), (x, y + 1, next_angle)]
                elif ground[x][y - 1] == 0:
                    return [(x, y, angle), (x, y - 1, next_angle)]
                elif ground[x + 1][y] == 0:
                    return [(x, y, angle), (x + 1, y, next_angle)]
                elif ground[x - 1][y] == 0:
                    return [(x, y, angle), (x - 1, y, next_angle)]
                elif ground[x][y + 1] < 1:
                    return [(x, y, angle), (x, y + 1, next_angle)]
                elif ground[x][y - 1] < 1:
                    return [(x, y, angle), (x, y - 1, next_angle)]
                elif ground[x + 1][y] < 1:
                    return [(x, y, angle), (x + 1, y, next_angle)]
                elif ground[x - 1][y] < 1:
                    return [(x, y, angle), (x - 1, y, next_angle)]
            else:
                if ground[next_x][next_y] < 1:
                    return [(x, y, angle), (next_x, next_y, next_angle)]
    return [(x, y, angle)]