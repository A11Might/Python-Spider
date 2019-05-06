/**
 * 逆序对个数
 */

 public class MergeSortCounting {
    public static int counting(int[] arr) {
        return mergeSortCounting(arr, 0, arr.length - 1);
    }

    public static int mergeSortCounting(int[] arr, int lo, int hi) {
        if (lo == hi) {
            return 0;
        }
        int mid = lo + ((hi - lo) >> 1);
        // 总逆序对数 = 左区间的逆序对数 + 右区间的逆序对数 + 两个区间合并产生的逆序对数
        return mergeSortCounting(arr, lo, mid) + mergeSortCounting(arr, mid + 1, hi) + 
                merge(arr, lo, mid, hi);
    }

    public static int merge(int[] arr, int lo, int mid, int hi) {
        int[] temp = new int[hi - lo + 1];
        int l = lo, r = mid + 1, i = 0, res = 0;
        while (l <= mid && r <= hi) {
            if (arr[l] > arr[r]) {
                // 产生逆序对
                res += hi - r + 1;
                temp[i++] = arr[r++];
            } else {
                temp[i++] = arr[l++];
            }
        }
        if (l <= mid) {
            for (int j = l; j <= mid; ++j) {
                temp[i++] = arr[l];
            }
        } else {
            for (int j = r; j <= hi; ++j) {
                temp[i++] = arr[r];
            }
        }
        for (int j = 0; j < temp.length; ++j) {
            arr[lo++] = temp[j];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 3, 1, 5, 6};
        System.out.println(counting(arr));
    }
 }