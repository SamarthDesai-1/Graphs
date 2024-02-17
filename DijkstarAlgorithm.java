import java.util.*;

public class DijkstarAlgorithm {

  static class Container implements Comparable<Container> {
    int vertex;
    int dist;
    int weight;

    Container(int vertex, int dist, int weight) {
      this.vertex = vertex;
      this.weight = weight;
      this.dist = dist;
    }

    @Override
    public int compareTo(Container y) {
      return this.weight - y.weight;
    }
  }

  static int constructGraph(int[][] edges, int n, int src, int dest, int k) {
    List<List<Container>> graph = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }

    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];
      int weight = edge[2];
      graph.get(u).add(new Container(v, weight, 0));
    }
    PriorityQueue<Container> pq = new PriorityQueue<>();
    pq.add(new Container(src, 0, -1));

    int[] stop = new int[n];
    Arrays.fill(stop, Integer.MAX_VALUE);

    while (pq.size() > 0) {
      Container p = pq.remove();
      if (p.dist > k || stop[p.vertex] < p.dist) continue;

      stop[p.vertex] = p.dist;
      if (p.vertex == p.dist) return p.weight; 

      for (Container nbr : graph.get(p.vertex)) {
        pq.add(new Container(nbr.vertex, p.weight + nbr.weight, p.dist + 1));
      }
    }
    return -1;
  }


  public static void main(String[] args) {
    int[][] edges = {
        { 0, 1, 100 }, { 1, 3, 2000 }, { 2, 3, 100 }, { 2, 5, 50 }, { 3, 6, 0 }
    };
    int cheapCost = constructGraph(edges, 6, 0, 2, 2);

    System.out.println(cheapCost);
  }

}
