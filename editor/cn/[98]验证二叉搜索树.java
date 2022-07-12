
//leetcode submit region begin(Prohibit modification and deletion)
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
    public boolean isValidBST(TreeNode root) {
      return isValidBST(root,null,null);
    }

    boolean isValidBST(TreeNode root,TreeNode max,TreeNode min){
        if(root == null){
            return true;
        }

        if(max!=null&&root.val>=max.val)return false;
        if(min!=null&&root.val<=min.val)return false;

        return isValidBST(root.left,root,min)
                && isValidBST(root.right,max,root);

    }
}
//leetcode submit region end(Prohibit modification and deletion)
