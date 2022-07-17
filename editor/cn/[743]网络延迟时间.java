import sun.security.util.ArrayUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        LinkedList<int[]>[] graph = new LinkedList[n + 1];
        //初始化图，否则下面调用add方法会NPE
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }
        //构造图
        for(int[] edge:times){
           int from = edge[0];
           int to = edge[1];
           int weight = edge[2];
           graph[from].add(new int[]{to,weight});
        }
        //调用方法
        int[] distTo = dijkstra(k,graph);
        int res = 0;
        for(int i = 1;i<distTo.length;i++){
            if(distTo[i]==Integer.MAX_VALUE){
                return -1;
            }
            res = Math.max(res,distTo[i]);
        }
        return res;
    }

    int[] dijkstra(int start,List<int[]>[] graph){
        //记录起点到当前节点的最短路径 -- distTo[i] 起点start到i的最短路径权重
        int[] distTo = new int[graph.length];
        //初始化全部为最大值,起点为0值
        Arrays.fill(distTo,Integer.MAX_VALUE);
        distTo[start] = 0;

        //优先级队列，将distFromState小的放前面
        Queue<State> pq = new PriorityQueue<>((a,b)->{
            return a.distFromStart-b.distFromStart;
        });
        //初始化把起点加到队列中
        pq.add(new State(start,0));

        while (!pq.isEmpty()){
           State curState = pq.poll();
           int curId = curState.id;
           // curDistFromStart为start节点到该节点的距离（注意：不一定为最短）
           int curDistFromStart = curState.distFromStart;
           //已经存在比当前距离更短的距离则跳过
           if(curDistFromStart > distTo[curId]){
               continue;
           }
           //遍历当前节点的所有邻居
           for(int[] neighbor : graph[curId]){
              int nextNodeId = neighbor[0];
              //当前节点到它邻居的距离
              int distToNextNode = neighbor[1]+distTo[curId];
              //如果更短，则更新并加入到队列中
              if(distTo[nextNodeId] > distToNextNode){
                  distTo[nextNodeId] = distToNextNode;
                  pq.offer(new State(nextNodeId,distToNextNode));
              }
          }
        }
        return distTo;
    }

}
class State{
    //记录节点id
    int id ;
    //记录起点到当前节点的距离
    int distFromStart;
    State(int id, int distFromStart) {
        this.id = id;
        this.distFromStart = distFromStart;
    }
}


//leetcode submit region end(Prohibit modification and deletion)
