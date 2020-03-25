import java.util.LinkedList;
import java.util.Queue;

/*
 * 题目：判断一个二叉树是否是完全二叉树
 * 
 * 思路：bfs，a、当前节点有右无左时，不是完全二叉树
 *           b、当前节点左右子节点不全时，后续节点必须为叶结点，否则不是完全二叉树
 */
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        boolean leaf = false; // leaf为ture时，当前节点都需为叶节点
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            TreeNode left = cur.left;
            TreeNode right = cur.right;
            if ((left == null && right != null) || // 当前节点有右无左
                    (leaf && (left != null || right != null))) { // 开启叶节点后，当前节点需为叶节点
                return false;
            }
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
            // 当前节点左右子节点不全
            if (left == null || right == null) {
                leaf = true;
            }
        }
        return true;
    }
}
