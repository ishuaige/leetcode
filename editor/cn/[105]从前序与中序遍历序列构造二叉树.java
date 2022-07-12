//给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并
//返回其根节点。 
//
// 
//
// 示例 1: 
//
// 
//输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//输出: [3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//输入: preorder = [-1], inorder = [-1]
//输出: [-1]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= preorder.length <= 3000 
// inorder.length == preorder.length 
// -3000 <= preorder[i], inorder[i] <= 3000 
// preorder 和 inorder 均 无重复 元素 
// inorder 均出现在 preorder 
// preorder 保证 为二叉树的前序遍历序列 
// inorder 保证 为二叉树的中序遍历序列 
// 
// Related Topics 树 数组 哈希表 分治 二叉树 👍 1615 👎 0


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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
      return  build(preorder,0,preorder.length-1,
                inorder,0,inorder.length-1);
    }

    TreeNode build(int[] preorder,int preStart,int preEnd,
                       int[] inorder,int inStart,int inEnd){
        if(preStart>preEnd){
            return null;
        }

        int rootVal=preorder[preStart];
        int inindex = -1;
        for(int i = inStart;i<=inEnd;i++){
            if(inorder[i]==rootVal){
                inindex=i;
                break;
            }
        }
        int leftsize=inindex-inStart;

        TreeNode root = new TreeNode(rootVal);
        root.left=build(preorder,preStart+1,preStart+leftsize,
                        inorder,inStart,inindex-1);
        root.right=build(preorder,preStart+leftsize+1,preEnd,
                        inorder,inindex+1,inEnd);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
