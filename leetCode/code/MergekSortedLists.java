import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=23 lang=java
 *
 * [23] 合并K个排序链表
 * 将各个链表的首节点放入小根堆，
 * 弹出堆顶元素即为当前各个链表中的最小值，将弹出节点的后继节点再次放入小根堆，再弹出栈顶
 * 直至小根堆为空，返回头结点
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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length < 1) { // 实例[]
            return null;
        }
        if (lists.length < 2) { // 只有一个链表时，直接返回lists[0]
            return lists[0];
        }
        ListNode sentry = new ListNode(0); // 哨兵节点，排序后的链表挂在其后面
        ListNode end = sentry; // 已排序链表的最后一个元素
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(new MyComparator());
        for (ListNode node : lists) {
            if (node != null) { // 实例[[1], [], [3]]或[[],[]]
                minHeap.add(node);
            }
        }
        while (!minHeap.isEmpty()) {
            ListNode cur = minHeap.poll();
            end.next = cur;
            end = cur;
            if (cur.next != null) {
                minHeap.add(cur.next); // cur.next加入栈中，即比较剩下的节点
            }
        }
        return sentry.next;
    }
}

class MyComparator implements Comparator<ListNode> { // 按val值从小到大排

    @Override
    public int compare(ListNode o1, ListNode o2) {
        return o1.val - o2.val;
    }
    
}
