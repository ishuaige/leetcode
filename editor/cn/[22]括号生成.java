import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> generateParenthesis(int n) {
        StringBuilder track = new StringBuilder();
        List<String> res = new ArrayList<>();
        backtrack(n,n,track,res);
        return res;
    }

    /**
     *  回溯算法
     * @param left 左括号剩余数量
     * @param right 右括号剩余数量
     * @param track 现在路径上已经有的括号
     * @param res   返回结果集
     */
    public void backtrack(int left,int right,StringBuilder track,List<String> res){
        //左括号剩下的多，说明不合法
        if(right < left){
            return;
        }
        //数量小于0，不合法
        if(left < 0 || right < 0){
            return;
        }
        //括号刚好用完，加入结果集
        if(left == 0 && right == 0){
            res.add(track.toString());
        }
        //尝试加左括号
        track.append("(");
        backtrack(left-1,right,track,res);
        //撤回操作
        track.deleteCharAt(track.length()-1);

        //尝试加右括号
        track.append(")");
        backtrack(left,right-1,track,res);
        //撤回操作
        track.deleteCharAt(track.length()-1);

    }
}
//leetcode submit region end(Prohibit modification and deletion)
