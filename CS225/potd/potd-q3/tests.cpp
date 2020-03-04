
#define CATCH_CONFIG_MAIN
#include "catch.hpp"
#include "epoch.h"

using namespace std;

bool correct[6];
time_t sec_since_epoch;

void reset_correct_arr() {
    sec_since_epoch = time(nullptr);
    for (int i = 0; i < 6; i++) {
        correct[i] = false;
    }
}

int sol_hours(time_t s) {
    return s / 3600;
};

int sol_days(time_t s) {
    return sol_hours(s)/24;
};


int sol_years(time_t s) {
    return sol_days(s) / 365;
};


TEST_CASE("Test that hours returns the correct number.") {
    reset_correct_arr();
    correct[0] = sol_hours(sec_since_epoch) == hours(sec_since_epoch);
    CHECK(correct[0]);
}

TEST_CASE("Test that days returns the correct number.") {
    reset_correct_arr();
    correct[1] = sol_days(sec_since_epoch) == days(sec_since_epoch);
    CHECK(correct[1]);
}

TEST_CASE("Test that years returns the correct number.") {
    reset_correct_arr();
    correct[2] = sol_years(sec_since_epoch) == years(sec_since_epoch);
    CHECK(correct[2]);
}
