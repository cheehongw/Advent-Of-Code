import re, cmath
f = open("input.txt","r")
input = f.read().split("\n")

instructions = list(map( lambda x: re.findall("\D+|\d+",x) ,input))

def steer(xs, waypoint, pos):

    def new_waypoint(curr_wp, direction, deg):
        if direction == "L":
            rotation = complex(0,1)**(deg/90)
            return curr_wp*rotation
        elif direction == "R":
            rotation = complex(0,-1)**(deg/90)
            return curr_wp*(rotation)
    
    if not xs:
        return abs(pos.real) + abs(pos.imag) #
    else:
        instruction = xs[0]
        if instruction[0] == "F": #forward
            new_pos = pos + complex(waypoint.real * int(instruction[1]), waypoint.imag * int(instruction[1]))
            return steer(xs[1:], waypoint, new_pos)
        
        elif instruction[0] == "L" or instruction[0] == "R": #turn left or right
            new_wp = new_waypoint(waypoint, instruction[0], int(instruction[1]))
            return steer(xs[1:], new_wp, pos)
        
        elif instruction[0] == "N":
            return steer(xs[1:], waypoint + complex(0, int(instruction[1])), pos) 
                         
        elif instruction[0] == "S":
            return steer(xs[1:], waypoint + complex(0, -int(instruction[1])), pos) 
            
        elif instruction[0] == "E":
            return steer(xs[1:], waypoint + complex(int(instruction[1]), 0), pos)
        elif instruction[0] == "W":
            return steer(xs[1:], waypoint + complex(-int(instruction[1]), 0), pos) #west negative
            


print(steer(instructions,complex(10,1),complex(0,0)))
