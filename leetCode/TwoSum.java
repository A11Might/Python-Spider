import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=1 lang=java
 *
 * [1] 两数之和
 * 
 * 1、暴力，两层遍历
 * 2、用哈希表，省去一层遍历
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            int aim = target - nums[i];
            if (map.containsKey(aim)) {
                return new int[] {map.get(aim), i};
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }
}

