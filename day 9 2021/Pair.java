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