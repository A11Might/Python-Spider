/**
 * 编辑距离：将一个字符串转化为另一个字符串，需要的最少编辑次数
 * 莱温斯坦距离 允许：增加、删除、替换
 */

 public class LevenshteinDistance {
     public static int levenshteinDistance1(String a, String b) {
        return process(a.toCharArray(), b.toCharArray(), 0, 0);
     }

     private static int process(char[] a, char[] b, int indexA, int indexB) {
         if (indexA == a.length) {
             // 当一个字符串已经到头，则编辑距离为另一个字符串剩余长度：(b.length - 1) - indexB + 1
             return b.length - indexB; 
         }
         if (indexB == b.length) {
             return a.length - indexA;
         }
         if (a[indexA] == b[indexB]) {
             return process(a, b, indexA + 1, indexB + 1);
         } else {
             // 删除a[i]或在b[j]前添加a[i]，后需要的最少编辑次数
             int case1 = process(a, b, indexA + 1, indexB); 
             // 删除b[j]或在a[i]前添加b[j]，后需要的最少编辑次数
             int case2 = process(a, b, indexA, indexB + 1); 
             // 替换a[i],b[j]为相同字符，后需要的最少编辑次数
             int case3 = process(a, b, indexA + 1, indexB + 1); 
             return 1 + Math.min(Math.min(case1, case2), case3);
         }
     }

     public static int levenshteinDistance2(String a, String b) {
         char[] chrsA = a.toCharArray();
         char[] chrsB = b.toCharArray();
         int[][] dp = new int[a.length() + 1][b.length() + 1];
         for (int j = 0; j < b.length() + 1; ++j) {
            dp[a.length()][j] = b.length() - j;
         }
         for (int i = 0; i < a.length() + 1; ++i) {
             dp[i][b.length()] = a.length() - i;
         }
         for (int i = a.length() - 1; i >= 0; --i) {
             for (int j = b.length() - 1; j >= 0; --j) {
                 if (chrsA[i] == chrsB[j]) {
                     dp[i][j] = dp[i + 1][j + 1];
                 } else {
                     dp[i][j] = 1 + Math.min(Math.min(dp[i + 1][j], dp[i][j + 1]), dp[i + 1][j + 1]);
                 }
             }
         }
         return dp[0][0];
     }

     public static void main(String[] args) {
         String a = "mitcmu";
         String b = "mtacnu";
         System.out.println(levenshteinDistance1(a, b));
         System.out.println(levenshteinDistance2(a, b));
     }
 }