import java.util.*;
import java.util.stream.*;

public class Day8Part2 {
    /**
     * We assign each segment in the seven-segment display a number like so:
     *   _        0
     *  |_| <--> 132
     *  |_|      465
     * 
     * Thus, the number ZERO would be:
     *   _        0
     *  | | <--> 1 2
     *  |_|      465 ,  or the set {0,1,2,4,5,6}
     */
    static Set<Integer> ZERO = Set.of(0,1,2,4,5,6);
    static Set<Integer> ONE = Set.of(2,5);
    static Set<Integer> TWO = Set.of(0,2,3,4,6);
    static Set<Integer> THREE = Set.of(0,2,3,5,6);
    static Set<Integer> FOUR = Set.of(1,2,3,5);
    static Set<Integer> FIVE = Set.of(0,1,3,5,6);
    static Set<Integer> SIX = Set.of(0,1,3,4,5,6);
    static Set<Integer> SEVEN = Set.of(0,2,5);
    static Set<Integer> EIGHT = Set.of(0,1,2,3,4,5,6);
    static Set<Integer> NINE = Set.of(0,1,2,3,5,6);
    static Map<Set<Integer>, Integer> NUMS = Map.of(
        ZERO, 0, ONE, 1, TWO, 2, 
        THREE, 3, FOUR, 4, FIVE, 5,
        SIX, 6, SEVEN,7, EIGHT, 8, NINE, 9);
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sum = 0;
        for (int i = 0; i < 200; i++) {
            String[] jumbledSegments = new String[10]; 
            HashMap<Character, Integer> segmentCount = new HashMap<>();
            String one = "";
            String four = "";
            
            for (int j = 0; j < 10; j++) {
                jumbledSegments[j] = sc.next();
                //each time a character appears, we increment their count by 1
                countChars(segmentCount, jumbledSegments[j]);

                switch(jumbledSegments[j].length()) {
                    case 2:
                        one = jumbledSegments[j];
                        break;
                    case 4:
                        four = jumbledSegments[j];
                        break;
                    default:
                }
            }

            segmentCount.forEach((k,v) -> System.out.printf("%s %s\n", k, v));

            //using the number of times a segment has appeared, we can deduce which letter 
            //corresponds to which segment
            HashMap<Character, Integer> segmentMap = decodeSegmentPosition(segmentCount, one, four);
            
            sc.next(); //skip the "|"
            int number = 0;
            for (int j = 0; j < 4; j++) {
                String num = sc.next();
                int digit = decodeString(num, segmentMap);
                number += digit*Math.pow(10, 3-j);
            }
            sum += number;
        }

        System.out.println(sum);

        sc.close();      
    } 

    public static void countChars(HashMap<Character, Integer> table, String str) {
        for (int i = 0; i < str.length(); i++) {
            Character character = str.charAt(i);
            table.putIfAbsent(character, 0);
            table.put(character, table.get(character) + 1);
        }
    }

    /**
     * If we use the segment positions defined above in line 5 to 15,
     * the number of times segment ONE lights up when we flash the digits from 0 to 9 is 6,
     * and so forth. The number of times a particular segment lights up when we flash the digits from
     * 0 to 9 is shown in the map below. As such, we only have to disambiguate between 
     * (segment ZERO and segment TWO) and (segment THREE and segment SIX). 
     * Thankfully, we can disambiguate ZERO and TWO using the display for number 1 
     * (segment ZERO does not light up when 1 is flashed), and likewise using number 4 for segment THREE and SIX.
     * 
     * Therefore, with the number of times a certain character lights up and with knowledge of what 
     * character lights up when 1/4 is flashed, we can create a map from character to the corresponding segment number.
     * 
     *  _         8
     * |_|  <--> 678
     * |_|       479
     * 
     */
    public static HashMap<Character,Integer> decodeSegmentPosition(HashMap<Character, Integer> table, String one, String four) {
            HashMap<Character,Integer> segmentsToLetter = new HashMap<>();
            for (Character c : table.keySet()) {
                switch (table.get(c)) {
                    case 4:
                        segmentsToLetter.put(c, 4);
                        break;
                    case 6:
                        segmentsToLetter.put(c, 1);
                        break;
                    case 9:
                        segmentsToLetter.put(c, 5);
                        break;
                    case 7:
                        if (four.contains(Character.toString(c))) {
                            segmentsToLetter.put(c, 3);
                        } else {
                            segmentsToLetter.put(c, 6);
                        }
                        break;
                    case 8:
                        if (one.contains(Character.toString(c))) {
                            segmentsToLetter.put(c, 2);
                        } else {
                            segmentsToLetter.put(c, 0);
                        }
                    default:
                }
            }
        return segmentsToLetter;
    }

    public static int decodeString(String s, HashMap<Character, Integer> map) {
        System.out.println(s);
        
        Set<Integer> num = s.chars()
            .mapToObj(chr -> (char) chr)
            .map(chr -> map.get(chr))
            .collect(Collectors.toSet());

        return NUMS.get(num);
    }
}
