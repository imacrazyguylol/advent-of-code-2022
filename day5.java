import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("./day5input.txt");
        Scanner in = new Scanner(f);

        char[][] stacks = new char[9][8];
        Map<Integer, Stack<Character>> stackmap = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            String s = in.nextLine();
            Matcher m = Pattern.compile("\\p{Upper}").matcher(s);

            char[] carr = s.toCharArray();

            while (m.find()) {
                int index = m.start();
                int stack = index == 2 ? 0 : ((((index - 2) / 2) + 2) / 2);

                stacks[stack][stacks[stack].length - i - 1] = carr[index];
                // System.out.println(carr[index]);
            }
            // System.out.println();
        }

        for (int i = 0; i < stacks.length; i++) {
            Stack<Character> temp = new Stack<>();
            // System.out.println(stackmap.toString());

            for (int j = 0; j < stacks[i].length; j++) {
                char c = stacks[i][j];
                // System.out.println(c);

                if (c != (char) 0) {
                    temp.push(stacks[i][j]);
                } else {
                    // System.out.println("whitespace");
                }
            }

            stackmap.put(i, temp);
        }

        stackmap.get(0).push('B');
        stackmap.get(0).push('Z');
        stackmap.get(0).push('T');

        while (in.hasNextLine()) {
            String s = in.nextLine();
            Matcher m = Pattern.compile("\\p{Digit}+").matcher(s);

            m.find();
            int amt = Integer.parseInt(m.group());
            
            m.find();
            int from = Integer.parseInt(m.group()) - 1;

            m.find();
            int to = Integer.parseInt(m.group()) - 1;

            List<Character> arr = new ArrayList<Character>();
            for (int i = amt; i > 0; i--) {
                arr.add(stackmap.get(from).pop());
            }
            for (int i = 0; i < amt; i++) {
                stackmap.get(to).push(arr.get(arr.size() - i - 1));
            }

            /* for (int i = amt; i > 0; i--) {
                stackmap.get(to).push(stackmap.get(from).pop());
                System.out.println(stackmap.toString());
            } */
        }

        in.close();

        for (int i = 0; i < 9; i++) {
            System.out.print(stackmap.get(i).peek());
        }
        System.out.println();
    }
}