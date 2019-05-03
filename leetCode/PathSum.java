/*
 * @lc app=leetcode.cn id=112 lang=java
 *
 * [112] 路径总和
 * 
 * 回溯
 */
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        return process(root, sum);
    }

    private boolean process(TreeNode node, int sum) {
        if (node == null) {
            return false;
        }
        if (node.left == null && node.right == null) { // node为叶结点时
            return sum == node.val;
        }
        return process(node.left, sum - node.val) || process(node.right, sum - node.val); // node有子树时
    }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

