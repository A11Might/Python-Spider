package day.class01;

/**
 * 给你一个原始串，只能在后面添加字符形成大串，要求形成回文串。返回最短的大串
 * 
 * 当 R 扩到字符串最后一个字符时停止，将 R 关于 C 的对称位置 L 前的所有字符逆序，加到字符串后面，即可组成最短回文字符串
 */

public class ManacherShorestEnd {
	private static char[] manacherString(String str) {
		char[] charArr = str.toCharArray();
		char[] res = new char[str.length() * 2 + 1];
		int index = 0;
		for (int i = 0; i < res.length; i++) {
			res[i] = (i & 1) == 0 ? '#' : charArr[index++];
		}
		return res;
	}

	private static String shortestEnd(String str) {
		if (str == null || str.length() == 0)
			return null;
		char[] charArr = manacherString(str);
		int[] pArr = new int[charArr.length];
		int C = -1;
		int R = -1;
		int endR = -1; // 当 R 扩到字符串最后一个字符时的回文半径
		for (int i = 0; i != charArr.length; i++) {
			pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
			while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
				if (charArr[i + pArr[i]] == charArr[i - pArr[i]]) {
					pArr[i]++;
				} else {
					break;
				}
			}
			if (i + pArr[i] > R) {
				R = i + pArr[i];
				C = i;
			}
			// 当 R 扩到字符串最后一个字符时停止
			if (R == charArr.length) {
				endR = pArr[i];
				break;
			}
		}
		char[] add = new char[str.length() - endR + 1]; // n - (endR - 1),为形成回文串，原字符后面应该追加的最少字符
		for (int i = 0; i < add.length; i++) {
			add[add.length - 1 - i] = charArr[i * 2 + 1]; // 逆序的加在原字符串末尾；原数组由于预处理过，所以偶数位才为真实字符
		}
		return str + String.valueOf(add);

	}



	public static void main(String[] args) {
		String str = "abcd123321";
		System.out.println(shortestEnd(str));
	}
}