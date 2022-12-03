#include <iostream>
#include <string>
#include <unordered_set>

using namespace std;

int main(int argc, char const *argv[])
{
    int priority_sum = 0;

    string rucksack;
    while(cin >> rucksack) {
        string tophalf = rucksack.substr(0, rucksack.size()/2);
        string bothalf = rucksack.substr(rucksack.size()/2);
        unordered_set<char> items;

        for (char c : tophalf) {
            items.insert(c);
        }

        for (char c : bothalf) {
            if (items.find(c) != items.end()) {
                if (c >= 97) {
                    priority_sum += (c - 96);
                } else {
                    priority_sum += (c- 38);
                }

                break;
            }
        }

    }
 
    cout << "total priority: " << priority_sum << endl;
    return 0;
}
