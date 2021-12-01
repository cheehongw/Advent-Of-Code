import re, pprint

f = open("input.txt", "r")
input = f.read().split("\n")

graph = {}

def parse_text(desc):
    parent = desc.split(" bags contain ")[0]
    children = desc.split(" contain ")[1] #the string after "contain"
    childrenxs = children.split(", ")
    childs = list(map(lambda x: (re.search("(\d+) (.*) bag|no other bags",x).group(2),re.search("(\d+) (.*) bag|no other bags",x).group(1)),childrenxs))

    return (parent,childs)

def build_graph(node,graph): #nodes are pairs of the form: (node, [other nodes])
    if node[0] not in graph:
        graph[node[0]] = node[1]

def count_bags(start):
    if graph[start][0][0] is None:
        return 1
    else:
        total = 1
        for child in graph[start]:
            total = total + (int(child[1])*count_bags(child[0]))
        return total



#print(parse_text("pale salmon bags contain no other bags."))
#print(parse_text(input[0]))        
#pprint.pprint(graph)

def main():
    for nodes in list(map(parse_text,input)):
        build_graph(nodes, graph)

    return count_bags("shiny gold") - 1 #dont count the first gold bag

print(main())

