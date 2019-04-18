import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化
 */

 public class SerializeAndReconstructTree {
     public static class Node {
         public int value;
         public Node left;
         public Node right;

         public Node(int value) {
             this.value = value;
         }
     }

     /**
      * 先序遍历序列化与反序列化
      * @param head
      * @return
      */
     public static String serialByPre(Node head) {
         if (head == null) {
             return "#_";
         }
         String res = head.value + "_";
         res += serialByPre(head.left);
         res += serialByPre(head.right);
         return res;
     }

     public static Node reconByPreString(String preStr) {
         String[] values = preStr.split("_");
         Queue<String> queue = new LinkedList<String>();
         for (int i = 0; i < values.length; ++i) {
             queue.offer(values[i]);
         }
         return reconPreOrder(queue);
     }

     public static Node reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
     }

     /**
      * 层次遍历实现序列化和反序列化
      * @param head
      * @return
      */
     public static String serialByLevel(Node head) {
         if (head == null) {
             return "#_";
         }
         String res = head.value + "_";
         Queue<Node> queue = new LinkedList<>();
         Node cur = null;
         queue.offer(head);
         while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur.left != null) {
                res += cur.left.value + "_";
                queue.offer(cur.left);
            } else {
                res += "#_";
            }
            if (cur.right != null) {
                res += cur.right.value + "_";
                queue.offer(cur.right);
            } else {
                res += "#_";
            }
         }
         return res;
     }

     public static Node reconByLevelString(String levelStr) {
        String[] values = levelStr.split("_");
        int index = 0;
        Node head = generateNodeByString(values[index++]);
        if (head == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        Node cur = null;
        queue.offer(head);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            cur.left = generateNodeByString(values[index++]);
            cur.right = generateNodeByString(values[index++]);
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        } 
        return head;
     }

     public static Node generateNodeByString(String val) {
         if (val.equals("#")) {
             return null;
         }
         return new Node(Integer.valueOf(val));
     }

     // for test -- print tree
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
        Node head = null;
        printTree(head);

        String pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        String level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(1);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.right = new Node(5);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(100);
        head.left = new Node(21);
        head.left.left = new Node(37);
        head.right = new Node(-42);
        head.right.left = new Node(0);
        head.right.right = new Node(666);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

    }
 }