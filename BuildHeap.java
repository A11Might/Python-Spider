/**
 * 将给定数组堆化
 */
public class BuildHeap {
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[index] > arr[largest] ? index : largest;
            if (index == largest) {
                break;
            }
            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }
    
    /**
     * 使用heapInsert实现堆化
     * @param arr
     */
    public static void buildHeapByHeapInsert(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            heapInsert(arr, i);//index依次增大，模拟插入
        }
    }

    /**
     * 使用heapify实现堆化
     * @param arr
     */
    public static void buildHeapByHeapify(int[] arr) {
        for (int i = arr.length - 1; i >= 0; --i) {
            if (i * 2 + 1 < arr.length) {
                heapify(arr, i, arr.length);//size直接是数组的长度，从当前堆顶一直堆化到堆底
            }
        }
    }
}
