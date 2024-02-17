import java.util.*;

public class ReorderRoutes {


  public static void main(String[] args) {
    int[][] matrix = {
        { 0, 1 }, { 1, 3 }, { 2, 3 }, { 4, 0 }, { 4, 5 }
    };
    int n = 6;

    List<List<Integer>> adj = new ArrayList<>();
    
    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<>());
    }

    for (int[] list : matrix) {
      int a = list[0];
      int b = list[1];

      adj.get(a).add(b);
      adj.get(b).add(-a);
    }

    /* Display GRAPH */ {

      for (int i = 0; i < adj.size(); i++) {
        
        List<Integer> l = adj.get(i);
        for (int j = 0; j < l.size(); j++) {
          System.out.print(i + " -> " + l.get(j) + " ");
        }
        System.out.println();
      }

    }


    System.out.println("Reorder Paths : " + dfs(0, new boolean[n], adj));

  }

  private static int dfs(int u, boolean[] bool, List<List<Integer>> adj) {
    bool[u] = true;
    int count = 0;

    for (int to : adj.get(u)) {
      if (!bool[Math.abs(to)]) {
        count += dfs(Math.abs(to), bool, adj) + ((to > 0) ? 1 : 0);
      }
    }
    return count;
  }

}
