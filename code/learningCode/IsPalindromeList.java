import java.util.Stack;

/**
 * 判断一个链表是否为回文结构 给定一个链表的头节点head，请判断该链表是否为回文结构。
 * 例如：1->2->1，返回true。1->2->2->1，返回true。15->6->15，返回true。1->2->3，返回false。
 * 进阶：如果链表长度为N，时间复杂度达到O(N)，额外空间复杂度达到O(1)。
 */

 public class IsPalindromeList {
     public static class Node {
         public int value;
         public Node next;

         public Node(int value) {
             this.value = value;
         }
     }

    //使用辅助栈，时间复杂度达到O(N)，额外空间复杂度达到O(N)
     public static boolean isPalindrome1(Node head) {
        Stack<Node> stack = new Stack<Node>();
        Node p = head;
        while (p != null) {
            stack.push(p);
            p = p.next;
        }
        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
     }

    //不使用辅助栈，时间复杂度达到O(N)，额外空间复杂度达到O(1)
     public static boolean isPalindrome2(Node head) {
         if (head == null || head.next == null) {
             return true;
         }
         Node slow = head;
         Node fast = head;
         while (fast.next != null && fast.next.next != null) {//快指针到达链尾，满指针到达中点
             slow = slow.next;
             fast = fast.next.next;
         }
         /**
          * 反转后半部分链表
          */
         Node pre = slow;
         Node p = slow.next;
         slow.next = null;// mid.next置位null，作为迭代停止条件
         Node succ = null;
         while (p != null) {
             succ = p.next;
             p.next = pre;
             pre = p;
             p = succ;
         }
         Node first = pre;//后半部分链表反转后的头结点
         boolean res = true;
         while (head != null) {
            if (first.value != head.value) {
                res = false;
                break;
            }
            first = first.next;
            head = head.next;
         }
         //将后半部分链表复原
         p = pre;
         pre = null;
         while (p != null) {
             succ = p.next;
             p.next = pre;
             pre = p;
             p = succ;
         }
         return res;
     }

     //for test
     public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.println(isPalindrome2(head));
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.println(isPalindrome2(head));
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.println(isPalindrome2(head));
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.println(isPalindrome2(head));
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.println(isPalindrome2(head));
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.println(isPalindrome2(head));
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.println(isPalindrome2(head));
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.println(isPalindrome2(head));
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.println(isPalindrome2(head));
        printLinkedList(head);
        System.out.println("=========================");

    }
 }
