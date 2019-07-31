package day.class04.question02;

/**
 * 题目：二叉树中，一个节点可以往上走和往下走，那么从节点a总能走到节点b
 *      节点a走到节点b的距离为：a走到b的最短路径上的节点个数
 *      求一个二叉树上最远距离
 * 
 * 思路：树型DP
 *        情况：a、最大距离来自左子树
 *              b、最大距离来自右子树
 *              c、最大距离经过当前节点(左子树的高度 + 1 + 右子树的高度)
 *        信息：a、当前输的最大距离
 *              b、当前数的高度
 */

public class MaxDistanceExplain {
	public static int maxDistance(TreeNode head) {
        return process(head).maxDistance;
    }

    public static ReturnData process(TreeNode node) {
        // 第三步，定义递归基
        if (node == null) {
            return new ReturnData(0, 0);
        }
        // 第一步，把process当做黑盒
        // 讨论每种情况 
        ReturnData left = process(node.left); // 左子树的信息
        ReturnData right = process(node.right); // 右子树的信息
        // 最大距离来自左子树
        int case1 = left.maxDistance;
        // 最大距离来自右子树
        int case2 = right.maxDistance;
        // 最大距离经过当前节点
        int case3 = left.h + 1 + right.h;
        int maxDistance = Math.max(case1, Math.max(case2, case3));
        // 第二步将黑盒实现
        return new ReturnData(maxDistance, Math.max(left.h, right.h) + 1);
    }

    // 定制返回值
    public static class ReturnData {
        public int maxDistance;
        public int h;

        public ReturnData(int maxDistance, int h) {
            this.maxDistance = maxDistance;
            this.h = h;
        }
    }
}
