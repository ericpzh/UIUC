CXX = clang++
CXXFLAGS = -std=c++0x -g -O0 -Wall -Wextra

all: piece.cpp piece.h queen.cpp queen.h main.cpp
	$(CXX) $(CXXFLAGS) main.cpp piece.cpp queen.cpp -o main

.PHONY: clean
clean:
	rm -f main
