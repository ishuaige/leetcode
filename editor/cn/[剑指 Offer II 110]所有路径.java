import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        LinkedList<Integer> path = new LinkedList<>();
        traverse(graph,0,path);
        return res;
    }

    List<List<Integer>> res = new LinkedList<>();
    void traverse(int[][] graph,int v,LinkedList<Integer> path){
        path.addLast(v);
        int n = graph.length;
        if(n-1==v){
            res.add(new LinkedList<>(path));
            path.removeLast();
            return;
        }
        for(int i : graph[v]){
            traverse(graph,i,path);
        }
        path.removeLast();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
