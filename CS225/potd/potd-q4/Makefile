EXENAME = main

#CXX = clang++
CXX = g++
CXXFLAGS = -std=c++11 -g -O0 -Wall -Wextra

all : $(EXENAME)

$(EXENAME): q4.o q4.h circle.h circle.o
	$(CXX) $(CXXFLAGS) main.cpp q4.o circle.o -o $(EXENAME)

q4.o: q4.h q4.cpp
	$(CXX) $(CXXFLAGS) -c q4.cpp

circle.o: circle.h circle.cpp
	$(CXX) $(CXXFLAGS) -c circle.cpp

.PHONY: clean
clean:
	rm -f *.o $(EXENAME)
