import java.util.LinkedList;
import java.util.Queue;

/*
 * @lc app=leetcode.cn id=226 lang=java
 *
 * [226] 翻转二叉树
 * 
 * 使用层次遍历，翻转每个子树的左右节点
 */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
            swap(cur);
        }
        return root;
    }

    private void swap(TreeNode node) { // 翻转当前节点的左右子节点
        TreeNode temp = node.right;
        node.right = node.left;
        node.left = temp;
    }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { 
          val = x; 
        }
  }

