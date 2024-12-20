/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1try2;

/**
 *
 * @author zelen
 */
import java.util.*;

class Graph {
  private Map<Integer, List<Integer>> adjacencyList;

  public Graph() {
    adjacencyList = new HashMap<>();
  }

  public void addEdge(int a, int b) {
    if (!adjacencyList.containsKey(a)) {
      adjacencyList.put(a, new ArrayList<>());
    }
    if (!adjacencyList.containsKey(b)) {
      adjacencyList.put(b, new ArrayList<>());
    }
    adjacencyList.get(a).add(b);
    adjacencyList.get(b).add(a);
  }

  public int degreeVertex(int a) {
    if (!adjacencyList.containsKey(a)) {
      return 0;
    }
    return adjacencyList.get(a).size();
  }

  public void printAdjVertices(int a) {
    if (!adjacencyList.containsKey(a)) {
      System.out.println("Vertex not found.");
      return;
    }
    System.out.print("Adjacent vertices of vertex " + a + ": ");
    for (int vertex : adjacencyList.get(a)) {
      System.out.print(vertex + " ");
    }
    System.out.println();
  }

  public void BFS(int start) {
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(start);
    visited.add(start);
    while (!queue.isEmpty()) {
      int vertex = queue.poll();
      System.out.print(vertex + " ");
      for (int neighbor : adjacencyList.get(vertex)) {
        if (!visited.contains(neighbor)) {
          queue.offer(neighbor);
          visited.add(neighbor);
        }
      }
    }
  }

  public void DFS(int start) {
    Set<Integer> visited = new HashSet<>();
    DFSUtil(start, visited);
  }

  private void DFSUtil(int vertex, Set<Integer> visited) {
    visited.add(vertex);
    System.out.print(vertex + " ");
    for (int neighbor : adjacencyList.get(vertex)) {
      if (!visited.contains(neighbor)) {
        DFSUtil(neighbor, visited);
      }
    }
  }
}
