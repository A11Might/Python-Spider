/**
 * 2,3,5 -> 11
 */

 public class MinCoins {
     public static int minCoins(int m) {
         return process(9);
     }

     public static int process(int m) {
         if (m == 1) {
             return Integer.MAX_VALUE;
         }
         if (m == 2 || m == 3 || m == 5) {
            return 1;
        }
        return Math.min(Math.min(1 + process(m - 1), 1 + process(m - 3)), 1 + process(m - 5));
     }

     public static int minCoins2(int m) {
         int[] dp = new int[m + 1];
         dp[1] = Integer.MAX_VALUE;
         dp[2] = 1;
         dp[3] = 1;
         dp[5] = 1;
         for (int i = 1; i <= m; ++i) {
             if (dp[i] == 0) {
                 if (i - 5 > 0) {
                     dp[i] = 1 + Math.min(Math.min(dp[i - 2], dp[i - 3]), dp[i - 5]);
                 } else if (i - 3 > 0) {
                     dp[i] = 1 + Math.min(dp[i - 2], dp[i - 3]);
                 } else if (i - 2 > 0) {
                     dp[i] = 1 + dp[i - 2];
                 }
             }
         }
         return dp[m];
     }

     class Solution {
        public int coinChange(int[] coins, int amount) {
            if (amount == 0)
                return 0;
            int[] dp = new int[amount + 1];
            for (int c = 0; c < coins.length; c++) {
                if (coins[c] < amount)
                    dp[coins[c]] = 1;
                else if (coins[c] == amount)
                    return 1;
            }

            for (int i = 1; i < amount + 1; i++) {
                for (int c = 0; c < coins.length; c++) {
                    if (i - coins[c] >= 0) {
                        if (dp[i - coins[c]] == 0)
                            continue;
                        if (dp[i] != 0)
                            dp[i] = Math.min(dp[i], dp[i - coins[c]] + 1);
                        else
                            dp[i] = dp[i - coins[c]] + 1;
                    }
                }
            }
            if (dp[amount] == 0)
                return -1;
            else
                return dp[amount];
        }
    }

     public static void main(String[] args) {
         System.out.println(minCoins2(11));
     }
 }
