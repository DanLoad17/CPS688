import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RabinKarp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the text:");
        String text = scanner.nextLine();
        System.out.println("Enter the pattern:");
        String pattern = scanner.nextLine();
        scanner.close();

        RabinKarp rk = new RabinKarp();
        int[] occurrences = rk.searchPattern(text, pattern);

        System.out.println("Pattern found at the following indices:");
        for (int index : occurrences) {
            System.out.println(index);
        }
    }

    public int[] searchPattern(String text, String pattern) {
        List<Integer> occurrencesList = new ArrayList<>();
        int patternLength = pattern.length();
        int textLength = text.length();
        int patternHash = 0;
        int textHash = 0;
        int prime = 31;
        int mod = 1000000007;

        int power = 1;
        for (int i = 0; i < patternLength - 1; i++) {
            power = (power * prime) % mod;
        }

        for (int i = 0; i < patternLength; i++) {
            patternHash = (patternHash * prime + pattern.charAt(i)) % mod;
            textHash = (textHash * prime + text.charAt(i)) % mod;
        }

        for (int i = 0; i <= textLength - patternLength; i++) {
            if (patternHash == textHash && text.substring(i, i + patternLength).equals(pattern)) {
                occurrencesList.add(i);
            }

            if (i < textLength - patternLength) {
                textHash = (textHash - text.charAt(i) * power) % mod;
                textHash = (textHash + mod) % mod;
                textHash = (textHash * prime + text.charAt(i + patternLength)) % mod;
            }
        }

        int[] occurrences = new int[occurrencesList.size()];
        for (int i = 0; i < occurrencesList.size(); i++) {
            occurrences[i] = occurrencesList.get(i);
        }

        return occurrences;
    }
}
