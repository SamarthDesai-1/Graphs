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

    public static void DFS(ArrayList<Edge>[] graph, int src, boolean[] isvisited) {
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
            if (isvisited[edge.destination] == false) {
                isConnected(graph, edge.destination, isvisited, list);
            }
        }
    }

    public static void countIslands(int[][] matrix, boolean[][] isvisited, int i, int j) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length || matrix[i][j] == 1 || isvisited[i][j] == true)
            return;
        isvisited[i][j] = true;
        countIslands(matrix, isvisited, i - 1, j);
        countIslands(matrix, isvisited, i + 1, j);
        countIslands(matrix, isvisited, i, j + 1);
        countIslands(matrix, isvisited, i, j - 1);
    }

    public static void calculatePerimeter(int[][] matrix, int i, int j, int[] perimeter) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length || matrix[i][j] == 0) {
            perimeter[0]++;
            return;
        }

        /*

            Below conditional increase the perimeter value by one when RECURSION CALL goes outside of matrix and found water 

        */

        if (matrix[i][j] == -1) return;

        matrix[i][j] = -1;
        calculatePerimeter(matrix, i - 1, j, perimeter);
        calculatePerimeter(matrix, i + 1, j, perimeter);
        calculatePerimeter(matrix, i, j + 1, perimeter);
        calculatePerimeter(matrix, i, j - 1, perimeter);
    }

    public static void getComponent(ArrayList<Edge>[] graph, int src, boolean[] isvisited, ArrayList<Integer> list) {
        isvisited[src] = true;
        list.add(src);
        for (Edge edge : graph[src]) {
            if (isvisited[edge.destination] == false) {
                getComponent(graph, edge.destination, isvisited, list);
            }
        }
    }

    public static int SUM = 0;
    public static void sumOfNodes(ArrayList<Edge>[] graph, int src, boolean[] isvisited) {
        isvisited[src] = true;

        for (Edge edge : graph[src]) {
            if (isvisited[edge.destination] == false) {
                SUM += edge.destination;
                sumOfNodes(graph, edge.destination, isvisited);
            }
        }
    }

    public static void maxArea(int[][] matrix, int row, int col, int[] area) {
        if (row < 0 || col < 0 || row == matrix.length || col == matrix[0].length || matrix[row][col] == 0) {
            return;
        }
        area[0]++;
        matrix[row][col] = 0;
        maxArea(matrix, row - 1, col, area);
        maxArea(matrix, row + 1, col, area);
        maxArea(matrix, row, col + 1, area);
        maxArea(matrix, row, col - 1, area);

    }

    public static boolean isCyclic(ArrayList<Edge>[] graph ,boolean[] isvisited ,int src) {
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(src, src + "" ,0));

        while (q.size() > 0) {
            Pair temp = q.remove();

            if (isvisited[temp.vertex] == true) {
                return true;
            }
            isvisited[temp.vertex] = true;

            for (Edge edge : graph[temp.vertex]) {
                if (isvisited[edge.destination] == false) {
                    q.add(new Pair(edge.destination, temp.path + edge.destination ,0));
                }
            }
        }
        return false;
    }

    public static boolean isBipartite(ArrayList<Edge>[] graph ,int src ,int[] isvisited) {
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(src, src + "", 0));

        while (q.size() > 0) {
            Pair temp = q.remove();

            if (isvisited[temp.vertex] != -1) {
                if (temp.level != isvisited[temp.vertex]) {
                    return false;
                }
            } else {
                isvisited[temp.vertex] = temp.level;
            }

            for (Edge edge : graph[temp.vertex]) {
                if (isvisited[edge.destination] == -1) {
                    q.add(new Pair(edge.destination, temp.path + edge.destination, temp.level + 1));
                }
            }

        }
        return true;
    }

    public static ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    public static void allPaths(ArrayList<Edge>[] graph ,boolean[] isvisited ,int src ,int dest ,String path ,ArrayList<Integer> list) {
        if (src == dest) {
            list.add(dest);
            ans.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }
        isvisited[src] = true;
        list.add(src);        
        for (Edge edge : graph[src]) {
            if (isvisited[edge.destination] == false) {
                allPaths(graph, isvisited, edge.destination, dest, path + " " + edge.destination, list);
            }
        }
        list.remove(list.size() - 1);
        isvisited[src] = false;
    
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
        graph[3].add(new Edge(3, 4, 75));
        graph[3].add(new Edge(3, 5, 85));

        graph[4].add(new Edge(4, 3, 50));
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
                    isConnected(graph, i, isvisited, list);
                    component.add(list);
                }
            }

            System.out.println(component);

        }

        /* Count Number of Islands */ {

            int row = 8;
            int col = 8;
            int count = 0;
            int[][] matrix = {
                { 0, 0, 1, 1, 1, 1, 1, 1 },
                { 0, 0, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 0 },
                { 1, 1, 0, 0, 0, 1, 1, 0 },
                { 1, 1, 1, 1, 0, 1, 1, 0 },
                { 1, 1, 1, 1, 0, 1, 1, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 0 }
            };
            boolean[][] isvisited = new boolean[row][col];

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] == 0 && isvisited[i][j] == false) {
                        countIslands(matrix, isvisited, i, j);
                        count++;
                    }
                }
            }
            System.out.println(count);

        }

        /* SUM of all edges OPTIMIZED APPROACH */ {

            boolean[] isvisited = new boolean[vertexes];
            for (int i = 0; i < vertexes; i++) {
                if (isvisited[i] == false) {
                    sumOfNodes(graph, i, isvisited);
                }
            }
            System.out.println("Sum of all NODES : " + SUM);

        }

        /* Perfect Friends */ {

            int v = 7;
            int e = 5;
            Scanner sc = new Scanner(System.in);

            ArrayList<Edge>[] g = new ArrayList[v];
            for (int i = 0; i < v; i++) {
                g[i] = new ArrayList<>();
            }

            // for (int i = 0; i < e; i++) {
            // int v1 = sc.nextInt();
            // int v2 = sc.nextInt();
            // g[v1].add(new Edge(v1, v2, 1));
            // g[v1].add(new Edge(v2, v1, 1));
            // }

            g[0].add(new Edge(0, 1, 1));
            g[1].add(new Edge(1, 0, 1));

            g[2].add(new Edge(2, 3, 1));
            g[3].add(new Edge(3, 2, 1));

            g[4].add(new Edge(4, 5, 1));
            g[4].add(new Edge(4, 6, 1));

            g[5].add(new Edge(5, 6, 1));

            g[6].add(new Edge(6, 4, 1));
            g[6].add(new Edge(6, 5, 1));

            boolean[] isvisited = new boolean[v];
            ArrayList<ArrayList<Integer>> component = new ArrayList<>();
            for (int i = 0; i < v; i++) {
                if (isvisited[i] == false) {
                    ArrayList<Integer> al = new ArrayList<>();
                    getComponent(g, i, isvisited, al);
                    component.add(al);
                }
            }

            int pairs = 0;
            for (int i = 0; i < component.size(); i++) {
                for (int j = i + 1; j < component.size(); j++) {
                    pairs = pairs + (component.get(i).size() * component.get(j).size());
                }
            }
            System.out.println(component);
            System.out.println(pairs);

        }

        /* Max area of island */ {

            int[][] matrix = {
                { 0, 0, 0, 0, 0 },
                { 0, 1, 0, 1, 0 },
                { 0, 1, 1, 1, 0 },
                { 1, 0, 0, 1, 0 },
                { 1, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0 }
            };
            int ans = 0;

            for (int i = 0; i < matrix.length; i++) {

                /*
                    1 - represents land
                    0 - represents water
                 */

                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] == 1) {
                        int[] area = new int[1];
                        maxArea(matrix, i, j, area);
                        ans = Math.max(ans, area[0]);
                    }
                }
            }
            System.out.println(ans);

        }

        /* Island Perimeter */ {

            int[][] matrix = {
                { 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 0 }
            };
            int peri = 0;

            for (int i = 0; i < matrix.length; i++) {

                /*
                    1 - represents land
                    0 - represents water
                */

                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == 1) {
                        int[] perimeter = new int[1];
                        calculatePerimeter(matrix, i, j, perimeter);
                        peri = perimeter[0];
                        break;
                    }
                }
            }

            System.out.println(peri);

        }

        /* All paths from source to target leetcode -> 797 */ {

            int v = 4;
            ArrayList<Edge>[] g = new ArrayList[v];

            for (int i = 0; i < g.length; i++) {
                g[i] = new ArrayList<>();
            }

            g[0].add(new Edge(0, 1, 1));
            g[0].add(new Edge(0, 2, 1));

            g[1].add(new Edge(1, 0, 1));
            g[1].add(new Edge(1, 3, 1));

            g[2].add(new Edge(2, 0, 1));
            g[2].add(new Edge(2, 3, 1));

            g[3].add(new Edge(3, 1, 1));
            g[3].add(new Edge(3, 2, 1));

            boolean[] isvisited = new boolean[v];

            allPaths(g, isvisited, 0 ,v - 1 ,src + "" ,new ArrayList<>());
            System.out.println("kjbgjkrbgklnrlvgerb");
            System.out.println(ans);
            ans = new ArrayList<>();


        }

        System.out.println();

        /* Iterative BFS */ {

            System.out.println("BFS : Breadth First Search");
    
            Queue<Pair> q = new ArrayDeque<>();
            boolean[] isvisited = new boolean[vertexes];
            q.add(new Pair(src, src + "" ,0));
            while (q.size() > 0) {
                Pair temp = q.remove();
                if (isvisited[temp.vertex] == true) {
                    continue;
                }
                isvisited[temp.vertex] = true;

                System.out.println(temp.vertex + " @ " + temp.path);

                for (Edge edge : graph[temp.vertex]) {
                    if (isvisited[edge.destination] == false) {
                        q.add(new Pair(edge.destination, temp.path + " " + edge.destination ,0));
                    }
                }
            }

        }

        /* Is a cyclic graph */ {

            boolean[] isvisited = new boolean[vertexes];
            for (int i = 0; i < vertexes; i++) {
                if (isvisited[i] == false) {
                    boolean isCyclic = isCyclic(graph, isvisited, i);
                    if (isCyclic) {
                        System.out.println(true);
                        break; /* return */
                    }
                }
            } 
            System.out.println(false);

        }

        System.out.println();

        /* Is Graph Bipartite */ {

            int[] isvisited = new int[vertexes];
            Arrays.fill(isvisited ,-1);
            for (int i = 0; i < vertexes; i++) {
                if (isvisited[i] == -1) {
                    if (isBipartite(graph ,i ,isvisited)) {
                        System.out.println(true);
                        break; /* return */
                    }
                }
            }

            System.out.println(false);

        }

        /* Spread Infection in a Graph */  {

            ArrayDeque<INFECTION> q = new ArrayDeque<>();
            int[] isvisited = new int[vertexes];
            q.add(new INFECTION(1, 1));
            int count = 0;
            int t = 3;

            while (q.size() > 0) {
                INFECTION rem = q.remove();

                if (isvisited[rem.v] > 0) continue;
                isvisited[rem.v] = rem.time;

                if (rem.time > t) break;
                count++;
                for (Edge edge : graph[rem.v]) {
                    if (isvisited[edge.destination] == 0) {
                        q.add(new INFECTION(edge.destination, rem.time + 1));
                    }
                }
            }
            System.out.println(count);

        }

        System.out.println();

        /* Dijkstra Algorithm */ {

            /*
            
                Dijkstra algorithm is same as BFS but the only dufference is BFS use Simple QUEUE instead DIJKSTRA use Priority QUEUE.
                
            */

            System.out.println("DIJKSTRA Algorithm.");
            PriorityQueue<DIJKSTRA> pq = new PriorityQueue<>();
            pq.add(new DIJKSTRA(src, src + "", 0));
            boolean[] isvisited = new boolean[vertexes];

            while (pq.size() > 0) {
                DIJKSTRA temp = pq.remove();
                if (isvisited[temp.v] == true) continue;
                isvisited[temp.v] = true;
                
                System.out.println("[ " + temp.v + " [ via ] " + temp.path + " --> " + temp.weight + " km " + " ]");

                for (Edge edge : graph[temp.v]) {
                    if (isvisited[edge.destination] == false) {
                        pq.add(new DIJKSTRA(edge.destination, temp.path + " " + edge.destination, temp.weight + edge.weight));
                    }
                }
            }

        }

        System.out.println();

        /* Prim's Algorithm (Minumum Spanning Tree) */ {

            System.out.println("PRIM'S Algorithm.");
            PriorityQueue<PRIMS> pq = new PriorityQueue<>();
            pq.add(new PRIMS(0, -1, 0));
            boolean[] isvisited = new boolean[vertexes];

            while (pq.size() > 0) {
                PRIMS temp = pq.remove();

                if (isvisited[temp.vertex] == true) continue;
                else isvisited[temp.vertex] = true;

                if (temp.aquiringVertex != -1)
                    System.out.println("[ " + temp.vertex + " " + temp.aquiringVertex + " " + temp.weight + " ]");
                
                for (Edge edge : graph[temp.vertex]) {
                    if (isvisited[edge.destination] == false) {
                        pq.add(new PRIMS(edge.destination, temp.vertex, edge.weight));
                    }
                }
            }

        }

        System.out.println();

        /* TOPOLOGICAL SORT Apply on Directed Graph */ {

            int v = 7;
            Stack<Integer> st = new Stack<>();
            boolean[] isvisited = new boolean[v];

            ArrayList<Edge>[] g = new ArrayList[v];
            for (int i = 0; i < v; i++) {
                g[i] = new ArrayList<>();
            }

            g[0].add(new Edge(0, 1, 1));
            g[0].add(new Edge(0, 3, 1));

            g[1].add(new Edge(1, 2, 1));

            g[2].add(new Edge(2, 3, 1));
            
            g[4].add(new Edge(4, 3, 1));
            g[4].add(new Edge(4, 5, 1));
            g[4].add(new Edge(4, 6, 1));

            g[5].add(new Edge(5, 6, 1));

            for (int i = 0; i < v; i++) {
                if (isvisited[i] == false) {
                    topologicalSort(g ,i ,isvisited ,st);
                }
            }
            while (st.size() > 0) {
                System.out.print(st.pop() + " ");
            }
            
        }

        System.out.println();

        /* Iterative DFS Depth First Search */ {

            System.out.println();
            System.out.println("Iterative Depth First Search");
            Stack<DFS> st = new Stack<>();
            st.push(new DFS(0, src + ""));
            boolean[] isvisited = new boolean[vertexes];
            while (st.size() > 0) {
                DFS temp = st.pop();

                if (isvisited[temp.vertex] == true) continue; 
                else isvisited[temp.vertex] = true;

                System.out.println(temp.vertex + " @ " + temp.path);

                for (Edge edge : graph[temp.vertex]) {
                    if (isvisited[edge.destination] == false) {
                        st.push(new DFS(edge.destination ,temp.path + " " + edge.destination));
                    }
                }
            }

        }
    }

    public static void topologicalSort(ArrayList<Edge>[] graph ,int src ,boolean[] isvisited ,Stack<Integer> st) {
        isvisited[src] = true;
        for (Edge edge : graph[src]) {
            if (isvisited[edge.destination] == false) {
                topologicalSort(graph, edge.destination, isvisited ,st);
            }
        }
        st.push(src);
    }

    
    public static class Pair {

        int vertex;
        String path;
        int level;

        /*
            I add level variable for only isBipartite problem and for other problem i pass level variable as 0  
        */

        Pair(int vertex ,String path ,int level) {
            this.vertex = vertex;
            this.path = path;
            this.level = level;
        }

    }

    public static class INFECTION {

        int v;
        int time;

        INFECTION(int v ,int time) {
            this.v = v;
            this.time = time;
        }

    }

    public static class DIJKSTRA implements Comparable<DIJKSTRA> {

        int v;
        String path;
        int weight;

        DIJKSTRA(int v ,String path ,int weight) {
            this.v = v;
            this.path = path;
            this.weight = weight;
        }

        @Override
        public int compareTo(DIJKSTRA x) {
            return this.weight - x.weight;
        }

    }

    public static class PRIMS implements Comparable<PRIMS> {
        int vertex;
        int aquiringVertex;
        int weight;

        PRIMS(int v ,int av ,int w) {
            this.vertex = v;
            this.aquiringVertex = av;
            this.weight = w;
        }

        @Override
        public int compareTo(PRIMS x) {
            return this.weight - x.weight;
        }
    }

    public static class DFS {

        int vertex;
        String path;

        DFS(int vertex ,String path) {
            this.vertex = vertex;
            this.path = path;
        }

    }

}

