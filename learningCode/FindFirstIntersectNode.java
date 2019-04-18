/**
 * 两个单链表相交的一系列问题 
 * 在本题中，单链表可能有环，也可能无环。
 * 给定两个单链表的头节点head1和head2，这两个链表可能相交，也可能不相交。
 * 请实现一个函数，如果两个链表相交，请返回相交的 第一个节点；果不相交，返回null 即可。
 * 要求：如果链表1的长度为N，链表2的长度为M，时间复杂度请达到 O(N+M)，额外空间复杂度请达到O(1)。
 */

public class FindFirstIntersectNode {
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    /**
     * 判断链表是否有环
     * @param head
     * @return
     */
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {//判断链表是否为空或走一步是否到达链尾
            return null;
        }
        /**
         * 先走一步
         */
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        /**
         * 找入环节点
         */
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 两个无环链表的交点
     * @param head1
     * @param head2
     * @return
     */
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node p1 = head1;
        Node p2 = head2;
        int i = 0;//记录两链表节点个数的差值
        while (p1.next != null) {
            ++i;
            p1 = p1.next;
        }
        while (p2.next != null) {
            --i;
            p2 = p2.next;
        }
        if (p1 != p2) {
            return null;
        }
        p1 = i > 0 ? head1 : head2;
        p2 = p1 == head1 ? head2 : head1;
        i = Math.abs(i);
        while (i != 0) {
            --i;
            p1 = p1.next;
        }
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    /**
     * 两个有环链表的交点
     * @param head1
     * @param loop1
     * @param head2
     * @param loop2
     * @return
     */
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        if (loop1 == loop2) {//同两个无环情况
            Node p1 = head1;
            Node p2 = head2;
            int i = 0;
            while (p1.next != loop1) {
                ++i;
                p1 = p1.next;
            }
            while (p2.next != loop2) {
                --i;
                p2 = p2.next;
            }
            if (p1 != p2) {
                return null;
            }
            p1 = i > 0 ? head1 : head2;
            p2 = p1 == head1 ? head2 : head1;
            i = Math.abs(i);
            while (i != 0) {
                --i;
                p1 = p1.next;
            }
            while (p1 != p2) {
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1;
        } else {//剩下的两种情况
            Node p = loop1.next;
            while (p != loop1) {
                if (p == loop2) {
                    return loop1;
                }
                p = p.next;
            }
            return null;
        }
    }

    //for test
    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }
}
