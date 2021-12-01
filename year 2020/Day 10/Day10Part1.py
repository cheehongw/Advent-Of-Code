import functools

f = open("test2.txt","r")
input = list(map(int,f.read().split("\n")))

sorted_xs = sorted(input)

count_1 = 0
count_3 = 1 #highest rated voltage is not included in the list of adapters

def counter(curr, nxt):
    global count_1, count_3
    if nxt - curr == 1:
        count_1 += 1
    elif nxt - curr == 3:
        count_3 += 1
    return nxt
    
    
functools.reduce(counter ,sorted_xs, 0)
print(count_1, count_3, count_1 * count_3)
