/*
 * @lc app=leetcode.cn id=136 lang=java
 *
 * [136] 只出现一次的数字
 * 
 * 1、暴力遍历 O(n^2)
 * 2、排序后，比较前后元素 O(nlogn)
 * 2、异或，自己与自己异或为0 O(n)
 */
class Solution {
    public int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}

