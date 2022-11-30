import java.util.*;
import java.util.stream.*;

public class Day4Part2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> inputArgs = Stream.of(sc.next().split(",")).map(Integer::parseInt).collect(Collectors.toList());
        HashSet<BingoBoard> bingoBoards = new HashSet<>();

        for (int i = 0; i < 100; i++) {
            BingoBoard b = new BingoBoard(i);

            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    b.addNum(sc.nextInt(), j, k);
                }
            }
            bingoBoards.add(b);
        }

        for (int i : inputArgs) {
            if (bingoBoards.size() == 0) break;
            bingoBoards
                .removeIf((BingoBoard board) -> {
                    boolean isDeleted = board.delNum(i);
                    if (isDeleted && bingoBoards.size() == 1) {
                        System.out.println(i*board.sum);
                    }
                    return isDeleted;
                });
        }
        sc.close();
    }
}
