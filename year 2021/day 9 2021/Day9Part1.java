import java.util.*;

class Day9Part1 {

    static int[][] LRTB = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } }; 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("");
        int[][] map = new int[100][100];

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                map[i][j] = Integer.parseInt(sc.next());
            }

            sc.next();sc.next();
        }

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

                if (localMinimaFlag) totalRisk += map[i][j] + 1;
            }
        }

        System.out.println(totalRisk);

        sc.close();
    }
}