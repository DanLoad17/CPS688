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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RabinKarpSubstringSearch {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("input.txt");
        Scanner scanner = new Scanner(inputFile);

        while (scanner.hasNextLine()) {
            String text = scanner.nextLine().substring(15); // remove "Enter the text: "
            String pattern = scanner.nextLine().substring(18); // remove "Enter the pattern: "

            List<Integer> indices = rabinKarpSearch(text, pattern);
            if (indices.isEmpty()) {
                System.out.println("Pattern " + pattern + " not found in text " + text);
            } else {
                System.out.println("Pattern " + pattern + " found at indices " + indices);
            }
        }
    }

    public static List<Integer> rabinKarpSearch(String text, String pattern) {
        List<Integer> indices = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        int prime = 101;

        long patternHash = hash(pattern, prime);
        long textHash = hash(text.substring(0, m), prime);

        for (int i = 0; i <= n - m; i++) {
            if (textHash == patternHash && text.substring(i, i + m).equals(pattern)) {
                indices.add(i);
            }

            if (i < n - m) {
                textHash = rehash(text, textHash, i, m, prime);
            }
        }

        return indices;
    }

    private static long hash(String str, int prime) {
        long hash = 0;
        int n = str.length();

        for (int i = 0; i < n; i++) {
            hash += str.charAt(i) * Math.pow(prime, n - 1 - i);
        }

        return hash;
    }

    private static long rehash(String str, long hash, int oldIndex, int m, int prime) {
        long newHash = (long) (hash - str.charAt(oldIndex) * Math.pow(prime, m - 1));
        newHash = newHash * prime + str.charAt(oldIndex + m);
        return newHash;
    }
}

