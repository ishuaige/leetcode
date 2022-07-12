
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
    public int kthSmallest(TreeNode root, int k) {
        traverse(root,k);
        return res;
    }

    int res = 0;
    int rank = 0;
    void traverse(TreeNode root,int k){
        if(root==null){
            return ;
        }
        traverse(root.left,k);
        //搜索二叉树中序遍历为有序集合
        rank++;
        if(rank==k){
            res=root.val;
            return;
        }
        traverse(root.right,k);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
