import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4Part1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> inputArgs = Stream.of(sc.next().split(",")).map(Integer::parseInt).collect(Collectors.toList());
        BingoBoard[] bingoBoards = new BingoBoard[100];

        for (int i = 0; i < 100; i++) {
            bingoBoards[i] = new BingoBoard(i);

            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    bingoBoards[i].addNum(sc.nextInt(), j, k);
                }
            }
        }

        boolean flag = false;
        for (int i: inputArgs) {
            if (flag) break;
            for (BingoBoard b : bingoBoards) {
                if (b.delNum(i)) {
                    flag = true;
                    System.out.println(i*b.sum);
                    break;
                };
            }
        }
        sc.close();
    }
}
