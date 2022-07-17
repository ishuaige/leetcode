import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        int[][] effortTo = new int[m][n];

        for(int i = 0;i < m;i++){
            Arrays.fill(effortTo[i],Integer.MAX_VALUE);
        }
        effortTo[0][0] = 0;
        Queue<State> pq = new PriorityQueue<>((a,b)->{
            return a.effortFromStart-b.effortFromStart;
        });
        pq.offer(new State(0,0,0));

        while (!pq.isEmpty()){
           State curState = pq.poll();
           int curX = curState.x;
           int curY = curState.y;
           int curEffortFromStart = curState.effortFromStart;

           if(curX == m-1 && curY == n-1){
               return curEffortFromStart;
           }

           if(curEffortFromStart > effortTo[curX][curY]){
               continue;
           }

           for(int[] neighbor: adj(heights,curX,curY)){
              int neighborX = neighbor[0];
              int neighborY = neighbor[1];
              int effortToNextNode = Math.max(
                      effortTo[curX][curY],
                      Math.abs(heights[neighborX][neighborY]-heights[curX][curY]));
              if(effortTo[neighborX][neighborY] > effortToNextNode){
                  effortTo[neighborX][neighborY] = effortToNextNode;
                  pq.offer(new State(neighborX,neighborY,effortToNextNode));
              }
           }
        }
        return -1;

    }

    //坐标上下左右偏移量
    int[][] dirs = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    //函数adj返回相应坐标的上下左右邻居坐标
    List<int[]> adj(int[][] matrix,int x,int y){
        int m = matrix.length;
        int n = matrix[0].length;
        List<int[]> neighbor = new ArrayList<>();
        for(int[] dir : dirs){
            int nx = x + dir[0];
            int ny = y + dir[1];
            if(nx >= m || nx < 0 || ny >= n || ny < 0){
                continue;
            }
            neighbor.add(new int[]{nx,ny});
        }
        return neighbor;
    }
}
class State {
    // 矩阵中的⼀个位置
    int x, y;
    // 从起点 (0, 0) 到当前位置的最小体力消耗（距离）
    int effortFromStart;
    State(int x, int y, int effortFromStart) {
        this.x = x;
        this.y = y;
        this.effortFromStart = effortFromStart;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
