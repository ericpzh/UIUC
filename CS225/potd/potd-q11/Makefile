EXENAME = main

#CXX = clang++
CXX = g++
CXXFLAGS = -std=c++11 -g -O0 -Wall -Wextra

all : $(EXENAME)

$(EXENAME): thing1.o thing2.o main.cpp
	$(CXX) $(CXXFLAGS) main.cpp thing1.o thing2.o -o $(EXENAME)

thing1.o: thing1.cpp thing1.h
	$(CXX) $(CXXFLAGS) -c thing1.cpp

thing2.o: thing2.cpp thing1.h thing2.h
	$(CXX) $(CXXFLAGS) -c thing2.cpp

.PHONY: clean
clean:
	rm -f *.o $(EXENAME)
