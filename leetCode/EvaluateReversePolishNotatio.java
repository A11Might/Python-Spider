import java.util.Stack;

/*
 * @lc app=leetcode.cn id=150 lang=java
 *
 * [150] 逆波兰表达式求值
 * 将数字压入栈中，遇到操作符时，弹出两个数字运算
 */
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> numbers = new Stack<>();
        for (String str : tokens) {
            if (str.equals("+")) {
                numbers.push(numbers.pop() + numbers.pop());
            } else if (str.equals("-")) {
                int succNum = numbers.pop();
                numbers.push(numbers.pop() - succNum);
            } else if (str.equals("*")) {
                numbers.push(numbers.pop() * numbers.pop());
            } else if (str.equals("/")) {
                int succNum = numbers.pop();
                numbers.push(numbers.pop() / succNum);
            } else {
                numbers.push(Integer.valueOf(str));
            }
        }
        return numbers.pop();
    }
}
