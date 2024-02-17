import java.util.*;

public class StarGraphCenter {

  static void printGraph(List<List<Integer>> graph) {
    for (int i = 0; i < graph.size(); i++) {

      List<Integer> temp = graph.get(i);
      for (int j = 0; j < temp.size(); j++) {
        System.out.println(i + " -> " + temp.get(j) + " ");
      }
      System.out.println();
    }
  }

  static void starGraphCenter(List<List<Integer>> graph) {
    int max = Integer.MIN_VALUE;
    int index = -1;

    for (int i = 0; i < graph.size(); i++) {

      if (max < graph.get(i).size()) {
        max = graph.get(i).size();
        index = i;
      }
    }
    System.out.println("Maximum Edges : " + max);
    System.out.println("Node that connects to more than one edge is : " + index);
  }

  public static void main(String[] args) {
    List<List<Integer>> l = new ArrayList<>();
    int[][] graph = {
        { 1, 2 }, { 5, 1 }, { 1, 3 }, { 1, 4 }
    };

    int n = Integer.MIN_VALUE;
    for (int i = 0; i < graph.length; i++) {
      for (int j = 0; j < graph[0].length; j++) {
        n = Math.max(n, graph[i][j]);
      }
    }

    for (int i = 0; i < n + 1; i++)
      l.add(new ArrayList<>());

    for (int[] edge : graph) {
      int u = edge[0];
      int v = edge[1];
      l.get(u).add(v);
      l.get(v).add(u);
    }

    printGraph(l);
    starGraphCenter(l);
  }
}