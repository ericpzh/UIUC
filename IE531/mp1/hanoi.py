import sys
import math
from collections import deque

Towers = deque()

number_of_steps = 0

sys.setrecursionlimit(3090)

def initialize(n, K) :
    for i in range(K) :
        X = deque()
        if (i == 0) :
            for j in range(n) :
                X.append(j+1)
        Towers.append(X)

def is_everything_legal(K) :
    result = True
    for i in range(K) :
        for j in range(len(Towers[i])) :
            for k in range(j,len(Towers[i])) :
                if (Towers[i][k] < Towers[i][j]) :
                    result = False
    return(result)

def move_top_disk(source, dest, K):
    global number_of_steps
    number_of_steps = number_of_steps + 1
    x = Towers[source].popleft()
    Towers[dest].appendleft(x)
    if (True == is_everything_legal(K)):
        y = " (Legal)"
    else:
        y = " (Illegal)"

    print("Move disk " + str(x) + " from Peg " + str(source + 1) + " to Peg " + str(dest + 1) + y)

def move_using_k_pegs(number_of_disks, source, dest, intermediates, K):
    if number_of_disks == 1:
        move_top_disk (source, dest, K)
    else:
        if K-2 > 1: 
            k = math.floor(number_of_disks/2)
        else:
            k = number_of_disks - 1
        move_using_k_pegs(k, source, intermediates[0], intermediates[1:K-2] + [dest], K)
        move_using_k_pegs(number_of_disks-k, source, dest, intermediates[1:K-2], K-1)
        move_using_k_pegs(k, intermediates[0], dest, [source] + intermediates[1:K-2], K)
        
        
def print_peg_state(m) :
    global number_of_steps
    print ("-----------------------------")
    print ("State of Peg " + str(m+1) + " (Top to Bottom): " + str(Towers[m]))
    print ("Number of Steps = " + str(number_of_steps))
    print ("-----------------------------")
    
def main():
    if len(sys.argv[1:]) != 2:
        print('Run using "py hanoi.py N k"')
        return
    n = int(sys.argv[1])
    K = int(sys.argv[2])
    initialize(n, K)
    print_peg_state(0)
    move_using_k_pegs(n,0,K-1,[i for i in range(1,K-1)], K)
    print_peg_state(K-1)

if __name__== "__main__":
    main()
