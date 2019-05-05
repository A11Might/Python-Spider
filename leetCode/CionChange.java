/*
 * @lc app=leetcode.cn id=322 lang=java
 *
 * [322] 零钱兑换
 * 
 * f(n) = 1 + Math.min(f(n - coins[0]), f(n - coins[1]), ...)
 */
class Solution {
    public static int coinChange(int[] coins, int amount) {
        // dp[i]代表从凑成i的最少硬币数量
        int[] dp = new int[amount + 1];
        // dp[0] = 0;
        for (int i = 1; i <= amount; ++i) {
            // dp[i]初始化为系统最大
            // 然后遍历每个硬币，一个一个的比较出最少个数
            dp[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] != -1) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
            // dp[i]仍未初始值，则说明当前总金额不能由零钱构成，置为-1
            if (dp[i] == Integer.MAX_VALUE) {
                dp[i] = -1;
            }
        }
        return dp[amount];
    }

}

