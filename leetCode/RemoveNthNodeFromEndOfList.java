/*
 * @lc app=leetcode.cn id=19 lang=java
 *
 * [19] 删除链表的倒数第N个节点
 * 
 * 使用前后指针，后指针走n步后，前后指针再同时走
 * 当后指针到达链尾，前指针在倒数n + 1位置，方便删除倒数第n个节点
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0); // 使用哨兵节点，方便解决删除头结点
        dummy.next = head;
        ListNode l = dummy;
        ListNode r = dummy;
        // 后指针先走n步
        while (n > 0) {
            r = r.next;
            n--;
        }
        // 当后指针到达链尾，前指针在倒数n + 1位置
        while (r.next != null) {
            l = l.next;
            r = r.next;
        }
        l.next = l.next.next; // 删除倒数第n个节点
        return dummy.next;
    }
}

