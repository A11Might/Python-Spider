import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=15 lang=java
 *
 * [15] 三数之和
 * 
 * 排序后固定第一个元素，寻找另两个元素。
 * 两数之和的O(n)的方法不试用，会重复，且逆序。实例[0, 0 , 0]和[0, 0, 0 ,0]
 */
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        java.util.Arrays.sort(nums); // 排序方便跳过重复元素
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; ++i) { // 先固定第一个数，再在后面的区间内找合为0 - nums[i]的两个数
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) { // 跳过重复元素
                int twoSum = 0 - nums[i];
                int l = i + 1;
                int r = nums.length - 1;
                while (l < r) {
                    if (nums[l] + nums[r] == twoSum) { // 用后面数组最小值加最大值与0 - nums[i]比较，判断两数位置
                        res.add(java.util.Arrays.asList(nums[i], nums[l], nums[r])); // 命中加入返回列表，继续寻找
                        while (l < r && nums[++l] == nums[l - 1]) { // 跳过重复元素
                        }
                        while (l < r && nums[--r] == nums[r + 1]) {
                        }
                    } else if (nums[l] + nums[r] > twoSum) {
                        while (l < r && nums[--r] == nums[r + 1]) {
                        }
                    } else {
                        while (l < r && nums[++l] == nums[l - 1]) {
                        }
                    }
                }
            }

        }
        return res;
    }
}
