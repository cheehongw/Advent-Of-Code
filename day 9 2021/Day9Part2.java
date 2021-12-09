import java.util.*;

class Day9Part2 {

    static int[][] LRTB = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("");
        int[][] map = new int[100][100];

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                map[i][j] = Integer.parseInt(sc.next());
            }

            sc.next();
            sc.next();
        }

        ArrayList<Pair> minimums = new ArrayList<>();
        int totalRisk = 0;

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                boolean localMinimaFlag = true;
                for (int[] move : LRTB) {
                    int newX = i + move[0];
                    int newY = j + move[1];

                    if (newX >= 0 && newX < 100 && newY >= 0 && newY < 100) {
                        localMinimaFlag = map[i][j] < map[newX][newY] && localMinimaFlag;
                    }
                }

                if (localMinimaFlag) {
                    minimums.add(new Pair(i, j));
                    totalRisk += map[i][j] + 1;
                }

            }
        }

        System.out.println(totalRisk);

        boolean[][] taken = new boolean[100][100];
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (Pair minPoint : minimums) {
            System.out.println(minPoint);
            pq.add(BFS(taken, map, minPoint));
        }

        System.out.println(pq.poll() * pq.poll() * pq.poll());

        sc.close();
    }

    public static int BFS(boolean[][] taken, int[][] map, Pair vertex) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(vertex);
        int count = 0;

        while(!queue.isEmpty()) {
            Pair nextVert = queue.poll();
            if (!taken[nextVert.x][nextVert.y]) {
                BFSHelper(taken, map, nextVert, queue);
                count += 1;
            }
        }
        return count;
    }

    public static void BFSHelper(boolean[][] taken, int[][] map, Pair vertex, Queue<Pair> queue) {
        if (taken[vertex.x][vertex.y]) return;
        taken[vertex.x][vertex.y] = true;

        for (int[] moves : LRTB) {
            int newX = vertex.x + moves[0];
            int newY = vertex.y + moves[1];

            if (newX >= 0 && newX < 100 && newY >= 0 && newY < 100) {
                if (map[newX][newY] > map[vertex.x][vertex.y] && !taken[newX][newY] && map[newX][newY] != 9){
                    queue.add(new Pair(newX, newY));
                }
            }
        }
    }
}

class Pair {
    int x, y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return String.format("%s, %s", x, y);
    }
}