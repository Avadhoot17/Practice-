import java.util.*;
x x x x x x
public class BFSGraph {

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

        Queue<Integer> q = new LinkedList<>();

        q.add(0);
        visited[0] = true;

        while(!q.isEmpty()){

            int node = q.poll();
            System.out.print(node + " ");

            for(int neighbor : adj[node]){

                if(!visited[neighbor]){
                    visited[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }
    }
}
