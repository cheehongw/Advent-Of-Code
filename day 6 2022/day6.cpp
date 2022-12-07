#include <iostream>
#include <algorithm>
#include <string>
#include <deque>
#include <set>

using namespace std;

int main(int argc, char const *argv[])
{
    char letter;
    int i = 0;
    bool found4 = false;
    bool found14 = false;
    deque<char> top4;
    deque<char> top14;
    while (cin.get(letter)) {
        i += 1;
        top4.push_back(letter);
        top14.push_back(letter);
        if (top4.size() > 4) {
            top4.pop_front();
        }

        if (top14.size() > 14) {
            top14.pop_front();
        }
        
        if (top4.size() == 4 && !found4) {
            set<char> s(top4.begin(), top4.end());
            if (s.size() == 4) {
                cout << "packet begins at: " << i << endl;
                found4 = true;
            }
        }

        if (top14.size() == 14 && !found14) {
            set<char> ss(top14.begin(), top14.end());
            if (ss.size() == 14 && top14.size() == 14) {
                cout << "message begins at: " << i << endl;
                found14 == true;
            }
        }

        if (found14 && found4) {
            break;
        }
    }

    return 0;
}
