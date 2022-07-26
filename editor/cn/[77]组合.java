import java.util.LinkedList;
import java.util.List;

// leetcode submit region begin(Prohibit modification and deletion)
class Solution {
  List<List<Integer>> res = new LinkedList<>();
  LinkedList<Integer> track = new LinkedList<>();

  public List<List<Integer>> combine(int n, int k) {
    if (n < k) {
      return res;
    }
    backtrack(1, n, k);
    return res;
  }

  void backtrack(int start, int n, int k) {
    if (track.size() == k) {
      res.add(new LinkedList<>(track));
      return;
    }

    for (int i = start; i <= n; i++) {
      track.addLast(i);
      backtrack(i + 1, n, k);
      track.removeLast();
    }
  }
}
// leetcode submit region end(Prohibit modification and deletion)
