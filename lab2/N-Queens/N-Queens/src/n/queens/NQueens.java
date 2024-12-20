/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n.queens;

/**
 *
 * @author zelen
 */
import java.util.Scanner;

public class NQueens 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of queens: ");
        int n = input.nextInt();
        input.close();
        
        int[][] board = new int[n][n];
        if (backSolve(board, 0)) 
        {
            displayRes(board);
        } else 
        {
            System.out.println("No soln");
        }
    }
    private static boolean backSolve(int[][] board, int column) 
    {
        if (column >= board.length) 
        {
            return true;
        }
        for (int i = 0; i < board.length; i++) 
        {
            if (isSafe(board, i, column)) 
            {
                board[i][column] = 1;
                if (backSolve(board, column + 1)) 
                {
                    return true;
                }
                board[i][column] = 0;
            }
        }
        return false;
    }
    private static boolean isSafe(int[][] board, int row, int column) 
    {
        for (int i = 0; i < column; i++) 
        {
            if (board[row][i] == 1) 
            {
                return false;
            }
        }
        for (int i = row, j = column; i >= 0 && j >= 0; i--, j--) 
        {
            if (board[i][j] == 1) 
            {
                return false;
            }
        }
        for (int i = row, j = column; i < board.length && j >= 0; i++, j--) 
        {
            if (board[i][j] == 1) 
            {
                return false;
            }
        }
        return true;
    }

    private static void displayRes(int[][] board) 
    {
        for (int[] board1 : board) 
        {
            for (int j = 0; j < board.length; j++) 
            {
                System.out.print(board1[j] + " ");
            }
            System.out.println();
        }
    }
}
