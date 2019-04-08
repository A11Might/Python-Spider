import java.util.HashMap;

/**
 * 复制含有随机指针节点的链表 Node类中的value是节点值 next指针和正常单链表中next指针的意义一样，都指向下一个节点
 * rand指针是Node类中新增的指针，这个指针可能指向链表中的任意一个节点，也可能指向null
 * 给定一个由Node节点类型组成的无环单链表的头节点head，请实现一个函数完成这个链表中所有结构的复制，并返回复制的新链表的头节点
 * 
 * 进阶：不使用额外的数据结构，只用有限几个变量，且在时间复杂度为O(N)内完成原问题要实现的函数
 */

 public class CopyListWithRandom {
     public static class Node {
         public int value;
         public Node next;
         public Node rand;

         public Node(int value) {
             this.value = value;
         }
     }

    /**
     * 维护一个哈希表存储，原节点和拷贝节点。时间复杂度O(N)，空间复杂度O(N)
     * @param head
     * @return
     */
    public static Node copyListWithRand1(Node head) {
        HashMap<Node, Node> map = new HashMap<Node, Node>();
        Node p = head;
        while (p != null) {
            map.put(p, new Node(p.value));//储存原节点及其拷贝
            p = p.next;
        }
        p = head;
        while (p != null) {
            map.get(p).next = map.get(p.next);//复制next
            map.get(p).rand = map.get(p.rand);//复制rand
            p = p.next;
        }
        return map.get(head);
    }   

    /**
     * 时间复杂度O(N)，空间复杂度O(1)
     */
    public static Node copyListWithRand2(Node head) {
        if (head == null) {
            return null;
        }
        Node p = head;
        Node succ = null;
        while (p != null) {
            succ = p.next;
            p.next = new Node(p.value);
            p.next.next = succ;
            p = succ;
        }
        p = head;
        Node pCopy = null;
        while (p != null) {
            pCopy = p.next;
            pCopy.rand = p.rand != null ? p.rand.next : null;
            p = pCopy.next;
        }
        Node res = head.next;
        p = head;
        while (p != null) {
            pCopy = p.next;
            succ = pCopy.next;
            p.next = succ;
            pCopy.next = succ != null ? succ.next : null;
            p = succ;      
        }
        return res;
    }
    
    //for test
    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

    }
 }
