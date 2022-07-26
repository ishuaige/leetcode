// leetcode submit region begin(Prohibit modification and deletion)
class Solution {
  public boolean canPartitionKSubsets(int[] nums, int k) {
    if (k > nums.length) return false;
    int sum = 0;
    for (int i : nums) {
      sum += i;
    }
    if (sum % k != 0) return false;
    int target = sum / k;
    boolean[] used = new boolean[nums.length];

   return backtrack(k, 0, nums, 0, used, target);
  }
//  以桶的视角：
//  现在 k 号桶正在思考是否应该把 nums[start] 这个元素装进来；
//  目前 k 号桶里面已经装的数字之和为bucket；
//  used 标志某一个元素是否已经被装到桶中；
//  target 是每个桶需要达成的目标和。
  boolean backtrack(int k, int bucket, int[] nums, int start, boolean[] used, int target) {
    if (k == 0) {
      return true;
    }
    if (bucket == target) {
     return backtrack(k - 1, 0, nums, 0, used, target);
    }

    for (int i = start; i < nums.length; i++) {
      if (used[i]) {
        continue;
      }
      if (bucket + nums[i] > target) {
        continue;
      }
      bucket += nums[i];
      used[i] = true;
      if(backtrack(k, bucket, nums, i + 1, used, target)){
        return true;
      }
      bucket -= nums[i];
      used[i] = false;
    }
    return false;
  }
}
// leetcode submit region end(Prohibit modification and deletion)

// 以数字的角度（超时）:数字要放进某一个桶
// class Solution {
//  public boolean canPartitionKSubsets(int[] nums, int k) {
//    if (k > nums.length) return false;
//    int[] bucket = new int[k];
//    int sum = 0;
//    for (int i : nums) {
//      sum += i;
//    }
//    if (sum % k != 0) return false;
//    int target = sum / k;
//
//    Arrays.sort(nums);
//    int i = 0, j = nums.length - 1;
//    for (; i < j; i++, j--) {
//      // 交换 nums[i] 和 nums[j]
//      int temp = nums[i];
//      nums[i] = nums[j];
//      nums[j] = temp;
//    }
//
//    return backtrack(nums, 0, bucket, target);
//  }
//
//  boolean backtrack(int[] nums, int index, int[] bucket, int target) {
//    if (index == nums.length) {
//      for (int i = 0; i < bucket.length; i++) {
//        if (bucket[i] != target) {
//          return false;
//        }
//      }
//      return true;
//    }
//    for (int i = 0; i < bucket.length; i++) {
//      if (bucket[i] + nums[index] > target) {
//        continue;
//      }
//      bucket[i] += nums[index];
//      if (backtrack(nums, index + 1, bucket, target)) {
//        return true;
//      }
//      bucket[i] -= nums[index];
//    }
//    return false;
//  }
// }
