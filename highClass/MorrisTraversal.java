package day.class02;

/**
 * morris遍历：
 * 当前节点记为cur(引用)
 * 1、如果cur无左孩子，cur向右移动
 * 2、如果cur有左孩子，找到cur左孩子最右的节点，记为mostRight
 *    a、如果mostRight.right指针指向null，让其指向cur，cur向左移动
 *    b、如果mostRight.right指针指向cur，让其指回null，cur向右移动
 * 
 * tips：Morris遍历深度模仿递归遍历，在Morris遍历中不同位置添加打印即可得到不同遍历顺序
 */

public class MorrisTraversal {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // 前序遍历，第一次遇到当前节点时打印
    public static void morrisPre(Node root) {
        if (root == null) {
            return;
        }
        Node cur = root;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.print(cur.value + " "); // <-----
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    cur = cur.right;
                }
            // 当前节点没有左子树时，相当于一下遍历当前节点两次
            } else {
                System.out.print(cur.value + " "); // <-----
                cur = cur.right;
            }
        }
        System.out.println();
    }

    // 中序遍历，第二次遇到当前节点时打印
    public static void morrisIn(Node root) {
        if (root == null) {
            return;
        }
        Node cur = root;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    System.out.print(cur.value + " "); // <-----
                    cur = cur.right;
                }
            // 当前节点没有左子树时，相当于一下遍历当前节点两次
            } else {
                System.out.print(cur.value + " "); // <-----
                cur = cur.right;
            }
        }
        System.out.println();
    }

    // 后序遍历，逆序打印真的第二次回到自己的节点左子树的右边界，函数退出前打印整棵树的右边界
    public static void morrisPos(Node root) {
        if (root == null) {
            return;
        }
        Node cur = root;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    printEdge(cur.left); // <-----
                    cur = cur.right;
                }
            } else {
                cur = cur.right;
            }
        }
        printEdge(root);
        System.out.println();
    }

    // 逆序打印当前节点右边界
    public static void printEdge(Node node) {
        Node tail = reverseEdge(node);
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    // 反转当前节点右边界
    public static Node reverseEdge(Node from) {
        Node pre = null;
        Node succ = null;
        while (from != null) {
            succ = from.right;
            from.right = pre;
            pre = from;
            from = succ;
        }
        return pre;
    }

    // for test
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		Node head = new Node(4);
		head.left = new Node(2);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(5);
		head.right.right = new Node(7);
		printTree(head);
		morrisPre(head);  //先序
		morrisIn(head);   //中序
		morrisPos(head);  //后序
		printTree(head);

	}
}