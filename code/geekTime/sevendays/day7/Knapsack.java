package sevendays.day7;

/**
 * 0-1背包问题
 * 
 *  给定两个数组w和v，两个数组长度相等
 *  weights[i]表示第i件商品的重量
 *  values[i]表示第i件商品的价值
 *  再给定一个整数bag，
 *  要求你挑选商品的重量加起来一定不能超过bag
 *  返回满足这个条件下，你能获得的最大价值
 * 
 * dp[i][j] = Math.max((values[i] + dp[i + 1][j + weights[i]]), dp[i + 1][j]);
 */

 public class Knapsack {
     public static int knapsack1(int[] weights, int[] values, int bag) {
        return process(weights, values, bag, 0, 0);
     }

     private static int process(int[] weights, int[] values, int bag, int index, int alreadyWeight) {
         if (index == weights.length) {
             return 0;
         }
         // 商品能加入背包
         if (alreadyWeight + weights[index] <= bag) {
             return Math.max(values[index] + process(weights, values, bag, index + 1, alreadyWeight + weights[index]), // 当前商品加入背包可以获得的最大价值
                             process(weights, values, bag, index + 1, alreadyWeight)); // 不加入背包可以获得的最大价值
         // 商品不能加入背包
         } else {
             return process(weights, values, bag, index + 1, alreadyWeight);
         }
     }

     public static int knapsack2(int[] weights, int[] values, int bag) {
         int[][] dp = new int[weights.length + 1][bag + 1];
         for (int i = weights.length - 1; i >= 0; --i) {
            for (int j = bag; j >= 0; --j) {
                if (j + weights[i] <= bag) {
                    dp[i][j] = Math.max((values[i] + dp[i + 1][j + weights[i]]), dp[i + 1][j]);
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
         }
         return dp[0][0];
     }

     public static void main(String[] args) {
        int[] c = { 1, 2, 5, 6, 7, 9 };
        int[] p = { 1, 6, 18, 22, 28, 36 };
        int bag = 20;
        System.out.println(knapsack1(c, p, bag));
        System.out.println(knapsack2(c, p, bag));
    }
 }