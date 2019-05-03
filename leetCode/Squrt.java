/*
 * @lc app=leetcode.cn id=69 lang=java
 *
 * [69] x 的平方根
 * 
 * 在x中二分查找，平方不大于x最大的数
 */
class Solution {
    public int mySqrt(int x) {
        if (x == 1) { // 实例 1
            return 1;
        }
        int l = 0;
        int mid = x >> 1;
        int r = x;
        while (l < mid && l < r) { // 防止出现l == mid而循环的情况。实例 99
            if (Math.pow(mid, 2) > x) {
                r = mid;
                mid = l + ((mid - l) >> 1);
            } else if (Math.pow(mid, 2) < x) {
                l = mid;
                mid = mid + ((r - mid) >> 1);
            } else {
                return mid;
            }
        }
        return l;
    }
}
