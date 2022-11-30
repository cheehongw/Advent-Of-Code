import java.util.*;

class Day5 {

    static int[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        map = new int[1000][1000];
        int count = 0;

        for (int i = 0; i < 500; i++) {
            Pair x1y1 = new Pair(sc.next().split(","));
            sc.next();
            Pair x2y2 = new Pair(sc.next().split(","));

            count += drawLines(x1y1, x2y2, true);
        }

        System.out.println(count);

        sc.close();
    }

    private static int drawLines(Pair x1y1, Pair x2y2, boolean part2) {
        int count = 0;
        Line line = new Line(x1y1, x2y2);
        if (line.isHorizontal()) {
            int lower = Math.min(x1y1.x, x2y2.x);
            int upper = Math.max(x1y1.x, x2y2.x);
            for (int i = lower ; i <= upper; i++) {
                map[i][x1y1.y] +=1;
                if (map[i][x1y1.y] == 2) {
                    count += 1;
                }
            }
        } else if (line.isVertical()) {
            int lower = Math.min(x1y1.y,x2y2.y);
            int upper = Math.max(x1y1.y,x2y2.y);
            for (int i = lower ; i <= upper; i++) {
                map[x1y1.x][i] +=1;
                if (map[x1y1.x][i] == 2) {
                    count += 1;
                }
            }
        } else if (part2) { //lines are diagonal
            Pair lowerPair = Pair.lowerX(x1y1, x2y2);
            Pair higherPair = Pair.higherX(x1y1, x2y2);

            if (line.isDiagonalDown()) {
                for (int i = lowerPair.x, j = lowerPair.y; i <= higherPair.x; i++, j--) {
                    map[i][j] += 1;
                    if (map[i][j] == 2) count += 1;
                }
            } else {
                for (int i = lowerPair.x, j = lowerPair.y; i <= higherPair.x; i++, j++) {
                    map[i][j] += 1;
                    if (map[i][j] == 2) count += 1;
                }
            }
        }

        return count;
    }
}

