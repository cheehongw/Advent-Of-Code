f = open("input.txt","r")

input = f.read().split("\n\n")

input2 = list(map(lambda x: x.split(), input))

passports = list(map(lambda arr: dict(s.split(':') for s in arr), input2))

def is_passport(passport):
    fields = ["ecl", "byr", "iyr", "eyr", "hgt", "hcl", "pid"]
    res = True

    for field in fields:
        if field in passport:
            res = res and True
        else:
            res = False
            break
        
    return res

#print(len(list(filter(is_passport, passports))))
print(passports)

