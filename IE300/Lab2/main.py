import csv

def run(s):
	infile = open(s,'r')
	reader = csv.reader(infile)
	ls = []
	product = []
	for line in reader:
		if line[0] != "custName":
			ls.append([line[0],line[1],float(line[2]),float(line[3])])
			product.append(line[1])

	ls.sort(key = lambda x: (x[0],x[1]))

	product.sort()
	i = 0
	while i != len(product):
		if(i != len(product)-1):
			if(product[i] == product[i+1]):
				del product[i+1]
				i += 0
			else:
				i += 1
		else:
			i += 1
	for i1 in range(len(ls)):
		ls[i1][3] = ls[i1][2]*ls[i1][3]
	i2 = 0
	while i2 != len(ls):
		try:
			if(ls[i2][0] == ls[i2+1][0] and ls[i2][1] == ls[i2+1][1]):
				ls[i2][2] += ls[i2+1][2]
				ls[i2][3] += ls[i2+1][3]
				del ls[i2+1]
				i2 += 0
			else:
				i2 += 1
		finally:
			if(i2 == len(ls)-1):
				i2 += 1
	newls = []
	for i3 in range(len(ls)):
		newls.append([ls[i3][0],ls[i3][1],ls[i3][3]/ls[i3][2]])
	newls.sort(key=lambda x:(x[0],x[1]))

	result = ''
	i4 = 0
	i5 = 0
	count = 0
	while i4 != len(newls):
		if(result == ""):
			result += ('Customer: ' + newls[i4][0] + '\n')
		if(newls[i4][0] != newls[i4-1][0]):
			if(count == len(product)):
				result += ('Customer: ' + newls[i4][0] + '\n')
				count = 0
		if (newls[i4][1] == product[i5]):
			result += ('Average price for ' + product[i5] + ": $" + str("%.2f" % newls[i4][2]) + '\n')
			i4 += 1
			i5 += 1
			count += 1
		else:
			result += ('Average price for ' + product[i5] + ": No purchase history" + '\n')
			i5 += 1
			count += 1
		if (i5 == len(product)):
			i5 = 0
			if (i4 != len(newls)):
				 result += '\n'

	outfile = open('result', 'w')
	outfile.write(result)
	infile.close()
	outfile.close()

def find(ls,name,SKU):
	if(len(ls) == 0):
		return -1
	for i in range(len(ls)):
		if(ls[i][0] == name and ls[i][1] == SKU):
			return i
	return -1

run("LabDemo.csv")
