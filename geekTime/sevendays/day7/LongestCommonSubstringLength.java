/**
 * 最长公共子串长度
 */

 public class LongestCommonSubstringLength {
     public static int longestCommonSubstringLength1(String a, String b) {
        return process(a.toCharArray(), b.toCharArray(), 0, 0);
     }

     private static int process(char[] a, char[] b, int indexA, int indexB) {
         if (indexA == a.length) {
             return 0;
         }
         if (indexB == b.length) {
             return 0;
         }
         if (a[indexA] == b[indexB]) {
             return 1 + process(a, b, indexA + 1, indexB + 1);
         } else {
             return Math.max(process(a, b, indexA + 1, indexB), // 跳过a串中不同的字符(删除a[i]或在b[i]前加上a[i])
                             process(a, b, indexA, indexB + 1)); // 跳过b串中不同的字符(删除b[i]或在a[i]前加上b[i])
         }
     }

     public static int longestCommonSubstringLength2(String a, String b) {
         char[] chrsA = a.toCharArray();
         char[] chrsB = b.toCharArray();
         int[][] dp = new int[a.length() + 1][b.length() + 1];
         for (int i = a.length() - 1; i >= 0; --i) {
            for (int j = b.length() - 1; j >= 0; --j) {
                if (chrsA[i] == chrsB[j]) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
         }
         return dp[0][0];
     }

     public static void main(String[] args) {
         String a = "mitcmu";
         String b = "mtacnu";
         System.out.println(longestCommonSubstringLength1(a, b));
         System.out.println(longestCommonSubstringLength2(a, b));
     }
 }