import sys
attributes = {}
train_data, test_data = [], []
ts = {}
for line in sys.stdin:
    x = line.strip().split(' ')
    attrs = []
    dim = len(x)
    x = [x[0]] + sorted(x[1:], key=lambda a:a.split(':')[0])

    for i in range(1,len(x)):
        attr = x[i].split(':')
        attrs.append(float(attr[1]))

        if i not in ts:
            ts[i] = [float(attr[1])]
        else:
            if float(attr[1]) not in ts[i]:
                ts[i].append(float(attr[1]))

    if int(x[0]) != 0:
        train_data.append([int(x[0])] + attrs)
    else:
        test_data.append([int(x[0])] + attrs)

ts_ = {}
for k, val in ts.items():
    v = []
    val.sort()
    for i in range(len(val) - 1):
        v.append((val[i] + val[i+1])/2)
    ts_[k] = v
    ts[k] += v

def split(data, t):
    g = []
    for x in range(1, dim):
        ginis = []
        thetas = t[x]
        for theta in thetas:
            A,B,C,D = 0,0,0,0
            l1, l2 = {}, {}
            for dat in data:
                if dat[x] <= theta:
                    A += 1
                    if dat[0] not in l1:
                        l1[dat[0]] = 1
                    else:
                        l1[dat[0]] += 1
                else:
                    B +=1
                    if dat[0] not in l2:
                        l2[dat[0]] = 1
                    else:
                        l2[dat[0]] += 1
            for val in l1.values():
                C += (val/A)**2
            for val in l2.values():
                D += (val/B)**2

            if A == 0:
                gini = B/(A+B)*(1-D/B**2)
            elif B == 0:
                gini = A/(A+B)*(1-C/A**2)
            else:
                #gini = A/(A+B)*(1-C/A**2)+B/(A+B)*(1-D/B**2)
                gini = A/(A+B)*(1-C) + B/(A+B)*(1-D)
            ginis.append([theta, gini])
        ginis.sort(key = lambda x: (x[1],x[0]))
        #print(ginis)
        g.append([x, ginis[0][0], ginis[0][1]])
    g.sort(key = lambda x:x[2])
    return g
g = split(train_data, ts_)[0]

#print(g)

train_data_1, train_data_2 = [], []
ts1, ts2 = {}, {}
x_ = g[0]
theta = g[1]
for dat in train_data:
    if dat[x_] <= theta:
        train_data_1.append(dat)
        for i in range(1, len(dat)):
            if i not in ts1:
                ts1[i] = [dat[i]]
            else:
                if dat[i] not in ts1[i]:
                    ts1[i].append(dat[i])
    else:
        train_data_2.append(dat)
        for i in range(1, len(dat)):
            if i not in ts2:
                ts2[i] = [dat[i]]
            else:
                if dat[i] not in ts2[i]:
                    ts2[i].append(dat[i])

#print(train_data_1, train_data_2)
#print(ts1, ts2)
ts1_, ts2_ = {}, {}
for k, val in ts1.items():
    val.sort()
    if len(val) > 1:
        v = []
        for i in range(len(val) - 1):
            v.append((val[i] + val[i+1])/2)
        ts1_[k] = v

for k, val in ts2.items():
    val.sort()
    if len(val) > 1:
        v = []
        for i in range(len(val) - 1):
            v.append((val[i] + val[i+1])/2)
        ts2_[k] = v
#print(ts1_, ts2_)

def split2(data, t):
    g = []
    for x in range(1, dim):
        if x != x_ and x in t:
            ginis = []
            thetas = t[x]
            for theta in thetas:
                A,B,C,D = 0,0,0,0
                l1, l2 = {}, {}
                for dat in data:
                    if dat[x] <= theta:
                        A += 1
                        if dat[0] not in l1:
                            l1[dat[0]] = 1
                        else:
                            l1[dat[0]] += 1
                    else:
                        B +=1
                        if dat[0] not in l2:
                            l2[dat[0]] = 1
                        else:
                            l2[dat[0]] += 1
                for val in l1.values():
                    C += val*val
                for val in l2.values():
                    D += val*val

                if A == 0:
                    gini = B/(A+B)*(1-D/B**2)
                elif B == 0:
                    gini = A/(A+B)*(1-C/A**2)
                else:
                    gini = A/(A+B)*(1-C/A**2)+B/(A+B)*(1-D/B**2)

                #print(l1, l2)

                m1 = max(l1.values())
                m2 = max(l2.values())
                for k1 in l1.keys():
                    if l1[k1] == m1:
                        break
                for k2 in l2.keys():
                    if l2[k2] == m2:
                        break

                ginis.append([theta, gini, k1, k2])
            ginis.sort(key = lambda x: (x[1],x[0]))
            g.append([x, ginis[0][0], ginis[0][1], ginis[0][2], ginis[0][3]])
    g.sort(key = lambda x:x[2])
    return g

g1 = split2(train_data_1, ts1_)[0]
g2 = split2(train_data_2, ts2_)[0]

#print(g1)
#print(g2)

for dat in test_data:
    if dat[g[0]] <= g[1]:
        if dat[g1[0]] <= g1[1]:
            print(g1[3])
        else:
            print(g1[4])
    else:
        if dat[g2[0]] <= g2[1]:
            print(g2[3])
        else:
            print(g2[4])
print()
#3NN

def dist(x,y):
    d = 0
    for i in range(1, len(x)):
        d += (x[i] - y[i])**2
    return d

for test in test_data:
    #3NN
    dists = [[train[0], dist(test,train)] for train in train_data]
    dists.sort(key=lambda x:x[1])
    if dists[2][1] == dists[3][1] and dists[2][0] > dists[3][0]:
        dists[2][0] = dists[3][0]
    if(dists[0][0] == dists[1][0] or dists[0][0] == dists[2][0]):
        print(dists[0][0])
    elif(dists[0][0] == dists[1][0] or dists[1][0] == dists[2][0]):
        print(dists[1][0])
    else:
        print(min(dists[0][0], dists[1][0], dists[2][0]))
