
//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    int res ;
    public int longestUnivaluePath(TreeNode root) {
        res = 0;
        dfs(root);
        return res;
    }
    //返回该节点形成的最长路径
    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //1. 先知道我的左右节点形成的最长路径
        int leftVal = dfs(root.left);
        int rightVal = dfs(root.right);
        //2. 这是我的最长路径
        int myLeftVal = 0;
        int myRightVal = 0;
        //3. 如果我有左节点并且左节点和我相等，那么我的左边的最长路径等于左节点最长路径加一
        if (root.left != null && root.val == root.left.val) {
            myLeftVal = leftVal +1;
        }
        // 同3
        if (root.right != null && root.val == root.right.val) {
            myRightVal = rightVal+1;
        }
        //4. 更新返回值，注意如果我的左节点为空或者和我不一样，那么我的左边的最大路径不会被赋值，将会是0
        res = Math.max(res,myLeftVal + myRightVal);
        //5. 返回加上我形成的最长路径
        return Math.max(myLeftVal, myRightVal);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
