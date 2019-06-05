package class_1;

/**
 * 给你一个原始串，只能在其后添加若干字符形成大串，并且要求大串包含两个原始串，同时它们开始位置不同。返回最短的大串
 */

public class DoubleStr {
	public static String getLeastDoubleStr(String str) {
		int n = str.length();
		char[] chrs = str.toCharArray();
		int[] next = getNextArray(chrs);
		return str + str.substring(0, n - next[n]);
	}

	public static int[] getNextArray(char[] str) {
		if (str.length == 1)
			return new int[] { -1 };
		int[] next = new int[str.length + 1];
		next[0] = -1;
		next[1] = 0;
		int i = 2;
		int cn = 0;
		while (i < next.length) {
			if (str[i - 1] == str[cn]) {
				next[i++] = ++cn;
			} else if (cn > 0) {
				cn = next[cn];
			} else {
				next[i++] = 0;
			}
		}
		return next;
	}

	public static void main(String[] args) {
		String str = "abcabc";
		String str2 = "aaaaaa";
		System.out.println(getLeastDoubleStr(str));
		System.out.println(getLeastDoubleStr(str2));
	}
}