package class_1;

/**
 * KMP算法
 */

public class KMP {
    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0; // 主串s中的指针
        int i2 = 0; // 模式串m中的指针
        int[] next = getNextArray(str2); // next数组
        while (i1 < str1.length && i2 < str2.length) {
            // 主串和模式串中的当前字符相等，比较下一位字符
            if (str1[i1] == str2[i2]) {
                i1++;
                i2++;
                // 主串和模式串中的当前字符不相等，且当前字符为模式串的第一个字符，i2不能再往前跳了，跳过主串的当前字符从头继续比较
            } else if (next[i2] == -1) {
                i1++;
                // 主串和模式串中的当前字符不相等，i2可以往前跳，则跳到前面继续比较(相等于一次滑动数位，跳过相等的字符直接从不同的字符开始比较)
            } else {
                i2 = next[i2]; // 模式串滑动相当于模式串中的指针移到next[i2]中值的位置(当前字符前最长匹配前缀字符串后的第一个字符位置)，继续开始比较
            }
        }
        return i2 == str2.length ? i1 - i2 : -1;
    }

    public static int[] getNextArray(char[] str) {
        if (str.length == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[str.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2; // i为当前字符位置
        int cn = 0; // cn为跳到的位置，即待与i - 1位置字符比较的字符的位置
        while (i < next.length) {
            // 情况1
            if (str[i - 1] == str[cn]) {
                next[i++] = ++cn;
                // 情况2，还能往前跳，则cn往前跳
            } else if (cn > 0) {
                cn = next[cn];
                // 情况2，不能往前跳，则当前位置的next数组值为0
                // 到这一步说明cn == 0，若下一次比较0位置字符与i - 1位置的字符
                // 若相等，则下一个字符next数组的值为1
                // 若不相等，则下一个字符的next数组的值为0
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str = "abcabcababaccc";
        String match = "ababa";
        System.out.println(getIndexOf(str, match));
    }
}
