package day.class02;

import java.util.LinkedList;

/**
 * 题目：给定数组arr和整数num，共返回有多少个子数组满足如下情况： 
 * max(arr[i...j]) - min(arr[i...j]) <= num
 * max(arr[i...j]) 表示子数组arr[i...j]中的最大值，min(arr[i...j]) 表示子数组arr[i...j]中的最小值
 * 要求：如果数组长度为N,请实现时间复杂度为O(N)的解法
 * 
 * 思路：当前子数组满足条件，则其全部子数组满足条件
 *      当前子数组不满足条件，则继续扩充数组也不满足条件
 */

public class AllLessNumSubArray {
    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> qmin = new LinkedList<Integer>();
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int start = 0, end = 0;
        int res = 0;
        while (start < arr.length) {
            while (end < arr.length) {
                // 更新滑动窗口最小值
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[end]) {
                    qmin.pollLast();
                }
                qmin.addLast(end);
                // 更新滑动窗口最大值
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[end]) {
                    qmax.pollLast();
                }
                qmax.addLast(end);
                // 不满足max(arr[i...j]) - min(arr[i...j]) <= num，结束end的滑动，开始收集符合题意的子数组数
                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
                    break;
                }
                end++;
            }
            // 清除窗口中过期的元素
            if (qmin.peekFirst() == start) {
                qmin.pollFirst();
            }
            if (qmax.peekFirst() == start) {
                qmax.pollFirst();
            }
            // 每次获得以start开头的满足条件的子数组个数
            res += end - start; // [start, end)为满足题意的最大窗口，以start开头的子数组个数为start - end
            start++;
        }
        return res;
    }

    // 暴力方法
    public static int getNum2(int[] arr, int num) {
        int res = 0;
        for (int start = 0; start < arr.length; ++start) {
            for (int end = start; end < arr.length; ++end) {
                if (isValid(arr, start, end, num)) {
                    res++;
                }
            }
        }

        return res;
    }

    private static boolean isValid(int[] arr, int start, int end, int num) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = start; i <= end; ++i) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        return max - min <= num;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

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

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 50;
        int maxValue = 100;
        int num = (int) ((maxValue + 1) * Math.random());
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (getNum(arr1, num) != getNum2(arr2, num)) {
                succeed = false;
                printArray(arr1);
                System.out.println(getNum(arr1, num));
                System.out.println(getNum2(arr2, num));
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}