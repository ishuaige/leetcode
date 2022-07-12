
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

//    备忘录，解决子重叠问题，降低时间复杂度
    int[][] memo;

    public int numTrees(int n) {
        memo = new int[n+1][n+1];
        return count(1,n);
    }
    //计算区间[n,m]组成的BTS数
    int count(int n,int m){
        if(n>m){
            return 1;
        }
        if(memo[n][m]!=0){
            return memo[n][m];
        }
        int res = 0;
        for(int i = n;i<=m;i++){
            //以i为根节点
            int left = count(n,i-1);
            int right = count(i+1,m);
             res += left*right;
        }
        memo[n][m]=res;
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
