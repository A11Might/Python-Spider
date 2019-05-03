import java.util.Stack;

/*
 * @lc app=leetcode.cn id=98 lang=java
 *
 * [98] 验证二叉搜索树
 * 
 * 二叉搜索树中序遍历顺序为递增的
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        long preNum = Long.MIN_VALUE; // 防止val为Integer.MIN_VALUE导致相等，误判。实例 [-2147483648,null,2147483647]
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (preNum >= cur.val) {
                    return false;
                }
                preNum = cur.val;
                cur = cur.right;
            }
        }
        return true;
    }
}



class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

