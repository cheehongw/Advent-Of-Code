import java.util.*;

public class Day3Part2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        HashSet<String> oxygenGeneratorRatings = new HashSet<>();
        HashSet<String> scrubberRatings = new HashSet<>();

        for (int i = 0; i < 1000; i++) {
            String x = sc.next();
            oxygenGeneratorRatings.add(x);
            scrubberRatings.add(x);
        }

        for (int i = 0; i < 12; i++) {
            oxygenGeneratorRatings = oxygenGeneratorRatings.size() != 1 
                ? partitionHashSet(i, oxygenGeneratorRatings, false) : oxygenGeneratorRatings;
            scrubberRatings = scrubberRatings.size() != 1 
                ? partitionHashSet(i, scrubberRatings, true) : scrubberRatings;
        }

        String scrubber = scrubberRatings.iterator().next();
        String oxygen = oxygenGeneratorRatings.iterator().next();

        int scrubberVal = 0;
        int oxygenVal = 0;

        for (int i = 0; i < 12; i++){
            int index = (11 - i);
            if (scrubber.charAt(index) == '1') {
                scrubberVal += Math.pow(2, i);
            }
            if (oxygen.charAt(index) == '1') {
                oxygenVal += Math.pow(2, i);
            }
        }

        System.out.println(scrubberVal*oxygenVal);


        sc.close();
    }

    private static HashSet<String> partitionHashSet(int index, HashSet<String> originalHashSet, boolean returnLeastCommon) {
        HashSet<String> zeroes = new HashSet<>();
        HashSet<String> ones = new HashSet<>();
        
        for (String i : originalHashSet) {
            if (i.charAt(index) == '0') {
                zeroes.add(i);
            } else {
                ones.add(i);
            }
        }

        if (returnLeastCommon) {
            return zeroes.size() <= ones.size() ? zeroes : ones;
        } else {
            return ones.size() >= zeroes.size() ? ones : zeroes;
        }
    }
}
