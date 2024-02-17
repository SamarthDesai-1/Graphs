import java.util.*;

public class AdjacencyMatrix {

  static void dfs(int[][] graph, int vertex, boolean[] isvisited) {
    if (!isvisited[vertex]) {
      System.out.println("Visiting vertex : " + vertex);

      isvisited[vertex] = true;

      for (int neighbour = 0; neighbour < graph.length; neighbour++) {
        if (graph[vertex][neighbour] == 1 && !isvisited[neighbour]) {
          dfs(graph, neighbour, isvisited);
        }
      }
    }
  }

  static void printAllPaths(int[][] graph, boolean[] isvisited, int src, int dest, String path) {
    if (src == dest) {
      System.out.println("Paths : " + path);
      return;
    }

    isvisited[src] = true;

    for (int neighbour = 0; neighbour < graph.length; neighbour++) {
      if (graph[src][neighbour] == 1 && !isvisited[neighbour]) {
        printAllPaths(graph, isvisited, neighbour, dest, path + " " + src);
      }
    }
    isvisited[src] = false;
  }

  static boolean hasPath(List<List<Integer>> graph, boolean[] isvisited, int src, int dest) {

    if (src == dest) {
      return true;
    }

    isvisited[src] = true;
    for (int neightbour : graph.get(src)) {
      if (!isvisited[neightbour]) {
        if (hasPath(graph, isvisited, neightbour, dest)) {
          return true;
        }
      }
    }

    return false;
  }

  public static void main(String[] args) {
    int[][] graph = {
        { 0, 1 }, { 1, 2 }, { 2, 0 }
    };
    boolean[] isvisited = new boolean[graph.length];

    // dfs(graph, 0, isvisited);
    // printAllPaths(graph, isvisited, 0, 4, "");

    {
      List<List<Integer>> list = new ArrayList<>();
      int n = 3;
      for (int i = 0; i < n; i++) {
        list.add(new ArrayList<>());
      }

      for (int[] edge : graph) {
        int u = edge[0];
        int v = edge[1];
        list.get(u).add(v);
        list.get(v).add(u);
      }

      boolean path = hasPath(list, isvisited, 0, 5);

      System.out.println(path);
    }

  }
}

