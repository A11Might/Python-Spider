package practice;

/**
 * 快速排序
 */

public class QuickSort {
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSortCore(arr, 0, arr.length - 1);
    }

    public static void quickSortCore(int[] arr, int lo, int hi) {
        if (lo < hi) {
            int mid = partition(arr, lo, hi); // divide array to two parts
            quickSortCore(arr, lo, mid - 1); // sorted left part
            quickSortCore(arr, mid + 1, hi); // sorted right part
        }
    }

    public static int partition(int[] arr, int lo, int hi) {
        // random swap reduce rate of baddest case appear 
        swap(arr, lo + (int) Math.random() * (hi - lo + 1), hi);
        // divide orginal array to smaller and equal or bigger two part
        int small = lo - 1;
        while (lo < hi) {
            if (arr[lo] < arr[hi]) {
                swap(arr, ++small, lo++);
            } else {
                lo++;
            }
        }
        // put pivot into middle
        swap(arr, ++small, hi);

        // return middle position
        return small;
    } 

    // swap array element in i and j
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 5, 4, 6, 9, 8};
        quickSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
