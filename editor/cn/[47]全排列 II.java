import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// leetcode submit region begin(Prohibit modification and deletion)
class Solution {

  List<List<Integer>> res = new LinkedList<>();
  LinkedList<Integer> track = new LinkedList<>();

  public List<List<Integer>> permuteUnique(int[] nums) {
    Arrays.sort(nums);
    boolean[] used = new boolean[nums.length];
    backtrack(nums,used);
    return res;
  }

  void backtrack(int[] nums, boolean[] used) {
    if (track.size() == nums.length) {
      res.add(new LinkedList<>(track));
      return;
    }

    for (int i = 0; i < nums.length; i++) {
      if (used[i]) {
        continue;
      }
      //为了保持相同元素相对位置，所以必须要你前面的用过了你才能用。
      if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
        continue;
      }
      track.addLast(nums[i]);
      used[i] = true;
      backtrack(nums, used);
      track.removeLast();
      used[i] = false;
    }
  }
}
// leetcode submit region end(Prohibit modification and deletion)
