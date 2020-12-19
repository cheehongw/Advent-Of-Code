import functools

f = open("input.txt","r")
input = list(map(int,f.read().split("\n")))
sorted_xs = [0] + sorted(input)

dict = {} #O(n) time and space with memoization. O(3^n) without memoization

def count_combi(curr): #curr refers to the current adapter's index. The initial adapter is always 0 and last adapter will be the largest adapter
    if curr in dict:
        return dict[curr]
    elif curr == len(sorted_xs) - 1: #there is only one way to go from last adapter to the phone
        return 1
    else:  
        no_of_ways = 0
        for i in [1,2,3]:
            if curr + i < len(sorted_xs) and sorted_xs[curr + i] - sorted_xs[curr] <= 3:
                no_of_ways += count_combi(curr + i)
        dict[curr] = no_of_ways
        return no_of_ways      


print(count_combi(0))
