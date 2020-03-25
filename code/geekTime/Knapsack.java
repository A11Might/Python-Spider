/**
 * 0-1背包问题
 */

 public class Knapsack {
     public static int knapsack(int[] weight, int[] price, int bag) {
         return (process(0, 0, weight, price, bag));
     }

     public static int process(int index, int cw, int[] weight, int[] price, int bag) {
         if (index == weight.length) {
             return 0;
         }
         if (cw + weight[index] <= bag) {
             return Math.max(process(index + 1, cw, weight, price, bag),
                            price[index] + process(index + 1, cw + weight[index],weight, price, bag));
         } else {
             return process(index + 1, cw, weight, price, bag);
         }
     }

     public static int knapsack2(int[] weight, int[] price, int bag) {
         int[][] states = new int[weight.length][bag + 1];
         for (int i = 0; i < weight.length; ++i) {
             for (int j = 0; j < bag + 1; ++j) {
                 states[i][j] = -1;
             }
         }
         states[0][0] = 0; // 不加入第一件商品
         states[0][weight[0]] = price[0]; // 加入第一件商品
         for (int i = 1; i < weight.length; ++i) {
             for (int j = 0; j < bag + 1; ++j) {
                 if (states[i - 1][j] >= 0) {
                     states[i][j] = states[i - 1][j]; // 不要第i件商品
                 }
             }
             for (int k = bag - weight[i]; k >= 0; --k) { // 从后往前遍历，防止新生成>=的位置也被遍历到
                 if (states[i][k] >= 0) {
                     int v = states[i][k] + price[i]; // 加入第i件商品，
                     if (states[i][k + weight[i]] < v) {
                         states[i][k + weight[i]] = v; // 重量相同时存储最大的价值
                     }
                 }
             }
         }
         int maxValue = -1;
         for (int i = 0; i < bag + 1; ++i) {
             if (maxValue < states[weight.length - 1][i]) {
                 maxValue = states[weight.length - 1][i]; // 找到最大值
             }
         }
         return maxValue;
     }

     public static void main(String[] args) {
         int[] weight = {2, 2, 4, 6, 3};
         int[] price = {3, 4, 8, 9, 6};
         int bag = 9;
         System.out.println(knapsack(weight, price, bag));
         System.out.println(knapsack2(weight, price, bag));
     }
 }

