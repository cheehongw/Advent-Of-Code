import java.util.*;

public class BingoBoard {

    int boardNum;
    HashMap<Integer, Pair> numberToBoardPosition;
    int[] rows;
    int[] cols;
    int sum;

    BingoBoard(int x) {
        this.boardNum = x;
        this.numberToBoardPosition = new HashMap<>();
        this.rows = new int[] { 5, 5, 5, 5, 5 };
        this.cols = new int[] { 5, 5, 5, 5, 5 };
    }

    public void addNum(int number, int row, int col) {
        numberToBoardPosition.put(number, new Pair(row, col));
        sum += number;
    }

    public boolean delNum(int number) {
        if (numberToBoardPosition.containsKey(number)) {
            Pair position = numberToBoardPosition.remove(number);
            rows[position.row] -= 1;
            cols[position.col] -= 1;
            sum -= number;
            return rows[position.row] == 0 || cols[position.col] == 0;
        }

        return false;
    }

    class Pair {
        int row;
        int col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

}