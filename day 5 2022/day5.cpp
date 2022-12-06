#include <iostream>
#include <string>
#include <stack>
#include <vector>
#include <queue>
#include <sstream>
#include <cmath>

using namespace std;

vector<int> generate_instructions(string);
void move_boxes_9000(vector<stack<char>>&, vector<int>);
void move_boxes_9001(vector<stack<char>>&, vector<int>);

int main(int argc, char const *argv[])
{   
    vector<stack<char>> piles(9);
    int i = 0;
    char in;
    while (cin.get(in)) {
        if (isdigit(in)) {
            break;
        }
        if (i % 4 == 1 && in != ' ') {
            int pile_num = floor(i/4);
            piles[pile_num].push(in);
        }
        i = (i + 1) % 36;
    }

    queue<char> temp;
    for (stack<char> &s : piles) {
        // cout << s.size() << " " ;
        
        while (!s.empty()) {
            temp.push(s.top());
            s.pop();
        }

        while(!temp.empty()) {
            s.push(temp.front());
            temp.pop();
        }

    }

    string instr;
    getline(cin, instr); //clear out line 9 of input, move to instructions
    while(getline(cin, instr)) {
        // move_boxes_9000(piles, generate_instructions(instr));    // challenge part 1
        move_boxes_9001(piles, generate_instructions(instr));   //challenge part 2
    }

    for (stack<char>& s : piles) {
        cout << s.top();
    }

    cout << endl;

    return 0;
}

vector<int> generate_instructions(string instr) {
    istringstream stream(instr);
    string word;
    int i = 0;
    vector<int> instructions(3);
    while (stream >> word) {
        switch (i) {
        case 1: {
            instructions[0] = stoi(word);
            break;
        }
        case 3: {
            instructions[1] = stoi(word);
            break;
        } case 5: {
            instructions[2] = stoi(word);
            break;
        }}
        i += 1;
    }

    return instructions;
}


void move_boxes_9000(vector<stack<char>>& piles, vector<int> instructions) {
    stack<char> &outpile = piles[instructions[1] - 1];
    stack<char> &inpile = piles[instructions[2] - 1];
    
    for (int i = 0; i < instructions[0]; i++) {
        char x = outpile.top();
        inpile.push(x);
        outpile.pop();
    }
}

void move_boxes_9001(vector<stack<char>>& piles, vector<int> instructions) {
    stack<char> &outpile = piles[instructions[1] - 1];
    stack<char> &inpile = piles[instructions[2] - 1];
    
    stack<char> temp;
    for (int i = 0; i < instructions[0]; i++) {
        char x = outpile.top();
        temp.push(x);
        outpile.pop();
    }

    while (!temp.empty()) {
        inpile.push(temp.top());
        temp.pop();
    }
}