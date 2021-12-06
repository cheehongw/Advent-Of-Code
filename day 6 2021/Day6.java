import java.util.*;
import java.util.stream.*;

public class Day6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> inputArgs = Stream.of(sc.next().split(",")).map(Integer::parseInt).collect(Collectors.toList());
        long[] fishes = new long[9];

        for (int fish : inputArgs) {
            fishes[fish] += 1;
        }

        for (int i = 0; i < 256; i++) { //change i<80 to i<256
            long[] nextFishes = new long[9];
            for (int j = 0; j < 8; j++) {
                nextFishes[j] += fishes[j+1];
            }
            nextFishes[6] += fishes[0];
            nextFishes[8] += fishes[0];
            fishes = nextFishes;
        }

        System.out.println(LongStream.of(fishes).sum());
    }
}
