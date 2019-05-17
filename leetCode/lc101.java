import java.util.LinkedList;
import java.util.Queue;

/*
 * @lc app=leetcode.cn id=101 lang=java
 *
 * [101] 对称二叉树
 * 
 * 迭代：类似于 BFS，每次提取两个结点并比较它们的值，
 *       将两个结点的左右子结点按相反的顺序插入队列中
 * 递归：树的左子树与右子树镜像对称，那么这个树是对称的，两个树互为镜像：
 *          1. 它们的两个根结点具有相同的值。
 *          2. 每个树的右子树都与另一个树的左子树镜像对称(牛逼，还以为少了左子树与右子树对称能)
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur1 = queue.poll();
            TreeNode cur2 = queue.poll();
            // 判断对称节点是否相等
            if (cur1 == null && cur2 == null) {
                continue;
            }
            if (cur1 == null || cur2 == null) {
                return false;
            }
            if (cur1.val != cur2.val) {
                return false;
            }
            // 将需要对称的节点，放在相邻的位置
            queue.add(cur1.left);
            queue.add(cur2.right);
            queue.add(cur1.right);
            queue.add(cur2.left);
        }
        return true;
    }

    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root.left, root.right);
    }

    private boolean process(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return left.val == right.val && // 它们的两个根结点具有相同的值
               // 每个树的右子树都与另一个树的左子树镜像对称
               process(left.left, right.right) &&
               process(left.right, right.left);
    }
}

