import sys
data = []
for i, line in enumerate(sys.stdin):
    if i == 0:
        l = line.strip().split(' ')
        N, k = [int(j) for j in l]
    else:
        l = line.strip().split(' ')
        data.append([float(j) for j in l])

distance_map = []

def dist(x,y):
    return sum([(x[i] - y[i])**2 for i in range(len(x))])

for i in range(N):
    d = []
    for j in range(N):
        d.append(dist(data[i], data[j]))
    distance_map.append(d)

def single_link(data, assignments):
    clusters = {}
    for i in range(N):
        if assignments[i] not in clusters:
            clusters[assignments[i]] = [i]
        else:
            clusters[assignments[i]].append(i)
    
    min_distance = 999999
    min_d = []
    for k1, v1 in clusters.items():
        for k2, v2 in clusters.items():
            if k1 != k2:
                dists = []
                for i in v1:
                    for j in v2:
                        dists.append(distance_map[i][j])
                if min(dists) < min_distance:
                    min_distance = min(dists)
                    min_d = [k1, k2, min(dists)]
    temp = min_d
    return min(temp[0], temp[1]), clusters[temp[0]], clusters[temp[1]]

assignments = [i for i in range(N)]

while len(set(assignments)) > k:
    C, can1, can2 = single_link(data, assignments)
    for j in can1:
        assignments[j] = C
    for j in can2:
        assignments[j] = C

for i in assignments:
    print(i)
