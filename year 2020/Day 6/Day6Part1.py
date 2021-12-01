import functools
f = open("input.txt", "r")
input = f.read().split("\n\n")

def count_qns(string):
    count = 0
    for alphabet in "abcdefghijklmnopqrstuvwxyz":
        if alphabet in string:
            count += 1
    return count

print(functools.reduce(lambda x,y: x + count_qns(y),input,0)) 
            
