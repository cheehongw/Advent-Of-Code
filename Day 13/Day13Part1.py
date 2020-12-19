f = open("input.txt","r")
input = f.read().split("\n")

time_of_arrival = input[0]
buses = list(filter(lambda x: x != "x",input[1].split(",")))

bus_in_x_mins = list(map(lambda x: (x, int(x) - int(time_of_arrival)%int(x)), buses))

info_sorted = sorted(bus_in_x_mins, key = lambda tuple : tuple[1])

print(int(info_sorted[0][0])*int(info_sorted[0][1]))
