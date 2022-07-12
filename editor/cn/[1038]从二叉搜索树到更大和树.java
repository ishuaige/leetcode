
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
    public TreeNode bstToGst(TreeNode root) {
        traverse(root);
        return root;
    }
    int sum = 0;
    void traverse(TreeNode root){
        if(root==null){
            return ;
        }
        //这里先递归右子树，则中序就是降序了，维持一个sum，每轮给root赋值就完了
        traverse(root.right);
        sum+=root.val;
        root.val=sum;
        traverse(root.left);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
