/**
 * 莱温斯坦距离：允许增加、删除、替换
 */

 public class LevenshteinDistance {
     /**
      * 回溯
      * @param a
      * @param b
      * @return
      */
     public static int lwstBT(char[] a, char[] b) {
         return process(a, b, 0, 0);
     }

     public static int process(char[] a, char[] b, int indexA, int indexB) {
         if (indexA == a.length) {
             return b.length - indexB;
         }
         if (indexB == b.length) {
             return a.length - indexA;
         }
         if (a[indexA] == b[indexB]) {
             return process(a, b, indexA + 1, indexB + 1);
         } else {
             int case1 = process(a, b, indexA + 1, indexB + 1); // 替换a[i],b[j]为相同字符
             int case2 = process(a, b, indexA, indexB + 1); // 删除b[j]或在a[i]前添加b[j]
             int case3 = process(a, b, indexA + 1, indexB); // 删除a[i]或在b[j]前添加a[i]
             return 1 + Math.min(case1, Math.min(case2, case3));
         }
     }

     /**
      * 状态转移表
      * @param a
      * @param b
      * @return
      */
     public static int lwstDP(char[] a, char[] b) {
         int row = a.length;
         int col = b.length;
         int[][] dp = new int[row + 1][col + 1];
         for (int j = 0; j <= col; ++j) {
             dp[row][j] = col - j;
         }
         for (int i = 0; i <= row; ++i) {
             dp[i][col] = row - i;
         }
         for (int i = row - 1; i >= 0; --i) {
             for (int j = col - 1; j >= 0; --j) {
                 if (a[i] == b[j]) {
                     dp[i][j] = dp[i + 1][j + 1];
                 } else {
                     dp[i][j] = 1 + Math.min(dp[i + 1][j + 1], Math.min(dp[i][j + 1], dp[i + 1][j]));
                 }
             }
         }
         return dp[0][0];
     }

     public static void main(String[] args) {
         char[] a = "mitcmu".toCharArray();
         char[] b = "mtacnu".toCharArray();
         System.out.println(lwstBT(a, b));
         System.out.println(lwstDP(a, b));
     }
 }
