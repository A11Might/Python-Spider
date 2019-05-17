import java.util.HashMap;
import java.util.Map.Entry;

/*
 * @lc app=leetcode.cn id=217 lang=java
 *
 * [217] 存在重复元素
 * 
 * 1、暴力遍历，O(n^2)
 * 2、排序后，比较前后元素，O(nlongn)
 * 3、HashMap<Integer, Integer> 注册数组中元素及其出现次数 O(n) 空间复杂度O(n)
 */
class Solution {
    public boolean containsDuplicate3(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        HashMap<Integer, Integer> numTimesMap = new HashMap<>();
        for (int num : nums) {
            if (!numTimesMap.containsKey(num)) {
                numTimesMap.put(num, 1);
            } else {
                numTimesMap.put(num, numTimesMap.get(num) + 1);
            }
        } 
        for (Entry<Integer, Integer> entry : numTimesMap.entrySet()) {
            if (entry.getValue() > 1) {
                return true;
            }
        }
        return false;
    }


    public boolean containsDuplicate2(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        java.util.Arrays.sort(nums);
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }
}

