import java.util.*;

public class Day14 {
    
    static char startingElement, endingElement;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String initialString = sc.nextLine();
        HashMap<String, Long> pairCount = new HashMap<>();
        HashMap<String, String> map = new HashMap<>();
        startingElement = initialString.charAt(0);
        endingElement = initialString.charAt(initialString.length() - 1);

        for (int i = 0; i < initialString.length() - 1; i++) {
            String pair = initialString.substring(i, i+2);
            pairCount.putIfAbsent(pair, (long) 0);
            pairCount.put(pair, pairCount.get(pair) + 1);
        }
        sc.nextLine(); //skip blank line

        while(sc.hasNext()) {
            String[] mapping = sc.nextLine().split(" ");
            map.putIfAbsent(mapping[0], mapping[2]);
        }

        HashMap<Character, Long> elements;

        for (int i = 0; i < 10; i++) {  //10 loops
            pairCount = polymerise(pairCount, map);
        }
        elements = countElement(pairCount);
        System.out.printf("Part 1: %s \n",Collections.max(elements.values()) - Collections.min(elements.values()));

        for (int i = 0; i < 30; i++) {  //10 + 30 = 40 loops
            pairCount = polymerise(pairCount, map);
        }
        elements = countElement(pairCount);
        System.out.printf("Part 2: %s \n",Collections.max(elements.values()) - Collections.min(elements.values()));

        sc.close();
    }

    public static HashMap<String, Long> polymerise(HashMap<String, Long> pairCount, HashMap<String, String> map) {
        HashMap<String, Long> newPairCount = new HashMap<>();

        for (String pair : pairCount.keySet()) {
            long count = pairCount.get(pair);
            String insert = map.get(pair);

            String[] newPolymer = newPolymers(pair, insert);
            newPairCount.put(newPolymer[0], newPairCount.getOrDefault(newPolymer[0], (long) 0) + count);
            newPairCount.put(newPolymer[1], newPairCount.getOrDefault(newPolymer[1], (long) 0) + count);
        }

        return newPairCount;
    }

    public static String[] newPolymers(String pair, String insert) {
        String[] pairs = new String[2];
        pairs[0] = pair.substring(0,1).concat(insert);
        pairs[1] = insert.concat(pair.substring(1,2));
        return pairs;
    }

    public static HashMap<Character, Long> countElement(HashMap<String, Long> pairCount) {
        HashMap<Character, Long> elementCounts = new HashMap<>();
        for (String key : pairCount.keySet()) {
            long count = pairCount.get(key);
            Character[] elements = {key.charAt(0), key.charAt(1)};

            elementCounts.put(elements[0], elementCounts.getOrDefault(elements[0], (long) 0) + count);
            elementCounts.put(elements[1], elementCounts.getOrDefault(elements[1], (long) 0) + count);
        }

        elementCounts.replaceAll((k, v) -> {
            if (k == startingElement || k == endingElement) return (v - 1)/2 + 1;
            else return v/2;
        });

        return elementCounts;
    }
}
