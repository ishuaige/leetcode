// leetcode submit region begin(Prohibit modification and deletion)
class Solution {
  public int closedIsland(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    for (int i = 0; i < m; i++) {
      // 把靠左边的岛屿淹掉
      dfs(grid, i, 0);
      // 把靠右边的岛屿淹掉
      dfs(grid, i, n - 1);
    }
    for (int j = 0; j < n; j++) {
      // 把靠上边的岛屿淹掉
      dfs(grid, 0, j);
      // 把靠下边的岛屿淹掉
      dfs(grid, m - 1, j);
    }

    // 遍历 grid，剩下的岛屿都是封闭岛屿
    int res = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 0) {
          res++;
          dfs(grid, i, j);
        }
      }
    }
    return res;
  }

  void dfs(int[][] graid, int i, int j) {
    if (i < 0 || j < 0 || i >= graid.length || j >= graid[0].length) {
      return;
    }
    if (graid[i][j] == 1) {
      return;
    }

    graid[i][j] = 1;
    dfs(graid, i + 1, j);
    dfs(graid, i, j + 1);
    dfs(graid, i, j - 1);
    dfs(graid, i - 1, j);
  }
}
// leetcode submit region end(Prohibit modification and deletion)
