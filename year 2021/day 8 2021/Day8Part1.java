import java.util.*;
import java.util.HashSet;
import java.util.Scanner;

class Day8Part1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //number of line segments corresponding to 1,4,7,8
        HashSet<Integer> nums = new HashSet<>(Arrays.asList(2,3,4,7));
        
        int count = 0;

        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 11; j++) { //discard the first 11 tokens
                sc.next();
            }

            for (int j = 0; j < 4; j++) {
                String in = sc.next();
                if (nums.contains(in.length())) count++;
            }
        }

        System.out.println(count);

        sc.close();
    }
}