import functools, sys

sys.setrecursionlimit(2000)

f = open("9day.txt","r")
input = list(map(int,f.read().split("\n")))

puzzle_1_ans = 32321523

def contiguous_sum(start, end):
    sum = functools.reduce(lambda x,y: x + y, input[start:end+1])

    if input[end] > puzzle_1_ans:
        return contiguous_sum(end, end + 1)
    elif sum < puzzle_1_ans:
        return contiguous_sum(start,end+1)
    elif sum > puzzle_1_ans:  
        return contiguous_sum(start+1,end)
    else:
        return min(input[start:end + 1]) + max(input[start:end + 1]),start,end+1

print(functools.reduce(lambda x,y: x + y, input[407:424]))

print(contiguous_sum(0,1))
