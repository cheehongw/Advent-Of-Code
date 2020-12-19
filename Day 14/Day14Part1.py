import re, functools

f = open("input.txt","r")
input = f.read().split("\n")

curr_mask = ""  #reversed order
mem = {}

for string in input:
    instr = string.split(" = ")
    if instr[0] == "mask":
        curr_mask = instr[1][::-1]
    else:
        idx = re.findall("\d+",instr[0])[0]

        rev_value = list('{:036b}'.format(int(instr[1]))[::-1]) #wtf is {:036b}

        for i in range(len(curr_mask)):
            if curr_mask[i] != "X":
                rev_value[i] = curr_mask[i]

        mem[idx] = (int("".join(rev_value[::-1]),2))



print(functools.reduce(lambda x,y: x + y,mem.values()))
        
