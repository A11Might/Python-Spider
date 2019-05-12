/*
 * @lc app=leetcode.cn id=21 lang=java
 *
 * [21] 合并两个有序链表
 * 
 * 同归并排序的merge
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 使用dummy哨兵节点和tail方便维护返回的链表
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy; // 返回链表的最后一个节点
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                tail = tail.next;
                l1 = l1.next;
            } else {
                tail.next = l2;
                tail = tail.next;
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            tail.next = l2;
        } else {
            tail.next = l1;
        }
        return dummy.next;
    }
}

