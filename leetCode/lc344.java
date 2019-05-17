/*
 * @lc app=leetcode.cn id=344 lang=java
 *
 * [344] 反转字符串
 * 
 * 使用首尾双指针，swap()函数原地反转字符串
 */
class Solution {
    public void reverseString(char[] s) {
        for (int i = 0, j = s.length - 1; i < j; ++i, --j) { // 字符串个数为偶数时i > j停止，奇数时i = j停止
            swap(s, i, j);
        }
    }

    private void swap(char[] s, int a, int b) {
        char temp = s[b];
        s[b] = s[a];
        s[a] = temp;
    }
}

