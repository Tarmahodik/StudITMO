package itmo.course3.algorithms.lab4;


import java.util.LinkedList;

public class Main {
    //number of points
    static final int V = 6;

    private static boolean bfs(int rGraph[][], int s, int t, int parent[]) {
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;

        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (visited[v] == false && rGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        return (visited[t] == true);
    }


    public static int fordFulkerson(int graph[][], int s, int t) {
        int u, v;

        int rGraph[][] = graph;

        int parent[] = new int[V];

        int max_flow = 0;

        while (bfs(rGraph, s, t, parent)) {
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }

            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            max_flow += path_flow;
        }

        return max_flow;
    }

    public static void main(String[] args) throws Exception {
        int graph[][] = new int[][]{
                {0, 10, 0, 0, 0, 0},
                {0, 0, 9, 4, 0, 2},
                {0, 9, 0, 0, 6, 0},
                {0, 4, 0, 0, 0, 0},
                {0, 0, 6, 11, 0, 8},
                {0, 0, 0, 0, 0, 0}
        };


        System.out.println("The maximum possible flow is " +
                fordFulkerson(graph, 0, 5));

    }
}
