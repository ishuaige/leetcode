
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes,new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                return a[0] == b[0] ?
                        b[1] - a[1] : a[0] - b[0];
            }
        });
        int n = envelopes.length;
        int[] height = new int[n];
        for (int i = 0; i < n; i++){
            height[i] = envelopes[i][1];
        }
        return lengthOfLIS(height);
    }

    public int lengthOfLIS(int[] nums){
        int piles = 0;
        int n = nums.length;
        int[] top = new int[n];
        for(int i = 0;i < n;i++){
            //当前要放的牌
            int poker = nums[i];
            int left = 0;
            int right = piles;
            //开始二分查找
            while(left < right){
                int mid = (right + left)/2;
                if(top[mid] >= poker){
                    right = mid;
                }else if(top[mid] < poker){
                    left = mid+1;
                }
            }
            //找不到合适的位置，自成一对
            if(left == piles){
                piles++;
            }
            //将这个牌放到最上面
            top[left] = poker;
        }
        return piles;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
