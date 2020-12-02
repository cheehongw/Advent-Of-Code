f = open("input.txt", "r" )

input = f.read().split("\n")

def is_correct(string):
    criteria = string.partition(":")[0]
    letter = criteria.partition(" ")[2]
    freq = criteria.partition(" ")[0]
    idx1 = int(freq.partition("-")[0])
    idx2 = int(freq.partition("-")[2])
    password = string.partition(":")[2] #there will be a whitespace infront of every password. That whitespace shall represent index 0

    return (password[idx1] == letter and password[idx2] != letter) or (password[idx1] != letter and password[idx2] == letter)

res = list(filter(is_correct, input))

print(len(res))



        
