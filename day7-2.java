import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    static Directory smallest;

    public static void main(String[] aaa) throws FileNotFoundException {
        File f = new File("./day7input.txt");
        Scanner in = new Scanner(f);

        Directory root = new Directory("root", false, 0, null);
        Directory current = root;

        String s = in.nextLine();
        cmd: while (in.hasNextLine()) {
            String[] args = s.split(" ");
            if (args[0].equals("$")) {
                switch (args[1]) {
                    case "ls":
                        s = in.nextLine();
                        continue cmd;
                    case "cd":
                        switch (args[2]) {
                            case "/":
                                current = root;
                            case "..":
                                current = current.backOut();
                            default:
                                current = current.getDir(args[2]);
                        }

                        s = in.nextLine();
                        continue cmd;
                }
            } else {
                String s2 = s;

                ls: while (in.hasNextLine()) {
                    args = s2.split(" ");

                    if (args.length > 2) {
                        break ls;
                    }

                    if (args[0].equals("dir")) {
                        current.newDir(new Directory(args[1], false, 0, current));

                        s2 = in.nextLine();
                        continue ls;
                    } else {
                        current.addFile(new Directory(args[1], true, Integer.parseInt(args[0]), current));

                        s2 = in.nextLine();
                        continue ls;
                    }
                }

                s = s2;
                continue cmd;
            }

            s = in.nextLine();
        }

        in.close();

        int used = calcAll(root);
        int free = 70000000 - used;
        int required = 30000000 - free;

        System.out.println(used + "\n" + free + "\n");

        smallest = root;
        System.out.println(findSmallestDir(root, required).sizeOfContents);
    }

    public static Directory findSmallestDir(Directory d, int required) {
        for (Directory dir : d.contents) {
            if (!dir.isFile) {
                int size = dir.calcSizeOfContents();
                if (size < smallest.sizeOfContents && size >= required) {
                    smallest = dir;
                } else {
                    smallest = findSmallestDir(dir, required);
                }
            } else {
                int size = dir.backOut().calcSizeOfContents();

                if (size < smallest.sizeOfContents && size >= required) {
                    smallest = dir.backOut();
                }
            }
        }

        return smallest;
    }

    public static int calcAll(Directory d) {
        int sum = 0;

        for (Directory dir : d.contents) {
            if (!dir.isFile) {
                int size = dir.calcSizeOfContents();
                // System.out.println(dir.name + " " + size);

                sum += size;
                System.out.println(dir.name + " " + size + "\n" + sum);
            } else {
                System.out.println("file " + dir.name + " " + dir.sizeOfFile);
            }
        }

        return sum;
    }
}

class Directory {
    String name;
    Directory parent;
    List<Directory> contents = new ArrayList<Directory>();
    int sizeOfFile;
    int sizeOfContents;
    Boolean isFile;

    public Directory(String n, Boolean f, int size, Directory parent) {
        this.name = n;
        this.isFile = f;
        this.parent = parent;

        if (isFile) {
            this.sizeOfFile = size;
        }
        if (this.name.equals("root")) {
            this.sizeOfContents = 46254731;
        }
    }

    public void addFile(Directory d) {
        if (!d.isFile) {
            System.out.println("not a file.");
        } else {
            this.contents.add(d);
            // calcSizeOfContents();
        }
    }

    public void newDir(Directory d) {
        if (d.isFile) {
            System.out.println("Not a directory.");
        } else {
            this.contents.add(d);
            // calcSizeOfContents();
        }
    }

    public int calcSizeOfContents() {
        int size = 0;

        for (Directory dir : this.contents) {
            // System.out.println(dir.name);
            if (!dir.isFile) {
                size += dir.calcSizeOfContents();
            } else {
                size += dir.sizeOfFile;
                // System.out.println("file " + dir.name + " " + dir.sizeOfFile);
            }
        }

        // System.out.println(size);
        this.sizeOfContents = size;
        return size;
    }

    public Directory getDir(String name) {
        for (Directory dir : this.contents) {
            if (dir.name.equals(name) && !dir.isFile) {
                return dir;
            }
        }
        return this;
    }

    public Directory backOut() {
        if (this.parent == null)
            return this;
        return this.parent;
    }

    public String toString() {
        String s = "";

        for (Directory dir : this.contents) {
            if (!dir.isFile) {
                s += " >" + dir + "\n";
            } else {
                s += dir.name + "\n";
            }
        }

        return s;
    }
}