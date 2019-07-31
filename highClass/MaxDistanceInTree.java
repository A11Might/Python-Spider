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

public class MaxDistanceInTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int maxDistance(Node head) {
		int[] record = new int[1];
		return posOrder(head, record);
	}
	
	public static class ReturnType{
		public int maxDistance;
		public int h;
		
		public ReturnType(int m, int h) {
			this.maxDistance = m;;
			this.h = h;
		}
	}
	
	public static ReturnType process(Node head) {
		if(head == null) {
			return new ReturnType(0,0);
		}
		ReturnType leftReturnType = process(head.left);
		ReturnType rightReturnType = process(head.right);
		int includeHeadDistance = leftReturnType.h + 1 + rightReturnType.h;
		int p1 = leftReturnType.maxDistance;
		int p2 = rightReturnType.maxDistance;
		int resultDistance = Math.max(Math.max(p1, p2), includeHeadDistance);
		int hitself  = Math.max(leftReturnType.h, rightReturnType.h) + 1;
		return new ReturnType(resultDistance, hitself);
	}

	public static int posOrder(Node head, int[] record) {
		if (head == null) {
			record[0] = 0;
			return 0;
		}
		int lMax = posOrder(head.left, record);
		int maxfromLeft = record[0];
		int rMax = posOrder(head.right, record);
		int maxFromRight = record[0];
		int curNodeMax = maxfromLeft + maxFromRight + 1;
		record[0] = Math.max(maxfromLeft, maxFromRight) + 1;
		return Math.max(Math.max(lMax, rMax), curNodeMax);
	}

	public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.left = new Node(2);
		head1.right = new Node(3);
		head1.left.left = new Node(4);
		head1.left.right = new Node(5);
		head1.right.left = new Node(6);
		head1.right.right = new Node(7);
		head1.left.left.left = new Node(8);
		head1.right.left.right = new Node(9);
		System.out.println(maxDistance(head1));

		Node head2 = new Node(1);
		head2.left = new Node(2);
		head2.right = new Node(3);
		head2.right.left = new Node(4);
		head2.right.right = new Node(5);
		head2.right.left.left = new Node(6);
		head2.right.right.right = new Node(7);
		head2.right.left.left.left = new Node(8);
		head2.right.right.right.right = new Node(9);
		System.out.println(maxDistance(head2));

	}

}
