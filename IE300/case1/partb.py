import csv

def prob2(arg1,pos1,arg2,pos2,list,m,p):
    noarg12 = 0.0
    noarg2 = 0.0
    for i in range(len(list)):
        if(list[i][pos1] == arg1 and list[i][pos2] == arg2):
            noarg12 += 1
        if(list[i][pos2] == arg2):
            noarg2 += 1
    return (noarg12+m*p)/(noarg2+m)

def prob1(arg,pos,list):
    noarg = 0.0
    for i in range(len(list)):
        if(list[i][pos] == arg):
            noarg += 1
    return noarg

def case1b(data):
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
    oriList.sort( key = lambda x:(x[0],x[1],x[2]))
    PAAY = prob2("AA",0,"Y",6,oriList,3,0.5)
    PAAN = prob2("AA",0,"N",6,oriList,3,0.5)
    PJFKY = prob2("JFK",1,"Y",6,oriList,3,0.5)
    PJFKN = prob2("JFK",1,"N",6,oriList,3,0.5)
    PLASY = prob2("LAS",2,"Y",6,oriList,3,0.5)
    PLASN = prob2("LAS",2,"N",6,oriList,3,0.5)
    PB6Y = prob2("B6",0,"Y",6,oriList,3,0.5)
    PB6N = prob2("B6",0,"N",6,oriList,3,0.5)
    PVXY = prob2("VX",0,"Y",6,oriList,3,0.5)
    PVXN = prob2("VX",0,"N",6,oriList,3,0.5)
    PSFOY = prob2("SFO",1,"Y",6,oriList,3,0.5)
    PSFON = prob2("SFO",1,"N",6,oriList,3,0.5)
    PORDY = prob2("ORD",2,"Y",6,oriList,3,0.5)
    PORDN = prob2("ORD",2,"N",6,oriList,3,0.5)
    PWNY = prob2("WN",0,"Y",6,oriList,3,0.5)
    PWNN = prob2("WN",0,"N",6,oriList,3,0.5)
    PY = prob1("Y",6,oriList)
    PN = prob1("N",6,oriList)

    PYJFKLASAA = PY*PAAY*PJFKY*PLASY
    PNJFKLASAA = PN*PAAN*PJFKN*PLASN
    PYJFKLASB6 = PY*PB6Y*PJFKY*PLASY
    PNJFKLASB6 = PN*PB6N*PJFKN*PLASN
    PYSFOORDVX = PY*PVXY*PSFOY*PORDY
    PNSFOORDVX = PN*PVXN*PSFON*PORDN
    PYSFOORDWN = PY*PWNY*PSFOY*PORDY
    PNSFOORDWN = PN*PWNN*PSFON*PORDN

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
    print(output)

case1b("/home/zp3/PycharmProjects/case1/FlightDelay.csv")