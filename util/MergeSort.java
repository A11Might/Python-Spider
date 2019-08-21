package practice;

/**
 * 归并排序
 */

public class MergeSort {
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        mergeSortCore(arr, 0, arr.length - 1);
    }
    public static void mergeSortCore(int[] arr, int lo, int hi) {
        if (lo == hi) { // if only have one element, it's sorted
            return;
        }
        int mid = ((hi - lo) >> 1) + lo;
        mergeSortCore(arr, lo, mid); // sorted left part
        mergeSortCore(arr, mid + 1, hi); // sorted right part
        merge(arr, lo, mid, hi); // merge left part and right
    }

    public static void merge(int[] arr, int lo,int mid, int hi) {
        int[] temp = new int[hi - lo + 1]; // temp array for put sorted sequence
        int i = 0, p1 = lo, p2 = mid + 1; // index i for temp, p1 for left part, p2 for right part
        // merge left part and right
        while (p1 <= mid && p2 <= hi) {
            temp[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // left part or right haven't finish
        while (p1 <= mid) {
            temp[i++] = arr[p1++];
        }
        while (p2 <= hi) {
            temp[i++] = arr[p2++];
        }
        // copy temp array to original array
        for (i = 0; i < temp.length; i++) {
            arr[lo + i] = temp[i];
        }
    }

    public static void merge2(int[] arr, int lo, int mid, int hi) {
        // prepartcopy is original array's previous part copy([lo, mid])
        int[] prePartCopy = new int[mid - lo + 1];
        for (int i = 0; i < prePartCopy.length; i++) {
            prePartCopy[i] = arr[lo + i]; 
        }
        // traverse from prepartcopy's first position as j, original array mid + 1 position as k
        // and original array lo position as i for merge part prepartcopy and original array (mid, hi] part to orginal array
        // when have none element both prepartcopy part and original array (mid, hi] part terminal iteration
        for (int i = lo, j = 0, k = mid + 1; (j < prePartCopy.length) || (k <= hi);) {
            // if prepartcopy not run out and 
            // original array (mid, hi] part is run out or current element of position j is equal or smaller than k
            // copy prepartcopy element in position j to orginal array
            if ((j < prePartCopy.length) && ((hi < k) || (prePartCopy[j] <= arr[k]))) {
                arr[i++] = prePartCopy[j++];
            }
            // same to above
            if ((k <= hi) && ((prePartCopy.length <= j) || (arr[k] < prePartCopy[j]))) {
                arr[i++] = arr[k++];
            }
        }
    }
}
