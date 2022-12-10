import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

class Main {
    public static void main(String[] aaa) throws FileNotFoundException {
        File f = new File("./day9input.txt");
        Scanner in = new Scanner(f);

        int[] head = { 0, 0 }; // [x, y]
        int[] tail = { 0, 0 }; // [x, y]

        Set<List<Integer>> headCoords = new HashSet<List<Integer>>();
        Set<List<Integer>> tailCoords = new HashSet<List<Integer>>();

        // thanks java arrays :scp:
        headCoords.add(coordsAdd(head));
        tailCoords.add(coordsAdd(tail));

        while (in.hasNextLine()) {
            String s = in.nextLine();
            String[] args = s.split(" ");

            int delta = Integer.parseInt(args[1]); // delta as in the distance between two points
            char dir = args[0].toCharArray()[0]; // direction

            // i here can be considered the distance the head has traveled as of yet.
            // Upon first loop, it will be exactly where it was (delta 0) and after
            // iterating, i increases to match the difference.
            for (int i = 0; i < delta; i++) {
                switch (dir) {
                    case 'U':
                        head[1]++;
                        break;
                    case 'D':
                        head[1]--;
                        break;
                    case 'R':
                        head[0]++;
                        break;
                    case 'L':
                        head[0]--;
                        break;
                }

                if (Math.abs(head[0] - tail[0]) > 1) {
                    switch (dir) { // can only be left or right if this becomes true, same below 
                        case 'R':
                            tail[0]++;
                            break;
                        case 'L':
                            tail[0]--;
                            break;
                    }

                    if (Math.abs(head[1] - tail[1]) > 0) {
                        tail[1] = head[1];
                    }
                }
                if (Math.abs(head[1] - tail[1]) > 1) {
                    switch (dir) {
                        case 'U':
                            tail[1]++;
                            break;
                        case 'D':
                            tail[1]--;
                            break;
                    }

                    if (Math.abs(head[0] - tail[0]) > 0) {
                        tail[0] = head[0];
                    }
                }

                headCoords.add(coordsAdd(head));
                tailCoords.add(coordsAdd(tail));

                // System.out.println(Arrays.toString(tail));
            }

            // System.out.println(Arrays.toString(head));
            // System.out.println(args[0] + "" + args[2]);
            // System.out.println(Arrays.toString(tail));
        }

        for (List<Integer> coord : tailCoords) {
            System.out.println(coord.toString());
        }

        System.out.println();

        System.out.println(tailCoords.size());
    }

    private static List<Integer> coordsAdd(int[] arr) {
        return Arrays.asList(new Integer[] { Integer.valueOf(arr[0]), Integer.valueOf(arr[1]) });
        // converts int[] to Integer[] so it can then be converted to a list, which can
        // be added to the set of lists of Integers where it's called
    }
}