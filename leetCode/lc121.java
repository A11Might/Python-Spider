/*
 * @lc app=leetcode.cn id=121 lang=java
 *
 * [121] 买卖股票的最佳时机
 * 
 * 前i天的最大收益 = max{前i-1天的最大收益，第i天的价格-前i-1天中的最小价格}
 */
class Solution {
    public int maxProfit(int[] prices) {
        // min为今天之前买入的最小值
        // 计算今天之前最小值买入，今天卖出的获利，也即今天卖出的最大获利
        // max为比较每天的最大获利，取取得最大值
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int min = prices[0], max = 0;
        for (int i = 1; i < prices.length; ++i) { 
            // 前i天的最大收益
            max = Math.max(max, prices[i] - min);
            // 第i + 1天之前的最小价格
            min = Math.min(min, prices[i]);
        }
        return max;
    }
}

