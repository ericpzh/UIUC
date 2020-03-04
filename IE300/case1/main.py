
import csv
import numpy as np
import matplotlib.pyplot as plt

## partB helper function
## calculate P(something | Y or N)
def prob2(arg1,pos1,arg2,pos2,list,m,p):
    noarg12 = 0.0
    noarg2 = 0.0
    for i in range(len(list)):
        if(list[i][pos1] == arg1 and list[i][pos2] == arg2):
            noarg12 += 1
        if(list[i][pos2] == arg2):
            noarg2 += 1
    return (noarg12+m*p)/(noarg2+m)
## partB helper function
## calculate P(Y or N)
def prob1(arg,pos,list):
    sumarg = 0.0
    for i in range(len(list)):
        sumarg += 1
    noarg = 0.0
    for i in range(len(list)):
        if(list[i][pos] == arg):
            noarg += 1
    return noarg/sumarg
##case 1a
def case1a(data):
    ##import data
    infile = open(data,'r')
    reader = csv.reader(infile)
	##initialize 3 lists for later use oriList contain all element, usefulist contain only carrier/2 delays, airlineList contains only carriers
    oriList = []
    usefulList = []
    airlineList =[]
    for line in reader:
        if(line[0] != "Flight Time"): ## not import 1st line
            if(float(line[9]) >= 230): ## filter invalid data
                oriList.append([line[0],line[1],line[2],line[3],line[4],float(line[5]),float(line[6]),float(line[7]),float(line[8]),float(line[9])])
                usefulList.append([line[1],float(line[6]),float(line[8])])
                airlineList.append(line[1])
	##initial sort
    oriList.sort()
    usefulList.sort()
    airlineList.sort()
    infile.close()
    ##processing data
    numberOfObservations = len(oriList)
    targetFlightTime = 0.117*1741.16+0.517*(-88.9+118.41)+20
    depDelay = 0
    ariDelay = 0
    for i in range(len(oriList)):
        depDelay += oriList[i][6]
        ariDelay += oriList[i][8]
    averageDepartureDelay = depDelay/numberOfObservations
    averageArrivalDelay = ariDelay/numberOfObservations
    typicalTime = averageArrivalDelay+averageDepartureDelay+targetFlightTime
	##reduce sum airline list to a list containing airlines, each airline appears in the list exactly once
    i = 0
    while i < len(airlineList)-1:
        if(airlineList[i] == airlineList[i+1]):
            del airlineList[i+1]
        else:
            i+=1
	##deep copy list, save airlineList for possible different usage in future
    sumAirlineList = []
    for i in range(len(airlineList)):
        sumAirlineList.append(1)
	##deep copy list, save usefulist for possible different usage in future
    tempUsefulList = []
    for i in range(len(usefulList)):
        tempUsefulList.append(usefulList[i])
	##delete redundant element in usefulList and sum them so that each carriers appears in the list exactly once
    i = 0
    while i < len(sumAirlineList):
        if(i == len(tempUsefulList)-1):
            break
        elif(tempUsefulList[i][0] == tempUsefulList[i+1][0]):
            sumAirlineList[i] += 1
            del tempUsefulList[i+1]
        else:
            i += 1
    i = 0
    while i < len(usefulList)-1:
        if(usefulList[i][0] == usefulList[i+1][0]):
            usefulList[i][1] += usefulList[i+1][1]
            usefulList[i][2] += usefulList[i+1][1]
            del usefulList[i+1]
        else:
            i += 1
	##calculate average for different carriers
    for i in range(len(usefulList)):
        usefulList[i][1] = usefulList[i][1]/sumAirlineList[i]
        usefulList[i][2] = usefulList[i][2] / sumAirlineList[i]
        usefulList[i][1] += usefulList[i][2]
        del usefulList[i][2]
    minTimeDic = {}
    minTimeList = []
    for i in range(len(usefulList)):
        minTimeDic[usefulList[i][1]] = usefulList[i][0]
        minTimeList.append(usefulList[i][1])
    minTime = min(minTimeList)
    minTimeAirline = minTimeDic[minTime]
    ##output data to a string + write string to file
    result = ""
    result += "Using FlightTime data of flight from: "
    result += oriList[0][3]
    result += " to "
    result += oriList[0][4]
    result += ". \n"
    result += "Number of observations in my dataset is: "
    result += str(numberOfObservations)
    result += ". \n"
    result += "Target flight time is: "
    result += str("%.2f" % targetFlightTime)
    result += "min. \n"
    result += "Typical time for this route is: "
    result += str("%.2f" % typicalTime)
    result += "min. \n"
    for i in range(len(usefulList)):
        result += "For airline: "
        result += usefulList[i][0]
        result += " average delay is: "
        result += str("%.2f" % usefulList[i][1])
        result +="min. \n"
    result += "So, the lowest delay time is: "
    result += str("%.2f" % minTime)
    result += "min of airline: "
    result += minTimeAirline
    result += ". \n"
    outfile = open('/home/zp3/PycharmProjects/case1/outputA.txt','w')
    outfile.write(result)
    outfile.close()

    ##graph
    for i in range(len(minTimeList)):
        minTimeList[i] = round(minTimeList[i],2)
    xAxis = airlineList
    yAxis = minTimeList
    fig,ax = plt.subplots()
    plt.bar(np.arange(len(yAxis)),yAxis)
    
    ax.set_ylabel('Average Delay Time')
    ax.set_title('Average Delay Time for Different Airline')
    ax.set_xticks(np.arange(len(yAxis)))
    ax.set_xticklabels(xAxis)
    plt.show()

##case 1b
def case1b(data):
	##input file
    infile = open(data, 'r')
    reader = csv.reader(infile)
    oriList = []
    for line in reader:
        if(line[0] != "Carrier"):
            oriList.append([line[0],line[1],line[2],float(line[3]),float(line[4]),float(line[3])+float(line[4])])
    for i in range(len(oriList)):
        if(oriList[i][5] > 15):
            oriList[i].append('Y')
        else:
            oriList[i].append('N')
	##sorting
    oriList.sort( key = lambda x:(x[0],x[1],x[2]))
	##calculate each P e.g. PAAY = P(AA|Y)
    PAAY = prob2("AA",0,"Y",6,oriList,3,1/8)
    PAAN = prob2("AA",0,"N",6,oriList,3,1/8)
    PJFKY = prob2("JFK",1,"Y",6,oriList,3,0.5)
    PJFKN = prob2("JFK",1,"N",6,oriList,3,0.5)
    PLASY = prob2("LAS",2,"Y",6,oriList,3,1/3)
    PLASN = prob2("LAS",2,"N",6,oriList,3,1/3)
    PB6Y = prob2("B6",0,"Y",6,oriList,3,1/8)
    PB6N = prob2("B6",0,"N",6,oriList,3,1/8)
    PVXY = prob2("VX",0,"Y",6,oriList,3,1/8)
    PVXN = prob2("VX",0,"N",6,oriList,3,1/8)
    PSFOY = prob2("SFO",1,"Y",6,oriList,3,0.5)
    PSFON = prob2("SFO",1,"N",6,oriList,3,0.5)
    PORDY = prob2("ORD",2,"Y",6,oriList,3,1/3)
    PORDN = prob2("ORD",2,"N",6,oriList,3,1/3)
    PWNY = prob2("WN",0,"Y",6,oriList,3,1/8)
    PWNN = prob2("WN",0,"N",6,oriList,3,1/8)
    PY = prob1("Y",6,oriList)
    PN = prob1("N",6,oriList)
	##calculate final result e.g. P(YJFKLASAA) = P(JFK,LAS,AA|Y)
    PYJFKLASAA = PY*PAAY*PJFKY*PLASY
    PNJFKLASAA = PN*PAAN*PJFKN*PLASN
    PYJFKLASB6 = PY*PB6Y*PJFKY*PLASY
    PNJFKLASB6 = PN*PB6N*PJFKN*PLASN
    PYSFOORDVX = PY*PVXY*PSFOY*PORDY
    PNSFOORDVX = PN*PVXN*PSFON*PORDN
    PYSFOORDWN = PY*PWNY*PSFOY*PORDY
    PNSFOORDWN = PN*PWNN*PSFON*PORDN
	##decide classification
    if(PYJFKLASAA>PNJFKLASAA):
        P1 = "Y"
    else:
        P1 = "N"
    if(PYJFKLASB6>PNJFKLASB6):
        P2 = "Y"
    else:
        P2 = "N"
    if(PYSFOORDVX>PNSFOORDVX):
        P3 = "Y"
    else:
        P3 = "N"
    if(PYSFOORDWN>PNSFOORDWN):
        P4 = "Y"
    else:
        P4 = "N"
	##output to string + write the string to txt
    output = ""
    output += "#1.P(Y|JFK,LAS,AA)= "
    output += str(PYJFKLASAA)
    output += "\n P(N|JFK,LAS,AA)= "
    output += str(PNJFKLASAA)
    output += "\n Classification = "
    output += str(P1)
    output += "\n \n"
    output += "#2.P(Y|JFK,LAS,B6)= "
    output += str(PYJFKLASB6)
    output += "\n P(N|JFK,LAS,B6)= "
    output += str(PNJFKLASB6)
    output += "\n Classification = "
    output += str(P2)
    output += "\n \n"
    output += "#3.P(Y|SFO,ORD,VX)= "
    output += str(PYSFOORDVX)
    output += "\n P(N|SFO,ORD,VX)= "
    output += str(PNSFOORDVX)
    output += "\n Classification = "
    output += str(P3)
    output += "\n \n"
    output += "#4.P(Y|SFO,ORD,WN)= "
    output += str(PYSFOORDWN)
    output += "\n P(N|SFO,ORD,WN)= "
    output += str(PNSFOORDWN)
    output += "\n Classification = "
    output += str(P4)
    outfile = open('/home/zp3/PycharmProjects/case1/outputB.txt','w')
    outfile.write(output)
    outfile.close()
##call the functions
case1a("/home/zp3/PycharmProjects/case1/FlightTime.csv")
case1b("/home/zp3/PycharmProjects/case1/FlightDelay.csv")
