
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int max_k = 2;

        int[][][] dp = new int[n][max_k + 1][2];

        for (int i = 0; i < n; i++) {
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k-1][0] - prices[i]);
            }
        }
        return dp[n - 1][max_k][0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
