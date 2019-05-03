/*
 * @lc app=leetcode.cn id=104 lang=java
 *
 * [104] 二叉树的最大深度
 * 
 * 二叉树的最大深度为其左子树和右子树深度的最大值加一
 */
class Solution {
    public int maxDepth(TreeNode root) {
        return process(root);   
    }

    private int process(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        if (node.left == null) {
            return 1 + process(node.right);
        }
        if (node.right == null) {
            return 1 + process(node.left);
        }
        return 1 + Math.max(process(node.left), process(node.right));
    }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
