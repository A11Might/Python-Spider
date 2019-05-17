/*
 * @lc app=leetcode.cn id=234 lang=java
 *
 * [234] 回文链表
 * 
 * 反转后半部分链表，再同时从头节点尾节点遍历，比较值是否相等，全部相等即为回文链表
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
    public boolean isPalindrome(ListNode head) {
        boolean res = true; // 标记链表是否为回文链表，方便之后恢复链表
        ListNode fast = head;
        ListNode slow = head;
        // 找到链表中间位置
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode pre = null;
        ListNode cur = slow;
        // 反转后半部分链表
        while (cur != null) {
            ListNode succ = cur.next;
            cur.next = pre;
            pre = cur;
            cur = succ;
        }
        cur = pre;
        // 判断是否为回文链表
        while (cur != head && cur != null) {
            if (cur.val != head.val) {
                res = false;
                break;
            }
            cur = cur.next;
            head = head.next;
        }
        cur = pre;
        pre = null;
        // 恢复链表
        while (cur != null) {
            ListNode succ = cur.next;
            cur.next = pre;
            pre = cur;
            cur = succ;
        }
        return res;
    }
}

