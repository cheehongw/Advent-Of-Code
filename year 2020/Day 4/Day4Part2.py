from Day4Part1 import is_passport
import re

f = open("input.txt","r")
input = f.read().split("\n\n")
input2 = list(map(lambda x: x.split(), input))
passports = list(map(lambda arr: dict(s.split(':') for s in arr), input2))

def validate_fields(passport):
    
    def is_hgt(hgt):
        value = (re.findall('(\d+)',hgt))
        unit = (re.findall('[c-n]+',hgt))

        if (unit and value):
            
            if unit[0] == 'cm':
                return 150 <= int(value[0]) <= 193
            if unit[0] == 'in':
                return 59 <= int(value[0]) <= 76
            else:
                return False
        else:
            return False

    def is_hcl(hcl):
        # beginning with # and having 6 other characters from 0-9 or a-f
        res = len(hcl) == 7 and hcl[0] == "#"
        for i in hcl[1:]:
            if not res:
                break
            else:
                res = res and bool(re.search("[0-9a-f]",i))
        return res

    def is_ecl(ecl):
        #only one of the following colors
        colors = ["amb","blu","brn","gry","grn","hzl","oth"]
        return ecl in colors
        
    def is_pid(pid):
        #nine digit number, including leading zeroes
        res = len(pid) == 9
        for i in pid:
            if not res:
                break
            else:
                res = res and bool(re.search("[0-9]",i))
        return res
    
    return ((1920 <= int(passport["byr"]) <= 2002) and
            (2010 <= int(passport["iyr"]) <= 2020) and
            (2020 <= int(passport["eyr"]) <= 2030) and
            (is_hgt(passport["hgt"])) and
           (is_hcl(passport["hcl"])) and
           (is_ecl(passport["ecl"])) and
           (is_pid(passport["pid"])))
 

print(len(list(filter(validate_fields,
                    list(filter(is_passport,passports))))))
