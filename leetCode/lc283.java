/*
 * @lc app=leetcode.cn id=283 lang=java
 *
 * [283] 移动零
 * 
 * 1、暴力，遇到0则将后面元素全部前移一位
 * 2、双指针，前指针为元素为0的位置，后指针为元素不为0的位置，swap
 */
class Solution {
    public void moveZeroes(int[] nums) {
        for (int i = 0, j = 1; j < nums.length;) {
            if (nums[i] == 0 && nums[j] != 0) {
                swap(nums, i, j);
            } else if (nums[i] == 0 && nums[j] == 0) {
                j++;
            } else if (nums[i] != 0 && nums[j] == 0) {
                i++;
                j++;
            } else {
                i += 2;
                j += 2;
            }
        }
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }
}

