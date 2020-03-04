EXENAME = main
OBJS = animal.o pet.o main.o

CXX = clang++
CXXFLAGS = -std=c++0x -c -g -O0 -Wall -Wextra
LD = clang++
LDFLAGS = -std=c++0x

all: $(EXENAME)

$(EXENAME): $(OBJS)
	$(LD) $^ $(LDFLAGS) -o $@

main.o: main.cpp
	$(CXX) $< $(CXXFLAGS)

animal.o: animal.cpp animal.h
	$(CXX) $< $(CXXFLAGS)

pet.o: pet.cpp pet.h
	$(CXX) $< $(CXXFLAGS)

clean :
	-rm -f *.o $(EXENAME)
