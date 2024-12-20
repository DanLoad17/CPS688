/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rodcutting;

/**
 *
 * @author zelen
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RodCuttingProblem {
    public static void main(String[] args) {
        String inputFileName;
        inputFileName = "input.txt";
        String line = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            while ((line = reader.readLine()) != null) {
                int rodLength;
                rodLength = Integer.parseInt(line.trim());
                int[] prices;
                prices = new int[rodLength];
                String[] priceStrings;
                priceStrings = reader.readLine().split(" "); //read from input file and split by space
                for (int i = 0; i < rodLength; i++) {
                    prices[i] = Integer.parseInt(priceStrings[i].trim());
                }
                int[] maxRevenue;
                maxRevenue = new int[rodLength+1]; //subproblems
                maxRevenue[0] = 0;
                for (int i = 1; i <= rodLength; i++) {
                    int q;
                    q = Integer.MIN_VALUE; //start bottom-up from smallest value
                    for (int j = 0; j < i; j++) {
                        q = Math.max(q, prices[j] + maxRevenue[i-j-1]);
                    }
                    maxRevenue[i] = q;
                }
                System.out.println("Rod length " + rodLength + " has max revenue of " + maxRevenue[rodLength]);
            }
        } catch (IOException e) { //error catch
            e.printStackTrace();
        }
    }
}