import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("./day3input.txt");
        Scanner in = new Scanner(f);

        String[] arr = new String[300];

        Map<Integer, String[]> groups = new HashMap<>();

        int i = 0;
        int sum = 0;

        while (in.hasNextLine()) {
            String s = in.nextLine();

            arr[i] = s;

            i++;
        }
        in.close();

        int j = 0;
        for (i = 0; i < arr.length; i += 3) {
            groups.put(j, new String[] { arr[i], arr[i + 1], arr[i + 2] });
            j++;
        }

        for (i = 0; i < groups.size(); i++) {
            String _array;
            String s;

            _array = "";
            s = groups.get(i)[0];
            for (int k = 0; k < s.length(); k++) {
                char[] carr = s.toCharArray();

                if (_array.indexOf(carr[k]) == -1) // check if a char already exist, if not exist then return -1
                    _array += carr[k]; // add new char
            }

            char[] c1 = _array.toCharArray();
            Arrays.sort(c1);

            _array = "";
            s = groups.get(i)[1];
            for (int k = 0; k < s.length(); k++) {
                char[] carr = s.toCharArray();

                if (_array.indexOf(carr[k]) == -1) // check if a char already exist, if not exist then return -1
                    _array += carr[k]; // add new char
            }

            char[] c2 = _array.toCharArray();
            Arrays.sort(c2);

            _array = "";
            s = groups.get(i)[2];
            for (int k = 0; k < s.length(); k++) {
                char[] carr = s.toCharArray();

                if (_array.indexOf(carr[k]) == -1) // check if a char already exist, if not exist then return -1
                    _array += carr[k]; // add new char
            }

            char[] c3 = _array.toCharArray();
            Arrays.sort(c3);

            System.out.println(new String(c1) + "\n");
            System.out.println(new String(c2) + "\n");
            System.out.println(new String(c3) + "\n");

            char c = '.';
            loop1: for (j = 0; j < c1.length; j++) {
                for (int k = 0; k < c2.length; k++) {
                    for (int l = 0; l < c3.length; l++) {
                        if (c1[j] == c2[k] && c2[k] == c3[l]) {
                            c = c1[j];
                            break loop1;
                        }
                    }
                }
            }

            System.out.println(c);
            if (c < 91) {
                sum += c - '@' + 26;
            } else {
                sum += c - 'F' - 26;
            }
        }

        System.out.println(sum);
    }
}
