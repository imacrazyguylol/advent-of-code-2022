import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("day4input.txt");
        Scanner in = new Scanner(f);

        int[][] arr = new int[1000][4];
        int pairs = 0;

        int i = 0;
        while (in.hasNextLine()) {
            String s = in.nextLine();
            String[] split = s.split("[\\,\\-]");

            arr[i][0] = Integer.parseInt(split[0]);
            arr[i][1] = Integer.parseInt(split[1]);
            arr[i][2] = Integer.parseInt(split[2]);
            arr[i][3] = Integer.parseInt(split[3]);

            i++;
        }
        in.close();
        
        for (i = 0; i < arr.length; i++) {
            int[] temp = arr[i];

            innerloop:
            for (int j = temp[0]; j <= temp[1]; j++) {
                if (j <= temp[3] && j >= temp[2]) {
                    System.out.println(Arrays.toString(temp));
                    pairs++;
                    break innerloop;
                }
            }
        }

        System.out.println(pairs);
    }
}
