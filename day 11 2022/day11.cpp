#include <iostream>
#include <string>
#include <queue>
#include <vector>
#include <functional> 
#include <algorithm>

#define MOD (11 * 3 * 5 * 7 * 19 * 2 * 13 * 17)

using namespace std;

class Monkey {
    private:
        queue<long> item_worry_levels {};
        int inspection_count {0};
        long (*operation)(long);
        bool (*test)(long); 
        function<void (bool, long)> throw_to;
    public:


        Monkey(vector<long> items, 
        long (*operation_)(long), 
        bool (*test_)(long), 
        function<void (bool, long)> throw_to_) : operation(operation_), test(test_), throw_to(throw_to_) {
            for (long i : items) {
                item_worry_levels.push(i);
            }
        };
        
        void inspect_items() {
            while(!item_worry_levels.empty()) {
                long item = item_worry_levels.front();
                item_worry_levels.pop();
                // long new_worry_level = operation(item) / 3;
                long new_worry_level = operation(item) % MOD;
                bool outcome = test(new_worry_level);
                throw_to(outcome, new_worry_level);
                inspection_count += 1;
            }
        }

        void receive_item(long item) {
            item_worry_levels.push(item);
        }

        int get_inspection_count() {
            return inspection_count;
        }

        void print_items() {
            queue<long> temp {};
            while(!item_worry_levels.empty()) {
                long item = item_worry_levels.front();
                temp.push(item);
                cout << item << " ";
                item_worry_levels.pop();
            }

            while(!temp.empty()) {
                item_worry_levels.push(temp.front());
                temp.pop();
            }
        }
        
};


int main(int argc, char const *argv[])
{
    vector<reference_wrapper<Monkey>> monkeys{};

    Monkey m0 = Monkey(vector<long>({56, 52, 58, 96, 70, 75, 72}), 
            [](long old) {return old * 17;}, 
            [](long input){return input % 11 == 0;},
            [&monkeys](bool outcome, long item){
                if (outcome) {monkeys[2].get().receive_item(item);}
                else {monkeys[3].get().receive_item(item);}
            });

    Monkey m1 = Monkey(vector<long>({75, 58, 86, 80, 55, 81}), 
            [](long old) {return old + 7;}, 
            [](long input){return input % 3 == 0;},
            [&monkeys](bool outcome, long item){
                if (outcome) {monkeys[6].get().receive_item(item);}
                else {monkeys[5].get().receive_item(item);}
            });

    Monkey m2 = Monkey(vector<long>({73, 68, 73, 90}), 
            [](long old) {return old * old;}, 
            [](long input){return input % 5 == 0;},
            [&monkeys](bool outcome, long item){
                if (outcome) {monkeys[1].get().receive_item(item);}
                else {monkeys[7].get().receive_item(item);}
            });

    Monkey m3 = Monkey(vector<long>({72, 89, 55, 51, 59}), 
            [](long old) {return old + 1;}, 
            [](long input){return input % 7 == 0;},
            [&monkeys](bool outcome, long item){
                if (outcome) {monkeys[2].get().receive_item(item);}
                else {monkeys[7].get().receive_item(item);}
            });

    Monkey m4 = Monkey(vector<long>({76, 76, 91}), 
            [](long old) {return old * 3;}, 
            [](long input){return input % 19 == 0;},
            [&monkeys](bool outcome, long item){
                if (outcome) {monkeys[0].get().receive_item(item);}
                else {monkeys[3].get().receive_item(item);}
            });

    Monkey m5 = Monkey(vector<long>({88}), 
            [](long old) {return old + 4;}, 
            [](long input){return input % 2 == 0;},
            [&monkeys](bool outcome, long item){
                if (outcome) {monkeys[6].get().receive_item(item);}
                else {monkeys[4].get().receive_item(item);}
            });

    Monkey m6 = Monkey(vector<long>({64, 63, 56, 50, 77, 55, 55, 86}), 
            [](long old) {return old + 8;}, 
            [](long input){return input % 13 == 0;},
            [&monkeys](bool outcome, long item){
                if (outcome) {monkeys[4].get().receive_item(item);}
                else {monkeys[0].get().receive_item(item);}
            });

    Monkey m7 = Monkey(vector<long>({79, 58}), 
            [](long old) {return old + 6;}, 
            [](long input){return input % 17 == 0;},
            [&monkeys](bool outcome, long item){
                if (outcome) {monkeys[1].get().receive_item(item);}
                else {monkeys[5].get().receive_item(item);}
            });

    monkeys.push_back(m0);
    monkeys.push_back(m1);
    monkeys.push_back(m2);
    monkeys.push_back(m3);
    monkeys.push_back(m4);
    monkeys.push_back(m5);
    monkeys.push_back(m6);
    monkeys.push_back(m7);

    // Monkey t0 = Monkey(vector<long>({79, 98}), 
    //         [](long old) {return old * 19;}, 
    //         [](long input){return input % 23 == 0;},
    //         [&monkeys](bool outcome, long item){
    //             if (outcome) {monkeys[2].get().receive_item(item);}
    //             else {monkeys[3].get().receive_item(item);}
    //         });
    
    // Monkey t1 = Monkey(vector<long>({54, 65, 75, 74}), 
    //         [](long old) {return old + 6;}, 
    //         [](long input){return input % 19 == 0;},
    //         [&monkeys](bool outcome, long item){
    //             if (outcome) {monkeys[2].get().receive_item(item);}
    //             else {monkeys[0].get().receive_item(item);}
    //         });

    // Monkey t2 = Monkey(vector<long>({79, 60, 97}), 
    //         [](long old) {return old * old;}, 
    //         [](long input){return input % 13 == 0;},
    //         [&monkeys](bool outcome, long item){
    //             if (outcome) {monkeys[1].get().receive_item(item);}
    //             else {monkeys[3].get().receive_item(item);}
    //         });
    
    // Monkey t3 = Monkey(vector<long>({74}), 
    //         [](long old) {return old + 3;}, 
    //         [](long input){return input % 17 == 0;},
    //         [&monkeys](bool outcome, long item){
    //             if (outcome) {monkeys[0].get().receive_item(item);}
    //             else {monkeys[1].get().receive_item(item);}
    //         });

    // monkeys.push_back(t0);
    // monkeys.push_back(t1);
    // monkeys.push_back(t2);
    // monkeys.push_back(t3);

    for(int i = 0; i < 10000; i++) {
        
        cout << "Round " << i + 1 << endl;

        for (int j = 0; j < 8; j++) {
            Monkey& m = monkeys[j];
            m.inspect_items();
        }

    }

    vector<int> counts {};
    for (int j = 0; j < 8; j++) {
        Monkey& m = monkeys[j];
        int count = m.get_inspection_count();
        counts.push_back(count);
        cout << "Monkey " << j << " inspected items " << count << " times." << endl;
        // cout << "Monkey " << j << ": ";
        // m.print_items();
        // cout << endl;
    } 

    sort(counts.rbegin(), counts.rend());
    cout << "score " << long(counts[0]) * long(counts[1]) << endl; 


    return 0;
}
