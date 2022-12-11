import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Main {
    public static void main(String[] aaa) throws FileNotFoundException {
        File f = new File("./day10input.txt");
        Scanner in = new Scanner(f);

        int cycle = 1; // which cycle the system is on
        int X = 1; // Register X value

        String output = "";

        System.out.println("                     111111111122222222223333333333");
        System.out.println("Index (X): 0123456789012345678901234567890123456789");
        System.out.println("Sprite   : ###.....................................");
        System.out.printf("X = %d%ncycle = %d%n%n", X, cycle);

        while (in.hasNextLine()) {
            if (in.hasNextLine()) {
                String s = in.nextLine();
                String[] args = s.split(" ");

                System.out.printf("Start cycle %d: begin " + s, cycle);
                System.out.println();

                int column = cycle % 40 == 0 ? 40 : cycle % 40; // column that cycle is on
                char lit;

                if (X - 1 == column - 1|| X == column - 1|| X + 1 == column - 1) { // account for all 3 parts of sprite
                    lit = '#';
                } else {
                    lit = '.';
                }

                System.out.printf("During cycle %d: CRT draws \"" + lit + "\" in column %d", cycle, column);
                System.out.println();
                output += lit; // append character (. is unlit, # is lit)
                output += column % 40 == 0 ? "\n" : ""; // append newline if reached end of row

                switch (args[0]) {
                    case "noop":
                        System.out.printf("End of cycle %d: finish executing noop%n", cycle, X);
                        System.out.println();
                        cycle++;

                        break;
                    case "addx":
                        System.out.printf("End of cycle %d: waiting on addx%n", cycle, X);
                        System.out.println();
                        cycle++;

                        System.out.printf("Current sprite position (X = %d):  ", X);
                        for (int i = 0; i < 40; i++) {
                            char c = Math.abs(i - X) <= 1 ? '#' : '.'; // if the index is one of 3 sprite parts
                            System.out.print(c);
                        }

                        System.out.println();
                        System.out.println("Current CRT display: ");
                        System.out.println(output);
                        // System.out.println("\n");
                        for (int i = 0; i < Integer.parseInt(aaa[0]); i++) { // args[0] is $COLUMNS bash env variable, this would look nice in visualization
                            System.out.print('-');
                        }
                        System.out.println("\n\n\n");

                        // redo the cycle because it goes twice for addx, now we can actually add x

                        System.out.printf("Start cycle %d: continue " + s, cycle);
                        System.out.println();

                        column = cycle % 40 == 0 ? 40 : cycle % 40;
                        
                        // account for all 3 parts of sprite
                        // column - 1 to align it with the scale of X, 
                        // which is 0-39 whereas column goes from 1-40
                        if (X - 1 == column - 1|| X == column - 1|| X + 1 == column - 1) { 
                            lit = '#';
                        } else {
                            lit = '.';
                        }

                        System.out.printf("During cycle %d: CRT draws \"" + lit + "\" in column %d", cycle, column);
                        System.out.println();
                        output += lit; // append character (. is unlit, # is lit)
                        output += column % 40 == 0 ? "\n" : ""; // append newline if reached end of row

                        X += Integer.parseInt(args[1]); // addx (finally lmao)
                        System.out.printf("End of cycle %d: finish executing addx%n", cycle, X);
                        System.out.println();
                        cycle++;

                        break;
                }

                System.out.printf("Current sprite position (X = %d):  ", X);
                for (int i = 0; i < 40; i++) {
                    char c = Math.abs(i - X) <= 1 ? '#' : '.'; // if the index is one of 3 sprite parts
                    System.out.print(c);
                }

                System.out.println();
                System.out.println("Current CRT display: ");
                System.out.println(output);
                // System.out.println("\n");
                for (int i = 0; i < Integer.parseInt(aaa[0]); i++) { // args[0] is $COLUMNS bash env variable, this would look nice for visualization
                    System.out.print('-');
                }
                System.out.println("\n\n\n");

                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        System.out.println("Final Output:");
        System.out.println(output);
    }
}