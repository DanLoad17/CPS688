/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strongcomponents;

/**
 *
 * @author zelen
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StronglyConnectedComponents {
    public static void main(String[] args) {
        String filename = "input.txt";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (true) {
                String line = reader.readLine(); //read the file
                if (line == null) break;
                String[] parts = line.split(" "); //split it into its parts so it all means different things
                int n = Integer.parseInt(parts[0]);
                int e = Integer.parseInt(parts[1]);
                boolean[][] adj = new boolean[n][n];
                for (int i = 0; i < e; i++) {
                    line = reader.readLine();
                    parts = line.split(" "); //split by space
                    int u = Integer.parseInt(parts[0]);
                    int v = Integer.parseInt(parts[1]);
                    adj[u][v] = true;
                }
                if (isStronglyConnected(adj)) { //if DFS reaches all nodes, yes, if not, no
                    System.out.println("yes");
                } else {
                    System.out.println("no");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isStronglyConnected(boolean[][] adj) {
        int n = adj.length;
        boolean[] visited = new boolean[n];
        dfs(adj, visited, 0); //init all as not visited, DFS
        for (boolean v : visited) {
            if (!v) return false;
        }
        boolean[][] revAdj = reverseGraph(adj); //reverse graph arc
        visited = new boolean[n];
        dfs(revAdj, visited, 0); //DFS nodes
        for (boolean v : visited) {
            if (!v) return false; //mark vertices as non-visted
        }
        return true;
    }

    public static void dfs(boolean[][] adj, boolean[] visited, int u) { //DFS same as lab 1
        visited[u] = true;
        for (int v = 0; v < adj.length; v++) {
            if (adj[u][v] && !visited[v]) {
                dfs(adj, visited, v);
            }
        }
    }

    public static boolean[][] reverseGraph(boolean[][] adj) { //the reverse way of the graph
        int n = adj.length;
        boolean[][] revAdj = new boolean[n][n];
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                revAdj[v][u] = adj[u][v];
            }
        }
        return revAdj;
    }
}

