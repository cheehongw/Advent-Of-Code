f = open("input.txt","r")
input = f.read().split("\n")

bus_ids = input[1].split(",")

def count_minutes(xs):
    counter = 0
    bus_ids = {}
    for i in range(len(xs)):
        if xs[i] != "x":
            bus_ids[int(xs[i])] = counter

        counter += 1

    return bus_ids

bus_timings = list(count_minutes(bus_ids).items())

def find_earliest(earliest, multiple, offset, modulo_n):
    #earliest + multiple*x = modulo_n - offset (mod modulo_n)
    #mulitple*x = (modulo_n - offset - earliest) % modulo_N
    multiplic_inv = pow(multiple, -1, mod = modulo_n)
    x = ((modulo_n - offset - earliest)*multiplic_inv) % modulo_n
    return earliest + multiple*x


def find_bus_timing(xs, earliest, multiple):
    if not xs:
        return earliest
    else:

        instruction = xs[0]

        offset = instruction[1]

        next_earliest = find_earliest(earliest, multiple, offset, instruction[0])
        return find_bus_timing(xs[1:], next_earliest, multiple*instruction[0])


#print(bus_timings)
#print(find_earliest(0,1,7,7))
print(find_bus_timing(bus_timings, 0, 1))


