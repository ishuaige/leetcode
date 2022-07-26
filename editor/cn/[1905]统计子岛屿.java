
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int n = grid1.length; int m = grid1[0].length;
        int res = 0;
        for(int i = 0 ;i<n;i++){
            for(int j = 0;j<m;j++){
                if(grid1[i][j]==0&&grid2[i][j] == 1){
                    dfs(grid2,i,j);
                }
            }
        }

        for(int i = 0 ;i<n;i++){
            for(int j = 0;j<m;j++){
                if(grid2[i][j] == 1){
                    res++;
                    dfs(grid2,i,j);
                }
            }
        }
            return res;

    }
    void dfs(int[][] grid,int i,int j){
        int n = grid.length; int m = grid[0].length;
        if(i<0||j<0||i>=n||j>=m){
            return;
        }
        if(grid[i][j]==0){
            return;
        }
        grid[i][j] = 0;
        dfs(grid,i+1,j);
        dfs(grid,i,j+1);
        dfs(grid,i-1,j);
        dfs(grid,i,j-1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
