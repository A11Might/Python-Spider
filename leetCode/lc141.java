/*
 * @lc app=leetcode.cn id=141 lang=java
 *
 * [141] 环形链表
 * 
 * 定义快慢指针，若慢指针能追上快指针，则为环形链表
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) { // fast到达链尾时，退出循环
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) { // 快慢指针相遇
                return true;
            }
        }
        return false;
    }
}

class ListNode {
     int val;
     ListNode next;

     ListNode(int x) {
         val = x;
         next = null;
     }
}
