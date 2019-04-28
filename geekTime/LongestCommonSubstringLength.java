package geektime;

/**
 * 最长公共子串长度
 */

 public class LongestCommonSubstringLength {
     /**
      * 回溯
      * @param a
      * @param b
      * @return
      */
    public static int lcsBT(char[] a, char[] b) {
         return process(a, b, 0, 0);
    }

    public static int process(char[] a, char[] b, int indexA, int indexB) {
        if (indexA == a.length || indexB == b.length) {
            return 0;
        }
        if (a[indexA] == b[indexB]) {
            return 1 + process(a, b, indexA + 1, indexB + 1);
        } else {
            int case1 = process(a, b, indexA + 1, indexB); // 跳过a串中不同的字符
            int case2 = process(a, b, indexA, indexB + 1); // 跳过b串中不同的字符
            return Math.max(case1, case2);
        }
    }

    /**
     * 状态转移表
     * @param a
     * @param b
     * @return
     */
    public static int lcsDP(char[] a, char[] b) {
        int row = a.length;
        int col = b.length;
        int[][] dp = new int[row + 1][col + 1];
        for (int i = row - 1; i >= 0; --i) {
            for (int j = col - 1; j >= 0; --j) {
                if (a[i] == b[j]) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        char[] a = "mitcmu".toCharArray();
        char[] b = "mtacnu".toCharArray();
        System.out.println(lcsBT(a, b));
        System.out.println(lcsDP(a, b));
    }
 }
