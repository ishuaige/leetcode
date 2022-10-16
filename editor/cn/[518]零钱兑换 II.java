
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int change(int amount, int[] coins) {
        //完全背包问题
        //dp[i][n]定义：前i个coin能凑成n数值的组合数
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j <= amount; j++) {
                //如果可以选择
                if (j - coins[i - 1] >= 0) {
                    //这里有两种情况
                    //  1. 前i-1个能够凑成amount
                    //  2. 前i个凑成j-coins[i-1] 然后加上我凑成amount
                    dp[i][j] = dp[i][j - coins[i - 1]]
                            + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[coins.length][amount];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
