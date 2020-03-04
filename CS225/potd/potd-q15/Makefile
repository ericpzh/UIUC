EXENAME = main
OBJS = food.o main.o

CXX = clang++
CXXFLAGS = -std=c++0x -c -g -O0 -Wall -Wextra
LD = clang++
LDFLAGS = -std=c++0x

all: $(EXENAME)

$(EXENAME): $(OBJS)
	$(LD) $^ $(LDFLAGS) -o $@

main.o: main.cpp
	$(CXX) $< $(CXXFLAGS)

food.o: food.cpp food.h
	$(CXX) $< $(CXXFLAGS)

clean:
	-rm -f *.o $(EXENAME)
