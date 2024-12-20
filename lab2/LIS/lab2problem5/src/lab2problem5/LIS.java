/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2problem5;

/**
 *
 * @author zelen
 */
import java.util.*;

public class LIS 
{
    public static void main(String[] args) 
    {
        Scanner sc;
        sc = new Scanner(System.in);
        System.out.print("Enter length of value values:\n");
        int n;
        n = sc.nextInt(); //split values put in
        int[] values = new int[n];
        System.out.println("\nEnter the values within parameters entered previously: ");
        for (int i = 0; i < n; i++) 
        {
            values[i] = sc.nextInt(); //put into array each different value input
        }

        int[] lis;
        lis = solveLIS(values);

        System.out.print("\nLIS is: ");
        for (int i = 0; i < lis.length; i++) 
        {
            System.out.print(lis[i] + " ");
        }
    }

    public static int[] solveLIS(int[] values) 
    {
        int n;
        n = values.length;
        int[] keep;
        keep = new int[n];
        int[] last;
        last = new int[n];
        Arrays.fill(last, -1);

        keep[0] = 1;
        int totalLength;
        totalLength = 1;
        int topVal;
        topVal = 0;

        for (int i = 1; i < n; i++) 
        {
            keep[i] = 1; //set values kept

            for (int j = 0; j < i; j++) 
            {
                if (values[i] > values[j] && keep[j] + 1 > keep[i]) 
                {
                    keep[i] = keep[j] + 1;
                    last[i] = j;
                }
            }

            if (keep[i] > totalLength) 
            {
                totalLength = keep[i];
                topVal = i;
            }
        }

        int[] lis;
        lis = new int[totalLength];
        int p;
        p = totalLength - 1;
        int i;
        i = topVal;
        while (i >= 0) 
        {
            lis[p--] = values[i];
            i = last[i];
        }
        return lis;
    }
}
