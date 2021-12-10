import java.util.*;

class Day10Part1 {

    public static Set<Character> openingBrackets = Set.of('(', '[', '{', '<');
    //public static Set<Character> closingBrackers = Set.of(')',']', '}', '>');
    public static Map<Character, Character> openCloseBrackets = Map.of(
        ')', '(', ']', '[', '}', '{', '>','<');
    public static Map<Character, Integer> errorScore = Map.of(
        ')', 3, ']', 57, '}', 1197, '>', 25137);
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int syntaxErrorScore = 0;
        int corruptCount = 0;
        while (sc.hasNext()) {
            String inputLine = sc.next();
            Stack<Character> stack = new Stack<>();
            char[] characters = inputLine.toCharArray();
            for (char c : characters) {
                if (openingBrackets.contains(c)) {
                    stack.push(c);
                } else {
                    char popped = stack.pop();
                    char expected = openCloseBrackets.get(c);
                    if (popped != expected) {
                        syntaxErrorScore += errorScore.get(c);
                        corruptCount += 1;
                        break;
                    }
                }
            }

        }

        System.out.printf("%s %s",syntaxErrorScore, corruptCount);


        sc.close();
    }
}