import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("./day8input.txt");
        Scanner in = new Scanner(f);

        Tree[][] grid = new Tree[99][99]; // [x][y]
        int visible = 0;

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

        x:
        for (x = 0; x < grid.length; x++) {
            y:
            for (int y = 0; y < grid.length; y++) {
                if (y == 0 || y == grid.length - 1 || x == 0 || x == grid.length - 1) {
                    grid[x][y].visible = true;
                    System.out.println(x + " " + y);
                    System.out.println();
                    visible++;

                    continue y;
                }

                Tree t = grid[x][y];

                // true if visible
                boolean n = true;
                boolean s = true;
                boolean e = true;
                boolean w = true;

                // System.out.println(x + " " + y);

                for (int i = 0; i < x; i++) { // North, checks above grid point
                    if (grid[i][y].height >= t.height) {
                        n = false; // if a tree is taller then it is not visible
                    }
                }

                for (int i = x + 1; i < grid.length; i++) { // South, checks below grid point
                    if (grid[i][y].height >= t.height) {
                        s = false; // if a tree is taller then it is not visible
                    }
                }

                for (int i = 0; i < y; i++) { // West, checks west of grid point
                    if (grid[x][i].height >= t.height) {
                        w = false; // if a tree is taller then it is not visible
                    }
                }

                for (int i = y + 1; i < grid.length; i++) { // East, checks east of grid point
                    if (grid[x][i].height >= t.height) {
                        e = false; // if a tree is taller then it is not visible
                    }
                }

                if (n || s || e || w) { // if any are true meaning visible from any direction
                    grid[x][y].visible = true;
                    visible++;
                    System.out.println(x + " " + y);
                    /* System.out.println(n + " " + s + " " + e + " " + w);
                    System.out.println(t.visible);
                     */System.out.println();
                } else  {
                    grid[x][y].visible = false;
                }

                /* System.out.println(x + " " + y);
                System.out.println(n + " " + s + " " + e + " " + w);
                System.out.println(t.visible);
                System.out.println(); */
            }
        }

        System.out.println(visible);
    }
}

class Tree {
    Boolean visible;
    int height;

    public Tree(int height) {
        this.height = height;
    }
}