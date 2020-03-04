#include <vector>
#include <string>
#include <cmath>
#include <iostream>
#include <algorithm>
using namespace std;

double Binomial(string Option, double K, double T, double S0, double sigma, double r, double q, double N, string Exercise){
    vector<double> arr(int(N)+1,0.0);
    arr[0] = (S0);
    int n = 0;
    for (n = 0; n < N + 1; n++){
        for (double i = 0; i < n + 1; i++){
            double delta = T / double(N);
            double u = exp(sigma *  sqrt(delta));
            double d = exp(-sigma *  sqrt(delta));
            arr[i] = pow(u,i) * pow(d,(n - i)) * S0;
          }
        }
    for (int i = 0; i < arr.size(); i++){
        if (Option == "P"){
            arr[i] = max(0.0, K - arr[i]);
          }
        else{
            arr[i] = max(0.0, arr[i] - K);}
          }
    n = arr.size();
    for (n = arr.size() - 2; n > -1; n--){
        for (double i = 0; i < n + 1; i++){
            double delta = T / double(N);
            double u = exp(sigma *  sqrt(delta));
            double d =  exp(-sigma *  sqrt(delta));
            double fu = arr[i + 1];
            double fd = arr[i];
            double pstar = (( exp((r - q) * delta) - d) / double(u - d));
            if (Exercise == "E"){
                arr[i] =  exp(-r * delta) * (pstar * fu + (1 - pstar) * fd);
              }
            else if (Option == "P"){
                double S = pow(u,i) * pow(d,(n - i)) * S0;
                arr[i] = max((K - S), exp(-r *  delta) * (pstar * fu + (1 - pstar) * fd));
              }
            else{
                double S = pow(u,i) * pow(d,(n - i)) * S0;
                arr[i] = max((S - K),  exp(-r * delta) * (pstar * fu + (1 - pstar) * fd));
              }
              }
            }
    return arr[0];
}

int main(){
  cout<<Binomial("P",100,10/12.0,75.0,0.2,0.4,0.05,10000,"E")<<endl;
  return 0;
}
