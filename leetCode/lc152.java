/*
 * @lc app=leetcode.cn id=152 lang=java
 * [152] 乘积最大子序列
 * 
 * 思路： 求最大值，可以看成求被0拆分的各个子数组的最大值。
 * 当一个数组中没有0存在，则分为两种情况：
 *  1.负数为偶数个，则整个数组的各个值相乘为最大值；
 *  2.负数为奇数个，则从左边开始，乘到最后一个负数停止有一个“最大值”，从右边也有一个“最大值”，比较，得出最大值。
 */
class Solution {
    public int maxProduct(int[] nums) {
        int product = 1, max = nums[0];
        for (int num : nums) {
            product *= num;
            if (product > max) {
                max = product;
            }
            if (num == 0) {
                product = 1;
            }
        }
        product = 1;
        for (int i = nums.length - 1; i >= 0; --i) {
            product *= nums[i];
            if (product > max) {
                max = product;
            }
            if (nums[i] == 0) {
                product = 1;
            }
        }
        return max;
    }
}

// >>>干<<<
// 判断max * b有没有变大，可以和max比有没有变大，也可以和b比有没有变大
// 与b比较取最值，当b == 0 时，也是重新计算乘积，即以0分割
// 同时维护当前乘积最小最大值，用于解决负数奇偶问题
// 1> 奇数时，每个负数之间imax比较出一个最大值即为res
// 2> 偶数时，imin为最后一个负数之前的乘积，乘上后面的负数为max
class Solution2 {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1; // 一个保存最大的，一个保存最小的。
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tmp = imax;
                imax = imin;
                imin = tmp;
            } // 如果数组的数是负数，那么会导致最大的变最小的，最小的变最大的。因此交换两个的值。
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);

            max = Math.max(max, imax);
        }
        return max;
    }
}

