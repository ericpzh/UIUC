EXENAME = main

CXX = clang++
#CXX = g++
CXXFLAGS = -std=c++0x -g -O0 -Wall -Wextra

all : $(EXENAME)

$(EXENAME): main.cpp foo.o foo.h bar.o bar.h
	$(CXX) $(CXXFLAGS) main.cpp foo.cpp bar.cpp -o $(EXENAME)

foo.o: foo.cpp foo.h
	$(CXX) $(CXXFLAGS) -c foo.cpp

bar.o: foo.h bar.h bar.cpp
	$(CXX) $(CXXFLAGS) -c bar.cpp

.PHONY: clean
clean:
	rm -f $(EXENAME)

