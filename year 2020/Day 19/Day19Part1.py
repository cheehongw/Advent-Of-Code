import re, sys

sys.setrecursionlimit(1000)

f = open("input.txt","r")
input = f.read().split("\n\n")

rules_xs = input[0].splitlines()
rules = dict(rule.split(": ") for rule in rules_xs)
messages = input[1].splitlines()

def simplify(string):
    if string in ("a","b"):
        return string
    else:
        if "|" in string:
            first, second = string.split(" | ")
            return "(" + simplify(first)+ "|" + simplify(second) + ")"
        else:
            digits = re.findall("\d+",string)
            x = "".join(string.split())
            for digit in digits:
                x = x.replace(digit, simplify(rules[digit]) ,1)
            return x.replace('"',"")
        
pattern = re.compile(simplify(rules["0"]))

count = 0

for m in messages:
    if(re.fullmatch(pattern,m)):
        count += 1
print(count)
