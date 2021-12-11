import java.util.*;

class Day11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("");
        Queue<Pair> flashingQueue = new LinkedList<Pair>();
        int[][] octopi = new int[10][10];
        int count = 0;
        int prevCount = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                octopi[i][j] = Integer.parseInt(sc.next());
                if (octopi[i][j] == 10) {
                    flashingQueue.add(new Pair(i, j));
                }
            }

            sc.next(); sc.next();
        }

        int steps = 1;
        while (count - prevCount != 100) {
            prevCount = count;
            int[][] newState = new int[10][10];


            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    newState[i][j] = octopi[i][j] + 1;
                    if (newState[i][j] >= 10) {
                        flashingQueue.add(new Pair(i, j));
                    }
                }
            }

            while (!flashingQueue.isEmpty()) {
                Pair p = flashingQueue.poll();
                count += 1;
                incrementSurrounding(newState, p, flashingQueue);
            }

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    newState[i][j] = newState[i][j] >= 10 ? 0 : newState[i][j];
                }
            }

            octopi = newState;
            //System.out.printf("step %s: %s \n", steps, count);
            if (steps == 100) System.out.printf("STEP 100: %s\n",count);
            steps++;
        }

        //System.out.println(count);
        System.out.printf("Fully synchronised at STEP %s\n",steps);

        sc.close();
    }

    public static void incrementSurrounding(int[][] map, Pair p, Queue<Pair> flashQ) {
        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) continue;
                int newX = p.x + i;
                int newY = p.y + j;
                if (newX >= 0 && newX < 10 && newY >= 0 && newY < 10) {
                    map[newX][newY] += 1;
                    if (map[newX][newY] == 10) {
                        flashQ.add(new Pair(newX, newY));
                    }
                }
                
            }
        }
    }

}

class Pair {
    int x,y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}