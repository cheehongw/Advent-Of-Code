#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;


int main(int argc, char const *argv[])
{   
    string input;
    int sum = 0;
    vector<int> sums;

    while (getline(cin, input)) {
        if (input.empty()) {
            sums.push_back(sum);
            sum = 0;
        } else {
            sum += stoi(input);
        }
    }

    sum = 0;
    sort(sums.begin(), sums.end(), greater<int>());
    for (int i = 0; i < 3; i++) {
        cout << i << ". " << sums[i] << endl;
        sum += sums[i];
    }
    cout << "Total Sum: " << sum << endl;
    return 0;
}
