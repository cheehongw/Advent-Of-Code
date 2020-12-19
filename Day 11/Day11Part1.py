import functools, pprint

f = open("input.txt","r")
input = dict(enumerate(f.read().split("\n")))

def seating(arr):
    def find_adjacent(row,col): #returns a list with the states of the adjacent seats
        adj = []
        for i in [-1,0,1]:
            if 0 <= row + i < len(arr):
                for j in [-1,0,1]:
                    if 0 <= col + j < len(arr[0]) and (i != 0 or j != 0):
                        adj.append(arr[r+i][col+j])
        return adj

    
    new_map = {}
    changes = 0
    count_seated = 0
    count_empty = 0
    
    for r in range(len(arr)):
        row = {}
        for col in range(len(arr[0])):
            if arr[r][col] == ".":
                row[col] = "."
                
            elif arr[r][col] == "L":
                count_empty += 1
                adjacent = find_adjacent(r,col)
                if "#" not in adjacent:
                    row[col] = "#"
                    changes += 1
                else:
                    row[col] = "L"
                    
            elif arr[r][col] == "#":
                count_seated += 1
                adjacent = find_adjacent(r,col)
                if adjacent.count("#") >= 4:
                    row[col] = "L"
                    changes += 1
                else:
                    row[col] = "#"

        new_map[r] = row

    if changes != 0:
        return seating(new_map)
    else:
        return list(map(lambda x: "".join(x.values()),list(new_map.values())))
        #return count_seated



pprint.pprint(seating(input))
                    
            
    
        
