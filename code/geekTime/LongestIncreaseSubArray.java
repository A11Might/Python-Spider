/**
 * 最长上升子序列
 */

 public class LongestIncreaseSubArray {
     /**
      * 回溯
      * @param arr
      * @return
      */
    public static int lisBT(int[] arr) {
        return process(arr, arr.length - 1);
    }

    public static int process(int[] arr, int index) {
        if (index == 0) {
            return 1;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < index; ++i) { // a[1, 2, 3, ..., i]的最长上升子序列 = a[i]之前所有比它小的元素中子序列长度最大的 + 1
            if (arr[i] < arr[index]) {
                if (process(arr, i) > max) {
                    max = process(arr, i);
                }
            }
        }
        return max + 1;
    }

    /**
     * 状态转移表
     * @param arr
     * @return
     */
    public static int lisDP(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = 1;
        for (int i = 1; i < arr.length; ++i) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < i; ++j) {
                if (arr[i] > arr[j]) {
                    if (dp[j] > max) {
                        max = dp[j];
                    }
                }
            }
            dp[i] = max + 1;
        }
        return dp[arr.length - 1];
    }

    public static void main(String[] args) {
        int[] arr = {2, 9, 3, 6, 5, 1, 7}; // 4
        System.out.println(lisBT(arr));
        System.out.println(lisDP(arr));
    }
 }
