/**
 * 给定彼此独立的两棵树头结点分别为 t1 和 t2， 
 * 判断 t1 中是否有与 t2 树拓扑结构完全相同的子树， 
 * t1 树有与 t2 树拓扑结构完全相同的子树，所以返回true，否则返回false
 * 
 * 先将两棵二叉树以相同的方式序列化，然后利用字符串的 KMP 匹配算法，在 t1 序列化的字符串中寻找 t2 序列化的字符串
 * 时间复杂度为 O(N + M)
 */

public class ContainSameSubTree {
    public static boolean IsContainSameSubTree(Node h1, Node h2) {
        if (h2 == null) {
            return true;
        }
        if (h1 == null) {

            return false;
        }
        String str1 = serialByPre(h1);
        String str2 = serialByPre(h2);
        return getIndexOf(str1, str2) != -1;
    }

    public static String serialByPre(Node head) {
        if (head == null) {
            return "#_";
        }
        String res = head.value + "_";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int[] next = getNextArray(str2);
        while (i1 < str1.length && i2 < str2.length) {
            if (str1[i1] == str2[i2]) {
                i1++;
                i2++;
            } else if (next[i2] == -1) {
                i1++;
            } else {
                i2 = next[i2];
            }
        }
        return i2 == str2.length ? i1 - i2 : -1;
    }

    public static int[] getNextArray(char[] str) {
        if (str.length == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[str.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < next.length) {
            if (str[i - 1] == str[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        Node h1 = new Node(1);
        h1.left = new Node(2);
        h1.right = new Node(3);
        h1.left.left = new Node(4);
        h1.left.right = new Node(5);
        h1.right.left = new Node(6);
        h1.right.right = new Node(7);
        h1.left.left.right = new Node(8);
        h1.left.right.left = new Node(9);

        Node h2 = new Node(2);
        h2.left = new Node(4);
        h2.right = new Node(5);
        h2.left.right = new Node(8);
        h2.right.left=new Node(9); // 注释前true，注释后false

        System.out.println(IsContainSameSubTree(h1, h2));

    }
}

class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }
}