/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2problem3;

/**
 *
 * @author zelen
 */
import java.util.*;
import java.util.function.Consumer;

public class MST 
{
    public static void main(String[] args) 
    {
        System.out.print("Enter nodes and edges:\n");
        Scanner sc;
        sc = new Scanner(System.in);

        int numNodes;
        numNodes = sc.nextInt();
        int numEdges;
        numEdges = sc.nextInt();

        List<List<int[]>> graph;
        graph = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) 
        {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < numEdges; i++) 
        {
            int u;
            u = sc.nextInt();
            int v;
            v = sc.nextInt();
            int weight;
            weight = sc.nextInt();
            graph.get(u).add(new int[] {v, weight});
            graph.get(v).add(new int[] {u, weight});
        }

        boolean[] visited;
        visited = new boolean[numNodes];
        int[] parent;
        parent = new int[numNodes];
        int[] weight;
        weight = new int[numNodes];
        Arrays.fill(visited, false);
        Arrays.fill(weight, Integer.MAX_VALUE);

        PriorityQueue<Integer> pq;
        pq = new PriorityQueue<>((u, v) -> weight[u] - weight[v]);
        pq.offer(0);
        weight[0] = 0;

        while (!pq.isEmpty()) 
        {
            int u;
            u = pq.poll();
            visited[u] = true;

            graph.get(u).forEach((int[] edge) -> {
                int v;
                v = edge[0];
                int edgeWeight; 
                edgeWeight = edge[1];
                if (edgeWeight >= weight[v] || visited[v])
                {
                }
                else
                {
                    pq.remove(v);
                    weight[v] = edgeWeight;
                    parent[v] = u;
                    pq.offer(v);
                }
            });
        }

        int mstWeight;
        mstWeight = 0;

        for (int i = 1; i < numNodes; i++) 
        {
            System.out.println("Edge " + parent[i] + "-" + i + " weight is " + weight[i]);
            mstWeight += weight[i];
        }
        System.out.println("MST weight = " + mstWeight);
    }
}
