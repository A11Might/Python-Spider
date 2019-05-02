import java.util.Stack;

/*
 * @lc app=leetcode.cn id=20 lang=java
 *
 * [20] 有效的括号
 * 用栈储存左括号，当遇到右括号时，弹出(为离当前右括号最近的左括号)判断是否成对
 */
class Solution {
    public boolean isValid(String s) {
        if (s == null) {
            return true;
        }
        char[] operator = s.toCharArray();
        Stack<Character> help = new Stack<>();
        for (char chr : operator) {
            if (chr == '(' || chr == '[' || chr == '{') {
                help.push(chr);
            } else if (chr == ')') {
                if (help.isEmpty() || help.pop() != '(') {
                    return false;
                }
            } else if (chr == ']') {
                if (help.isEmpty() || help.pop() != '[') {
                    return false;
                }
            } else if (chr == '}') {
                if (help.isEmpty() || help.pop() != '{') {
                    return false;
                }
            }
        }
        return help.isEmpty(); // 字符全部抵消，则为true(若有剩余说明有不匹配的字符)
    }
}
