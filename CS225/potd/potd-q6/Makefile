EXENAME = main

#CXX = clang++
CXX = g++
CXXFLAGS = -std=c++0x -g -O0 -Wall -Wextra

all : $(EXENAME)

$(EXENAME): main.cpp student.cpp student.h q6.cpp q6.h
	$(CXX) $(CXXFLAGS) main.cpp student.cpp q6.cpp -o $(EXENAME)

.PHONY: clean
clean:
	rm -f $(EXENAME)

