import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Main {
    static Set<Character> unique(char[] arr) {
        int end = arr.length;
        Set<Character> set = new HashSet<Character>();

        for (int i = 0; i < end; i++) {
            set.add(arr[i]);
        }

        return set;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("./day6input.txt");
        Scanner in = new Scanner(f);

        String input = in.nextLine();
        char[] car = input.toCharArray();

        in.close();

        for (int i = 0; i <= car.length - 14; i++) {
            char[] c = new char[14];
            char[] d = new char[14];

            for (int j = 0; j < 14; j++) {
                c[j] = car[i + j];
            }

            Set<Character> u = unique(c);

            int j = 0;
            for (char cc : u) {
                d[j] = cc;
                j++;
            }

            Arrays.sort(c);
            Arrays.sort(d);

            int com = new String(c).compareTo(new String(d));

            if (com == 0) {
                System.out.println(i + 15);
                break;
            }
        }
    }
}
