/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2problem2;

/**
 *
 * @author zelen
 */
import java.util.*;

public class AcyclicGraph 
{
    public static void main(String[] args) 
    {
        // Define edges, could not get file reder to work
        int[][] edges = 
        {
            {6, 5},
            {0, 1},
            {0, 2},
            {0, 3},
            {1, 4},
            {2, 5}
        };

        // Create adjacency list for graph
        int n = 7; // number of vertices + 1
        List<Integer>[] adj;
        adj = new List[n];
        for (int i = 0; i < n; i++) 
        {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) 
        {
            int u;
            u = edge[0];
            int v;
            v = edge[1];
            //boolean add = adj[u].add(v);
            //boolean add1 = adj[v].add(u);
        }

        // Check if the graph has a cycle
        boolean hasCycle;
        hasCycle = hasCycle(adj, n);

        System.out.println(hasCycle ? "no" : "yes"); //display based on result
    }

    
    private static boolean hasCycle(List<Integer>[] adj, int n) // Returns true if graph has a cycle
    {
        boolean[] visited;
        visited = new boolean[n];
        for (int i = 0; i < n; i++) 
        {
            if (visited[i] || !hasCycleHelper(adj, i, visited, -1)) 
            {
            } 
            else 
            {
                return true;
            }
        }
        return false;
    }

    // Helper function for the DFS traversal
    private static boolean hasCycleHelper(List<Integer>[] adj, int u, boolean[] visited, int parent) 
    {
        visited[u] = true;
        for (int v : adj[u]) 
        {
            if (visited[v]) 
            {
                if (v != parent) 
                {
                    return true;
                }
            } 
            else 
            {
                if (!hasCycleHelper(adj, v, visited, u))
                {
                } 
                else 
                {
                    return true;
                }
            }
        }
        return false;
    }
}
