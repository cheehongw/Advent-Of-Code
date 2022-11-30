import java.util.*;

public class Day1Part2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Queue<Integer> queue = new LinkedList<>();
        int prevSum = 0;
        int count = 0;

        for (int i = 0; i < 3; i++) {
            int next = sc.nextInt();
            prevSum += next;
            queue.add(next);
        }

        for (int i=0; i < 1997; i++) {
            int next = sc.nextInt();
            int newSum = prevSum - queue.poll() + next;
            count = newSum > prevSum ? count + 1 : count;
            queue.add(next);
            prevSum = newSum;
        }

        System.out.println(count);
        sc.close();
    }    
}
