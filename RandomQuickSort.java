import java.util.Arrays;

/**
 * 随机快速排序的细节和复杂度分析 
 * 可以用荷兰国旗问题来改进快速排序 
 * 时间复杂度O(N*logN)， 额外空间复杂度O(logN)
 */

 public class RandomQuickSort {
     public static void quickSort(int[] arr) {
         if (arr.length < 2) {
             return;
         }
         quickSort(arr, 0, arr.length - 1);
     }
     public static void quickSort(int[] arr, int lo, int hi) {
         if (lo < hi) {
            swap(arr, lo + (int) Math.random() * (hi - lo + 1), hi);
            int[] mid = partition(arr, lo, hi);
            quickSort(arr, lo, mid[0]);
            quickSort(arr, mid[1], hi);
        }
     }
     public static int[] partition(int[] arr, int lo, int hi) {
         int less = lo - 1;
         int more = hi;//不动arr[hi]
         while (lo < more) {
            if (arr[lo] < arr[hi]) {
                swap(arr, ++less, lo++);
            } else if (arr[lo] == arr[hi]) {
                lo++;
            } else {
                swap(arr, --more, lo);
            }
         }
         swap(arr, more, hi);
         return new int[] {less, more + 1};
     } 
     public static void swap(int[] arr, int i, int j) {
         int temp = arr[j];
         arr[j] = arr[i];
         arr[i] = temp;
     }

    //对数器
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

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
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            quickSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        quickSort(arr);
        printArray(arr);

    }

 }
