// 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
//
// 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
//
//
//
// 示例 1:
//
//
// 输入: s = "cbaebabacd", p = "abc"
// 输出: [0,6]
// 解释:
// 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
// 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
//
//
// 示例 2:
//
//
// 输入: s = "abab", p = "ab"
// 输出: [0,1,2]
// 解释:
// 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
// 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
// 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
//
//
//
//
// 提示:
//
//
// 1 <= s.length, p.length <= 3 * 10⁴
// s 和 p 仅包含小写字母
//
// Related Topics 哈希表 字符串 滑动窗口 👍 808 👎 0

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// leetcode submit region begin(Prohibit modification and deletion)
class Solution {
  //滑动窗口
  public List<Integer> findAnagrams(String s, String p) {
    HashMap<Character, Integer> need = new HashMap<>();
    HashMap<Character, Integer> win = new HashMap<>();
    List<Integer> res = new ArrayList<>();

    int left = 0;
    int right = 0;
    int val = 0;
    // 填充need数组
    for (int i = 0; i < p.length(); i++) {
      char a = p.charAt(i);
      need.put(a, need.getOrDefault(a, 0) + 1);
    }

    while (right < s.length()) {
    //扩大窗口
      char c = s.charAt(right);
      right++;
    //更新窗口状态
      if (need.containsKey(c)) {
        win.put(c, win.getOrDefault(c, 0) + 1);
        if (win.get(c).equals(need.get(c))) {
          val++;
        }
      }
    //判断是否缩小，因为找异位词，所以当窗口大于等于p的大小的时候就可以缩小窗口了
      while (right - left >= p.length()) {
        if (val == need.size()) {
          //将满足条件的左边界加入返回列表
          res.add(left);
        }
        //缩小窗口并更新窗口状态
        char b = s.charAt(left);
        left++;
        if (need.containsKey(b)) {
          if (win.get(b).equals(need.get(b))) {
            val--;
          }
          win.put(b, win.getOrDefault(b, 0) - 1);
        }
      }
    }
    return res;
  }
}
// leetcode submit region end(Prohibit modification and deletion)
