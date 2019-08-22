package practice;

/**
 * 插入排序
 */

public class InsertSort {
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        insertSortCore(arr, 0, arr.length - 1);
    }

    private static void insertSortCore(int[] arr, int start, int end) {
        // start from index equal 1, because only have two or more elements can compare
        // if current element is smaller than previous element and current element isn't arrive first position
        // move the previous element to current position, continue compare to previous' previous element
        // until conditions can't satisfy, put current element into current empty position
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int cur = i;
            while (cur > 0 && value < arr[cur - 1]) {
                arr[cur] = arr[cur - 1];
                cur--;
            }
            arr[cur] = value;
        }
    }
}
