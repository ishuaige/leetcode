import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    int[][] memo;
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int res = Integer.MAX_VALUE;
        memo = new int[n][n];
        for(int i = 0;i<n;i++){
            Arrays.fill(memo[i],66666);
        }

        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp(matrix, n-1, i));
        }
        return res;
    }

    public int dp(int[][] matrix, int i, int j) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length) {
            return 99999;
        }

        if (i == 0) {
            return matrix[0][j];
        }

        if(memo[i][j] != 66666){
            return memo[i][j];
        }
        memo[i][j] = matrix[i][j] + min(
                dp(matrix, i - 1, j - 1),
                dp(matrix, i - 1, j),
                dp(matrix, i - 1, j + 1)
        );
        return memo[i][j];
    }

    public int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

}
//leetcode submit region end(Prohibit modification and deletion)
