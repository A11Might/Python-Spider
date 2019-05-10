import java.util.Stack;

/*
 * @lc app=leetcode.cn id=32 lang=java
 *
 * [32] 最长有效括号
 *
 * 遇到左括号下标入栈，遇到右括号出栈并更新最大值
 */
class Solution {
    public int longestValidParentheses(String s) {
        int start = 0; // 记录每个子串的起点
        int res = 0; // 最长子串的长度
        Stack<Integer> help = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            char cur = s.charAt(i);
            if (cur == '(') {
                help.push(i); // 存入当前字符下标，用于更新当前有效子串长度
            } else {
                if (help.isEmpty()) { // 字符不匹配，当前子串结束，记录下个子串
                    start = i + 1;
                } else {
                    help.pop(); // 字符匹配，抵消掉
                    res = help.isEmpty() ? Math.max(res, i - start + 1) : Math.max(res, i - help.peek()); // 实时更新最长子串长度，当前index与栈顶之间的距离(若栈为空，则一直到start)
                }
            }
        }
        return res;
    }
}
