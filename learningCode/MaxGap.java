import java.util.Arrays;

/**
 * 给定一个数组， 求如果排序之后， 相邻两数的最大差值
 * 要求时间复杂度O(N)， 且要求不能用非基于比较的排序
 */

 public class MaxGap {
    public static int maxGap(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int len = arr.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; ++i) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }
        if (min == max) {
            return 0;
        }
        /**
         * 鸽巢原理：len个数入len + 1个桶，至少有一个空桶 
         * 保证相邻两数的最大差值大于一个桶宽，即同一桶中相邻两数的差值不可能最大 
         * 相邻两数的最大差值为相邻两桶中两数的差值
         */
        boolean[] hasNum = new boolean[len + 1];
        int[] mins = new int[len + 1];
        int[] maxs = new int[len + 1];
        int bid = 0;
        for (int i = 0; i < len; ++i) {
            bid = bucket(arr[i], len, min, max);
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], arr[i]) : arr[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], arr[i]) : arr[i];
            hasNum[bid] = true;
        }
        int lastMax = maxs[0];
        int res = 0;
        for (int i = 1; i < len + 1; ++i) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }   
    
    /**
     * 计算当前目标元素所在桶的位置
     * @param num
     * @param len
     * @param min
     * @param max
     * @return
     */
    public static int bucket(int num, int len, int min, int max) {
        return ((num - min) * len) / (max - min); // [0, len]将len个数分在len + 1个桶中
    }

    //对数器
    public static int comparator(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int gap = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            gap = Math.max(nums[i] - nums[i - 1], gap);
        }
        return gap;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (maxGap(arr1) != comparator(arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
 }
