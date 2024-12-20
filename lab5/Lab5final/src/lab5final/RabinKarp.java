/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5final;

/**
 *
 * @author zelen
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RabinKarp {
public static void main(String[] args) throws FileNotFoundException {
Scanner fileScanner = new Scanner(new File("input.txt"));
String text = fileScanner.nextLine().trim();
String pattern = fileScanner.nextLine().trim();
fileScanner.close();
    RabinKarp rabinKarp = new RabinKarp();
    int[] occurrences = rabinKarp.findOccurrences(text, pattern);

    System.out.println("Pattern found at the following indices:");
    for (int index : occurrences) {
        System.out.println(index);
    }
}

public int[] findOccurrences(String text, String pattern) {
    List<Integer> occurrencesList = new ArrayList<>();
    int patternLength = pattern.length();
    int textLength = text.length();
    int patternHash = 0;
    int textHash = 0;
    int prime = 31;
    int modulus = 1000000007;

    int power = 1;
    for (int i = 0; i < patternLength - 1; i++) {
        power = (power * prime) % modulus;
    }

    for (int i = 0; i < patternLength; i++) {
        patternHash = (patternHash * prime + pattern.charAt(i)) % modulus;
        textHash = (textHash * prime + text.charAt(i)) % modulus;
    }

    for (int i = 0; i <= textLength - patternLength; i++) {
        if (patternHash == textHash && text.substring(i, i + patternLength).equals(pattern)) {
            occurrencesList.add(i);
        }

        if (i < textLength - patternLength) {
            textHash = (textHash - text.charAt(i) * power) % modulus;
            textHash = (textHash + modulus) % modulus;
            textHash = (textHash * prime + text.charAt(i + patternLength)) % modulus;
        }
    }

    int[] occurrences = new int[occurrencesList.size()];
    for (int i = 0; i < occurrencesList.size(); i++) {
        occurrences[i] = occurrencesList.get(i);
    }

    return occurrences;
}
}