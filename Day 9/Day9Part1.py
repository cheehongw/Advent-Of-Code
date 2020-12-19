f = open("9day.txt","r")
input = f.read().split("\n")

def two_sum(target, xs): #naive approach
    result = False
    int_xs = list(map(int,xs))
    for i in int_xs:
        if ((target - i) in int_xs) and (target - 2*i != 0):
            result = True
            
    return result
    

def parse(preamble_length, xs):
    preamble = xs[:preamble_length]
    test = xs[preamble_length]
    if len(xs) <= preamble_length:
        return True
    elif two_sum(int(test),preamble):
        return parse(preamble_length, xs[1:])
    else:
        return int(test)
    
print(parse(25,input))
