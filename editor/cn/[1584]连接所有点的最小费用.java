import java.util.ArrayList;
import java.util.Collections;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        //构建所有边及权重
        ArrayList<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int xi = points[i][0];
                int yi = points[i][1];
                int xj = points[j][0];
                int yj = points[j][1];
                int vali2j = Math.abs(xi - xj) + Math.abs(yi - yj);
                edges.add(new int[]{i, j, vali2j});
            }
        }
        //将所有边按权重从小到大排序
        Collections.sort(edges, (a, b) -> {
            return a[2] - b[2];
        });
        //执行Kruskal算法
        UF uf = new UF(n);
        int mst = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            //如果会形成环，则不加入
            if (uf.connected(u, v)) {
                continue;
            }
            uf.union(u, v);
            mst += weight;
        }
        return mst;
    }
}

class UF {

    private int[] parents; // 存储⼀棵树
    private int count; // 连通分量个数
    private int[] size; //一棵树的重量

    public UF(int count) {
        this.count = count;
        parents = new int[count];
        size = new int[count];
        for (int i = 0; i < count; i++) {
            parents[i] = i;
            size[i] = 1;
        }
    }

    //将两个节点连接起来
    public void union(int a, int b) {
        int i = find(a);
        int j = find(b);
        if (i == j) {
            return;
        }
        // 小树接到大树下面，较平衡
        if (size[j] > size[i]) {
            parents[i] = j;
            size[j] += size[i];
        } else {
            parents[j] = i;
            size[i] += size[j];
        }
        // 两个连通分量合并成一个连通分量
        count--;
    }

    //判断两个节点是否连接
    public boolean connected(int a, int b) {
        int i = find(a);
        int j = find(b);
        if (i == j) {
            return true;
        }
        return false;
    }

    //找到某一结点的根节点
    public int find(int n) {
        while (parents[n] != n) {
            // 进行路径压缩
            parents[n] = parents[parents[n]];
            n = parents[n];
        }
        return n;
    }

    public int count() {
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
