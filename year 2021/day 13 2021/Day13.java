import java.util.Arrays;
import java.util.Scanner;

public class Day13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] dots = new char[1311][895];

        for (int i = 0; i < 1311; i++) {
            for (int j = 0; j < 895; j++) {
                dots[i][j] = ' ';
            }
        }

        for (int i = 0; i < 722; i++) {
            int[] pair = Arrays.stream(sc.next().split(",")).mapToInt(Integer::parseInt).toArray();
            dots[pair[0]][pair[1]] = '#';
        }

        sc.nextLine();sc.nextLine(); //skip two blank lines

        while (sc.hasNextLine()) {
            String[] foldingLine = sc.nextLine().split(" ")[2].split("=");
            if (foldingLine[0].equals("x")) {
                dots = fold(Integer.parseInt(foldingLine[1]), dots, true); 
            } else {
                dots = fold(Integer.parseInt(foldingLine[1]), dots, false);
            }
        }

        for (int i = 0; i < dots[0].length; i++) {
            for (int j = 0; j < dots.length; j++) {
                System.out.print(dots[j][i]);
            }
            System.out.println();
        }

        sc.close();
    }

    public static char[][] fold(int foldAlong, char[][] paper, boolean isFoldAlongX) {
        int rowLength = paper.length;
        int colLength = paper[0].length;
        char[][] newFold;
        int count = 0;

        if (isFoldAlongX) newFold = new char[foldAlong][colLength];
        else newFold = new char[rowLength][foldAlong];

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                int newXPos, newYPos;

                if (isFoldAlongX) {
                    if (i == foldAlong) continue;
                    newXPos = i > foldAlong ? -(i - 2*foldAlong) : i;
                    newYPos = j;
                } else {
                    if (j == foldAlong) continue;
                    newXPos = i;
                    newYPos = j > foldAlong ? -(j - 2*foldAlong) : j;
                }

                if (newFold[newXPos][newYPos] != '#') {
                    newFold[newXPos][newYPos] = paper[i][j];
                    count = paper[i][j] == '#' ? count + 1 : count;
                } 
            }
        }

        if (isFoldAlongX) System.out.printf("Folding along x = %s, count of # = %s \n", foldAlong, count);
        else System.out.printf("Folding along y = %s, count of # = %s \n", foldAlong, count);

        return newFold;
    }
}
