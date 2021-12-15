import java.util.PriorityQueue;
import java.util.Scanner;

public class Day15Part2 {
    static int[][] MOVES = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("");
        int[][] map = new int[500][500];
        
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                int readIn = Integer.parseInt(sc.next());
                for (int k = 0; k < 5; k++) {
                    for (int l = 0; l < 5; l++) {
                        int newRisk = ((readIn + l + k -1) % 9) + 1;
                        map[i + 100*k][j + 100*l] = newRisk;
                    }
                }
            }
            sc.next();sc.next(); 
        }

        int[] distanceEstimate = new int[100*100*5*5];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i = 1; i < 250000; i++) {
            distanceEstimate[i] = 999999999;
        }
        pq.add(new Pair(0,0));

        while(!pq.isEmpty()) {
            Pair p = pq.poll();
            if (p.d == distanceEstimate[p.v]) {
                Pair vertex = Pair.rowMajorNumToPair(p.v, 500);

                for (int[] move: MOVES) {
                    int newX = vertex.v + move[0];
                    int newY = vertex.d + move[1];

                    if (newX >= 0 && newX < 500 && newY >= 0 && newY < 500) {
                        int vertNum = Pair.pairToRowMajorNum(new Pair(newX, newY), 500);
                        if (distanceEstimate[vertNum] > distanceEstimate[p.v] + map[newX][newY]) {
                            distanceEstimate[vertNum] = distanceEstimate[p.v] + map[newX][newY];
                            pq.add(new Pair(vertNum, distanceEstimate[vertNum]));
                        }
                    }
                    
                }
            }
        }

        System.out.println(distanceEstimate[249999]);


        
        sc.close();
    }
}

class Pair implements Comparable<Pair>{
    int v,d;

    Pair(int v, int d) {
        this.v = v;
        this.d = d;
    }

    public static Pair rowMajorNumToPair(int x, int numOfCols) {
        return new Pair(x/numOfCols, x % numOfCols);
    }

    public static int pairToRowMajorNum(Pair p, int numofCols) {
        return p.v*numofCols + p.d;
    }

    public int compareTo(Pair other) {
        return this.d - other.d;
    }



}
