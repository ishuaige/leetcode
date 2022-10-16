
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
            dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
            dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i])
                        = max(dp[i-1][1][1], -prices[i])
            解释：k = 0 的 base case，所以 dp[i-1][0][0] = 0。
            现在发现 k 都是 1，不会改变，即 k 对状态转移已经没有影响了。
            可以进行进一步化简去掉所有 k：
            dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp[i][1] = max(dp[i-1][1], -prices[i])
           */
          dp[i][0] = Math.max( dp[i-1][0] , dp[i-1][1] + prices[i] );
          dp[i][1] = Math.max( dp[i-1][1] ,  - prices[i] );
      }

      return dp[n - 1][0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
