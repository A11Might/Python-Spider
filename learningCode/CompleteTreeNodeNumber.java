/**
 * 已知一棵完全二叉树，求其节点的个数 
 * 要求：时间复杂度低于O(N)，N为这棵树的节点个数
 */

 public class CompleteTreeNodeNumber {
     public static class Node {
         public int value;
         public Node left;
         public Node right;

         public Node(int value) {
             this.value = value;
         }
     }

     public static int nodeNum(Node head) {
        return bs(head, 1, mostLeftLevel(head));
     }

     /**
      * 计算以node为头结点的子树结点个数；level为node结点所在深度 + 1；h为总深度 + 1
      * @param node
      * @param level
      * @param h
      * @return
      */
     public static int bs(Node node, int level, int h) {
         if (level == h) {//node为叶结点，以node为头结点的子树结点总数为1
             return 1;
         }
         if (mostLeftLevel(node.right) + level == h) {//当前节点的右子树的最左叶结点的level为h，所以node节点左子树为满二叉树
             return (1 << (h - level)) + bs(node.right, level +1, h);
         } else {//当前节点的右子树的最左节点的level为h - 1，所以node节点右子树为满二叉树
             return bs(node.left, level + 1, h) + (1 << (h - level - 1));
         }
     }

     /**
      * 获得以当前节点为头结点的子树的叶结点的level
      * @param node
      * @return
      */
     public static int mostLeftLevel(Node node) {
         int level = 0;
         while (node != null) {
             ++level;
             node = node.left;
         }
         return level;
     }

     //for test
     public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(nodeNum(head));

    }
 }
