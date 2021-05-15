import sys

sys.setrecursionlimit(3000)

starting = [1,20,11,6,12,0]


def nth_num(n):

    spoken = {}

    def say(num, turn): #says the number at its turn
        if turn >= n:
            return num,turn
        elif turn <= len(starting):
            spoken[starting[turn - 1]] = turn
            return say(0, turn + 1)
        elif num not in spoken:
            spoken[num] = turn
            return say(0, turn + 1)
        elif num in spoken:
            turn_diff = turn - spoken[num]
            spoken[num] = turn
            return say(turn_diff, turn + 1)

    return say(0,1)

print(nth_num(2020))
        
        
                
