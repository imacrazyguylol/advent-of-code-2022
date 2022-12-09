import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("./day8input.txt");
        Scanner in = new Scanner(f);

        Tree[][] grid = new Tree[99][99]; // [x][y]

        int x = 0;
        while (in.hasNextLine()) {
            String s = in.nextLine();
            char[] carr = s.toCharArray(); // array of chars, line x

            for (int y = 0; y < carr.length; y++) {
                int i = carr[y] - '0'; // convert char into int of character
                grid[x][y] = new Tree(i);
            }

            x++;
        }

        in.close();

        Tree max = new Tree(0);

        for (x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {

                Tree t = grid[x][y];

                int n = 0;
                int s = 0;
                int e = 0;
                int w = 0;

                for (int i = x - 1; i >= 0; i--) { // North, checks above grid point
                    if (grid[i][y].height >= t.height) {
                        n++;
                        break; // if a tree is taller then break, stop adding to scenic score
                    } else {
                        n++; // otherwise add to number of visible trees in said direction and keep checking
                    }
                }

                for (int i = x + 1; i < grid.length; i++) { // South, checks below grid point
                    if (i == grid.length) {
                        break; // stops it from breaking by preventing indexOutOfBounds
                    }
                    if (grid[i][y].height >= t.height) {
                        s++;
                        break; // if a tree is taller then break, stop adding to scenic score
                    } else {
                        s++; // otherwise add to number of visible trees in said direction and keep checking
                    }
                }

                for (int i = y - 1; i >= 0; i--) { // West, checks west of grid point
                    if (grid[x][i].height >= t.height) {
                        w++;
                        break; // if a tree is taller then break, stop adding to scenic score
                    } else {
                        w++; // otherwise add to number of visible trees in said direction and keep checking
                    }
                }

                for (int i = y + 1; i < grid.length; i++) { // East, checks east of grid point
                    if (i == grid.length) {
                        break; // stops it from breaking by preventing indexOutOfBounds
                    }
                    if (grid[x][i].height >= t.height) {
                        e++;
                        break; // if a tree is taller then break, stop adding to scenic score
                    } else {
                        e++; // otherwise add to number of visible trees in said direction and keep checking
                    }
                }

                /*
                 * System.out.println(x + " " + y);
                 * System.out.println(n + " " + s + " " + w + " " + e);
                 * System.out.println();
                 */

                int score = grid[x][y].calcScenicScore(new int[] { n, s, w, e });

                if (score > max.scenicscore) {
                    max = t;

                    System.out.println(x + " " + y);
                    System.out.println(n + " " + s + " " + w + " " + e);
                    System.out.println();
                }

            }
        }

        System.out.println(max.scenicscore);
    }
}

class Tree {
    Boolean visible;
    int height;
    int scenicscore = 0;

    public Tree(int height) {
        this.height = height;
    }

    public int calcScenicScore(int[] dir) {
        this.scenicscore = dir[0] * dir[1] * dir[2] * dir[3];
        return this.scenicscore;
    }
}