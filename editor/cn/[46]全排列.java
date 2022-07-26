import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    List<List<Integer>> res =new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums,track);
        return res;
    }

    void backtrack(int[] nums,LinkedList<Integer> track){
        if(track.size() == nums.length){
            res.add(new LinkedList<>(track));
        }

        for(int i = 0;i<nums.length;i++){
            if(track.contains(nums[i])){
                continue;
            }
            track.addLast(nums[i]);
            backtrack(nums, track);
            track.removeLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
