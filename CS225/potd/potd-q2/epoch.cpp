/* Your code here! */
#include "epoch.h"

int hours(time_t t){
  return t/3600;
}
int days(time_t t){
 return t/3600/24;
}
int years(time_t t){
  return t/365/3600/24;
}
