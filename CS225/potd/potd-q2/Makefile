EXENAME = q2

CXX = clang++
CXXFLAGS = -std=c++0x -g -O0 -Wall -Wextra

.PHONY: all
all : $(EXENAME)

.PHONY: help
help:
	@echo Makefile targets:
	@echo help   --- display this message
	@echo all    --- compile $(EXENAME)
	@echo clean  --- remove executable and temporary files
	@echo upload --- send your submission to PrairieLearn

$(EXENAME): q2.cpp epoch.cpp epoch.h
	$(CXX) $(CXXFLAGS) q2.cpp epoch.cpp -o $(EXENAME)

.PHONY: clean
clean:
	rm -f $(EXENAME)
