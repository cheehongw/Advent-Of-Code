#include <iostream>
#include <string>
#include <unordered_set>

using namespace std;

int main(int argc, char const *argv[])
{
    int priority_sum = 0;

    string rucksack[3];
    while(cin >> rucksack[0]) {
        cin >> rucksack[1];
        cin >> rucksack[2];

        unordered_set<char> items;
        unordered_set<char> items2;

        for (char c : rucksack[0]) {
            items.insert(c);
        }

        for (char c : rucksack[1]) {
            if (items.find(c) != items.end()) {
                items2.insert(c);
            }
        }

        for (char c: rucksack[2]) {
            if (items2.find(c) != items2.end()) {
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
