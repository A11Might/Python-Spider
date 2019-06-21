package day.class02;

import java.util.LinkedList;

/**
 * 有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右边滑一个位置，返回所有窗口最大值
 */

public class SlidingWindowMaxArray {
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int[] res = new int[arr.length - w + 1]; // 数组中一共可以形成arr.length - w + 1个窗口
        int index = 0; // res中的索引
        for (int i = 0; i < arr.length; ++i) {
            // 当前元素大于等于窗口中其前面元素时，将前面元素删除
            while (!qmax.isEmpty() && arr[qmax.getLast()] <= arr[i]) {
                qmax.pollLast();
            }
            qmax.addLast(i);
            // 窗口的第一个元素失效，将其删除
            if (qmax.getFirst() == i - w) {
                qmax.pollFirst();
            }
            // 形成窗口时开始记录窗口最大值
            if (i >= w - 1) {
                res[index++] = arr[qmax.getFirst()];
            }
        }
        return res;
    }

    // fot test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        printArray(getMaxWindow(arr, 3));
    }
}