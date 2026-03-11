import java.util.*;

public class DetectCycleGraph {

    static boolean dfs(int node, boolean[] visited, boolean[] stack, List<List<Integer>> graph){

        if(stack[node]) return true;
        if(visited[node]) return false;

        visited[node] = true;
        stack[node] = true;

        for(int neighbor : graph.get(node)){

            if(dfs(neighbor, visited, stack, graph))
                return true;
        }

        stack[node] = false;

        return false;
    }

    public static boolean hasCycle(int V, List<List<Integer>> graph){

        boolean[] visited = new boolean[V];
        boolean[] stack = new boolean[V];

        for(int i=0;i<V;i++){

            if(dfs(i, visited, stack, graph))
                return true;
        }

        return false;
    }

    public static void main(String[] args){

        int V = 4;

        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0;i<V;i++)
            graph.add(new ArrayList<>());

        graph.get(0).add(1);
        graph.get(1).add(2);
        graph.get(2).add(0);

        System.out.println("Cycle present: " + hasCycle(V, graph));
    }
}
