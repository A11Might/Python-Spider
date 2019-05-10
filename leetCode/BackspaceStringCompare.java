import java.util.Stack;

/*
 * @lc app=leetcode.cn id=844 lang=java
 *
 * [844] 比较含退格的字符串
 * 
 * 用栈存储字符，遇到 '#' 则弹出一个字符，比较两个栈中元素是否相等
 */
class Solution {
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> stackS = new Stack<>();
        Stack<Character> stackT = new Stack<>();
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        for (char chr : s) {
            if (chr != '#') {
                stackS.push(chr);
            } else if (chr == '#' && !stackS.isEmpty()) {
                stackS.pop();
            }
        }
        for (char chr : t) {
            if (chr != '#') {
                stackT.push(chr);
            } else if (chr == '#' && !stackT.isEmpty()) {
                stackT.pop();
            }
        }
        while (!stackS.isEmpty() && !stackT.isEmpty()) {
            // 栈中元素不相等，为false
            if (stackS.pop() != stackT.pop()) {
                return false;
            }
        }
        // 栈都为空，两个栈中元素全部相同，为true，否则为false
        return stackS.isEmpty() && stackT.isEmpty();
    }
}

