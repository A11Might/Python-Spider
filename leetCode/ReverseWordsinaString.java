/*
 * @lc app=leetcode.cn id=151 lang=java
 *
 * [151] 翻转字符串里的单词
 * 以空格为分隔符取得每个单词，翻转单词
 */
class Solution {
    public String reverseWords(String s) {
        if (s.length() == 0 || s == null) {
            return "";
        }
        StringBuilder res = new StringBuilder(""); // 字符串的循环累加的时候用StringBuilder
        String[] strs = s.trim().split(" +"); // trim()去除首尾空格，" +"多个空格
        for (int i = strs.length - 1; i >= 0; --i) {
            if (i == 0) {
                res.append(strs[i]);
            } else {
                res.append(strs[i] + " ");
            }
        }
        return res.toString();
    }
}

