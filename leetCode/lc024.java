/*
 * @lc app=leetcode.cn id=24 lang=java
 *
 * [24] 两两交换链表中的节点
 * 
 * 迭代：使用一个哨兵节点dummy和三个指针pre、head和succ
 * 递归：f(head) = head和succ交换 + f(head.next)
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
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        while (head != null && head.next != null) {
            ListNode succ = head.next;
            pre.next = succ;
            head.next = head.next.next;
            succ.next = head;
            pre = head;
            head = head.next;
        }
        return dummy.next;
    }

    public ListNode swapPairs2(ListNode head) {
        return process(head);
    }

    private ListNode process(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = process(next.next);
        next.next = head;
        return next;
    }
}

