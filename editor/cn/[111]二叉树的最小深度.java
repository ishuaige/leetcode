// leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
 * right; TreeNode() {} TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left,
 * TreeNode right) { this.val = val; this.left = left; this.right = right; } }
 */
class Solution {
  public int minDepth(TreeNode root) {
    if(root == null){
      return 0;
    }
    int depth = 1;
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    while(!q.isEmpty()) {
      int sz = q.size();
      for (int i = 0; i < sz; i++) {
        TreeNode curNode = q.poll();
        if (curNode.right == null && curNode.left == null) {
          return depth;
        }
        if (curNode.left != null) q.offer(curNode.left);
        if (curNode.right != null) q.offer(curNode.right);
      }
      depth++;
    }
    return depth;
  }
}
// leetcode submit region end(Prohibit modification and deletion)
