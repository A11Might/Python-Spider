import java.util.Deque;
import java.util.LinkedList;

/*
 * @lc app=leetcode.cn id=239 lang=java
 *
 * [239] 滑动窗口最大值
 * 
 * 维护保存当前窗口最大值下标的一个双向队列
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length < 1) { // 实例[]
            return nums;
        }
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; ++i) {
            while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) { // 保证从大到小，如果前面数小则弹出(窗口中进入队列的数值较大，则可忽略窗口中进入队列数值较小的)
                deque.removeLast();
            }
            deque.addLast(i); // 添加当前值对应的数组下标，用于判断数值是否过期
            if (deque.getFirst() <= i - k) { // 窗口移动后删除出窗的数值
                deque.removeFirst();
            }
            if (i >= k - 1) { // 窗口长度为k时，再保存当前窗口中最大值
                res[i - (k - 1)] = nums[deque.getFirst()];
            }
        }
        return res;
    }
}
