/*
 * @lc app=leetcode.cn id=169 lang=java
 *
 * [169] 求众数
 * 
 * 排序后，出现次数大于⌊ n/2 ⌋即为众数。
 */
class Solution {
    public int majorityElement(int[] nums) {
        java.util.Arrays.sort(nums);
        int time = nums.length / 2;
        int cur = nums[0]; // 当前正在计数的元素
        int count = 0; // 出现次数
        for (int num : nums) {
            if (cur == num) {
                count++;
                if (count > time) {
                    return cur;
                }
            } else {
                count = 1;
                cur = num;
            }
        }
        return -1; // 没有众数
    }
}
