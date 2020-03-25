import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的 比如长度为20的金条，不管切成长度多大的两半，都要花费20个铜板
 * 一群人想整分整块金条，怎么分最省铜板？例如,给定数组{10,20,30}，代表一共三个人， 整块金条长度为
 * 10+20+30=60.金条要分成10,20,30三个部分
 * 如果，先把长度60的金条分成10和50，花费60再把长度50的金条分成20和30，花费50一共花费110铜板
 * 但是如果，先把长度60的金条分成30和30，花费60再把长度30金条分成10和20，花费30一共花费90铜板 输入一个数组，返回分割的最小代价
 */

 public class LessMoney {
     public static int lessMoney(int[] arr) {
         Queue<Integer> minHeap = new PriorityQueue<>();
         for (int temp : arr) {
             minHeap.add(temp);
         }
         int cur = 0;
         int sum = 0;
         while (minHeap.size() != 1) {
             cur = minHeap.poll() + minHeap.poll();
             sum += cur;
             minHeap.add(cur);
         }
         return sum;
     }

     //for test
     public static void main(String[] args) {
        int[] arr = { 6, 7, 8, 9 };
        System.out.println(lessMoney(arr));
    }
 }