/**
 * 给定一个数组arr， 和一个数num， 请把小于等于num的数放在数 组的左边， 大于num的数放在数组的右边。 要求额外空间复杂度O(1)，
 * 时间复杂度O(N) 
 * 问题二（荷兰国旗问题） 给定一个数组arr， 和一个数num， 请把小于num的数放在数组的 左边， 等于num的数放在数组的中间，
 * 大于num的数放在数组的 右边。 要求额外空间复杂度O(1)， 时间复杂度O(N)
 */

 public class NetherlandsFlag {
     /**
      * 问题一
      * @param arr
      * @param num
      * @return
      */
     public static int[] partition1(int[] arr, int num) {
        int j = -1;//<=num的最后一个位置的下标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= num) {
                swap(arr, ++j, i);
            }
        }
        return arr;
     }
     private static void swap(int[] arr, int a, int b) {
         int temp = arr[b];
         arr[b] = arr[a];
         arr[a] = temp;
     }
     /**
      * 问题二
      * @param arr
      * @param num
      * @return
      */
     public static int[] partition2(int[] arr, int num) {
         int less = -1;//<num区间最后元素的下标
         int more = arr.length;//>num区间第一个元素的下标
         int cur = 0;//当前遍历的元素下标
         while (cur < more) {
             if (arr[cur] < num) {
                 swap(arr, ++less, cur++);//当前元素小于目标元素时，交换小于区间后第一个元素与当前元素，遍历下一个元素
             } else if (arr[cur] == num) {
                cur++;//当前元素与目标元素相等时，直接遍历下一个元素
             } else {
                swap(arr, --more, cur);//当前元素比目标元素大时，交换大于区间前第一个元素与当前元素，对从后面交换来得元素再进行一次循环判断
             }
         }
         return arr;
     }

    // 对数器
    public static int[] generateArray() {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 3);
        }
        return arr;
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
        int[] test = generateArray();

        printArray(test);
        printArray(partition2(test, 1));
    }
 }
