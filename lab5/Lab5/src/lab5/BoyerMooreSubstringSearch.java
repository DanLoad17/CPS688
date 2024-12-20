/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

/**
 *
 * @author zelen
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BoyerMooreSubstringSearch {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("input.txt");
        Scanner scanner = new Scanner(inputFile);

        while (scanner.hasNextLine()) {
            String text = scanner.nextLine().substring("Enter the text: ".length());
            String pattern = scanner.nextLine().substring("Enter the pattern: ".length());


            int index = boyerMooreSearch(text, pattern);
            if (index == -1) {
                System.out.println("Pattern " + pattern + " not found in text " + text);
            } else {
                System.out.println("Pattern " + pattern + " found at index " + index);
            }
        }
    }

    public static int boyerMooreSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        int[] last = buildLast(pattern);

        int i = m - 1;
        int j = m - 1;

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                if (j == 0) {
                    return i;
                }
                i--;
                j--;
            } else {
                i += m - Math.min(j, 1 + last[text.charAt(i)]);
                j = m - 1;
            }
        }

        return -1;
    }

    private static int[] buildLast(String pattern) {
        int[] last = new int[256];
        for (int i = 0; i < last.length; i++) {
            last[i] = -1;
        }

        for (int i = 0; i < pattern.length(); i++) {
            last[pattern.charAt(i)] = i;
        }

        return last;
    }
}

