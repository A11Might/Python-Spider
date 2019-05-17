/*
 * @lc app=leetcode.cn id=237 lang=java
 *
 * [237] 删除链表中的节点
 * 
 * 想要删除的节点的值替换为它后面节点中的值，然后删除它之后的节点
 * 我说没前继节点咋删，原来这样，牛批。之前链表题说不能修改节点值终于知道为什么了:)
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
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}

