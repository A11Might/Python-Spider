import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 求中位数
 */

 public class MedianQuick {
    public static class MedianHolder {
        private PriorityQueue<Integer> maxHeap;
        private PriorityQueue<Integer> minHeap;

        public MedianHolder() {
            maxHeap = new PriorityQueue<Integer>(new MaxHeapComparator());
            minHeap = new PriorityQueue<Integer>(new MinHeapComparator());
        }

        //调整两栈元素个数，保证相差不超过1
        public void modifyTwoHeapsSize() {
            if (maxHeap.size() == minHeap.size() + 2) {
                minHeap.add(maxHeap.poll());
            }
            if (minHeap.size() == maxHeap.size() + 2) {
                maxHeap.add(minHeap.poll());
            }
        }

        public void addNumber(int obj) {
            if (maxHeap.isEmpty()) {
                maxHeap.add(obj);
                return;
            }
            if (obj <= maxHeap.peek()) {
                maxHeap.add(obj);
            } else {
                minHeap.add(obj);
            }
            modifyTwoHeapsSize();
        }

        public void addNumber2(int num) {
            if (this.maxHeap.isEmpty()) {
                this.maxHeap.add(num);
                return;
            }
            if (this.maxHeap.peek() >= num) {
                this.maxHeap.add(num);
            } else {
                if (this.minHeap.isEmpty()) {
                    this.minHeap.add(num);
                    return;
                }
                if (this.minHeap.peek() > num) {
                    this.maxHeap.add(num);
                } else {
                    this.minHeap.add(num);
                }
            }
            modifyTwoHeapsSize();
        }

        public Integer getMedian() {
            int maxHeapSize = maxHeap.size();
            int minHeapSize = minHeap.size();
            if (maxHeapSize + minHeapSize == 0) {//元素个数为零
                return null;
            }
            Integer maxHeapTop = maxHeap.peek();
            Integer minHeapTop = minHeap.peek();
            if (((maxHeapSize + minHeapSize) & 1) == 0) {//元素个数为偶数
                return (maxHeapTop + minHeapTop) / 2;
            }
            return maxHeapSize > minHeapSize ? maxHeapTop : minHeapTop;//元素个数为基数
        }
    }

    //比较器
     public static class MaxHeapComparator implements Comparator<Integer> {
         @Override
         public int compare(Integer o1, Integer o2) {//返回0，相等
             if (o1 < o2) {
                 return 1;//返回正数后面的排在前面
             } else {
                 return -1;//返回负数前面的排在前面
             }
         }
     } 

     public static class MinHeapComparator implements Comparator<Integer> {
         @Override
         public int compare(Integer o1, Integer o2) {
             if (o1 > o2) {
                 return 1;
             } else {
                 return -1;
             }
         }
     }

    //对数器
    public static int[] getRandomArray(int maxLen, int maxValue) {
        int[] res = new int[(int) (Math.random() * maxLen) + 1];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue);
        }
        return res;
    }

    // for test, this method is ineffective but absolutely right
    public static int getMedianOfArray(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(newArr);
        int mid = (newArr.length - 1) / 2;
        if ((newArr.length & 1) == 0) {
            return (newArr[mid] + newArr[mid + 1]) / 2;
        } else {
            return newArr[mid];
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        boolean err = false;
        int testTimes = 200000;
        for (int i = 0; i != testTimes; i++) {
            int len = 30;
            int maxValue = 1000;
            int[] arr = getRandomArray(len, maxValue);
            MedianHolder medianHold = new MedianHolder();
            for (int j = 0; j != arr.length; j++) {//模拟流输入
                medianHold.addNumber(arr[j]);
            }
            if (medianHold.getMedian() != getMedianOfArray(arr)) {
                err = true;
                printArray(arr);
                break;
            }
        }
        System.out.println(err ? "Oops..what a fuck!" : "today is a beautiful day^_^");
    }
 }
