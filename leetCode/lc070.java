/*
 * @lc app=leetcode.cn id=70 lang=java
 *
 * [70] 爬楼梯
 * 状态转移方程为f(n) = f(n - 1) + f(n - 2)
 */
class Solution {

    // 动态规划
    public int climbStairs1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n + 1; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 回溯
    public int climbStairs2(int n) {
        return process(n);
    }

    public int process(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return process(n - 1) + process(n - 2);
    }
}
