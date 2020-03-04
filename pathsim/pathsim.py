import numpy as np
import random
import math
from PIL import Image
import imageio as io
import glob
import os
import queue

###########################
#          INPUTS         #
###########################
#how many unit of time per img
sample_rate = 50
#threshold for finishing jobs float(0 ~ 1)
threshold = 0.975
#how wide a strip a machine can processed (m)
machine_width = 0.341122
#area to move in white (not transparent)
area = Image.open("area.jpg")
#1px in area to grid_size ^ 2 (m ^ 2)
grid_size = 0.05517
#start location of the job  (row, col, angle) (px in area)
start_loc = (31,86,0)
#define what to do when no obstacle
#INPUT : loc tuple (x,y,angle) + ground 2d numpy array + accuracy level
#OUTPUT: list(tuple(next_x,next_y,next_angle)) next cells to go
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
#define what to do when hit a wall
#INPUT :loc tuple (x,y,angle) + ground 2d numpy array + accuracy level
#OUTPUT: list(tuple(next_x,next_y,next_angle)) next cells to go
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
#define what to do when it deadlocked
#INPUT :loc tuple (x,y,angle) + ground 2d numpy array + accuracy level
#OUTPUT: list(tuple(next_x,next_y,next_angle)) next cells to go
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
#too long threshold in time steps
too_long = 100000
#too_much threshold in visited times
too_much = 1
#scale up/down image float(0+)
scale_factor = 10
#accurate to the level
accuracy = 1
###########################

#convert original Image to 2d array
#INPUT :Image area
#OUTPUT:list of Image represent path in discrete time
def main(sample_rate,threshold,machine_width,area,grid_size,start_loc,movement_pattern,wall_pattern,deadlock_pattern,too_long = 1000000,too_much = 100,scale_factor = 0.1,accuracy = 0.01):
    ground = img_matrix(area, grid_size, machine_width,accuracy)
    ratio = (grid_size / machine_width * (1 / accuracy))
    task = np.count_nonzero(ground==0)
    wall = len(ground)*len(ground[0]) - np.count_nonzero(ground==0)
    time = 0
    curr_loc = (int(start_loc[0]*ratio),int(start_loc[1]*ratio),start_loc[2])
    max_cnt = 0
    if ground[curr_loc[0]][curr_loc[1]] < 1:
        ground[curr_loc[0]][curr_loc[1]] += 1 / float(too_much)
        while (np.count_nonzero(ground) - wall) < task * threshold and time < too_long:
            # what's next
            next_locs = movement_pattern(curr_loc,ground,accuracy)
            next_loc = next_locs[len(next_locs) - 1]
            if ground[next_loc[0]][next_loc[1]] >= 1: #if wall
                next_locs = wall_pattern(curr_loc,ground,accuracy)
                next_loc = next_locs[len(next_locs) - 1]
            if curr_loc[0] == next_loc[0] and curr_loc[1] == next_loc[1]: #if deadlock
                next_locs = deadlock_pattern(curr_loc, ground, accuracy)
                next_loc = next_locs[len(next_locs) - 1]
                time += int(np.sqrt((next_loc[0] - curr_loc[0])**2+(next_loc[1] - curr_loc[1])**2))
            # visited
            for i in range(1,len(next_locs)):
                ground[next_locs[i][0]][next_locs[i][1]] += 1 / float(too_much)
                if ground[next_locs[i][0]][next_locs[i][1]] > max_cnt:
                    max_cnt = ground[next_locs[i][0]][next_locs[i][1]]
            #sample
            if time % sample_rate == 0:
                matrix_img(ground,time,scale_factor,accuracy)
            #next
            curr_loc = next_loc
            time += 1
    else:
        print("Another start point")
    print("Travel through: " + str(time) + " tiles which is: " + str(time*machine_width) + " meters in given speed.")
#convert original Image to 2d array
#INPUT :Image area
#OUTPUT:2d numpy array ground
def img_matrix(area, grid_size, machine_width,accuracy):
    ratio = (grid_size / machine_width * (1 / accuracy))
    bw_area = area.convert('L').transform((int(area.width * ratio), int(area.height * ratio)),Image.EXTENT,(0,0,area.width,area.height))
    ground = np.zeros((bw_area.height, bw_area.width ))
    for row in range(bw_area.height):
        for col in range(bw_area.width):
            ground[row][col] = int(bw_area.getpixel((col,row)) < 255.0/2)
    return ground

#convert 2d array to Image
#INPUT :2d numpy array ground, time steps, scale_factor
def matrix_img(ground,time,scale_factor,accuracy):
    height = len(ground)
    width = len(ground[0])
    area = Image.new("L",(width,height))
    for row in range(height):
        for col in range(width):
            area.putpixel((col,row),255 - int(ground[row][col] * 255))
    area = area.transform((int(width * scale_factor * accuracy), int(height * scale_factor * accuracy)),Image.EXTENT,(0,0,width,height))
    area.save("./snap/" + str(time) + ".jpg")

fileList = os.listdir('./snap')
for fileName in fileList:
    os.remove('./snap'+"/"+fileName)

main(sample_rate,threshold,machine_width,area,grid_size,start_loc,movement_pattern,wall_pattern,deadlock_pattern,too_long,too_much,scale_factor,accuracy)

try:
    images = []
    file_names = glob.glob('./snap/*.jpg')
    file_names.sort(key=lambda f: int(''.join(filter(str.isdigit, f))))
    for filename in file_names:
        images.append(io.imread(filename))
    io.mimsave('path.gif', images, fps = 25)
except:
    print("Fail to generate .gif")