import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * prim算法 适用范围：要求无向图
 */

 public class Prim {
     public static class EdgeComparator implements Comparator<Edge> {
         @Override
         public int compare(Edge o1, Edge o2) {
             return o1.weight - o2.weight;
         }
     }
     
     public static Set<Edge> primMST(Graph graph) {
         Queue<Edge> minHeap = new PriorityQueue<>(new EdgeComparator());
         HashSet<Node> nodeSet = new HashSet<>();
         Set<Edge> res = new HashSet<>();
         Edge curEdge = null;
         Node toNode = null;
         for (Node node : graph.nodes.values()) {
            if (!nodeSet.contains(node)) {
                nodeSet.add(node);
                for (Edge edge : node.edges) {
                     minHeap.add(edge);
                }
                while (!minHeap.isEmpty()) {
                     curEdge = minHeap.poll();
                     toNode = curEdge.to;
                     if (!nodeSet.contains(toNode)) { // 不会重复加入已加入的边，如果边已经加入，那么toNode一定已经在set里了
                         nodeSet.add(toNode);
                         res.add(curEdge);
                         for (Edge edge : toNode.edges) {
                             minHeap.add(edge);
                         }
                     }
                }
            }
         }
         return res;
     }
 }