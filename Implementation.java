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

    public static void bestPathFinder(ArrayList<Edge>[] graph, int src, int dest, boolean[] isvisited, String paths, int weight) {
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
                bestPathFinder(graph, edge.destination, dest, isvisited, paths + " " + edge.destination, weight + edge.weight);
        }
        isvisited[src] = false;
    }

    public static ArrayList<Integer> component = new ArrayList<>();
    public static void DFS(ArrayList<Edge>[] graph, int src ,boolean[] isvisited) {
        isvisited[src] = true;
        for (Edge edge : graph[src]) {
            if (isvisited[edge.destination] == false) {
                component.add(src);
                DFS(graph, edge.destination, isvisited);
            }
        }
    }

    public static void isConnected(ArrayList<Edge>[] graph, int src, boolean[] isvisited, ArrayList<Integer> list) {
        isvisited[src] = true;
        list.add(src);
        for (Edge edge : graph[src]) {
            if (isvisited[src] == false) {
                isConnected(graph, edge.destination, isvisited, list);
            }
        }
    }


    public static void main(String[] args) {
        int vertexes = 8; /* 0 1 2 3 4 5 6 7 */
        int src = 0, dest = 4;
        ArrayList<Edge>[] graph = new ArrayList[vertexes];

        for (int i = 0; i < vertexes; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 2, 10));
        graph[0].add(new Edge(0, 1, 20));

        graph[1].add(new Edge(1, 0, 25));
        graph[1].add(new Edge(1, 4, 30));
        graph[1].add(new Edge(1, 6, 35));

        graph[2].add(new Edge(2, 0, 25));
        graph[2].add(new Edge(2, 3, 15));

        graph[3].add(new Edge(3, 2, 15));
        graph[3].add(new Edge(3, 4, 15));
        graph[3].add(new Edge(3, 5, 15));

        graph[4].add(new Edge(4, 3, 5));
        graph[4].add(new Edge(4, 7, 5));
        graph[4].add(new Edge(4, 5, 5));

        graph[5].add(new Edge(5, 3, 85));
        graph[5].add(new Edge(5, 4, 95));
        graph[5].add(new Edge(5, 6, 5));

        graph[6].add(new Edge(6, 1, 35));
        graph[6].add(new Edge(6, 7, 35));
        graph[6].add(new Edge(6, 5, 35));

        graph[7].add(new Edge(7, 4, 85));
        graph[7].add(new Edge(7, 6, 75));


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

        System.out.println();

        /* DFS */ {
            
            boolean[] isvisited = new boolean[vertexes];
            
            for (int i = 0; i < vertexes; i++) {
                if (isvisited[i] == false) {
                    DFS(graph, i, isvisited);
                }
            }

            System.out.println("DFS : " + component);

        }

        System.out.println();

        /* Display Graph */ {

            for (int i = 0; i < vertexes; i++) {
                ArrayList<Edge> al = graph[i];
                for (int j = 0; j < al.size(); j++) {
                    Edge e = al.get(j);
                    System.out.println(i + " -> " + e.source + " " + e.destination);
                }
                System.out.println();
            }

        }

        /* Graph is connected or not */ {
            boolean[] isvisited = new boolean[vertexes];
            ArrayList<ArrayList<Integer>> component = new ArrayList<>();
            for (int i = 0; i < vertexes; i++) {
                if (isvisited[i] == false) {
                    ArrayList<Integer> list = new ArrayList<>();
                    isConnected(graph ,i ,isvisited ,list);
                    component.add(list);
                }
            }

            System.out.println(component);
        }

        

    }
}