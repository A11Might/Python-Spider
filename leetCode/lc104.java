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
        int left = process(node.left);
        int right = process(node.right);
        return 1 + Math.max(left, right);
    }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
