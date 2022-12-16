#include <iostream>
#include <string>

using namespace std;

int main(int argc, char const *argv[])
{
    int x_reg {1};
    int execution_time {0};
    int val_reg {0};
    string token{};
    int signal_strength {0};

    for (int i = 1; i <= 240; i++) {
        
        if (execution_time == 0) {
            cin >> token;

            if (token ==  "addx") {
                cin >> token;
                val_reg = stoi(token);
                execution_time += 1;
            }
        } else {
            execution_time--;
        }

        if (abs(x_reg - ((i-1) % 40)) <= 1) {
            cout << "#";
        } else {
            cout << ".";
        }
        
        if (i % 40 == 0) {
            cout << endl;
        }


        if (i % 40 == 20) {
            signal_strength += i*x_reg;
        }

        if (execution_time == 0) {
            x_reg += val_reg;
            val_reg = 0;
        }
    
    }

    cout << "signal strength: " << signal_strength << endl;


    return 0;
}
