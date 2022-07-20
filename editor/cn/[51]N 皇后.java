import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// leetcode submit region begin(Prohibit modification and deletion)
class Solution {
  // 符合条件的某个棋盘布局（路径）
  List<String> path = new LinkedList<>();
  // 最终返回
  List<List<String>> res = new ArrayList<>();

  public List<List<String>> solveNQueens(int n) {
    // 初始化棋盘
    char[][] board = new char[n][n];
    for (int i = 0; i < board.length; i++) {
      Arrays.fill(board[i], '.');
    }
    // 开始回溯
    backtrack(board, 0);
    return res;
  }

  // 路径：board 中小于 row 的那些行都已经成功放置了皇后
  // 选择列表：第 row 行的所有列都是放置皇后的选择
  // 结束条件：row 超过 board 的最后一行
  void backtrack(char[][] board, int row) {
    if (row == board.length) {
      // 将char[][]转化为List
      path = array2List(board);
      res.add(path);
      return;
    }

    int n = board[row].length;
    for (int col = 0; col < n; col++) {
      // 排除非法选择
      if (!isValid(board, row, col)) {
        continue;
      }
      // 做选择
      board[row][col] = 'Q';
      // 进行下一行选择
      backtrack(board, row + 1);
      // 撤销选择
      board[row][col] = '.';
    }
  }

  List<String> array2List(char[][] board) {
    List<String> ListFromArray = new LinkedList<>();
    for (int i = 0; i < board.length; i++) {
      ListFromArray.add(new String(board[i]));
    }
    return ListFromArray;
  }

  boolean isValid(char[][] board, int row, int col) {
    int n = board.length;
    // 检查列是否有皇后互相冲突
    for (int i = 0; i < n; i++) {
      if (board[i][col] == 'Q') {
        return false;
      }
    }
    // 检查右上方是否有皇后互相冲突
    for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
      if (board[i][j] == 'Q') {
        return false;
      }
    }
    // 检查左上方是否有皇后互相冲突
    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
      if (board[i][j] == 'Q') {
        return false;
      }
    }
    return true;
  }
}
// leetcode submit region end(Prohibit modification and deletion)
