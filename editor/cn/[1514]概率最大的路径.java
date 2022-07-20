import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        LinkedList<double[]>[] graph = new LinkedList[n];
        for(int i = 0;i < n; i++){
            graph[i] = new LinkedList<>();
        }
        for(int i = 0;i<edges.length;i++){
           int from = edges[i][0];
           int to = edges[i][1];
           double weight = succProb[i];
           graph[from].add(new double[]{(double) to,weight});
           graph[to].add(new double[]{(double) from,weight});
        }
       return dijkstra(graph,start,end);
    }

    double dijkstra(List<double[]>[] graph,int start,int end){
        double[] probTo = new double[graph.length];
//        for(int i =0;i<probTo.length;i++){
//            probTo[i] = -1;
//        }
        Arrays.fill(probTo,-1);
        probTo[start] = 1;

        Queue<State> pq = new PriorityQueue<>((a,b)->{
           return Double.compare(b.probFromStart, a.probFromStart);
        });
        pq.offer(new State(start,1));

        while(!pq.isEmpty()){
           State curNode = pq.poll();
           int curNodeId = curNode.id;
           double curProbFromStart = curNode.probFromStart;
           if(curNodeId == end){
               return curProbFromStart;
           }

           if(curProbFromStart < probTo[curNodeId]){
               continue;
           }

           for( double[] neighbor : graph[curNodeId] ){
              int nextToNode = (int) neighbor[0];
              double probToNextNode = neighbor[1] * curProbFromStart;
              if(probToNextNode > probTo[nextToNode]){
                  probTo[nextToNode] = probToNextNode;
                  pq.offer(new State(nextToNode,probToNextNode));
              }
           }
        }
        return 0.0;
    }
}
class State{
    int id ;
    double probFromStart;

    public State(int id, double probFromStart) {
        this.id = id;
        this.probFromStart = probFromStart;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
