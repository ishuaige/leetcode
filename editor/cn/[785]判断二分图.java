
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    boolean ok = true;
    boolean[] visited;
    boolean[] color;

    public boolean isBipartite(int[][] graph) {
        visited = new boolean[graph.length];
        color = new boolean[graph.length];
        for(int i = 0;i < graph.length;i++){
            if(!visited[i]){
                traverse(graph,i);
            }
        }
        return ok;
    }

    void traverse(int[][] graph,int v){
        if(!ok) return;
        visited[v] = true;
        for(int s:graph[v]){
            if(!visited[s]){
                color[s] = !color[v];
                traverse(graph,s);
            }else {
                if(color[s] == color[v]){
                    ok = false;
                    return;
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
