/*
 * @lc app=leetcode.cn id=10 lang=java
 *
 * [10] 正则表达式匹配
 * 
 * 分两种情况：1> 字母与字母(后面的字母可以为.)
 *            2> 字母与字母加*(后面的字母可以为.) 1) s匹配0-n个字母加*
 *                                             2) 忽略字母加*
 */
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        return process(s.toCharArray(), p.toCharArray(), 0, 0);
    }

    private boolean process(char[] s, char[] p, int indexS, int indexP) {
        // 已经匹配完毕
        if (indexP == p.length) {
            return indexS == s.length;
        }
        // p的下一个字符不是* 则需要当前字符相等 且后面全部匹配
        if (indexP == p.length - 1 || p[indexP + 1] != '*') {
            return indexS < s.length && (s[indexS] == p[indexP] || p[indexP] == '.') && process(s, p, indexS + 1, indexP + 1);
        }
        // p的下一个字符是* 且有s[i] == p[j] || p[j] == '.')
        // p[j]*匹配零个或多个s中元素，直到s[i] ！= p[j]
        while (indexS < s.length && (s[indexS] == p[indexP] || p[indexP] == '.')) {
                if (process(s, p , indexS, indexP + 2)) {
                    return true;
                }
                indexS++;
        }
        // 不能用*匹配时略过*
        return process(s, p, indexS, indexP + 2);
    }

}

