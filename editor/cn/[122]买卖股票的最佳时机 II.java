
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int[][] dp = new int[n][2];

        for(int i = 0 ; i < n;i++){
            if(i - 1 == -1){
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
          /*
            dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
            dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
                        = max(dp[i-1][k][1], dp[i-1][k][0] - prices[i])
            我们发现数组中的 k == k-1 （K为正无穷）已经不会改变了，也就是说不需要记录 k 这个状态了：
            dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])

           */
            dp[i][0] = Math.max( dp[i-1][0] , dp[i-1][1] + prices[i] );
            dp[i][1] = Math.max( dp[i-1][1] , dp[i-1][0] - prices[i] );
        }

        return dp[n - 1][0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
