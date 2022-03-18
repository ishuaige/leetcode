//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。 
//
// 
//
// 注意： 
//
// 
// 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。 
// 如果 s 中存在这样的子串，我们保证它是唯一的答案。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
// 
//
// 示例 2： 
//
// 
//输入：s = "a", t = "a"
//输出："a"
// 
//
// 示例 3: 
//
// 
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, t.length <= 10⁵ 
// s 和 t 由英文字母组成 
// 
//
// 
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 字符串 滑动窗口 👍 1695 👎 0


import java.util.HashMap;


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //滑动窗口题目
    public String minWindow(String s, String t) {
        //need记录需要的字符及其数量 window记录窗口中有的字符及其数量
        HashMap<Character,Integer> need = new HashMap<Character, Integer>();
        HashMap<Character,Integer> window = new HashMap<Character, Integer>();
        int left = 0;
        int right = 0;
        //valid记录符合条件的字符数
        int valid = 0;
        int start = 0;
        int len = Integer.MAX_VALUE;

        //填充need
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            need.put(c,need.getOrDefault(c,0)+1);
        }

        while(right<s.length()){
            //窗口扩大
            char c = s.charAt(right);
            right++;

            //更新扩大后的状态
            if(need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if(window.get(c).equals(need.get(c)) ){
                    valid++;
                }
            }

            //所有字符满足需要条件
            while(valid == need.size()){
                //更新len保持最小
                if(right - left < len){
                    start = left;
                    len = right - left;
                }
                //缩小窗口
                char a = s.charAt(left);
                left++;
                if(need.containsKey(a)){
                    //更新缩小状态
                    if(window.get(a).equals(need.get(a)) ){
                        valid--;
                    }
                    window.put(a,window.getOrDefault(a,0)-1);
                }
            }
        }

        return len==Integer.MAX_VALUE?"":s.substring(start,start+len);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
