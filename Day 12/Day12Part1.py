import re
f = open("input.txt","r")
input = f.read().split("\n")

instructions = list(map( lambda x: re.findall("\D+|\d+",x) ,input))

def steer(xs, heading, eastwest, northsouth):

    def new_head(curr_head, direction, deg):
        heading = {"E":0,"S":90,"W":180,"N":270}
        reverse = {v: k for k, v in heading.items()}
        
        if direction == "R":
            new_heading = (heading[curr_head] + deg)%360
            return reverse[new_heading]
        elif direction == "L":
            new_heading = (heading[curr_head] - deg)%360
            return reverse[new_heading]
    
    if not xs:
        return abs(eastwest) + abs(northsouth)
    else:
        instruction = xs[0]
        if instruction[0] == "F": #forward
            to_add = [heading, instruction[1]]
            xs.append(to_add)
            return steer(xs[1:], heading, eastwest, northsouth)
        
        elif instruction[0] == "L" or instruction[0] == "R": #turn left or right
            new_heading = new_head(heading, instruction[0], int(instruction[1]))
            return steer(xs[1:], new_heading, eastwest, northsouth)
        
        elif instruction[0] == "N":
            return steer(xs[1:], heading, eastwest, northsouth + int(instruction[1])) #north positive
                         
        elif instruction[0] == "S":
            return steer(xs[1:], heading, eastwest, northsouth - int(instruction[1])) #south negative
            
        elif instruction[0] == "E":
            return steer(xs[1:], heading, eastwest + int(instruction[1]), northsouth) #east positive
        elif instruction[0] == "W":
            return steer(xs[1:], heading, eastwest - int(instruction[1]), northsouth) #west negative
            


print(steer(instructions,"E",0,0))
