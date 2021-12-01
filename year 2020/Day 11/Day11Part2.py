import functools

f = open("input.txt","r")
input = dict(enumerate(f.read().split("\n")))

def seating(arr):
    def find_adjacent(row,col): #returns a list with the states of the adjacent seats
        adj = []

        for dir in [(-1,-1),(-1,0),(-1,1),(0,-1),(0,1),(1,-1),(1,0),(1,1)]:
            k = 1
            while True:
                r = row + k*dir[0] #row to inspect
                c = col + k*dir[1] #col to inspect
                if 0 <= r < len(arr) and 0 <= c < len(arr[0]):
                    if arr[r][c] != ".":
                        adj.append(arr[r][c])
                        break
                    else:
                        k += 1
                else:
                    break

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
                if adjacent.count("#") >= 5: 
                    row[col] = "L"
                    changes += 1
                else:
                    row[col] = "#"

        new_map[r] = row

    if changes != 0:
        return seating(new_map)
    else:
        #return list(map(lambda x: "".join(x.values()),list(new_map.values())))
        return count_seated



print(seating(input))
                    
            
    
