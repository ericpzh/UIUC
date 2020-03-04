import random
import numpy as np

def Game(p):
	M1, M2 = 0, 0
	while True:
		G1, G2 = 0, 0
		if (M1 >= 6 and M1 - M2 >= 2):
			return 1
		elif (M2 >= 6 and M2 - M1 >= 2):
			return 2
		elif ((M1 + M2) % 2 == 0):
			matchNumber = M1 + M2 + 1
			while M1 + M2 != matchNumber:
				R1, R2 = random.random(), random.random()
				if (R1 < 0.25 and R2 < 0.35):
					G2 += 1
				elif (R1 < 0.25):
					G1 += 1
				elif (R2 < 0.25):
					G2 += 1
				else:
					G1 += 1
				if (G1 >= 4 and G1 - G2 >= 2):
					M1 += 1
				elif (G2 >= 4 and G2 - G1 >= 2):
					M2 += 1
		else:
			matchNumber = M1 + M2 + 1
			while M1 + M2 != matchNumber:
				R1, R2 = random.random(), random.random()
				if (R1 < 1-p and R2 < 0.8):
					G1 += 1
				elif (R1 < 1-p):
					G2 += 1
				elif (R2 < 0.05):
					G1 += 1
				else:
					G2 += 1
				if (G1 >= 4 and G1 - G2 >= 2):
					M1 += 1
				elif (G2 >= 4 and G2 - G1 >= 2):
					M2 += 1


def main():
    arr = []
    for j in range(100):
        ret = np.array([0, 0])
        for i in range(10000):
            ret[Game(0.70) - 1] += 1
        arr.append(ret[0])
        print(str(j)+"% Done")
    np.savetxt("x.csv", np.array(arr), delimiter=",")

main()
