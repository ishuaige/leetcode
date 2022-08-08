import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    boolean[] visited ;
    boolean[] color;
    boolean ok = true;

    public boolean isBipartite(int[][] graph) {
        visited = new boolean[graph.length];
        color = new boolean[graph.length];

        for(int i= 0;i<graph.length;i++){
            if(!visited[i])
            bfs(graph,i);
        }
        return ok;
    }

    void traverse(int[][] graph,int start){
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;

        q.offer(start);
        while (!q.isEmpty() && ok){
            int v = q.poll();
            for(int w : graph[v]){
                if(!visited[w]){
                    color[w] = !color[v];
                    visited[w] = true;
                    q.offer(w);
                }else {
                    if(color[w] == color[v]){
                        ok = false;
                    }
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
