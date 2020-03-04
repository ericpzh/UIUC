def P(s, p):
    sum = 0
    for i in range(len(p)):
        sum += p[i] * s**i
    return sum


def dP(p):
    return sum(p[i] * i for i in range(len(p)))


def find(p):
    if dP(p) <= 1:
        ksi = 1
        return ksi
    else:
        N = 1000000
        for s in range(N):
            if(abs(P(s/float(N), p) - s/float(N)) < 0.00001):
                ksi = s/float(N)
                return ksi

def find1(p):
    if dP(p) <= 1:
        ksi = 1
        return ksi
    else:
        N = 1000000
        for s in range(N):
            if(abs(P(0.5*(s/float(N))+0.5, p) - (s/float(N))) < 0.00001):
                ksi = s/float(N)
                return ksi

def main(pop):
    p = [i / float(sum(pop)) for i in pop]
    ksi = find(p)
    ksi1 = find1(p)
    return ksi,ksi1


China = [9080779, 11519885, 8548763, 3187520, 895589, 315130]
Mexico = [9421296, 4070609, 4852040, 4164527, 2643692, 1743119]

print(main(China))
print(main(Mexico))
