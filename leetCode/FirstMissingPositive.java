/*
 * @lc app=leetcode.cn id=41 lang=java
 *
 * [41] 缺失的第一个正数
 * 遍历数组把1<=nums[i]<=nums.length的值放到原数组对应位置，
 * 再次遍历数组查当前下标是否和值对应
 * 若不对应那这个下标就是答案，若遍历完都没有答案就是数组长度加1。
 */
class Solution {
    public int firstMissingPositive(int[] nums) {
        int l = nums.length;
        for (int i = 0; i < l; ++i) {
            if ((nums[i] >= 1 && nums[i] <= l) && (i != nums[i] - 1)) { // 在1<=nums[i]<=nums.length范围内，但未在原数组对应位置上的元素
                if (nums[nums[i] - 1] == nums[i]) { // 若原数组对应位置上的元素已为对应元素，则不用重复放置，防止死循环。实例[1, 1]
                    continue;
                }
                swap(nums, i, nums[i] - 1); // 放在对应位置
                i--; // 交换后，重新判断i上元素(即判断新换回来的元素)
            }
        }
        for (int i = 0; i < l; ++i) {
            if (nums[i] != i + 1) { // 元素不对应下标，则为答案
                return i + 1;
            }
        }
        return l + 1; // 全部元素对应下标，则答案为nums.length + 1
    }

    public void swap(int[] nums, int a, int b) {
        int temp = nums[b];
        nums[b] = nums[a];
        nums[a] = temp;
    }
}
