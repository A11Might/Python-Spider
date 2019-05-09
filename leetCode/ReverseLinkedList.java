/*
 * @lc app=leetcode.cn id=206 lang=java
 *
 * [206] 反转链表
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
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode succ = cur.next; // 防止断链
            cur.next = pre;
            pre = cur;
            cur = succ;
        }
        return pre;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        return process(head);
    }

    private ListNode process(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode res = process(head.next);
        head.next.next = head;
        head.next = null; // 让反转后最后一个节点的next为null
        return res;
    }
}

