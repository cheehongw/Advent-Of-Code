import functools
f = open("input.txt", "r")
input = f.read().split("\n\n")

groups = list(map(lambda grp: grp.split("\n"), input))

def count_grps(group):
    count = 0
    for char in group[0]:
        exists = True
        for person in group:
            if char not in person:
                exists = exists and False
                break
        if exists:
            count += 1
            
    return count


print(functools.reduce(lambda x,y: x + count_grps(y),groups,0)) 
            
