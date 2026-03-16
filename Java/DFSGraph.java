import java.util.*;

public class DFSGraph {

    static void dfs(int node, boolean visited[], List<Integer> adj[]){

        visited[node] = true;
        System.out.print(node + " ");

        for(int neighbor : adj[node]){

            if(!visited[neighbor])
                dfs(neighbor, visited, adj);
        }
    }

    public static void main(String[] args) {

        int V = 5;

        List<Integer> adj[] = new ArrayList[V];

        for(int i=0;i<V;i++)
            adj[i] = new ArrayList<>();

        adj[0].add(1);
        adj[0].add(2);
        adj[1].add(3);
        adj[2].add(4);

        boolean visited[] = new boolean[V];

        dfs(0, visited, adj);
    }
}
