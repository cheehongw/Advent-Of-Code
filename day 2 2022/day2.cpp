#include <iostream>
#include <string>
using namespace std;

int rock_paper_scissors(char, char);
int rock_paper_scissors_v2(char, char);


int main(int argc, char const *argv[])
{
    char oppo;
    char us;
    long total = 0;
    long total2 = 0;
    while (cin >> oppo)
    {
        cin >> us;
        total += rock_paper_scissors(oppo, us);
        total2 += rock_paper_scissors_v2(oppo, us);

    }

    cout << "score: " << total << endl;
    cout << "score_v2: " << total2 << endl;
    
    return 0;
}

int rock_paper_scissors(char oppo, char us) {
    // cout << "oppo: " << oppo << endl;
    // cout << "us: " << us << endl;

    int score = 0;

    switch (us) {
        case 'X': { //rock
            if (oppo == 'A') score = 3;
            if (oppo == 'B') score = 0;
            if (oppo == 'C') score = 6;
            score += 1;
            break;
        }

        case 'Y': { //paper
            if (oppo == 'A') score = 6;
            if (oppo == 'B') score = 3;
            if (oppo == 'C') score = 0;    
            score += 2;

            break;

        }

        case 'Z': { //scissors
            if (oppo == 'A') score = 0;
            if (oppo == 'B') score = 6;
            if (oppo == 'C') score = 3;
            score += 3;
            break;
        }
    }

    // cout << "outcome: " << score << endl << endl;

    return score;
}

int rock_paper_scissors_v2(char oppo, char us) {
    // cout << "oppo: " << oppo << endl;
    // cout << "us: " << us << endl;

    int score = 0;

    switch (us) {
        case 'X': { //lose
            if (oppo == 'A') score = 3; //rock - scissors
            if (oppo == 'B') score = 1;  //paper - rock
            if (oppo == 'C') score = 2;
            break;
        }

        case 'Y': { //draw
            if (oppo == 'A') score = 1;
            if (oppo == 'B') score = 2;
            if (oppo == 'C') score = 3;    
            score += 3;
            break;

        }

        case 'Z': { //win
            if (oppo == 'A') score = 2;
            if (oppo == 'B') score = 3;
            if (oppo == 'C') score = 1;
            score += 6;
            break;
        }
    }

    // cout << "outcome: " << score << endl << endl;

    return score;
}
