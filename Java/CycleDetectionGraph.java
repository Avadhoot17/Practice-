import java.util.*;

class CycleDetectionGraph {

    private int V;
    private LinkedList<Integer>[] adj;

    CycleDetectionGraph(int v) {
        V = v;
        adj = new LinkedList[v];

        for(int i=0;i<v;i++)
            adj[i] = new LinkedList<>();
    }

    void addEdge(int v,int w){
        adj[v].add(w);
    }

    boolean isCyclicUtil(int v, boolean visited[], boolean stack[]) {

        if(stack[v])
            return true;

        if(visited[v])
            return false;

        visited[v] = true;
        stack[v] = true;

        for(int n : adj[v]) {
            if(isCyclicUtil(n, visited, stack))
                return true;
        }

        stack[v] = false;
        return false;
    }

    boolean isCyclic(){

        boolean visited[] = new boolean[V];
        boolean stack[] = new boolean[V];

        for(int i=0;i<V;i++)
            if(isCyclicUtil(i, visited, stack))
                return true;

        return false;
    }

    public static void main(String args[]){

        CycleDetectionGraph g = new CycleDetectionGraph(4);

        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(2,0);
        g.addEdge(2,3);

        System.out.println("Cycle Present: " + g.isCyclic());
    }
}
