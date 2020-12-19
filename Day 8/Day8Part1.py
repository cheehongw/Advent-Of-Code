f = open("input.txt", "r")
input = f.read().split("\n")

instructions = list(map(lambda x: x.split(" "),input))

def visit(pos,visited, accumulator):
    if pos in visited:
        return accumulator,visited
    else:
        visited.append(pos)
        
    if instructions[pos][0] == "nop":
        return visit(pos + 1, visited,accumulator)
    elif instructions[pos][0] == "acc":
        return visit(pos + 1, visited, accumulator + int(instructions[pos][1]))
    elif instructions[pos][0] == "jmp":
        return visit(pos + int(instructions[pos][1]), visited, accumulator)

print(visit(0,[],0))
        
    
