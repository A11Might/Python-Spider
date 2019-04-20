import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 输入：参数1，正数数组costs 
 * 参数2，正数数组profits 
 * 参数3，正数k 
 * 参数4，正数m 
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润) 
 * k表示你不能并行、只能串行的最多做k个项目
 * m表示你初始的资金
 * 说明：你每做完一个项目，马上获得的收益，可以支持你去做下 一个项目 
 * 输出：你最后获得的最大钱数
 */

 public class IPO {
     public static class Node {
         public int cost;
         public int profit;

         public Node(int c, int p) {
             cost = c;
             profit = p;
         }
     }

     public static class MinCostComparator implements Comparator<Node> {
         @Override
         public int compare(Node o1, Node o2) {
             return o1.cost - o2.cost;
         }
     }

     public static class MaxProfitComparator implements Comparator<Node> {
         @Override
         public int compare(Node o1, Node o2) {
             return o2.profit - o1.profit;
         }
     }

     public static int findMaximizedCapital(int[] costs, int[] profits, int k, int m) {
         Queue<Node> minCostHeap = new PriorityQueue<>(new MinCostComparator());
         Queue<Node> maxProfitHeap = new PriorityQueue<>(new MaxProfitComparator());
         for (int i = 0; i < costs.length; ++i) {
             minCostHeap.add(new Node(costs[i], profits[i]));
         }
         for (int i = 0; i < k; ++i) {
             while (!minCostHeap.isEmpty() && minCostHeap.peek().cost <= m) {
                 maxProfitHeap.add(minCostHeap.poll());
             }
             if (maxProfitHeap.isEmpty()) {
                 return m;
             }
             m += maxProfitHeap.poll().profit;
         }
         return m;
     }
 }