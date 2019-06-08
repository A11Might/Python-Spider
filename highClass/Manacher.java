package day.class01;

/**
 * Manacher算法
 */
public class Manacher {
	private static char[] manacherString(String str) {
		char[] charArr = str.toCharArray();
		char[] res = new char[str.length() * 2 + 1]; // 相当于在每个字符后面加一个'#',再在第一个字符前加一个'#'
		int index = 0; // 当前字符位置
		for (int i = 0; i < res.length; i++) {
			res[i] = (i & 1) == 0 ? '#' : charArr[index++]; // i为res数组中的位置，若为偶数则放置'#'，奇数则放置当前字符(
		}
		return res;
	}

	private static int maxLcpsLength(String str) {
		if (str == null || str.length() == 0)
			return 0;
		char[] charArr = manacherString(str); // 字符串预处理
		int[] pArr = new int[charArr.length]; // 回文半径数组
		int C = -1; // 最早到达最右回文右边界时所对应的回文中心
		int R = -1; // 最右回文半径的右边界
		int max = Integer.MIN_VALUE; // 最大回文半径
		for (int i = 0; i != charArr.length; i++) {
            // 两种情况：
            // a. i不在R内，需暴力扩，i位置的回文子串至少是它自己即1
            // b. i在R内，则i位置的回文长度至少为pArr[2 * C - i](i' 位置字符的回文在L和R之间)
            //    和R - i(i' 位置字符的回文在L和R之外)中较小值，即使i'位置字符的回文在L上也是在
            //    上述值上继续增加的，只需额外再向外扩一次即可判断具体是哪种情况
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1; // i' 位置为c - (i - c)即2 * c - i
            // 暴力扩时，pArr[i]初始值为1，依次向外扩增加值
            // 在之前加速的基础上，继续外扩
			while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
				if (charArr[i + pArr[i]] == charArr[i - pArr[i]]) {
					pArr[i]++;
				} else {
					break;
				}
			}
			// 实时更新R 和 C
			if (i + pArr[i] > R) {
				R = i + pArr[i];
				C = i;
			}
			// 实时更新max
			max = Math.max(max, pArr[i]);
		}
		return max - 1;
	}

	public static void main(String[] args) {
		String str = "zkabatftabaky";
		int res = maxLcpsLength(str);
		System.out.println("res=" + res);
	}
}