import sys
sys.setrecursionlimit(3000)

def move(n, source, intermediate, target):
    if n > 0:
        move(n - 1, source, target, intermediate)
        print("Move disk", n, " from ", source, " to ", target)
        move(n - 1, intermediate, target, source)

move(4,"source", "intermediate", "target")

