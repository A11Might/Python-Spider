/**
 * 小和问题 在一个数组中， 每一个数左边比当前数小的数累加起来， 叫做这个数组的小和。 求一个数组 的小和。 
 * 例子： [1,3,4,2,5]
 * 1左边比1小的数， 没有； 
 * 3左边比3小的数， 1；
 * 4左边比4小的数， 1、 3； 
 * 2左边比2小的数， 1； 
 * 5左边比5小的数， 1、 3、 4、 2；
 * 所以小和为1+1+3+1+1+3+4+2=16
 */

public class SmallSum {
    //一定正确的方法
    public static int smallSumSure(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0; 
            }
        }
        return res;
    }
    
    //main方法
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (smallSumSure(arr1) != smallSum(arr2)) {
                System.out.println("fuck");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("over");
    }
    
    //优化后的方法
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return internally(arr, 0, arr.length - 1);
    }
    public static int internally(int[] arr, int lo, int hi) {
        if (lo == hi) {
            return 0;
        }
        int mid = ((hi - lo) >> 1) + lo;
        // 左边部分产生的小和 + 右边部分产生的小和 + 左右两部分合并产生的小和
        return internally(arr, lo, mid) + internally(arr, mid + 1, hi) + merge(arr, lo, mid, hi);
    }
    public static int merge(int[] arr, int lo,int mid, int hi) {
        int[] temp = new int[hi - lo + 1];
        int res = 0;
        int i = 0;
        int p1 = lo;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= hi) {
            res += arr[p1] < arr[p2] ? (hi - p2 + 1) * arr[p1] : 0;
            temp[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            temp[i++] = arr[p1++];
        }
        while (p2 <= hi) {
            temp[i++] = arr[p2++];
        }
        for (i = 0; i < temp.length; i++) {
            arr[lo + i] = temp[i];
        }
        return res;
    }

    //对数器
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];// [0,maxSize]
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        return temp;
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
}
