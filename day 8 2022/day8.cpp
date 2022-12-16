#include <iostream>
#include <vector>
#define ROWS 99
#define COLS 99

using namespace std;

int main(int argc, char const *argv[])
{
    vector<vector<bool>> visible_trees(ROWS, vector<bool>(COLS, false));
    vector<vector<int>> trees(ROWS, vector<int>(COLS, 0));
    char tree {0};

    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {

            cin.get(tree);

            while (tree == '\n' || tree == '\r') { 
                cin.get(tree);
            }

            trees[i][j] = tree - '0';
        }
    }


    vector<int> top_trees(COLS, -1);
    vector<int> left_trees(ROWS, -1);
    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            if (trees[i][j] > top_trees[j]) {
                visible_trees[i][j] = true;
                top_trees[j] = trees[i][j];
            }

            if (trees[i][j] > left_trees[i]) {
                visible_trees[i][j] = true;
                left_trees[i] = trees[i][j];
            }
        }
    }

    vector<int> bot_trees(COLS, -1);
    vector<int> right_trees(ROWS, -1);
    for (int i = ROWS - 1; i >= 0; i--) {
        for (int j = COLS - 1; j >= 0; j--) {
            if (trees[i][j] > bot_trees[j]) {
                visible_trees[i][j] = true;
                bot_trees[j] = trees[i][j];
            }

            if (trees[i][j] > right_trees[i]) {
                visible_trees[i][j] = true;
                right_trees[i] = trees[i][j];
            }
        }
    }

    int count {0};
    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            if (visible_trees[i][j]) count++;
        }
    }

    cout << "total visible trees: " << count << endl;

    return 0;
}
