import sys
d = []
for i, line in enumerate(sys.stdin):
    if i == 0:
        N, k = [int(j) for j in line.strip().split(' ')]
    else:
        d.append([float(j) for j in line.strip().split(' ')])

def dist(x,y):
    return sum([(x[i] - y[i])**2 for i in range(len(x))])

centers = []
for i in range(N,k+N):
    centers.append(d[i])

data = []
for i in range(N):
    data.append(d[i])

last_centers = []
while centers != last_centers:
    #print("----")
    #print(centers)
    last_centers = [[j for j in i] for i in centers]

    assignments = []
    for i in data:
        distances = [dist(i,j) for j in centers]
        min_distance = min(distances)
        min_distances = [i for i in range(len(distances)) if distances[i] == min_distance]
        assignments.append(min(min_distances))
    #print(assignments)

    centers = []
    for i in range(k):
        m = [data[j] for j in range(len(data)) if assignments[j] == i]
        center = [float(sum(l))/len(l) for l in zip(*m)]
        centers.append(center)


for i in assignments:
    print(i)
