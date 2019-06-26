package day.class04;

import java.util.HashMap;

/**
 * 题目：给定一个数组arr，任意把arr分成很多不相容的子数组，使得分出来的子数组中，异或和为0的子数组最多
 * 
 * 思路：两种情况
 *      case1、以i为结尾的子数组不是异或和为0的，dp[i] = dp[i - 1]
 *      case2、以i为结尾的子数组不是异或和为0的，dp[i] = pre == -1 ? 1 : (dp[pre] + 1)
 *      dp[i] = max(case1, case2)
 */

public class MostEOR {
    public static int mostEOR(int[] arr) {
        int res = 0; // 以当前位置为结尾的子数组中异或和为0的子数组最多有res个
        int sum = 0; // 以当前位置为结尾的子数组所有元素的异或和
        int[] dp = new int[arr.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < arr.length; ++i) {
            sum ^= arr[i];
            // 任何数异或0后值不变，所以从0异或到当前位置的值xor，若在之前pre位置出现过，则dp[cur] = dp[pre] + 1
            // 即pre + 1到cur异或和为0
            if (map.containsKey(sum)) {
                int pre = map.get(sum);
                dp[i] = pre == -1 ? 1 : (dp[pre] + 1); // 当前元素可以组成新的异或和为0的子数组
            }
            if (i > 0) {
                dp[i] = Math.max(dp[i - 1], dp[i]); // 当前元素不能组成新的异或和为0的子数组
            }
            map.put(sum, i); // 为了才能获得更多子数组，需要最后出现的位置，所以每次都更新
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}