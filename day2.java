import java.io.File;
import java.io.IOException;
// import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws IOException {
        File f = new File("./day2input.txt");
        try (Scanner in = new Scanner(f)) {
            char[][] rounds = new char[2500][2];
            int i = 0;
            char[] temp;
            int total = 0;

            while (in.hasNextLine()) {
                temp = in.next().toCharArray();
                rounds[i][0] = temp[0];

                temp = in.next().toCharArray();
                rounds[i][1] = temp[0];

                in.nextLine();

                i++;
            }

            for (i = 0; i < rounds.length; i++) {
                int roundvalue = 0;
                int oppvalue = 0;

                switch (rounds[i][0]) {
                    case 'A': // rock
                        oppvalue = 1;
                        break;
                    case 'B': // paper
                        oppvalue = 2;
                        break;
                    case 'C': // scissors
                        oppvalue = 3;
                        break;
                }

                switch (rounds[i][1]) {
                    case 'X': // lose
                        switch (oppvalue) {
                            case 1:
                                roundvalue = 3 + 0;
                                break;
                            case 2:
                                roundvalue = 1 + 0;
                                break;
                            case 3:
                                roundvalue = 2 + 0;
                                break;
                        }
                        break;
                    case 'Y': // draw
                        switch (oppvalue) {
                            case 1:
                                roundvalue = 1 + 3;
                                break;
                            case 2:
                                roundvalue = 2 + 3;
                                break;
                            case 3:
                                roundvalue = 3 + 3;
                                break;
                        }
                        break;
                    case 'Z': // win
                        switch (oppvalue) {
                            case 1:
                                roundvalue = 2 + 6;
                                break;
                            case 2:
                                roundvalue = 3 + 6;
                                break;
                            case 3:
                                roundvalue = 1 + 6;
                                break;
                        }
                        break;
                }

                total += roundvalue;
            }

            System.out.println(total);
            in.close();
        }
    }
}
