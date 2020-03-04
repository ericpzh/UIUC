CXX = clang++
CXXFLAGS = -std=c++0x -g -O0 -Wall -Wextra

all: thing.cpp thing.h main.cpp
	$(CXX) $(CXXFLAGS) main.cpp thing.cpp -o main

.PHONY: clean
clean:
	rm -f main
