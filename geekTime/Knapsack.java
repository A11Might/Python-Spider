package geektime;

/**
 * 0-1背包问题
 */

 public class Knapsack {
     public static int knapsack(int[] weight, int[] price, int bag) {
         return process(weight, price, 0, 0, bag);
     } 

     public static int process(int[] weight, int[] price, int index, int presentWeight, int bag) {
         if (index == weight.length) {
            return 0;
        }
        if (presentWeight + weight[index] <= bag) {
            return Math.max(
                    process(weight, price, index + 1, presentWeight, bag),
                    price[index] + process(weight, price, index + 1, presentWeight + weight[index], bag));
        } else {
            return process(weight, price, index + 1, presentWeight, bag);
        }
     }

     public static int knapsack2(int[] weight, int[] price, int bag) {
         int[][] dp = new int[weight.length + 1][bag + 1];
         for (int i = weight.length - 1; i >= 0; --i) {
             for (int j = bag; j >= 0; --j) {
                 dp[i][j] = dp[i + 1][j];
                 if (j + weight[i] <= bag) {
                     dp[i][j] = Math.max(dp[i + 1][j], price[i] + dp[i + 1][j + weight[i]]);
                 }
             }
         }
         return dp[0][0];
     }

     public static void main(String[] args) {
         int[] weight = {2, 2, 4, 6, 3};
         int[] price = {3, 4, 8, 9, 6};
         int bag = 9;
         System.out.println(knapsack(weight, price, bag));
         System.out.println(knapsack2(weight, price, bag));
     }
 }

