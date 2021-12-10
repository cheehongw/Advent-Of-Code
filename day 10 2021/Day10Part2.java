import java.util.*;

public class Day10Part2 {
    public static Set<Character> openingBrackets = Set.of('(', '[', '{', '<');
    public static Map<Character, Character> openCloseBrackets = Map.of(
        ')', '(', ']', '[', '}', '{', '>','<');
    public static Map<Character, Integer> errorScore = Map.of(
        '(', 1, '[', 2, '{', 3, '<', 4);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Long> scores = new ArrayList<>();
        
        while (sc.hasNext()) {
            String inputLine = sc.next();
            Stack<Character> stack = new Stack<>();
            char[] characters = inputLine.toCharArray();
            boolean corruptedFlag = false;
            for (char c : characters) {
                if (openingBrackets.contains(c)) {
                    stack.push(c);
                } else {
                    char popped = stack.pop();
                    char expected = openCloseBrackets.get(c);
                    if (popped != expected) {
                        corruptedFlag = true;
                        break; 
                    }
                }
            }
            if (corruptedFlag) continue;
            if (stack.isEmpty()) continue;
            long score = 0;
            while (!stack.isEmpty()) {
                char popped = stack.pop();
                score = score * 5;
                score += errorScore.get(popped);
            }

            scores.add(score);

        }
        scores.sort(null); //should have used quickselect for O(n) performance, but i was lazy.
        System.out.println(scores.get(scores.size()/2));

        sc.close();
    }
}
