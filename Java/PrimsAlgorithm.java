import java.util.*;

class PrimsAlgorithm {

    static void primMST(int[][] graph) {

        int V = graph.length;

        int[] key = new int[V];
        boolean[] mst = new boolean[V];

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;

        for(int count=0; count<V-1; count++){

            int u = -1;

            for(int i=0;i<V;i++)
                if(!mst[i] && (u==-1 || key[i] < key[u]))
                    u = i;

            mst[u] = true;

            for(int v=0; v<V; v++){

                if(graph[u][v] != 0 && !mst[v] && graph[u][v] < key[v])
                    key[v] = graph[u][v];
            }
        }

        System.out.println("Edges in MST:");

        for(int i=1;i<V;i++)
            System.out.println("0 - "+ i +" weight "+ key[i]);
    }

    public static void main(String args[]) {

        int graph[][] = {
            {0,2,0,6,0},
            {2,0,3,8,5},
            {0,3,0,0,7},
            {6,8,0,0,9},
            {0,5,7,9,0}
        };

        primMST(graph);
    }
}
