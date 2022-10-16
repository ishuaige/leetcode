
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canPartition(int[] nums) {

        int sum = 0;
        int n = nums.length;
        for (int i : nums) {
            sum += i;
        }
        //如果不能整除2，说明不可能能分割成相同和的两个子集
        if (sum % 2 != 0) {
            return false;
        }

        sum /= 2;

        //dp[i][sum]定义：前i个数字能不能组成和为sum的数组
        boolean[][] dp = new boolean[n + 1][sum + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][sum];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
