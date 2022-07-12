import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    boolean ok = true;
    boolean[] visited;
    boolean[] color;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        visited = new boolean[n+1];
        color = new boolean[n+1];
        List<Integer>[] graph = buildGraph(n,dislikes);
        for (int v = 1; v <= n; v++) {
            if (!visited[v]) {
                traverse(graph, v);
            }
        }

        return ok;

    }

    public List<Integer>[] buildGraph(int n,int[][] dislikes){
        List<Integer>[] graph = new LinkedList[n+1];
        for(int i = 1;i <= n;i++){
            graph[i] = new LinkedList<>();
        }
        for(int[] edge:dislikes){
            int v = edge[0];
            int w = edge[1];
            graph[v].add(w);
            graph[w].add(v);
        }
        return graph;
    }

    public void traverse(List<Integer>[] graph,int v){
        if(!ok) return;
        visited[v] = true;
        for(int w :graph[v]){
            if(!visited[w]){
                color[w] = !color[v];
                traverse(graph,w);
            }else {
                if(color[w] == color[v]){
                    ok = false;
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
