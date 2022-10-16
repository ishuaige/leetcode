import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    int[][] memo;

    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        memo = new int[m][n];
        for (int[] i : memo) {
            Arrays.fill(i, -1);
        }
        return dp(dungeon, 0, 0);
    }

    //dp:在dungeon数组中，从[i,j]->右下角需要的最小健康点数
    int dp(int[][] dungeon, int i, int j) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        // basecase 从终点到终点
        if (i == m - 1 && j == n - 1) {
            //大于0则返回骑士最小生命值1，小于零则需要把欠的血加上
            return dungeon[i][j] >= 0 ? 1 : -dungeon[i][j] + 1;
        }

        //下标越界
        if (i == m || j == n) {
            return Integer.MAX_VALUE;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        //
        int res = Math.min(
                dp(dungeon, i + 1, j),
                dp(dungeon, i, j + 1)
        ) - dungeon[i][j];

        //
        memo[i][j] = res <= 0 ? 1 : res;
        return memo[i][j];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
