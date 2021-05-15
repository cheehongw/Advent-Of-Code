starting = [1,20,11,6,12,0]


def nth_num(n):

    turn = 1
    spoken = {}
    num = 0

    while turn < n:
        if turn <= len(starting):
            spoken[starting[turn - 1]] = turn
            turn += 1
        elif num in spoken:
            diff = turn - spoken[num]
            spoken[num] = turn
            num = diff
            turn += 1
        else:
            spoken[num] = turn
            num = 0
            turn += 1
            
    return num
    

print(nth_num(30000000))
        
