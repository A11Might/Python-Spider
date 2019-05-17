/*
 * @lc app=leetcode.cn id=142 lang=java
 *
 * [142] 环形链表 II
 */
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        // fast到达链尾时，退出循环
        while (fast != null && fast.next != null) { 
            fast = fast.next.next;
            slow = slow.next;
            // 快慢指针相遇，说明有环
            if (fast == slow) { 
                break;
            }
        }
        // 快指针到达链尾，无环
        if (fast == null || fast.next == null) {
            return null;
        }
        // 有环，将slow置为head，fast不变，步速都为1，相遇则为入环的第一个节点
        slow = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}

