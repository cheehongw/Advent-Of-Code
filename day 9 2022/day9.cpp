#include <iostream>
#include <string>
#include <set>
#include <vector>

using namespace std;

struct Pair {
    int x {0};
    int y {0};

    bool operator<(const Pair& other) const {
        return x < other.x || (x == other.x && y < other.y);
    }
};

class Rope {
    private:
        vector<Pair> knots;
        set<Pair> visited;

    public:

        Rope(int num_of_knots) {
            knots = vector<Pair>(num_of_knots);
        }

        void follow(int head) {
            int tail = head + 1;

            int dist_x = abs(knots[head].x - knots[tail].x);
            int dist_y = abs(knots[head].y - knots[tail].y);

            if (dist_x + dist_y >= 3) {
                knots[tail].x += (knots[head].x - knots[tail].x > 0) ? 1 : -1;
                knots[tail].y += (knots[head].y - knots[tail].y > 0) ? 1 : -1;
            } else if (dist_x > 1) {
                knots[tail].x += (knots[head].x - knots[tail].x > 0) ? 1 : -1;
            } else if (dist_y > 1) {
                knots[tail].y += (knots[head].y - knots[tail].y > 0) ? 1 : -1;
            }

            if (tail == knots.size() - 1) {
                visited.emplace(knots[tail]);
            } else {
                follow(tail);
            };
        }

        void move(char dir, int count) {

            for (;count > 0; count--) {
                switch (dir)
                {
                case 'U':
                    knots[0].y += 1;
                    break;
                case 'D':
                    knots[0].y -= 1;
                    break;
                case 'L':
                    knots[0].x -= 1;
                    break;
                case 'R':
                    knots[0].x += 1;
                    break;
                }

                follow(0);
            }
        }

        int visited_count() {
            return visited.size();
        }

};

int main(int argc, char const *argv[])
{
    string token;
    Rope r(2);
    Rope r2(10);

    while (cin >> token) {
        char dir = token.at(0);
        cin >> token;
        int steps = stoi(token);
        r.move(dir, steps);
        r2.move(dir, steps);
    }

    cout << "total visited for rope of length 2: " << r.visited_count() << endl;
    cout << "total visited for rope of length 10: " << r2.visited_count() << endl;
    return 0;
}
