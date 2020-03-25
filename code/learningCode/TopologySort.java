import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 拓扑排序算法 适用范围：要求有向图，且有入度为0的节点，且没有环
 */

 public class TopologySort {
     public static List<Node> sortedTopology(Graph graph) {
         HashMap<Node, Integer> inMap = new HashMap<>();
         Queue<Node> zeroInQueue = new LinkedList<>();
         for (Node node : graph.nodes.values()) {
             inMap.put(node, node.in);
             if (node.in == 0) {
                 zeroInQueue.add(node);
             }
         }
         List<Node> res = new ArrayList<>();
         Node cur = null;
         while (!zeroInQueue.isEmpty()) {
             cur = zeroInQueue.poll();
             res.add(cur);
             for (Node next : cur.nexts) {
                 inMap.put(next, inMap.get(next) - 1); // 需要拿inMap中的入度，更新移除当前节点作用后其后继节点的入度表中的入度
                 if (inMap.get(next) == 0) { // 同上，节点内部的入度不发生变化
                     zeroInQueue.add(next);
                 }
             }
         }
         return res;
     } 
 }