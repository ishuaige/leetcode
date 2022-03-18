//给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。 
//
// 换句话说，s1 的排列之一是 s2 的 子串 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s1 = "ab" s2 = "eidbaooo"
//输出：true
//解释：s2 包含 s1 的排列之一 ("ba").
// 
//
// 示例 2： 
//
// 
//输入：s1= "ab" s2 = "eidboaoo"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s1.length, s2.length <= 10⁴ 
// s1 和 s2 仅包含小写字母 
// 
// Related Topics 哈希表 双指针 字符串 滑动窗口 👍 604 👎 0


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //滑动窗口
    public boolean checkInclusion(String s1, String s2) {
        HashMap<Character,Integer> need = new HashMap<>();
        HashMap<Character,Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;
        int valid = 0;

        for (int i = 0; i < s1.length(); i++) {
            char a =s1.charAt(i);
            need.put(a,need.getOrDefault(a,0)+1);
        }

        while(right < s2.length()){
            char c = s2.charAt(right);
            right++;

            if(need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if(window.get(c).equals(need.get(c))){
                    valid++;
                }
            }

            while (right - left >= s1.length()){
                if(valid == need.size()){
                    return true;
                }
                char d = s2.charAt(left);
                left++;
                if(need.containsKey(d)){
                    if(window.getOrDefault(d,0).equals(need.get(d))){
                        valid--;
                    }
                    window.put(d,window.getOrDefault(d,0)-1);
                }
            }
        }
    return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
