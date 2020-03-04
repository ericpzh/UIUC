EXENAME = main
OBJS = main.o Chara.o

CXX = clang++
CXXFLAGS = -std=c++0x -c -g -O0 -Wall -Wextra
LD = clang++
LDFLAGS = -std=c++0x

all: $(EXENAME)

$(EXENAME): $(OBJS)
	$(LD) $^ $(LDFLAGS) -o $@

main.o: main.cpp
	$(CXX) $< $(CXXFLAGS)

Chara.o: Chara.cpp Chara.h
	$(CXX) $< $(CXXFLAGS)

clean:
	-rm -f *.o $(EXENAME)
