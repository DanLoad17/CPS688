/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zelen
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BoyerMoore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the text:");
        String text = scanner.nextLine();
        System.out.println("Enter the pattern:");
        String pattern = scanner.nextLine();
        scanner.close();

        BoyerMoore bm = new BoyerMoore();
        int[] occurrences = bm.searchPattern(text, pattern);

        System.out.println("Pattern found at the following indices:");
        for (int index : occurrences) {
            System.out.println(index);
        }
    }

    public int[] searchPattern(String text, String pattern) {
        int[] result = new int[text.length()];
        int index = 0;

        Map<Character, Integer> badCharacterRule = preprocessBadCharacterRule(pattern);

        int t = 0;
        while (t <= text.length() - pattern.length()) {
            int j = pattern.length() - 1;
            while (j >= 0 && pattern.charAt(j) == text.charAt(t + j)) {
                j--;
            }
            if (j < 0) {
                result[index++] = t;
                t += (t + pattern.length() < text.length()) ? pattern.length() - badCharacterRule.getOrDefault(text.charAt(t + pattern.length()), -1) : 1;
            } else {
                t += Math.max(1, j - badCharacterRule.getOrDefault(text.charAt(t + j), -1));
            }
        }

        int[] occurrences = new int[index];
        System.arraycopy(result, 0, occurrences, 0, index);
        return occurrences;
    }

    private Map<Character, Integer> preprocessBadCharacterRule(String pattern) {
        Map<Character, Integer> badCharacterRule = new HashMap<>();
        int patternLength = pattern.length();

        for (int i = 0; i < patternLength - 1; i++) {
            char currentChar = pattern.charAt(i);
            badCharacterRule.put(currentChar, patternLength - 1 - i);
        }

        if (!badCharacterRule.containsKey(pattern.charAt(patternLength - 1))) {
            badCharacterRule.put(pattern.charAt(patternLength - 1), patternLength);
        }

        return badCharacterRule;
    }
}
