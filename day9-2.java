import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/* Holy Fucking Shit */

class Main {
    public static void main(String[] aaa) throws FileNotFoundException {
        File f = new File("./day9input.txt");
        Scanner in = new Scanner(f);

        // [x, y]
        // index 0 is head, index 9 is tail
        int[][] knots = new int[][] { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } };

        Set<List<Integer>> tailCoords = new HashSet<List<Integer>>();

        // thanks java arrays :scp:
        tailCoords.add(arrToList(knots[9]));

        while (in.hasNextLine()) {
            String s = in.nextLine();
            String[] args = s.split(" ");

            int delta = Integer.parseInt(args[1]); // delta as in the distance between two points
            char dir = args[0].toCharArray()[0]; // direction

            // i here can be considered the distance the head has traveled as of yet.
            // Upon first loop, it will be exactly where it was (delta 0) and after
            // iterating, i increases to match the difference.
            for (int i = 0; i < delta; i++) {
                switch (dir) { // moves head directly first
                    case 'U':
                        knots[0][1]++;
                        break;
                    case 'D':
                        knots[0][1]--;
                        break;
                    case 'R':
                        knots[0][0]++;
                        break;
                    case 'L':
                        knots[0][0]--;
                        break;
                }

                for (int j = 1; j < knots.length; j++) { // - 1 beecause otherwise it would throw indexoutofbounds
                    int[] k = move(knots[j - 1], knots[j], dir);
                    knots[j] = k;
                }

                /*
                 * System.out.println(args[0] + "" + args[1]);
                 * for (int j = 0; j < 10; j++) {
                 * System.out.println(j + " " + Arrays.toString(knots[j]));
                 * }
                 */

                tailCoords.add(arrToList(knots[9]));
                // System.out.println(Arrays.toString(tail));
            }

            System.out.println(args[0] + "" + args[1]);
            for (int i = 0; i < 10; i++) {
                System.out.println(i + " " + Arrays.toString(knots[i]));
            }

        }

        /*
         * for (List<Integer> coord : tailCoords) {
         * System.out.println(coord.toString());
         * }
         */

        System.out.println();

        System.out.println(tailCoords.size());
    }

    private static List<Integer> arrToList(int[] arr) {
        return Arrays.asList(new Integer[] { Integer.valueOf(arr[0]), Integer.valueOf(arr[1]) });
        // converts int[] to Integer[] so it can then be converted to a list, which can
        // be added to the set of lists of Integers where it's called
    }

    private static int[] move(int[] head, int[] tail, char dir) {
        // Math.abs(head[0] - tail[0]) > 1
        if (head[0] - tail[0] > 1) { // head moved right
            tail[0]++;
            if (head[1] - tail[1] > 0) {
                tail[1]++;
            } else if (head[1] - tail[1] < 0) {
                tail[1]--;
            }

            return tail;
        }

        if (head[0] - tail[0] < -1) { // head moved left
            tail[0]--;
            if (head[1] - tail[1] > 0) {
                tail[1]++;
            } else if (head[1] - tail[1] < 0) {
                tail[1]--;
            }

            return tail;
        }

        if (head[1] - tail[1] > 1) { // head moved up
            tail[1]++;
            if (head[0] - tail[0] > 0) {
                tail[0]++;
            } else if (head[0] - tail[0] < 0) {
                tail[0]--;
            }

            return tail;
        }

        if (head[1] - tail[1] < -1) { // head moved down
            tail[1]--;
            if (head[0] - tail[0] > 0) {
                tail[0]++;
            } else if (head[0] - tail[0] < 0) {
                tail[0]--;
            }

            return tail;
        }

        return tail;
    }
}