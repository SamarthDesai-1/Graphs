import java.util.*;

public class PrintAllPaths {

  static List<List<Integer>> constructGraph(int[][] edges, int n) {
    List<List<Integer>> graph = new ArrayList<>();

    for (int i = 0; i < n + 1; i++)
      graph.add(new ArrayList<>());

    for (int[] edge : edges) {
      graph.get(edge[0]).add(edge[1]);
      graph.get(edge[1]).add(edge[0]);
    }
    return graph;
  }

  static List<String> list = new ArrayList<>();
  static void paths(List<List<Integer>> graph, int n, int src, int dest, boolean[] isvisited, String paths) {
    if (src == dest) {
      list.add(paths);
      return;
    }

    isvisited[src] = true;
    for (int neighbour : graph.get(src)) {
      if (!isvisited[neighbour]) {
        paths(graph, n, neighbour, dest, isvisited, paths + " " + neighbour);
      }
    }
    isvisited[src] = false;

  }

  public static void main(String[] args) {
    int[][] edges = {
        { 0, 1 }, { 1, 3 }, { 3, 2 }, { 2, 5 }, { 3, 6 }
    };

    int n = Integer.MIN_VALUE;

    /* Finding maximum value of node to create array of N size of boolean */ {

      for (int i = 0; i < edges.length; i++) {
        for (int j = 0; j < edges[0].length; j++) {
          n = Math.max(n, edges[i][j]);
        }
      }
    }

    List<List<Integer>> graph = constructGraph(edges, n);

    int src = 0, dest = 6;
    paths(graph, n, src, dest, new boolean[n + 1], "" + src);

  }

}
