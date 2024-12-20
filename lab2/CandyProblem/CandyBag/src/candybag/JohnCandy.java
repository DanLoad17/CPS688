/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candybag;

/**
 *
 * @author zelen
 */
import java.util.Scanner;

public class JohnCandy 
{
    
    public static void main(String[] args) 
    {
    System.out.println("Enter candy details: ");
    Scanner sc;
        sc = new Scanner(System.in);
    
    // input detect and understand
    int numCandies;
        numCandies = sc.nextInt();
    int[] sentVal;
        sentVal = new int[numCandies];
    int[] weights;
        weights = new int[numCandies];
        
    for (int i = 0; i < numCandies; i++) 
    {
        sentVal[i] = sc.nextInt(); //splits the inputs
    }
    
    for (int i = 0; i < numCandies; i++) 
    {
        weights[i] = sc.nextInt(); //assigns the weights as well
    }
    
    int maxWeight;
        maxWeight = sc.nextInt();
    
    // calculate highest possible sentimental value
    int[][] carr;
        carr = new int[numCandies+1][maxWeight+1];
        
    for (int i = 1; i <= numCandies; i++) 
    {
        for (int j = 1; j <= maxWeight; j++) 
        {
            if (weights[i-1] <= j) 
            {
                carr[i][j] = Math.max(sentVal[i-1] + carr[i-1][j-weights[i-1]], carr[i-1][j]);
            } 
            else 
            {
                carr[i][j] = carr[i-1][j];
            }
        }
    }
    System.out.println("Maximum value carried: " + carr[numCandies][maxWeight]);
}
}
