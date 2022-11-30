import java.util.*;

public class Day9Part2UFDS {
    static int[][] LT = { { -1, 0 }, { 0, -1 } }; 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("");
        int[][] map = new int[100][100];
        UFDS ufds = new UFDS(100*100);

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                map[i][j] = Integer.parseInt(sc.next());
                for (int[] moves : LT) {
                    if (map[i][j] == 9) break;
                    int newX = i + moves[0];
                    int newY = j + moves[1]; 

                    if (newX >= 0 && newY >= 0 && map[newX][newY] != 9) {
                        ufds.unionSet(UFDS.pairToRowMajor(i, j, 100), UFDS.pairToRowMajor(newX, newY, 100));
                    }
                }
            }

            sc.next();sc.next();
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int key : ufds.sizes.keySet()) {
            pq.add(ufds.sizes.get(key));
        }

        System.out.println(pq.poll() * pq.poll() * pq.poll());
        sc.close();
    } 
}

class UFDS {
    int[] ufds;
    int[] rank;
    HashMap<Integer, Integer> sizes; //key is row*col + col (row major order) //do not keep keys of size 1

    UFDS(int size) {
        this.ufds = new int[size];
        this.rank = new int[size];
        this.sizes = new HashMap<>();

        for (int i = 0; i < size; i++) {
            ufds[i] = i;
        }
    }

    public int sizeOfSet(int p) {
        int representativeElement = findSet(p);
        return sizes.get(representativeElement) == null ? 1 : sizes.get(representativeElement);
    }

    public void unionSet(int p1, int p2) {
        if (!isSameSet(p1, p2)) {
            int x = findSet(p1);
            int y = findSet(p2);
            int sizeX = sizeOfSet(x);
            int sizeY = sizeOfSet(y);

            if (rank[x] > rank[y]) {
                sizes.put(x, sizeX + sizeY);
                sizes.remove(y);
                ufds[y] = x;
            
            } else {
                sizes.put(y, sizeX + sizeY);
                sizes.remove(x);
                ufds[x] = y;
                if (rank[x] == rank[y]) {
                    rank[y] = rank[y + 1];
                }
            }
        }
    }

    public boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    public int findSet(int p) {
        if (ufds[p] == p) return p;
        else {
            ufds[p] = findSet(ufds[p]);
            return ufds[p];
        }
    }

    public static Pair rowMajorToPair(int p, int numOfCols) {
        return new Pair(p/numOfCols, p% numOfCols);
    }

    public static int pairToRowMajor(int x, int y, int numOfCols) {
        return x*numOfCols + y;
    }
}
