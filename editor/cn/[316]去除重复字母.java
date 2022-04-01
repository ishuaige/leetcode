//给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "bcabc"
//输出："abc"
// 
//
// 示例 2： 
//
// 
//输入：s = "cbacdcbc"
//输出："acdb" 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 由小写英文字母组成 
// 
//
// 
//
// 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-
//distinct-characters 相同 
// Related Topics 栈 贪心 字符串 单调栈 👍 695 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //单调栈思想去重
    public String removeDuplicateLetters(String s) {
        //存储返回值的逆序
        Stack<Character> res = new Stack<>();
        //记录字符是否在栈中，方便去重
        boolean[] inStack = new boolean[256];
        //记录每个字符出现的次数，因为要保持最小字典序，因此将字符弹出时要判断后面是否还有该字符
        //如果后面没有该字符就不弹出
        int[] count = new int[256];

        //记录每个字符出现次数
        for(int i = 0;i<s.length();i++){
            count[s.charAt(i)]++;
        }

        for(int i = 0 ;i<s.length();i++){
            char c = s.charAt(i);
            //更新字符的数量
            count[c]--;
            //如果字符已经出现在栈中，则跳过（去重）
            if(inStack[c])continue;
            //将栈中字符字典序比c大的字符弹出
            while( !res.isEmpty() && c < res.peek()){
                //如果该字符在后面已经没有了，就不弹出了
                if(count[res.peek()]==0){
                    break;
                }
                //更新栈中字符状态
                inStack[res.pop()] = false;
            }
            res.push(c);
            inStack[c]=true;
        }
        StringBuilder sb = new StringBuilder();
        //将结果保存到StringBuilder中
        while (!res.isEmpty()){
            sb.append(res.pop());
        }
        //将结果逆转，返回字符串
        return sb.reverse().toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
