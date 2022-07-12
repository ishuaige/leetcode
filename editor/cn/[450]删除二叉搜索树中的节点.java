
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
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null){
            return null;
        }
        if(root.val==key){
            /*
            相等有三种情况：1.是叶子，直接删
                         2.有一个孩子，交换
                         3.有两个孩子
             */
            if(root.left==null){
                return root.right;
            }
            if(root.right==null){
                return root.left;
            }
            //处理第三种情况
            //得到右子树最小 或 左子树最大 交换 这里找最小
            TreeNode min = getMin(root.right);
            //删掉右子树最小
            root.right=deleteNode(root.right,min.val);
            min.left=root.left;
            min.right=root.right;
            root=min;
        }else if(root.val>key){
           root.left = deleteNode(root.left,key);
        }else if(root.val<key){
           root.right = deleteNode(root.right,key);
        }
        return root;
    }

    TreeNode getMin(TreeNode root){
        if(root==null){
            return null;
        }
        while(root.left!=null){
            root=root.left;
        }
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
