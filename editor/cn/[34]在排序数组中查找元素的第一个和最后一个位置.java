//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。 
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 进阶： 
//
// 
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// nums 是一个非递减数组 
// -10⁹ <= target <= 10⁹ 
// 
// Related Topics 数组 二分查找 👍 1537 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //二分查找题目
    public int[] searchRange(int[] nums, int target) {
        int left = left_bound(nums,target);

        int right = right_bound(nums,target);

        return new int[]{left,right};

    }
    //找左边界
    public int left_bound(int[] nums, int target){
        int left = 0;
        int right = nums.length-1;
        //左右都闭区间 ===== 最终条件位left > right=nums.length-1 , 所以最后要判断越界
         while(left <= right){
             int mid = left + (right - left)/2;
             if(nums[mid] == target){
                 //找左边界因此相等时让右边界缩小
                 right = mid -1 ;
             }else if(nums[mid] < target){
                 left = mid + 1;
             }else if(nums[mid] > target){
                 right = mid - 1;
             }
         }
         //当数组中所有数都比target小时左边界会越界
         if(left >= nums.length || nums[left] != target ){
             return -1;
         }
         return left;
    }

    public int right_bound(int[] nums, int target){
        int left = 0;
        int right = nums.length-1;
        while(left <= right){
            int mid = left + (right - left)/2;

            if(nums[mid] == target){
                left = mid + 1 ;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else if(nums[mid] > target){
                right = mid - 1;
            }

        }

        if(right < 0 || nums[right] != target ){
            return -1;
        }
        return right;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
