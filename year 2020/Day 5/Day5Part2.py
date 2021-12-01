import re, functools

f = open("input.txt","r")
input = f.read().split("\n")

def pass_id(passes):
    replacementsFB = [
        ('F','0'),('B','1')]
    replacementsLR = [
        ('L','0'),('R','1')]
    
    rows = re.findall("[FB]+", passes)[0]
    cols = re.findall("[LR]+", passes)[0]

    for old, new in replacementsFB:
        rows = re.sub(old, new, rows)

    for old, new in replacementsLR:
        cols = re.sub(old, new, cols)

    return int(rows,2)*8 + int(cols,2)


sorted_xs = sorted(list(map(pass_id,input)))
arr = []

for rows in range(128): #128 rows
    row = []
    for seats in range(8): #8 cols
        iD = rows*8 + seats
        if (iD) in sorted_xs:
            row.insert(seats,"X")
        else :
            row.insert(seats, "_")
            
            if sorted_xs[0] < iD < sorted_xs[-1]: #main logic to determine missing seat
                print(iD)

    arr.insert(rows,row)

print(arr) #visualisation
        
        
    
