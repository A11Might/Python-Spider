package sevendays.day7;

/**
 * 最长递增子序列
 * 
 * a[1, 2, 3, ..., i]的最长上升子序列 = a[i]之前所有比它小的元素中子序列长度最大的 + 1
 */

 public class LongIncreaseSubArray {
    public static int longIncreaseSubArray1(int[] arr) {
        return process(arr, arr.length - 1);
    }

    private static int process(int[] arr, int index) {
        if (index == 0) {
            return 1;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < index; ++i) {
            if (arr[i] < arr[index]) {
                max = Math.max(process(arr, i) + 1, max);
            }
        }
        return max;
    }

    public static int longIncreaseSubArray2(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = 1;
        for (int i = 1; i < arr.length; ++i) {
            dp[i] = Integer.MIN_VALUE;
            for (int j = 0; j < i; ++j) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }
        return dp[arr.length - 1];
    }

    public static void main(String[] args) {
        int[] arr = {2, 9, 3, 6, 5, 1, 7};
        System.out.println(longIncreaseSubArray1(arr));
        System.out.println(longIncreaseSubArray2(arr));
    }
 }