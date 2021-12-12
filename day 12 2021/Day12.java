import java.util.*;

class Day12 {

    static int count;
    static StringBuilder sb;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, ArrayList<String>> adjList = new HashMap<>();
        sb = new StringBuilder();
        count = 0;
        while (sc.hasNext()) {
            String[] edge = sc.next().split("-");
            adjList.putIfAbsent(edge[0], new ArrayList<>());
            adjList.putIfAbsent(edge[1], new ArrayList<>());
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        DFS(new HashSet<>(), "start", adjList, new Stack<>(), false, false);
        //System.out.print(sb);  //<-- uncomment for the list of paths
        System.out.printf("Part 1 answer: %s \n", count);

        sb = new StringBuilder();
        count = 0;
        DFS(new HashSet<>(), "start", adjList, new Stack<>(), false, true);
        //System.out.print(sb);  //<-- uncomment for the list of paths
        System.out.printf("Part 2 answer: %s \n", count);

        sc.close();
    }

    public static void DFS(HashSet<String> taken, String node, HashMap<String, ArrayList<String>> adjList, 
            Stack<String> path, boolean hasVisitSmallCaveTwice, boolean Part2) {

        if (taken.contains(node)) return;
        if (!isLargeCave(node)) {
            if (!Part2) taken.add(node); //part 1
        
            else { //part 2 logic
                if (node.equals("end") || node.equals("start")) {
                    taken.add(node);
                } else {
                    if (path.contains(node) && hasVisitSmallCaveTwice) return;
                    if (path.contains(node) || hasVisitSmallCaveTwice) {
                        taken.add(node);
                        hasVisitSmallCaveTwice = true;
                    } 
                }
            }
    }
        path.add(node);

        if (node.equals("end")) {
            count += 1;
            sb.append(path.toString()).append("\n");
        } else {
            for (String neighbour : adjList.get(node)) {
            DFS(taken, neighbour, adjList, path, hasVisitSmallCaveTwice, Part2);
            }
        }

        taken.remove(node);
        path.pop();

    }

    public static boolean isLargeCave(String node) {
        return Character.isUpperCase(node.charAt(0));
    }


}