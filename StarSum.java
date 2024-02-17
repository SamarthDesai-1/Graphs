import java.util.*;

public class StarSum {

  public static void main(String[] args) {
    int[] vals = { 1, 2, 3, 4, 10, -10, -20 };
    int n = vals.length;
    int[][] edges = {
        { 0, 1 },
        { 1, 2 },
        { 1, 3 },
        { 3, 4 },
        { 3, 5 },
        { 3, 6 }
    };
    int k = 2;

    List<List<Integer>> graph = new ArrayList<>();

    for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

    for (int[] edge : edges) {
      graph.get(edge[0]).add(vals[edge[1]]);
      graph.get(edge[1]).add(vals[edge[0]]);
    }

    int maxo = Integer.MIN_VALUE;

    for (int i = 0; i < n; i++) {
      Collections.sort(graph.get(i), Collections.reverseOrder());

      int sum = 0;
      sum = vals[i];

      for (int j = 0; j < k && j < graph.get(i).size(); j++) {
        sum += Math.max(0, graph.get(i).get(j));
      }
      maxo = Math.max(maxo, sum);
    }

    System.out.println(maxo);
  }

}
