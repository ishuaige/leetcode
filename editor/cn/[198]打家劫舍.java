
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int rob(int[] nums) {
//        ================ 自顶向下 ===================
//        // 初始化备忘录
//        memo = new int[nums.length];
//        Arrays.fill(memo, -1);
//        // 强盗从第 0 间房子开始抢劫
//        return dp(nums, 0);

//        ================ 自底向上 ===================
//        int n = nums.length;
//        int[] dp = new int[n + 2];
//
//        //需要先知道后面的信息，才能构造我，所以得从后开始遍历
//        for (int i = n - 1; i >= 0; i--) {
//            dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
//        }
//        return dp[0];
//        ================ 优化后 ===================
        int n = nums.length;
        int dp_i = 0;
        int dp_i_1 = 0;
        int dp_i_2 = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }

//    private int[] memo;
//    private int dp(int[] nums,int start){
//        if (start >= nums.length) {
//            return 0;
//        }
//        // 避免重复计算
//        if (memo[start] != -1) return memo[start];
//
//        int res = Math.max(dp(nums, start + 1),
//                nums[start] + dp(nums, start + 2));
//        // 记入备忘录
//        memo[start] = res;
//        return res;
//    }
}
//leetcode submit region end(Prohibit modification and deletion)
