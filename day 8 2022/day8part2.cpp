#include <iostream>
#include <vector>
#define ROWS 99
#define COLS 99

using namespace std;


void scan_left(vector<vector<int>>& scenic_scores, vector<vector<int>> trees) {
    for (int i = 0; i < ROWS; i++) {
        int tree_index[10]{};
        for (int j = 0; j < COLS; j++) {
            int tree_height = trees[i][j];
            int closest_blocking_tree_pos {0};
            for (int k = tree_height; k < 10; k++) {
                if (tree_index[k] > closest_blocking_tree_pos) {
                    closest_blocking_tree_pos = tree_index[k];
                }
            }
            int scenic_score = j - closest_blocking_tree_pos;
            scenic_scores[i][j] *= scenic_score;

            tree_index[tree_height] = j ;
        }
    }
}

void scan_top(vector<vector<int>>& scenic_scores, vector<vector<int>> trees) {
    for (int i = 0; i < COLS; i++) {
        int tree_index[10]{};
        for (int j = 0; j < ROWS; j++) {
            int tree_height = trees[j][i];
            int closest_blocking_tree_pos {0};
            for (int k = tree_height; k < 10; k++) {
                if (tree_index[k] > closest_blocking_tree_pos) {
                    closest_blocking_tree_pos = tree_index[k];
                }
            }
            int scenic_score = j - closest_blocking_tree_pos;
            scenic_scores[j][i] *= scenic_score;

            tree_index[tree_height] = j ;
        }
    }
}

void scan_right(vector<vector<int>>& scenic_scores, vector<vector<int>> trees) {
    for (int i = 0; i < ROWS; i++) {
        int tree_index[10]{};
        fill(tree_index, tree_index + 10, COLS - 1);

        for (int j = COLS - 1; j >= 0; j--) {
            int tree_height = trees[i][j];
            int closest_blocking_tree_pos {COLS - 1};
            for (int k = tree_height; k < 10; k++) {
                if (tree_index[k] < closest_blocking_tree_pos) {
                    closest_blocking_tree_pos = tree_index[k];
                }
            }
            int scenic_score = closest_blocking_tree_pos - j;
            scenic_scores[i][j] *= scenic_score;

            tree_index[tree_height] = j ;
        }
    }
}

void scan_bot(vector<vector<int>>& scenic_scores, vector<vector<int>> trees) {
    for (int i = 0; i < COLS; i++) {
        int tree_index[10]{};
        fill(tree_index, tree_index + 10, ROWS - 1);

        for (int j = ROWS - 1; j >= 0; j--) {
            int tree_height = trees[j][i];
            int closest_blocking_tree_pos {ROWS - 1};
            for (int k = tree_height; k < 10; k++) {
                if (tree_index[k] < closest_blocking_tree_pos) {
                    closest_blocking_tree_pos = tree_index[k];
                }
            }
            int scenic_score = closest_blocking_tree_pos - j;
            scenic_scores[j][i] *= scenic_score;

            tree_index[tree_height] = j ;
        }
    }
}

int main(int argc, char const *argv[])
{
    vector<vector<int>> scenic_scores(ROWS, vector<int>(COLS, 1));
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

    scan_left(scenic_scores, trees);
    scan_top(scenic_scores, trees);
    scan_bot(scenic_scores, trees);
    scan_right(scenic_scores, trees);

    int top_score{0};

    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            top_score = scenic_scores[i][j] > top_score ? scenic_scores[i][j] : top_score;
        }
    }

    cout << "top_score: " << top_score << endl;
    return 0;
    
}