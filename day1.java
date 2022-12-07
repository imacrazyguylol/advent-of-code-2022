import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("./day1input.txt");
        Scanner in = new Scanner(f);

        List<Integer> maxes = new ArrayList<Integer>();
        int i = 0;
        int max = 0;

        maxes.add(0);

        while (in.hasNextLine()) {
            String s = in.nextLine();
            

            if (s.isEmpty()) {
                maxes.add(0);
                i++;
            } else {
                int food = (int) Integer.parseInt(s);
                int t = maxes.get(i);

                maxes.set(i, t + food); // adds food to total
            }

        }

        in.close();

        int[] top3 = new int[3]; // 0, 1, 2

        for (i = 0; i < maxes.size(); i++) {
            int g = maxes.get(i);

            if (g >= top3[0]) {
                top3[2] = top3[1];
                top3[1] = top3[0];
                top3[0] = g;
            } else if (g >= top3[1]) {
                top3[2] = top3[1];
                top3[1] = g;
            } else if (g >= top3[2]) {
                top3[2] = g;
            }
        }
        max = top3[0] + top3[1] + top3[2];
        
        System.out.println(max);
    }
}