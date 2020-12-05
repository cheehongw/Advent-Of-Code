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


print(functools.reduce(lambda x,y: pass_id(y) if pass_id(y) > x else x,
                       input, 0))
