import java.util.*;

public class Implementation {

    public static class Edge {
        int source;
        int destination;
        int weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] isvisited) {
        if (src == dest)
            return true;
        isvisited[src] = true;
        for (Edge edge : graph[src]) {
            if (isvisited[edge.destination] == false) {
                boolean hasNeighBourPath = hasPath(graph, edge.destination, dest, isvisited);
                if (hasNeighBourPath == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void printAllPaths(ArrayList<Edge>[] graph, int src, int dest, boolean[] isvisited, String paths) {
        if (src == dest) {
            System.out.println(paths);
            return;
        }
        isvisited[src] = true;
        for (Edge edge : graph[src]) {
            if (isvisited[edge.destination] == false)
                printAllPaths(graph, edge.destination, dest, isvisited, paths + " " + edge.destination);
        }
        isvisited[src] = false;
    }

    public static String SHORTESTPATH;
    public static Integer shortestPathWeight = Integer.MAX_VALUE;
    public static String LONGESTPATH;
    public static Integer longestPathWeight = Integer.MIN_VALUE;

    public static void bestPathFinder(ArrayList<Edge>[] graph, int src, int dest, boolean[] isvisited, String paths,
            int weight) {
        if (src == dest) {
            if (weight < shortestPathWeight) {
                SHORTESTPATH = paths;
                shortestPathWeight = weight;
            }
            if (weight > longestPathWeight) {
                LONGESTPATH = paths;
                longestPathWeight = weight;
            }
            return;
        }
        isvisited[src] = true;
        for (Edge edge : graph[src]) {
            if (isvisited[edge.destination] == false)
                bestPathFinder(graph, edge.destination, dest, isvisited, paths + " " + edge.destination,
                        weight + edge.weight);
        }
        isvisited[src] = false;
    }

    public static void main(String[] args) {
        int vertexes = 7; /* 0 1 2 3 4 5 6 */
        int src = 0, dest = 4;
        ArrayList<Edge>[] graph = new ArrayList[7];

        for (int i = 0; i < vertexes; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 1, 10));
        graph[0].add(new Edge(0, 3, 20));

        graph[1].add(new Edge(1, 0, 10));
        graph[1].add(new Edge(1, 2, 30));

        graph[2].add(new Edge(2, 3, 40));
        graph[2].add(new Edge(2, 1, 30));

        graph[3].add(new Edge(3, 4, 50));
        graph[3].add(new Edge(3, 2, 40));
        graph[3].add(new Edge(3, 0, 20));

        graph[4].add(new Edge(4, 3, 50));
        graph[4].add(new Edge(4, 2, 60));

        /* Graph contains path from given source to destination */ {

            boolean[] isvisited = new boolean[vertexes];
            boolean containsPath = hasPath(graph, src, dest, isvisited);

            System.out.println(containsPath);

        }

        System.out.println();

        /* Print all paths from source to destination */ {
            boolean[] isvisited = new boolean[vertexes];
            printAllPaths(graph, src, dest, isvisited, src + "");
        }

        System.out.println();

        /* Shortest Longest Path in Graph */ {
            
            boolean[] isvisited = new boolean[vertexes];
            bestPathFinder(graph, src, dest, isvisited, src + "", 0);

            System.out.println("Shortest PATH : " + SHORTESTPATH);
            System.out.println("Shortest Path Weight : " + shortestPathWeight);
            System.out.println("Longest PATH : " + LONGESTPATH);
            System.out.println("Longest Path Weight : " + longestPathWeight);

        }

    }
}