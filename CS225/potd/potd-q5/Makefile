EXENAME = main

CXX = clang++
#CXX = g++
CXXFLAGS = -std=c++11 -g -O0 -Wall -Wextra

all : $(EXENAME)

$(EXENAME): main.cpp food.h food.o q5.o q5.h
	$(CXX) $(CXXFLAGS) main.cpp food.o q5.o -o $(EXENAME)

food.o: food.h food.cpp
	$(CXX) $(CXXFLAGS) -c food.cpp

q5.o: q5.h q5.cpp
	$(CXX) $(CXXFLAGS) -c q5.cpp

.PHONY: clean
clean:
	rm -f $(EXENAME)
