f = open("input.txt","r")
input = f.read().split("\n")

patternLength = len(input[0])
slopeLength = len(input)

def knocked_trees(count,row,pos): #row, pos represents current pos
    if row == slopeLength:
        return count
    else:
        if input[row][pos] == "#":
            return knocked_trees(count + 1, row + 1, (pos + 3)%patternLength)
        else:
            return knocked_trees(count, row + 1, (pos + 3)%patternLength)

print(knocked_trees(0,0,0))

#Output: 228
