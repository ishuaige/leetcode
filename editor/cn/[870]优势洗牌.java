//给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。 
//
// 返回 A 的任意排列，使其相对于 B 的优势最大化。 
//
// 
//
// 示例 1： 
//
// 输入：A = [2,7,11,15], B = [1,10,4,11]
//输出：[2,11,7,15]
// 
//
// 示例 2： 
//
// 输入：A = [12,24,8,32], B = [13,25,32,11]
//输出：[24,32,8,12]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length = B.length <= 10000 
// 0 <= A[i] <= 10^9 
// 0 <= B[i] <= 10^9 
// 
// Related Topics 贪心 数组 排序 👍 170 👎 0


import java.util.Arrays;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //田忌赛马，双指针
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        //nums2降序排序，这里要记录数组下标
        //优先级队列PriorityQueue
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(
                (int[] pair1,int[] pair2)->{
                    return pair2[1] - pair1[1];
                }
            );

        for(int i = 0; i < nums2.length; i++) {
            priorityQueue.offer(new int[]{i,nums2[i]});
        }

        //nums1升序排序
        Arrays.sort(nums1);

        int[] res = new int[n];
        //双指针
        int left = 0;
        int right = n - 1;

        while (!priorityQueue.isEmpty()){
            int[] pair =  priorityQueue.poll();
            int i = pair[0];
            int maxval = pair[1];
        // nums1 最大比 nums2 大 直接比
            if(maxval < nums1[right]){
                res[i] = nums1[right];
                right--;
            }else {
        //nums1 最大比 nums2 小 拿个最小的跟他混
                res[i] = nums1[left];
                left++;
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
