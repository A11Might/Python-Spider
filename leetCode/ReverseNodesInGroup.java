/*
 * @lc app=leetcode.cn id=25 lang=java
 *
 * [25] k个一组翻转链表
 * 
 * 递归：f(n) = 反转k个节点 + f(n - k)
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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode tail = head;
        int count = 1;
        // 从头数k个节点
        while (tail.next != null && count < k) {
            tail = tail.next;
            count++;
        }
        // 有k个节点
        if (count == k) {
            tail.next = reverseKGroup(tail.next, k);
            ListNode cur = head;
            ListNode succ = cur.next;
            while (cur != tail) {
                cur.next = tail.next;
                tail.next = cur;
                cur = succ;
                succ = cur.next;
            }
            return tail;
        // 不够k个
        } else {
            return head;
        }
    }
}

