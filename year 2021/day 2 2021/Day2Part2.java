import java.util.Scanner;

public class Day2Part2 {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int horizontal = 0;
        int vertical = 0;
        int aim = 0;

        for (int i = 0; i < 1000; i++) {
            String instr = sc.next();
            int value = sc.nextInt();

            switch (instr) {
                case "forward":
                    horizontal += value;
                    vertical += aim*value;
                    break;
                case "up":
                    aim -= value;
                    break;
                case "down":
                    aim += value;
                    break;
                default:
                    break;
            }

        }

        System.out.println(vertical*horizontal);
        sc.close();
    }
}
