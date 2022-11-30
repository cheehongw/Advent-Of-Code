class Line {
    Pair x1y1;
    Pair x2y2;

    Line(Pair x1y1, Pair x2y2) {
        this.x1y1 = x1y1;
        this.x2y2 = x2y2;
    }

    boolean isHorizontal() {
        return x1y1.y == x2y2.y;
    }

    boolean isVertical() {
        return x1y1.x == x2y2.x;
    }

    boolean isDiagonalDown() { 
        /**
         * starting with the point with the lower x coordinate, 
         * the y coordinate could either increase or decrease:
         *                q'
         *              1
         *            1
         *           p
         *            1
         *              1
         *                q
         */
        Pair lowerPair = Pair.lowerX(x1y1, x2y2);
        Pair higherPair = Pair.higherX(x1y1, x2y2);

        return lowerPair.y > higherPair.y;
    }

    boolean isDiagonalUp() {
        Pair lowerPair = Pair.lowerX(x1y1, x2y2);
        Pair higherPair = Pair.higherX(x1y1, x2y2);

        return lowerPair.y < higherPair.y;
    }

}