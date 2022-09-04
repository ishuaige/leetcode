
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        //遍历整个数组
        for(int i = 1;i < n;i++){
            //遍历 i 前面的数字
            for(int j = i; j>=0;j--){
                //如果前面的数比该数小，更新最大值
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
        }
        int res = 1;
        for(int i = 0;i<n;i++){
            res = Math.max(res,dp[i]);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
