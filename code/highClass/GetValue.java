import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 题目：给定一个字符串str表示一个公式，公式里可能有整数、加减乘除符号和左右括号，返回公式的计算结果
 * 
 * 注意：1、给定字符串一定合法
 *      2、如果是负数，就需要用括号括起来，比如4 * (-3)，但负数作为公式的开头或括号部分的开头，则可以没有括号-3 * 4和(-3 * 4)
 *      3、不考虑计算过程中会发生溢出的情况
 */

public class GetValue {
    public static int getValue(String str) {
        char[] chrs = str.toCharArray();
        return value(chrs, 0)[0];
    }

    // 返回值为含两个值的数组，bra[0] 括号中元素运算后的值，bra[1] 运算到第几位
    private static int[] value(char[] str, int index) {
        Deque<String> deque = new ArrayDeque<>();
        int curNum = 0;
        while (index < str.length && str[index] != ')') {
            char curChr = str[index++];
            if ('0' <= curChr && curChr <= '9') {
                curNum = curNum * 10 + (curChr - '0');
            } else if (curChr != '(') { // 当前位置元素为加减乘除
                // 每次添加都是一个数字一个符号
                addNum(deque, curNum); // 将当前统计的数加入双端队列
                deque.addLast(String.valueOf(curChr)); // 加入遍历到的运算符
                curNum = 0;
            } else {
                int[] bra = value(str, index); // 递归计算括号中的值
                curNum = bra[0];
                index = bra[1] + 1; 
            }
        }
        addNum(deque, curNum);  // 加入最后一个数(没有符号位给它相遇遇，所以额外添加它)(最后一个符号位是乘除的也计算了)
        return new int[] {getNum(deque), index};
    }

    // 将数值放在当前扩号所属queue中，并将之前的*或/先行运算掉，方便后续计算(只有加减)
    private static void addNum(Deque<String> deque, int num) {
        if (!deque.isEmpty()) {
            String top = deque.pollLast();
            if (top.equals("*") || top.equals("/")) {
                int preNum = Integer.valueOf(deque.pollLast());
                num = top.equals("*") ? (preNum * num) : (preNum / num);
            } else {
                deque.addLast(top);
            }
        }
        deque.addLast(String.valueOf(num));
    }

    // 计算只有加减的公式
    private static int getNum(Deque<String> deque) {
        int res = 0;
        boolean add = true; // 若队列第一个数字无符号位，默认为true，相当于0 + queue
        while (!deque.isEmpty()) { 
            String cur = deque.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                int num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        String exp = "48*((70-65)-43)+8*1"; // -1816
        System.out.println(getValue(exp));
    }
}
