package day.class04.question02;

/*
 * 题目：判断二叉树是否高度平衡
 * 
 * 思路：树型DP,https://github.com/A11Might/leetcode/blob/master/codes/lc110.java
 *     情况：
 *      a、左子树不平衡或者右子树不平衡
 *      b、左子树平衡并且右子树平衡，但高度差 > 1
 *      c、左子树平衡并且右子树平衡，但高度差 <= 1
 *     信息：
 *      a、左子树是否平衡及其高度
 *      b、右子树是否平衡及其高度
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

public class IsBalancedTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isBalance(Node head) {
		boolean[] res = new boolean[1];
		res[0] = true;
		getHeight(head, 1, res);
		return res[0];
	}
	
	public static class ReturnType {
		public int level;
		public boolean isB;
		
		public ReturnType(int l, boolean is) {
			level = l;
			isB = is;
		}
	}
	
	// process(head, 1)
	
	public static ReturnType process(Node head, int level) {
		if (head == null) {
			return new ReturnType(level, true);
		}
		ReturnType leftSubTreeInfo = process(head.left, level + 1);
		if(!leftSubTreeInfo.isB) {
			return new ReturnType(level, false);
		}
		ReturnType rightSubTreeInfo = process(head.right, level + 1);
		if(!rightSubTreeInfo.isB) {
			return new ReturnType(level, false);
		}
		if (Math.abs(rightSubTreeInfo.level - leftSubTreeInfo.level) > 1) {
			return new ReturnType(level, false);
		}
		return new ReturnType(Math.max(leftSubTreeInfo.level, rightSubTreeInfo.level), true);
	}

	public static int getHeight(Node head, int level, boolean[] res) {
		if (head == null) {
			return level;
		}
		int lH = getHeight(head.left, level + 1, res);
		if (!res[0]) {
			return level;
		}
		int rH = getHeight(head.right, level + 1, res);
		if (!res[0]) {
			return level;
		}
		if (Math.abs(lH - rH) > 1) {
			res[0] = false;
		}
		return Math.max(lH, rH);
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		System.out.println(isBalance(head));

	}

}
