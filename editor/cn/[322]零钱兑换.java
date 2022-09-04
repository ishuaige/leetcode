
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
//    =========================================================
    /*
    int[] memo ;
    public int coinChange(int[] coins, int amount) {
        memo = new int[amount + 1];
        Arrays.fill(memo, -666);
        return dp(coins,amount);
    }

    int dp(int[] coins,int amount){
        if(amount == 0) {
            return 0;
        }
        if(amount < 0){
            return -1;
        }
        if(memo[amount] != -666){
            return memo[amount];
        }
        int res = Integer.MAX_VALUE;
        for(int coin : coins){
            int sub = dp(coins,amount - coin);
            if(sub == -1){
                continue;
            }
            res = Math.min(res,sub + 1);
        }
        memo[amount] = res == Integer.MAX_VALUE? -1 : res;
        return memo[amount];
    }
    */
    //自顶向下的思路，可以得到状态转移方程
//            0,n=0
//    dp(n)=  -1,n<0
//            min{dp(n-coin)+1|coin in coins},n>0
//    ========================================================

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // 初始化为amount + 1,因为每个金额最多就是全部用1块钱，初始化为 amount + 1 就相当于初始化为正无穷
        Arrays.fill(dp, amount + 1);
        //base case
        dp[0] = 0;
        // 外层 for 循环在遍历所有状态的所有取值
        for (int i = 1; i < amount + 1; i++) {
            // 内层 for 循环在求所有选择的最小值
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
