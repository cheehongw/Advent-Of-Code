f = open("input.txt","r")
input = f.read().split("\n")

patternLength = len(input[0])
slopeLength = len(input)

def knocked_trees(count,row,pos,right,down): #row, pos represents current pos
    if row >= slopeLength:
        return count
    else:
        if input[row][pos] == "#":
            return knocked_trees(count + 1, row + down, (pos + right)%patternLength, right, down)
        else:
            return knocked_trees(count, row + down, (pos + right)%patternLength, right, down)

print(knocked_trees(0,0,0,3,1)*
      knocked_trees(0,0,0,1,1)*
      knocked_trees(0,0,0,5,1)*
      knocked_trees(0,0,0,7,1)*
      knocked_trees(0,0,0,1,2))

#Output: 6818112000
