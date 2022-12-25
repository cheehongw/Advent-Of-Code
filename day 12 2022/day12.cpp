#include <string>
#include <iostream>
#include <vector>
#include <deque>
#define ROWS 41
#define COLS 113

using namespace std;

class Vertex { 
    int LRTB[4][2] {{0, -1}, {0,1}, {-1, 0}, {1, 0}};

    public:
        int row;
        int col;

        bool operator==(const Vertex& other) {
            return row == other.row && col == other.col;
        }

        Vertex(int row, int col) : row(row), col(col) {};
        Vertex() {};

        vector<Vertex> visit(vector<vector<char>>& input) {
            cout << "visiting: " << row << ", " << col << ": " <<  input[row][col] << endl;
            vector<Vertex> list {};

            for (auto i : LRTB) {
                int new_row = row + i[0];
                int new_col = col + i[1];

                if (new_row < 0 || new_row >= ROWS || new_col < 0 || new_col >= COLS) {
                    continue;
                }

                if (input[new_row][new_col] < 0) { //take note of chars in other architectures.
                    continue;
                }

                if (input[new_row][new_col] - input[row][col] <= 1) {
                    list.push_back(Vertex(new_row, new_col));
                }
            }

            input[row][col] = -1;
            return list;
    }
    
};

int main(int argc, char const *argv[])
{
    vector<vector<char>> input(ROWS, vector<char>(COLS));
    Vertex target;
    Vertex start;

    char in;
    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++)
        {
            cin.get(in);
            while (in < 65 || in > 122) {
                cin.get(in);
            }

            input[i][j] = in;

            if (input[i][j] == 'E') {
                cout << "Target found at: " << i << "," << j << endl;
                target = Vertex{i,j};
                input[i][j] = 'z';
            }
            if (input[i][j] == 'S') {
                cout << "Start found at: " << i << "," << j << endl;
                start = Vertex{i,j};
                input[i][j] = 'a';
            }
        }
    }

    int wave {0};
    int visited {0};
    deque<Vertex> visiting {};
    deque<Vertex> other{};
    visiting.push_back(start);
    bool foundFlag {false};

    while (!visiting.empty() || !other.empty()){
        cout << "steps: " << wave << endl;
        cout << "visited: " << visited  << endl;
        // cout << "visiting: " << visiting.size() << ",  other: " << other.size() << endl;

        while (!visiting.empty()) {
            Vertex curr = visiting.front();
            visiting.pop_front();
            
            if (curr == target) {
                cout << "found target: " << curr.row << ", " << curr.col << endl;
                foundFlag = true;
                break;
            }

            if (input[curr.row][curr.col] < 0) {
                // cout << "skipping: " << curr.row << ", " << curr.col << input[curr.row][curr.col] << endl;
                continue;
            }
            
            vector<Vertex> list = curr.visit(input);
            visited += 1;
        
            for (auto i : list) {
                if (input[i.row][i.col] < 0) {
                    cout << "error" << endl;
                }
                other.push_back(i);
            }   
        }

        // cout << "visiting: " << visiting.size() << ",  other: " << other.size() << endl;

        if (foundFlag) {
            break;
        }
        
        wave += 1;
        other.swap(visiting);
    }

    cout << "Steps: " << wave << endl;

    return 0;
}
