/*
 * @lc app=leetcode.cn id=88 lang=java
 *
 * [88] 合并两个有序数组
 * 
 * 类似归并排序，但不申请额外空间(题意nums1可以放下nums2中的元素)，在nums1上从后往前排序(从大到小)
 * 牛批，思维定式一直想申请额外空间或从前排序
 */
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m + n - 1; // 返回数组的最后一个有效元素下标
        m--; // num1中未比较的最后一个元素的下标
        n--; // num2中未比较的最后一个元素的下标
        while (m >= 0 && n >= 0) {
            nums1[p--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
        // 若nums2中仍有未排序元素
        while (n >= 0) {
            nums1[p--] = nums2[n--];
        }
        // 若nums2中无未排序元素
    }
}
