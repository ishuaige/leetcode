
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int[][] memo ;

    public int minPathSum(int[][] grid) {
        int i = grid.length;
        int j = grid[0].length;
        memo = new int[i][j];
        for (int[] row : memo)
            Arrays.fill(row, -1);
        return dp(grid, i - 1, j - 1);
    }
    //dp：grid数组，从[0,0]->[i,j]的最小路径
    int dp(int[][] grid, int i, int j) {
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }
        if (i == 0 && j == 0) {
            return grid[0][0];
        }
        if(memo[i][j] != -1){
            return memo[i][j];
        }
        memo[i][j] = Math.min(  dp(grid, i, j - 1),
                                dp(grid, i - 1, j) ) + grid[i][j];
        return memo[i][j];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
