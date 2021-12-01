import java.util.Scanner;

class Day1Part1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int prev = sc.nextInt();
        int count = 0;
        for (int i = 0; i < 1999; i++) {
            int curr = sc.nextInt();
            if (curr > prev) {
                count++;
            }
            prev = curr;
        }

        System.out.println(count);
        sc.close();

    }

}