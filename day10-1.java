import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {
    public static void main(String[] aaa) throws FileNotFoundException {
        File f = new File("./day10input.txt");
        Scanner in = new Scanner(f);

        int cycle = 1; // which cycle the system is on
        int X = 1;
        int total = 0;

        while (in.hasNextLine() || cycle <= 220) {
            if (in.hasNextLine()) {
                String s = in.nextLine();
                String[] args = s.split(" ");

                switch (args[0]) {
                    case "noop":
                        if (cycle % 40 == 20) {
                            total += X * cycle;
                            System.out.println(cycle + " " + X + " " + total);
                        }
                        cycle++;

                        continue;
                    case "addx":
                        if (cycle % 40 == 20) {
                            total += X * cycle;
                            System.out.println(cycle + " " + X + " " + total);
                        }
                        cycle++;
                        
                        if (cycle % 40 == 20) {
                            total += X * cycle;
                            System.out.println(cycle + " " + X + " " + total);
                        }
                        cycle++;

                        X += Integer.parseInt(args[1]);

                        continue;
                }
            } 
        }


        System.out.println();
        System.out.println(total);
    }
}