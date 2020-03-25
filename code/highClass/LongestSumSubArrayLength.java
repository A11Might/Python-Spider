import java.util.HashMap;

/**
 * 题目：给定一个数组arr，和一个整数num，求在arr中，累加和等于num的最长子数组的长度
 * 
 * 思路：sum记录当前位置及其之前所有元素的和
 *      当sum - aim，存在储存每个位置sum的map中时，当前位置减去sum == sum - aim的位置，即为累加和为aim的子数组
 *      (sum - aim) + aim = sum
 * 
 * 变形：给定一个数组arr，和一个整数num，求在arr中，奇偶数个数相等的最长子数组的长度
 * 
 * 思路：将奇数变为-1，偶数变为1，求累加和为0的最长子数组的长度即可
 *  
 * tips：遇到子数组问题，考虑以每个位置结尾的情况怎么样，答案就在其中
 */

public class LongestSumSubArrayLength {
    public static int maxLength(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(); // (sum, position)
        map.put(0, -1); // 加上-1位置，因为算子数组长度时是(-1, cur]，如果不加入-1位置，就无法计算从0位置开始的子数组
        int res = 0; // 以当前位置为结尾的子数组中累加和等于num的最长子数组的长度
        int sum = 0; // 以当前位置为结尾的子数组所有元素的累加和
        for (int i = 0; i < arr.length; ++i) {
            sum += arr[i];
            if (map.containsKey(sum - aim)) {
                res = Math.max(i - map.get(sum - aim), res);
            }
            // 只需记录sum第一次出现的位置即可，这样可以获得最长子数组
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return res;
    }
}