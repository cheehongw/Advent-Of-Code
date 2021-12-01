import re, functools

f = open("input.txt","r")
input = f.read().split("\n")

curr_mask = 0
mem = {}

def apply_mask_to_int(dec_int, rev_mask):
    rev_bin = list('{:036b}'.format(int(dec_int))[::-1])
    
    for i in range(len(rev_mask)): #replace all bits to "X"or "1" when bitmask bit is not 0
        if rev_mask[i] != "0":
            rev_bin[i] = rev_mask[i]
            
    return "".join(rev_bin[::-1])
    

def decode_and_write(masked_address, value):
    if "X" in masked_address: #recursive case
        case_1 = masked_address.replace("X","1",1)
        case_2 = masked_address.replace("X","0",1)
        decode_and_write(case_1, value)
        decode_and_write(case_2, value)
    else: #base case
        mem[masked_address] = value

for string in input:
    instr = string.split(" = ")
    if instr[0] == "mask":
        curr_mask = instr[1][::-1]
    else:
        value = int(instr[1])
        address = int(re.findall("\d+",instr[0])[0])
        masked_address = apply_mask_to_int(address, curr_mask)
        decode_and_write(masked_address, value)



print(functools.reduce(lambda x,y: x + y,mem.values()))
        
