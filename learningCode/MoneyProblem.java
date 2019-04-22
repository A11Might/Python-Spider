package class_8;

/**
 * 给你一个数组arr，和一个整数aim
 * 如果可以任意选择arr中的数字，能不能累加得到aim，返回true或者false
 */

 public class MoneyProblem {
     /**
      * 暴力递归
      * @param arr
      * @param aim
      * @return
      */
     public static boolean money1(int[] arr, int aim) {
         return process(arr, 0, 0, aim);
     }

     public static boolean process(int[] arr, int index, int sum, int aim) {
         if (sum == aim) {
             return true;
         }
         if (index == arr.length) {
             return false;
         }
         return process(arr, index + 1, sum + arr[index], aim) || process(arr, index + 1, sum, aim);
     }

     /**
      * 动态规划
      * @param arr
      * @param aim
      * @return
      */
     public static boolean money2(int[] arr,int aim) {
         boolean[][] dp = new boolean[arr.length + 1][aim + 1]; // 需要第arr.length行和aim列
         for (int i = 0; i < dp.length; ++i) { // 数组初始化默认为false
             dp[i][aim] = true;
         }
         for (int j = arr.length - 1; j >= 0; --j) {
             for (int k = aim - 1; k >= 0; --k) {
                 if (k + arr[j] <= aim) { // 不用做全部元素和列的dp表，若k + arr[j]已经大于aim则肯定false
                     dp[j][k] = dp[j + 1][k + arr[j]] || dp[j + 1][k];
                 }
                 dp[j][k] = dp[j + 1][k]; // k + arr[j]为false，dp[j][k] = dp[j + 1][k]
             }
         }
         return dp[0][0];
     }

     public static void main(String[] args) {
        int[] arr = { 1, 4, 8 };
        int aim = 12;
        System.out.println(money1(arr, aim));
        System.out.println(money2(arr, aim));
    }
 }