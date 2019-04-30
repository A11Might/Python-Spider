package class_6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

/**
 * Dijkstra算法：没有权值为负数的边
 */

 public class Dijkstra {

     // 经典dijkstra算法
     public static HashMap<Node, Integer> dijkstra1(Node head) {
         HashMap<Node, Integer> distanceMap = new HashMap<>(); // 当前节点到头节点的距离
         distanceMap.put(head, 0);
         HashSet<Node> selectedNodes = new HashSet<>(); // toNode全部更新进入distanceMap的节点

         Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
         while (minNode != null) {
             int distance = distanceMap.get(minNode);
             for (Edge edge : minNode.edges) {
                 Node toNode = edge.to;
                 int weight = edge.weight;
                 if (!distanceMap.containsKey(toNode)) {
                     distanceMap.put(toNode, distance + weight);
                 }
                 distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + weight));
             }
             selectedNodes.add(minNode);
             minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
         }
         return distanceMap;
     }

     private static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> touchedNodes) {
         Node minNode = null;
         int minDistance = Integer.MAX_VALUE;
         for (Entry<Node, Integer> entry : distanceMap.entrySet()) {
             Node cur = entry.getKey();
             int distance = entry.getValue();
             if (!touchedNodes.contains(cur) && minDistance > distance) {
                 minNode = cur;
                 minDistance = distance;
             }
         }
         return minNode;
     }

     
     // 堆优化的dijkstra算法
     public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
     }

     public static class NodeHeap {
         private Node[] nodes;
         private HashMap<Node, Integer> distanceMap;
         private HashMap<Node, Integer> nodeIndexMap;
         private int size;

         public NodeHeap(int size) {
             nodes = new Node[size];
             distanceMap = new HashMap<>();
             nodeIndexMap = new HashMap<>();
             this.size = 0;
         }

         public void addOrUpdateOrIgnore(Node node, int distance) { // 忽略弹出过的节点
             if (inHeap(node)) {
                 distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                 insertHeapify(node); // 因为distance的值只能变小，所以只用从当前更新的节点向上堆化(下面的节点一定大于它)
             }
             if (!isEntryed(node)) {
                 nodes[size] = node;
                 distanceMap.put(node, distance);
                 nodeIndexMap.put(node, size++);
                 insertHeapify(node); // 堆化的时候会更新nodeIndexMap中的index(swap中)
             }
         }

         public NodeRecord pop() {
             NodeRecord res = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
             swap(0, size - 1);
             distanceMap.remove(nodes[size - 1]);
             nodeIndexMap.put(nodes[size - 1], -1); // 把删除节点的index更新为-1，用于标记进过堆中，但被pop
             nodes[size - 1] = null;
             heapify(0, --size); 
             return res;
         }

         private void insertHeapify(Node node) {
             int index = nodeIndexMap.get(node);
             while (distanceMap.get(node) < distanceMap.get(nodes[(index - 1) / 2])) {
                 swap(index, (index - 1) /2);
                 index = (index - 1) / 2;
             }
         }

         private void heapify(int index, int size) {
             int left = index * 2 + 1;
             while (left < size) {
                 int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
                              ? left + 1 : left;
                 smallest = distanceMap.get(nodes[index]) < distanceMap.get(nodes[smallest]) 
                              ? index : smallest;
                 if (smallest == index) {
                     return;
                 }
                 swap(index, smallest);
                 index = smallest;
                 left = index * 2 + 1;
             }
         }

         private void swap(int index1, int index2) {
             nodeIndexMap.put(nodes[index1], index2);
             nodeIndexMap.put(nodes[index2], index1);
             Node temp = nodes[index2];
             nodes[index2] = nodes[index1];
             nodes[index1] = temp;
         }

         public boolean isEmpty() {
             return size == 0;
         }

         public boolean isEntryed(Node node) {
             return nodeIndexMap.containsKey(node);
         }

         public boolean inHeap(Node node) {
             return isEntryed(node) && nodeIndexMap.get(node) != -1;
         }
     }

     public static HashMap<Node, Integer> dijkstra2(Node head, int size) { // 需要总节点数，用于初始化堆
         NodeHeap nodeHeap = new NodeHeap(size);
         nodeHeap.addOrUpdateOrIgnore(head, 0);
         HashMap<Node, Integer> res = new HashMap<>();
         while (!nodeHeap.isEmpty()) {
             NodeRecord record = nodeHeap.pop();
             Node cur = record.node;
             int distance = record.distance;
             for (Edge edge : cur.edges) {
                 nodeHeap.addOrUpdateOrIgnore(edge.to, distance + edge.weight);
             }
             res.put(cur, distance);
         }
         return res;
     }
 }
