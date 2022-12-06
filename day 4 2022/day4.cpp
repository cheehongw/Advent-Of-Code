#include <iostream>
#include <string>
#include <vector>

using namespace std;

vector<int> findrange(string);

int main(int argc, char const *argv[])
{

    int fully_overlap_count = 0;
    int partial_overlap_count = 0;
    string line;
    while (cin >> line) {
        size_t pos = line.find(',');
        string str_range_1 = line.substr(0,pos);
        string str_range_2 = line.substr(pos + 1);
        vector<int> range_1 = findrange(str_range_1);
        vector<int> range_2 = findrange(str_range_2);

        if (range_1[1] >= range_2[1] && range_1[0] <= range_2[0]) { //check if range_1 contains range_2
            fully_overlap_count += 1; 
        } else if (range_2[1] >= range_1[1] && range_2[0] <= range_1[0]) { //check if range_2 fully contains range_1
            fully_overlap_count += 1; 
        } else if (range_1[1] >= range_2[0] && range_1[1] <= range_2[1]) {
            partial_overlap_count += 1;
        } else if (range_1[0] >= range_2[0] && range_1[0] <= range_2[1]) {
            partial_overlap_count += 1;
        }

    }

    cout << "count: " << fully_overlap_count << endl;
    cout << "total overlap: " << fully_overlap_count + partial_overlap_count << endl;
    return 0;
}

vector<int> findrange(string str_range) {
    vector<int> range(2);
    size_t pos = str_range.find('-');
    // cout << "finding range" << endl;
    range[0] = stoi(str_range.substr(0,pos));
    range[1] = stoi(str_range.substr(pos + 1));

    return range;
}