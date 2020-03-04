#define CATCH_CONFIG_MAIN

#include <string>
#include <iostream>
#include "catch.hpp"
#include "pet.h"


using namespace std;

Pet Rover;
Pet Trinity("Trinity",2002,"cat","Kelly");
Pet Ravi("Ravi",1995,"tarantula","Mattox");

TEST_CASE("Test that default constructor sets pet name properly.") {
        CHECK(Rover.getName() == "Rover");
}

TEST_CASE("Test that default constructor sets pet type properly.") {
        CHECK(Rover.getType() == "dog");
}

TEST_CASE("Test that default constructor sets pet birth year properly.") {
        CHECK(Rover.getBY() == 2017);
}

TEST_CASE("Test that default constructor sets pet owner properly.") {
        CHECK(Rover.getOwnerName() == "Cinda");
}


TEST_CASE("Test that generic constructor sets pet name properly.") {
        CHECK(Trinity.getName() == "Trinity");
        CHECK(Ravi.getName() == "Ravi");
}

TEST_CASE("Test that generic constructor sets pet type properly.") {
        CHECK(Trinity.getType() == "cat");
        CHECK(Ravi.getType() == "tarantula");
}

TEST_CASE("Test that generic constructor sets pet birth year properly.") {
        CHECK(Trinity.getBY() == 2002);
        CHECK(Ravi.getBY() == 1995);
}

TEST_CASE("Test that generic constructor sets pet owner properly.") {
        CHECK(Trinity.getOwnerName() == "Kelly");
        CHECK(Ravi.getOwnerName() == "Mattox");
}

