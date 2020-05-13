from itertools import combinations
import fileinput

lines = fileinput.input()

minsup = int(lines[0])
transactions = [line.replace('\n','').split(' ') for line in lines]

def init(transactions):
    Fk = {}
    for i in transactions:
        for j in i:
            if j in Fk:
                Fk[j] += 1
            else:
                Fk[j] = 1
    return [([key],val) for key, val in Fk.items() if val >= minsup]

def gen(Fk, k):
    letters = [] 
    for i in Fk:
        for j in i[0]:
            letters.append(j) 
    
    return combinations(list(set(letters)), k)
    
def count(C, transactions):
    ret = 0
    for i in transactions:
        c = 0
        for j in C:
            if j in i:
                c += 1
        if c == len(C):
            ret += 1
    return ret

def Filter(Ck, transactions, minsup):
    ret = []
    for C in Ck:
        c = count(C, transactions)
        if c >= minsup:
            ret.append((sorted(list(C)), c))
    return ret

k = 1
Fk = init(transactions)
max_k = len(Fk)
while k <= max_k:
    k += 1
    Ck = gen(Fk, k)
    Fk += Filter(Ck, transactions, minsup)
Fk = sorted(Fk, key=lambda x:[-x[1],''.join(x[0])])

for i in Fk:
    print(str(i[1]) + ' ['+' '.join(i[0])+']')

print('')

close = []
for index, F in enumerate(sorted(Fk, key = lambda x:-len(x[0]))):
    if index == 0:
        close.append(F)
    else:
        c = 0
        for i in close:
            if not set(F[0]).issubset(set(i[0])) or F[1] > i[1]:
                c += 1
        if c == len(close):
            close.append(F)
            
close = sorted(close, key=lambda x:[-x[1],''.join(x[0])])
for i in close:
    print(str(i[1]) + ' ['+' '.join(i[0])+']')

print('')

m = []
for index, F in enumerate(sorted(Fk, key = lambda x:-len(x[0]))):
    if index == 0:
        m.append(F)
    else:
        c = 0
        for i in m:
            if not set(F[0]).issubset(set(i[0])):
                c += 1
        if c == len(m):
            m.append(F)
            
m = sorted(m, key=lambda x:[-x[1],''.join(x[0])])
for i in m:
    print(str(i[1]) + ' ['+' '.join(i[0])+']')
