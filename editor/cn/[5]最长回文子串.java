//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母组成 
// 
// Related Topics 字符串 动态规划 👍 5061 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /*
    判断回文用中心拓展，双指针向两边动
     */
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
          // 因为最小回文子串长度为1-2
          // 返回s从i向两边拓展的最大回文串
          String s1 = huiwen(s,i,i);
          //返回s从 i - i+1 向两边拓展的最大回文串
          String s2 = huiwen(s,i,i+1);
          res = res.length() > s1.length() ? res : s1;
          res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    private String huiwen(String s , int l,int r){
        while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }
        //退出循环时l,r不相等，因此左边界为l+1，左闭右包区间因此这里为l+1 到 r
        return s.substring(l+1,r);

    }
}
//leetcode submit region end(Prohibit modification and deletion)
