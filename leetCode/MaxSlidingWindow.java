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

// another way
class Solution2 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if (len == 0)
            return new int[] {};
        if (len == 1)
            return new int[] { nums[0] };
        int localMax = Integer.MIN_VALUE;
        int[] result = new int[len - k + 1];
        for (int i = 0; i < k; i++) {
            localMax = Math.max(nums[i], localMax);
        }
        result[0] = localMax;
        for (int i = 1; i < len - k + 1; i++) {
            if (nums[i + k - 1] > localMax) {
                localMax = nums[i + k - 1];
            } else if (nums[i - 1] == localMax) {
                localMax = nums[i];
                for (int x = i; x < i + k; x++) {
                    localMax = Math.max(nums[x], localMax);
                }
            }
            result[i] = localMax;
        }
        return result;
    }
}
