import re, pprint

f = open("input.txt", "r")
input = f.read().split("\n")

graph = {}

def parse_text(desc):
    parent = desc.split(" bags contain ")[0]
    children = desc.split(" contain ")[1] #the string after "contain"
    childrenxs = children.split(", ")
    childs = list(map(lambda x: re.search("\d+ (.*) bag|no other bags",x).group(1),childrenxs))

    return (parent,childs)

def build_graph(node,graph): #nodes are pairs of the form: (node, [other nodes])
    if node[0] not in graph:
        graph[node[0]] = node[1]

def traverse(target, start):
    if target in graph[start]:
        return True
    elif None in graph[start]:
        return False
    else:
        res = False
        for child in graph[start]:
            res = res or traverse(target,child)
        return res




#print(parse_text("pale salmon bags contain no other bags."))
#print(parse_text(input[0]))        
#pprint.pprint(graph)

def main():
    count = 0
    for nodes in list(map(parse_text,input)):
        build_graph(nodes, graph)

    for nodes in graph:
        if traverse("shiny gold",nodes):
            count += 1
    return count

print(main())
        

