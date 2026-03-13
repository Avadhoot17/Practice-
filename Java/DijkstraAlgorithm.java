import java.util.*;

class DijkstraAlgorithm {

    static class Node implements Comparable<Node> {
        int vertex, distance;

        Node(int v, int d) {
            vertex = v;
            distance = d;
        }

        public int compareTo(Node other) {
            return this.distance - other.distance;
        }
    }

    static void dijkstra(int[][] graph, int src) {

        int V = graph.length;
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {

            int u = pq.poll().vertex;

            if (visited[u]) continue;
            visited[u] = true;

            for (int v = 0; v < V; v++) {

                if (graph[u][v] != 0 && !visited[v]) {

                    int newDist = dist[u] + graph[u][v];

                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        pq.add(new Node(v, newDist));
                    }
                }
            }
        }

        System.out.println("Shortest distances from source:");

        for (int i = 0; i < V; i++)
            System.out.println("To " + i + " = " + dist[i]);
    }

    public static void main(String[] args) {

        int graph[][] = {
            {0,4,0,0,0,0},
            {4,0,8,0,0,0},
            {0,8,0,7,0,4},
            {0,0,7,0,9,14},
            {0,0,0,9,0,10},
            {0,0,4,14,10,0}
        };

        dijkstra(graph, 0);
    }
}
