package class_8;

/**
 * 给定两个数组w和v，两个数组长度相等
 * w[i]表示第i件商品的重量
 * v[i]表示第i件商品的价值
 * 再给定一个整数bag，
 * 要求你挑选商品的重量加起来一定不能超过bag
 * 返回满足这个条件下，你能获得的最大价值
 */

 public class Knapsack {
     /**
      * 暴力递归
      * @param c
      * @param p
      * @param bag
      * @return
      */
     public static int maxValue1(int[] c, int[] p, int bag) {
        return process2(c, p, 0, 0, bag);
    }

    /**
     * 普通的
     * @param weights
     * @param values
     * @param i
     * @param alreadyweight
     * @param bag
     * @return
     */
    public static int process1(int[] weights, int[] values, int i, int alreadyweight, int bag) {
        if (i == weights.length) {
            return 0;
        }
        if (alreadyweight + weights[i] <= bag) {
            return Math.max(
    
                    process1(weights, values, i + 1, alreadyweight, bag),
    
                    values[i] + process1(weights, values, i + 1, alreadyweight + weights[i], bag));

        } else {
            return process1(weights, values, i + 1, alreadyweight, bag);
        }
    }
    
    /**
     * 左神的
     * @param weights
     * @param values
     * @param i
     * @param alreadyweight
     * @param bag
     * @return
     */
    public static int process2(int[] weights, int[] values, int i, int alreadyweight, int bag) {
        if (alreadyweight > bag) { // 需在i == weight.length前面，如果在后面，会返回0；最后一个商品重量超过背包总重量，可能会被返回
            return Integer.MIN_VALUE; // 若设为0，仍可能为Math.max中的大值，得到错误的答案，所以设为系统最小，把这条路彻底封死
        }
        if (i == weights.length) {
            return 0;
        }
        return Math.max(

                process2(weights, values, i + 1, alreadyweight, bag),

                values[i] + process2(weights, values, i + 1, alreadyweight + weights[i], bag));
    }

    /**
     * 动态规划
     * @param c
     * @param p
     * @param bag
     * @return
     */
    public static int maxValue2(int[] c, int[] p, int bag) {
        int[][] dp = new int[c.length + 1][bag + 1];
        for (int i = c.length - 1; i >= 0; --i) {
            for (int j = bag; j >= 0; --j) {
                 dp[i][j] = dp[i + 1][j];
                 if ((j + c[i]) <= bag) {
                     dp[i][j] = Math.max(dp[i + 1][j], p[i] + dp[i + 1][j + c[i]]);
                 }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] c = {1, 2, 5, 6, 7, 9};
        int[] p = {1, 6, 18, 22, 28, 36};
        int bag = 20;
        System.out.println(maxValue1(c, p, bag));
        System.out.println(maxValue2(c, p, bag));
    }
 }
