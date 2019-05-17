import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=102 lang=java
 *
 * [102] 二叉树的层次遍历
 * 
 * 使用last和nlast分别记录当前行的最后一个节点和下一行的最后一个节点
 * 判断目前在哪行和进行换行操作
 */
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList();
        }
        TreeNode last = root; // 记录当前行的最后一个节点
        TreeNode nlast = null; // 记录下一行的最后一个节点
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            list.add(cur.val);
            if (cur.left != null) {
                queue.add(cur.left);
                nlast = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nlast = cur.right;
            }
            // 到达当前行的最后一个节点，更新last，记录下一行节点
            if (cur == last) {
                last = nlast;
                res.add(list);
                list = new ArrayList<>();
            }
        }
        return res;
    }
}
