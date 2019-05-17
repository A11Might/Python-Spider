/*
 * @lc app=leetcode.cn id=26 lang=java
 *
 * [26] 删除排序数组中的重复项
 * 
 * 双指针：慢快指针i和j，当nums[i] = nums[j]时，快指针j增加跳过重复项；
 *                     当nums[i] != nums[j]时，nums[i + 1] == nums[j]，快慢指针同时增加
 */
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; ) {
            if (nums[i] == nums[j]) {
                j++;
            } else {
                nums[++i] = nums[j++];
            }
        }
        return i + 1;
    }
}

