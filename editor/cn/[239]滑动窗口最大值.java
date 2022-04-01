//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。 
//
// 返回 滑动窗口中的最大值 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], k = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 1 <= k <= nums.length 
// 
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 👍 1503 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue window = new MonotonicQueue();
        List<Integer> res = new ArrayList<>();

        for(int i = 0 ; i < nums.length ; i++){
            //将窗口填满k个数
            if(i < k - 1){
                window.push(nums[i]);
            }else{
                window.push(nums[i]);
                res.add(window.getMax());
                window.pop(nums[i - k + 1]);
            }
        }
        int[] arr = new int[res.size()];
        for(int i = 0 ; i < res.size() ; i++){
            arr[i] = res.get(i);
        }

        return arr;

    }
}

//单调队列
class MonotonicQueue{

    LinkedList<Integer> g  = new LinkedList<>();
    //添加元素时，从后面添加，并且将前面比n小的数全部弹出
    public void push(int n){
        while (!g.isEmpty() && g.getLast() < n){
            g.pollLast();
        }
        g.addLast(n);
    }
    //第一个数就是最大值
    public int getMax(){
        return g.getFirst();
    }
    //n为最大值才弹出（从窗口中移除的数是最大值才移除）
    public void pop(int n){
        if(n==g.getFirst()){
            g.pollFirst();
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
