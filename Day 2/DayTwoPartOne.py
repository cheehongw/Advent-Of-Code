f = open("input.txt", "r" )

input = f.read().split("\n")

def is_correct(string):
    criteria = string.partition(":")[0]
    letter = criteria.partition(" ")[2]
    freq = criteria.partition(" ")[0]
    minimum = int(freq.partition("-")[0])
    maximum = int(freq.partition("-")[2])
    password = string.partition(":")[2]

    count = password.count(letter)

    return(count >= minimum and count <= maximum)

res = list(filter(is_correct, input))

print(len(res))

        
