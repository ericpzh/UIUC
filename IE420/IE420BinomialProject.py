import numpy as np
import scipy.stats as sp
import time


def BlackScholes(Option, K, T, S0, sigma, r, q, Exercise):
    d1 = np.log(S0 / K) + (r - q + sigma ** 2 / 2) * T / sigma / T ** 0.5
    d2 = d1 - sigma * T ** 0.5
    if (Option == "P"):
        return -S0 * np.exp(-q * T) * sp.norm.cdf(-d1) + K * np.exp(
            -r * T) * sp.norm.cdf(-d2)
    else:
        return S0 * np.exp(-q * T) * sp.norm.cdf(d1) - K * np.exp(
            -r * T) * sp.norm.cdf(d2)


def Binomial(Option, K, T, S0, sigma, r, q, N, Exercise):
    # Timer starts
    startTime = time.time()
    ################
    # Build Array: #
    ################
    arr = np.zeros(N + 1,dtype=np.float64)
    arr[0] = (S0)
    # Fillup S (n,j)
    for n in range(N+1):
        for i in range(n+1):
            delta = T / float(N)
            u = np.exp(sigma * np.sqrt(delta))
            d = np.exp(-sigma * np.sqrt(delta))
            j = i
            arr[i] = u ** j * d ** (n - j) * S0
    # Transform into f (n,j)
    # fN
    for i in range(len(arr)):
        if (Option == "P"):
            arr[i] = max(0, K - arr[i])
        else:
            arr[i] = max(0, arr[i] - K)
    # fN-1~0
    for n in range(len(arr) - 2, -1, -1):
        for i in range(n+1):
            delta = T / float(N)
            u = np.exp(sigma * np.sqrt(delta))
            d = np.exp(-sigma * np.sqrt(delta))
            fu = arr[i + 1]
            fd = arr[i]
            pstar = ((np.exp((r - q) * delta) - d) / float(u - d))
            if (Exercise == "E"):
                arr[i] = np.exp(-r * delta) * (pstar * fu + (1 - pstar) * fd)
            elif (Option == "P"):
                S = u ** i * d ** (n - i) * S0
                arr[i] = max((K - S),np.exp(-r *  delta) * (pstar * fu + (1 - pstar) * fd))
            else:
                S = u ** i * d ** (n - i) * S0
                arr[i] = max((S - K), np.exp(-r * delta) * (pstar * fu + (1 - pstar) * fd))
    ##########
    # Ouput: #
    ##########
    # Timer ends
    endTime = time.time()
    return (arr[0], (endTime - startTime))  # (f0,runtime)


def Q2():
    Exercise, Option, K, S0, r, q, sigma, T = "E", "C", 100, 100, 0.05, 0.04, 0.2, 1
    ret = []
    BS = round(BlackScholes(Option, K, T, S0, sigma, r, q, Exercise),3)
    runtime = 0
    for N in [1,2,3,4,5,6,7,8,9,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100,200,300,400,500,1000,5000,10000]:
        BN = Binomial(Option, K, T, S0, sigma, r, q, N, Exercise)
        runtime += BN[1]
        ret.append([N, BS, round(BN[0],3)])
        print(N)
    print(runtime)
    np.savetxt("Q2.csv", np.array(ret), delimiter=",")


def Q3():
    N = 400
    Exercise, Option, K, r, q, sigma = "E", "P", 100, 0.05, 0, 0.2
    ret1 = []
    for i in range(1,13):
        up = K*2
        low = 0
        T = i / float(12)
        n = 0
        while n < 100:
            n += 1
            S0 = (up + low) /2
            BN = Binomial(Option, K, T, S0, sigma, r, q, N, Exercise)
            BL = Binomial(Option, K, T, low, sigma, r, q, N, Exercise)
            if (abs(BN[0] - max(0,K - S0)) < 0.005 or (up - low)/2.0 < 0.001):
                ret1.append([i, BN[0], S0])
                n = 100
            elif np.sign(BN[0] - max(0,K - S0)) == np.sign(BL[0] - max(0,K - S0)):
                up = S0
            else:
                low = S0
    np.savetxt("Q31.csv", np.array(ret1), delimiter=",")

    Exercise, Option, K, r, q, sigma = "E", "P", 100, 0.05, 0.04, 0.2
    ret2 = []
    for i in range(1, 13):
        up = K * 2
        low = 0
        T = i / float(12)
        n = 0
        while n < 100:
            n += 1
            S0 = (up + low) / 2
            BN = Binomial(Option, K, T, S0, sigma, r, q, N, Exercise)
            BL = Binomial(Option, K, T, low, sigma, r, q, N, Exercise)
            if (abs(BN[0] - max(0, K - S0)) < 0.005 or (up - low) / 2.0 < 0.001):
                ret2.append([i, BN[0], S0])
                n = 100
            elif np.sign(BN[0] - max(0, K - S0)) == np.sign(BL[0] - max(0, K - S0)):
                up = S0
            else:
                low = S0
    np.savetxt("Q32.csv", np.array(ret2), delimiter=",")




def Q4():
    N = 300
    Exercise, Option, K, r, q, sigma = "E", "C", 100, 0.05, 0.04, 0.2
    ret1 = []
    for i in range(1,13):
        up = K*2
        low = 0
        T = i / float(12)
        n = 0
        while n < 100:
            n += 1
            S0 = (up + low) /2
            BN = Binomial(Option, K, T, S0, sigma, r, q, N, Exercise)
            BL = Binomial(Option, K, T, low, sigma, r, q, N, Exercise)
            if (abs(BN[0] - max(0,S0 - K)) < 0.005 or (up - low)/2.0 < 0.001):
                ret1.append([i, BN[0], S0])
                n = 100
            elif np.sign(BN[0] - max(0,S0 - K)) == np.sign(BL[0] - max(0,S0 - K)):
                up = S0
            else:
                low = S0
    np.savetxt("Q41.csv", np.array(ret1), delimiter=",")

    Exercise, Option, K, r, q, sigma = "E", "C", 100, 0.05, 0.08, 0.2
    ret2 = []
    for i in range(1, 13):
        up = K * 2
        low = 0
        T = i / float(12)
        n = 0
        while n < 100:
            n += 1
            S0 = (up + low) / 2
            BN = Binomial(Option, K, T, S0, sigma, r, q, N, Exercise)
            BL = Binomial(Option, K, T, low, sigma, r, q, N, Exercise)
            if (abs(BN[0] - max(0, S0 - K)) < 0.005 or (up - low) / 2.0 < 0.001):
                ret2.append([i, BN[0], S0])
                n = 100
            elif np.sign(BN[0] - max(0, S0 - K)) == np.sign(BL[0] - max(0, S0 - K)):
                up = S0
            else:
                low = S0
    np.savetxt("Q42.csv", np.array(ret2), delimiter=",")

