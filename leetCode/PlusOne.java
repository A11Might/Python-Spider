/*
 * @lc app=leetcode.cn id=66 lang=java
 *
 * [66] 加一
 * 
 * 1、用一个carry维护每位上的进位
 * 2、转成整数加一后，再转成数组
 */
class Solution {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; --i) {
            int temp = (digits[i] + carry) / 10;
            digits[i] = (digits[i] + carry) % 10;
            carry = temp;
            if (carry == 0) {
                break;
            }
        }
        if (carry != 0) {
            int[] res = new int[digits.length + 1];
            int count = 1;
            res[0] = carry;
            for (int num : digits) {
                res[count++] = num;
            }
            return res;
        }
        return digits;
    }
}

