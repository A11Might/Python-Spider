import java.util.Stack;

/**
 * 实现二叉树的先序、中序、后序遍历，包括递归方式和非递归方式
 */

 public class PreInPosTraversal {
     public static class Node {
         public int value;
         public Node left;
         public Node right;

         public Node(int value) {
             this.value = value;
         }
     }

     /**
      * 先序遍历
      * @param head
      */
     public static void preOrderRecur(Node head) {
         if (head == null) {
             return;
         }
         System.out.print(head.value + " ");
         preOrderRecur(head.left);
         preOrderRecur(head.right);
     }

     public static void preOrderUnRecur(Node head) {
         if (head != null) {
             Stack<Node> stack = new Stack<>();
             Node cur = null;
             stack.push(head);
             while (!stack.isEmpty()) {
                cur = stack.pop();
                System.out.print(cur.value + " ");
                /**
                 * 栈中先压右，再压左，弹出顺序为先左后右
                 */
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
             }
         }
         System.out.println();
     }

     /**
      * 中序遍历
      * @param head
      */
     public static void inOrderRecur(Node head) {
         if (head == null) {
             return;
         }
         inOrderRecur(head.left);
         System.out.print(head.value + " ");
         inOrderRecur(head.right);
     }

     public static void inOrderUnRecur(Node head) {
         if (head != null) {
             Stack<Node> stack = new Stack<>();
             Node cur = head;
             while (!stack.isEmpty() || cur != null) {
                 if (cur != null) {
                     stack.push(cur);
                     cur = cur.left;
                 } else {
                     cur = stack.pop();
                     System.out.print(cur.value + " ");
                     cur = cur.right;
                 }
             }
         }
     }

     /**
      * 后序遍历
      * @param head
      */
     public static void posOrderRecur(Node head) {
         if (head == null) {
             return;
         }
         posOrderRecur(head.left);
         posOrderRecur(head.right);
         System.out.print(head.value + " ");
     }

     //用两个栈实现后序遍历（改造先序遍历）
     public static void posOrderUnRecur1(Node head) {
         if (head != null) {
             Stack<Node> stack = new Stack<>(); //弹出顺序中右左
             Stack<Node> help = new Stack<>(); //弹出顺序左右中
             stack.push(head);
             Node cur = null;
             while (!stack.isEmpty()) {
                 cur = stack.pop();
                 help.push(cur);
                 /**
                 * 栈中先压左，再压右，弹出顺序为先右后左
                 */
                 if (cur.left != null) {
                     stack.push(cur.left);
                 }
                 if (cur.right != null) {
                     stack.push(cur.right);
                 }
             }
             while (!help.isEmpty()) {
                 System.out.print(help.pop().value + " ");
             }
         }
         System.out.println();
     }

     //使用一个栈实现后序遍历
     public static void posOrderUnRecur2(Node head) {
         if (head != null) {
             Stack<Node> stack = new Stack<>();
             stack.push(head);
             Node cur = null;
             Node visited = head; //用visited标记打印过的最后一个节点，初始化为第一个节点；若初始化为null，则会漏打节点
             while (!stack.isEmpty()) {
                 cur = stack.peek();
                 if (cur.left != null && cur.left != visited && cur.right != visited) {
                     stack.push(cur.left);
                 } else if (cur.right != null && cur.right != visited) {
                     stack.push(cur.right);
                 } else {
                     System.out.print(stack.pop().value + " ");
                     visited = cur;
                 }
             }
         }
         System.out.println();
     }

     //for test
     public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        // recursive
        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        posOrderRecur(head);
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        System.out.print("pre-order: ");
        preOrderUnRecur(head);
        System.out.print("in-order: ");
        inOrderUnRecur(head);
        System.out.print("pos-order: ");
        posOrderUnRecur1(head);
        System.out.print("pos-order: ");
        posOrderUnRecur2(head);

    }
 }
