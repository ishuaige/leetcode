// leetcode submit region begin(Prohibit modification and deletion)
class Solution {

  public int maxAreaOfIsland(int[][] grid) {
    int res = 0;
    int n = grid.length;
    int m = grid[0].length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == 1) {
          int thisV = dfs(grid, i, j);
          res = Math.max(res, thisV);
        }
      }
    }
    return res;
  }

  int dfs(int[][] grid, int i, int j) {
    int n = grid.length;
    int m = grid[0].length;
    if (i < 0 || j < 0 || i >= n || j >= m) {
      return 0;
    }

    if (grid[i][j] == 0) {
      return 0;
    }

    grid[i][j] = 0;
    return dfs(grid, i + 1, j)
        + dfs(grid, i, j + 1)
        + dfs(grid, i - 1, j)
        + dfs(grid, i, j - 1)
        + 1;
  }
}
// leetcode submit region end(Prohibit modification and deletion)
