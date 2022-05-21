package cce;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static ArrayList<String> keys = new ArrayList<String>();
    private static Scanner read;
    private static String key;
    private static int mode;

    public static void main(String[] args) {
        RedBlackTree redBlackTree = new RedBlackTree();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1- Load Dictionary.");
            System.out.println("2- Print Dictionary Size.");
            System.out.println("3- Insert Word.");
            System.out.println("4- Look-up a Word.");
            System.out.println("5- Exit.\n");
            System.out.print("Enter your choice: ");
            mode = scanner.nextInt();
            if (mode == 5)
                exit(0);
            System.out.println("\n--------------------------------\n");
            if (mode == 1) {
                readFile();
                for (int i = 0; i < keys.size(); i++)
                    redBlackTree.insert(keys.get(i));
                System.out.println("Dictionary has been read successfully");
                System.out.println("Tree Root: " + redBlackTree.getRoot().data);
                redBlackTree.printTreeSize();
                redBlackTree.printTreeHeight();
                System.out.println("\n--------------------------------\n");
            }
            else if (mode == 2) {
                System.out.println("Dictionary Size: " + redBlackTree.getNumberOfNodes());
                System.out.println("\n--------------------------------\n");
            }
            else if (mode == 3) {
                System.out.print("Enter the word you want to insert: ");
                scanKey();
                Node temp = redBlackTree.searchTree(key);
                if (temp.data == null){
                    redBlackTree.insert(key);
                    redBlackTree.printTreeSize();
                    redBlackTree.printTreeHeight();
                    System.out.println("Word has been inserted successfully.");
                }
                else{
                    System.out.println("\"ERROR: Word already in the dictionary!\"");
                }
                System.out.println("\n--------------------------------\n");
            }
            else if (mode == 4) {
                System.out.print("Enter the word you want to search for: ");
                scanKey();
                System.out.println("");
                Node temp = redBlackTree.searchTree(key);
                if (temp.data == null)
                    System.out.println("\"NO\", Word is not in the dictionary");
                else System.out.println("\"YES\", Word is in the dictionary");
                System.out.println("\n--------------------------------\n");
            }
        }
    }

    private static void scanKey() {
        Scanner scanner = new Scanner(System.in);
        key = scanner.nextLine().toLowerCase();
    }

    public static void readFile() {
        try {
            read = new Scanner(new File("EN-US-Dictionary.txt"));
        }catch (Exception e) {
            System.err.println("Could not find the file");
        }

        while (read.hasNext()) {
            keys.add(read.next());
        }
    }
}
