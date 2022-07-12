
//leetcode submit region begin(Prohibit modification and deletion)


import java.util.LinkedList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<TreeNode> generateTrees(int n) {
        if(n==0){
            return new LinkedList<>();
        }
        return build(1,n);
    }

    List<TreeNode> build(int n,int m){
        List<TreeNode> res = new LinkedList<>();

        if(n>m){
            res.add(null);
            return res;
        }

        for(int i = n;i <= m;i++){
            List<TreeNode> leftTree = build(n,i-1);
            List<TreeNode> rightTree = build(i+1,m);

            for(TreeNode left:leftTree){
                for(TreeNode right:rightTree){
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
