f = open("input.txt", "r")
input = f.read().split("\n")

instructions = list(map(lambda x: x.split(" "),input))

candidates = [0, 265, 266, 268, 182, 489, 19, 534, 199, 185, 291, 564, 308, 311, 521, 525, 526, 215, 166, 221, 66, 67, 416, 480, 482, 389, 123, 129, 253, 380, 189, 473,
 7, 578, 588, 180, 404, 573, 462, 466, 194, 446, 447, 72, 75, 115, 117, 395, 454, 456, 97, 315, 442,
 433, 331, 155, 157, 11, 16, 543, 546, 600, 24, 26, 4, 5, 111, 112, 35, 273, 538, 510, 245, 210, 338, 370, 425, 147, 149, 583, 585, 282, 227, 29, 373, 374]


def visit(pos, visited, accumulator):
    if pos in visited:
        return False
    elif pos == len(instructions):
        print(accumulator)
        return True
    else:
        visited.append(pos)

    if instructions[pos][0] == "nop":
        if pos == 425:
            return visit(pos + int(instructions[pos][1]), visited, accumulator)
        else: 
            return visit(pos + 1, visited,accumulator)
    elif instructions[pos][0] == "acc":
        return visit(pos + 1, visited, accumulator + int(instructions[pos][1]))
    elif instructions[pos][0] == "jmp":
        if pos == 425:
            return visit(pos + 1, visited,accumulator)
        else:
            return visit(pos + int(instructions[pos][1]), visited, accumulator)

def check(pos,visited):
    if pos in visited:
        return False
    elif pos == len(instructions):
        return True
    else:
        visited.append(pos)

    if instructions[pos][0] == "nop":
        return visit(pos + int(instructions[pos][1]), visited, 0)
    elif instructions[pos][0] == "jmp":
        return visit(pos + 1, visited, 0)


#for pos in candidates:
#    if check(pos,[]):
#        print(pos)
#        break

print(visit(0,[],0))       


#1941, [0, 265, 266, 267, 268, 182, 488, 489, 19, 530, 531, 532, 533, 534, 199, 184, 185, 287, 288, 289, 290, 291, 561, 562, 563, 564, 307, 308, 309, 310,
#311, 519, 520, 521, 525, 526, 214, 215, 165, 166, 221, 64, 65, 66, 67, 413, 414, 415, 416, 479, 480, 481, 482, 385, 386, 387, 388, 389, 123, 127, 128, 129,
#251, 252, 253, 380, 189, 472, 473, 7, 575, 576, 577, 578, 587, 588, 178, 179, 180, 403, 404, 569, 570, 571, 572, 573, 462, 463, 464, 465, 466, 194, 446,
#447, 71, 72, 73, 74, 75, 115, 116, 117, 395, 453, 454, 455, 456, 95, 96, 97, 314, 315, 441, 442, 430, 431, 432, 433, 327, 328, 329, 330, 331, 155, 156,
#157, 11, 14, 15, 16, 543, 544, 545, 546, 599, 600, 24, 25, 26, 2, 3, 4, 5, 109, 110, 111, 112, 34, 35, 273, 536, 537, 538, 508, 509, 510, 241,
#242, 243, 244, 245, 210, 337, 338, 368, 369, 370, 421, 422, 423, 424, 425, 146, 147, 148, 149, 582, 583, 584, 585, 280, 281, 282, 226, 227, 29, 373, 374]

