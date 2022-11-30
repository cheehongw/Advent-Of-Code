public class Pair {
    int x;
    int y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Pair(String[] xy) {
        this.x = Integer.parseInt(xy[0]);
        this.y = Integer.parseInt(xy[1]);
    }

    public static Pair lowerX(Pair p1, Pair p2) {
        return p1.x < p2.x ? p1 : p2;
    }

    public static Pair higherX(Pair p1, Pair p2) {
        return p1.x > p2.x ? p1 : p2;
    }
}
