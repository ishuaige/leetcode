import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    LinkedList<Integer> postorder = new LinkedList<>();
    boolean[] onPath;   //记录节点是否在路径上
    boolean[] visited;  //记录节点是否已经访问过
    boolean hasCycle = false;   //是否有环

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        onPath = new boolean[numCourses];
        visited = new boolean[numCourses];
        //不一定所有节点都在图中，所以全都要遍历一下
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }
        if (hasCycle) {
            return new int[]{};
        }
        //反转拓扑排序结果
        Collections.reverse(postorder);
        int[] res = new int[numCourses];
        for (int i = 0; i < postorder.size(); i++) {
            res[i] = postorder.get(i);
        }
        return res;
    }

    /**
     * 根据题目条件构造出图
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new LinkedList[numCourses];
        //初始化
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            graph[from].add(to);
        }
        return graph;
    }


    void traverse(List<Integer>[] graph, int s) {
        if (onPath[s]) {
            hasCycle = true;
        }
        //已经存在环了则不用继续遍历了
        if (visited[s] || hasCycle) {
            return;
        }
        visited[s] = true;
        onPath[s] = true;
        for (int i : graph[s]) {
            traverse(graph, i);
        }
        //后序遍历位置
        //拓扑排序就是后序遍历结果反转
        postorder.add(s);
        onPath[s] = false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
