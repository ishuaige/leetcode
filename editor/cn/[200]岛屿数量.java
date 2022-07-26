// leetcode submit region begin(Prohibit modification and deletion)
class Solution {

  public int numIslands(char[][] grid) {
    int res = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == '1') {
          res++;
          dfs(grid, i, j);
        }
      }
    }
    return res;
  }

  //负责把岛屿给淹了
  void dfs(char[][] grid, int i, int j) {
    int n = grid.length;
    int m = grid[0].length;
    if (i < 0 || j < 0 || i >= n || j >= m) {
      return;
    }
    if (grid[i][j] == '0') {
      return;
    }
    grid[i][j] = '0';
    dfs(grid, i + 1, j);
    dfs(grid, i, j + 1);
    dfs(grid, i - 1, j);
    dfs(grid, i, j - 1);
  }
}
// leetcode submit region end(Prohibit modification and deletion)
