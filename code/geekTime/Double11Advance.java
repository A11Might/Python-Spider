/**
 * 双十一凑单
 */

 public class Double11Advance {
     public static void double11Advance(int[] items, int n, int w) {
         boolean[][] states = new boolean[n][3 * w + 1]; // 超过三倍没有凑单的必要
         states[0][0] = true;
         states[0][items[0]] = true;
         for (int i = 1; i < n; ++i) {
             for (int j = 0; j < 3 * w + 1; ++j) {
                 if (states[i - 1][j] == true) {
                     states[i][j] = true; // 不加第i个商品
                 }
             }
             for (int j = 0; j < 3 * w + 1 - items[i]; ++j) {
                 if (states[i - 1][j] == true) {
                    states[i][j + items[i]] = true; // 加第i个商品
                }
             }
         }

         int j;
         for (j = w; j < 3 * w + 1; ++j) {
             if (states[n - 1][j] == true) {
                 break;
             }
         }
         if (j == 3 * w + 1) {
             return; // 无解
         }
         for (int i = n - 1; i >= 1; --i) {
             if (j - items[i] >= 0 && states[i - 1][j - items[i]] == true) { // 根据上一行判断这一行，无法判断第一行
                 System.out.print(items[i] + "");
                 j = j - items[i];
             }
         }
         if (j != 0) { // 判断是否购买第一个商品
             System.out.print(items[0] + "");
         }
     }
 }
