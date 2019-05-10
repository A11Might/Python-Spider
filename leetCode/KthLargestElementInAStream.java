import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=703 lang=java
 *
 * [703] 数据流中的第K大元素
 * 
 * 使用堆维护k大元素
 */
class KthLargest {
    private PriorityQueue<Integer> pq;
    private int size; // nums.size >= k - 1，k大的堆可能没有满，用size判断

    public KthLargest(int k, int[] nums) {
        pq = new PriorityQueue<>();
        size = k;
        for (int num : nums) {
            if (pq.size() < size) {
                pq.add(num);
            } else if (num > pq.peek()) {
                pq.remove();
                pq.add(num);
            }
        }
    }
    
    public int add(int val) {
        // k大的堆没满
        if (pq.size() < size) {
            pq.add(val);
            return pq.peek();
        }
        // 堆满了
        if (val > pq.peek()) {
            pq.remove();
            pq.add(val);
            return pq.peek();
        } else {
            return pq.peek();
        }
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */

