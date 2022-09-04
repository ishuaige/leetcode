import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int[][] memo;

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        memo = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dp(word1, n - 1, word2, m - 1);
    }

    int dp(String s1, int i, String s2, int j) {
        //一个字符串先到头了，就只能用插入操作把剩下的全部插入
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            //不操作，直接往前
            memo[i][j] = dp(s1, i - 1, s2, j - 1);
        } else {
            //注意：操作数 + 1
            memo[i][j] = min(
                    dp(s1, i, s2, j - 1) + 1, // 插入
                    dp(s1, i - 1, s2, j) + 1, // 删除
                    dp(s1, i - 1, s2, j - 1) + 1 //替换
            );

        }
        return memo[i][j];
    }

    int min(int i, int j, int k) {
        return Math.min(i, Math.min(j, k));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
