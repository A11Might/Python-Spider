/**
 * 在一个无序数组中，找出前 k 个小的元素
 * 
 * 1、用大小为k的大顶堆
 * 2、使用bfprt找到第 k 小的数，再遍历一遍无序数组找到前 k 个小的元素
 */

public class BFPRT {

    // O(N * logk)
    public static int[] getMinKNumsByHeap(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return arr;
        }
        int[] kHeap = new int[k];
        for (int i = 0; i != k; ++i) {
            heapInsert(kHeap, arr[i], i);
        }
        for (int i = k; i != arr.length; ++i) {
            if (arr[i] < kHeap[0]) {
                kHeap[0] = arr[i];
                heapify(kHeap, 0, k);
            }
        }
        return kHeap;
    }

    public static void heapInsert(int[] arr, int value, int index) {
        arr[index] = value;
        while (index != 0) {
            int parent = (index - 1) / 2;
            if (arr[parent] < arr[index]) {
                swap(arr, parent, index);
                index = parent;
            } else {
                break;
            }
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int largest = index;
        while (left < heapSize) {
            if (arr[left] > arr[index]) {
                largest = left;
            }
            if (right < heapSize && arr[right] > arr[largest]) {
                largest = right;
            }
            if (largest != index) {
                swap(arr, largest, index);
            } else {
                break;
            }
            index = largest;
            left = index * 2 + 1;
            right = index * 2 + 2;
        }
    }

    // O(N)
    public static int[] getMinKNumsByBFPRT(int[] arr, int k) {
		if (k < 1 || k > arr.length) {
			return arr;
		}
		int minKth = getMinKthByBFPRT(arr, k);
		int[] res = new int[k];
		int index = 0;
		for (int i = 0; i != arr.length; i++) {
			if (arr[i] < minKth) {
				res[index++] = arr[i];
			}
		}
		for (; index != res.length; index++) {
			res[index] = minKth;
		}
		return res;
    }
    
	public static int getMinKthByBFPRT(int[] arr, int K) {
		int[] copyArr = copyArray(arr);
		return bfprt(copyArr, 0, copyArr.length - 1, K - 1);
	}
 
	public static int[] copyArray(int[] arr) {
		int[] res = new int[arr.length];
		for (int i = 0; i != res.length; i++) {
			res[i] = arr[i];
		}
		return res;
    }
    
	public static int bfprt(int[] arr, int begin, int end, int i) {
		if (begin == end) {
			return arr[begin];
		}
		int pivot = medianOfMedians(arr, begin, end); // n / 10个组的中位数中的中位数
		int[] pivotRange = partition(arr, begin, end, pivot); // 将arr数组分为小于等于大于时，等于pivot的范围
		// 命中直接返回
		if (i >= pivotRange[0] && i <= pivotRange[1]) {
			return arr[i];
		// 否则跳至小于部分或大于部分，继续bfprt
		} else if (i < pivotRange[0]) {
			return bfprt(arr, begin, pivotRange[0] - 1, i);
		} else {
			return bfprt(arr, pivotRange[1] + 1, end, i);
		}
    }
	
	// 找到n / 10个组的中位数中的中位数
	public static int medianOfMedians(int[] arr, int begin, int end) {
		int num = end - begin + 1;
		int offset = num % 5 == 0 ? 0 : 1; // 不满5个元素的也成一组
		int[] mArr = new int[num / 5 + offset]; // 每组的中位数组成的数组
		for (int i = 0; i < mArr.length; i++) {
			int beginI = begin + i * 5; // 每组的开始位置
			int endI = beginI + 4; // 每组的结束位置
			mArr[i] = getMedian(arr, beginI, Math.min(end, endI)); // 获取每一组的中位数
		}
		return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2); // 找到mArr数组的中位数
    }
    
	public static int[] partition(int[] arr, int begin, int end, int pivotValue) {
		int small = begin - 1;
		int cur = begin;
		int big = end + 1;
		while (cur != big) {
			if (arr[cur] < pivotValue) {
				swap(arr, ++small, cur++);
			} else if (arr[cur] > pivotValue) {
				swap(arr, cur, --big);
			} else {
				cur++;
			}
		}
		int[] range = new int[2];
		range[0] = small + 1;
		range[1] = big - 1;
		return range;
    }
	
	// 获取当前数组中位数
	public static int getMedian(int[] arr, int begin, int end) {
		insertionSort(arr, begin, end);
		int sum = end + begin;
		int mid = (sum / 2) + (sum % 2); // 奇数取中位数；偶数取中间位置前面的数为中位数
		return arr[mid];
    }
	
	// 插排
	public static void insertionSort(int[] arr, int begin, int end) {
		for (int i = begin + 1; i != end + 1; i++) {
			for (int j = i; j != begin; j--) {
				if (arr[j - 1] > arr[j]) {
					swap(arr, j - 1, j);
				} else {
					break;
				}
			}
		}
    }
    
	public static void swap(int[] arr, int index1, int index2) {
		int tmp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = tmp;
    }
	
	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

    public static void main(String[] args) {
        int[] arr = {6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9};
        printArray(getMinKNumsByHeap(arr, 10));
        printArray(getMinKNumsByBFPRT(arr, 10));
    }
}
