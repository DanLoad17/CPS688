/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxbipartitematching;

/**
 *
 * @author zelen
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MaxBipartiteMatching 
{
    public static void main(String[] args) 
    { 
        int[][] graph = readInputFromFile("input.txt"); // Read input from file
        int maxMatching = maxBipartiteMatching(graph); // Run maximum bipartite matching of the graph values given
        System.out.println("The maximum number of applicants matching for the jobs is " + maxMatching); // output
    }
    
    public static int[][] readInputFromFile(String filename) // Reads file for input and returns adjacency matrix
    {
        int[][] graph = null;
        try {
            Scanner scanner = new Scanner(new File(filename));
            int numRows = 0;
            int numCols = 0;
            while (scanner.hasNextLine())  //go down the input file
            {
                numRows++;
                String[] row = scanner.nextLine().trim().split("\\s+");
                numCols = row.length;
            }
            scanner.close(); //close the scanner 

            graph = new int[numRows][numCols]; // Initialize graph array

            scanner = new Scanner(new File(filename)); // Read input again to populate graph array
            int i = 0;
            while (scanner.hasNextLine()) 
            {
                String[] row = scanner.nextLine().trim().split("\\s+");
                for (int j = 0; j < row.length; j++) 
                {
                    graph[i][j] = Integer.parseInt(row[j]);
                }
                i++;
            }
            scanner.close();
        } 
        catch (FileNotFoundException e) //exception
        {
            e.printStackTrace();
        }
        return graph;
    }

    
    public static int maxBipartiteMatching(int[][] graph) // Runs Ford-Fulkerson algorithm to find maximum bipartite matching
    {
        int numRows = graph.length;
        int numCols = graph[0].length;

        int[] matching = new int[numCols]; // Initialize array to store matching
        Arrays.fill(matching, -1);

        for (int u = 0; u < numRows; u++) // Run augmenting paths until no more can be found
        {
            boolean[] visited = new boolean[numCols];
            if (augmentPath(graph, u, visited, matching)) 
            {
                // Found augmenting path, increase matching size
                // by 1
            }
        }
        
        int maxMatching = 0; // Count number of matches
        for (int i = 0; i < numCols; i++) 
        {
            if (matching[i] != -1) 
            {
                maxMatching++;
            }
        }
        return maxMatching;
    }
    // Searches for augmenting path in graph and updates matching
    // Returns true if an augmenting path was found
    public static boolean augmentPath(int[][] graph, int u, boolean[] visited, int[] matching) 
    {
        int numCols = graph[0].length;

        for (int v = 0; v < numCols; v++) 
        {
            if (graph[u][v] == 1 && !visited[v]) //Uses BFS
            {
                visited[v] = true;
                if (matching[v] == -1 || augmentPath(graph, matching[v], visited, matching)) 
                {
                    matching[v] = u; // Found augmenting path, update matching
                    return true;
                }
            }
        }
        return false;
    }
}
