import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 并查集
 */

 public class UnionFind {
     public static class Node {
         //whatever you want
     }

     public static class UnionFindSet {
         private HashMap<Node, Node> fatherMap;
         private HashMap<Node, Integer> sizeMap;

         public UnionFindSet(List<Node> nodeList) {
             fatherMap = new HashMap<>();
             sizeMap = new HashMap<>();
             for (Node node : nodeList) {
                  fatherMap.put(node, node);
                  sizeMap.put(node, 1);
             }
         }

         public Node findHead(Node node) {
             Stack<Node> stack = new Stack<>();
             Node cur = node;
             Node father = fatherMap.get(node);
             while (cur != father) {
                 stack.push(cur);
                 cur = father;
                 father = fatherMap.get(cur);
             }
             while (!stack.isEmpty()) {
                 fatherMap.put(stack.pop(), father);
             }
             return father;
         }

         public Node findHeadRecursion(Node node) {
             Node father = fatherMap.get(node);
             if (father != node) {
                 father = findHeadRecursion(father);
             }
             fatherMap.put(node, father);//重复将头结点的父亲设为自己后，依次将路径上的节点的父节点设为头结点
             return father;
         }

         public boolean isSameSets(Node a, Node b) {
             return findHead(a) == findHead(b);//加入优化并查集结构
         }

         public void union(Node a, Node b) {
             if (a == null || b == null) {
                 return;
             }
             Node aHead = findHead(a);// 加入优化并查集结构
             Node bHead = findHead(b);// 加入优化并查集结构
             if (aHead != bHead) {
                 int aSize = sizeMap.get(aHead);
                 int bSize = sizeMap.get(bHead);
                 if (aSize > bSize) {
                     fatherMap.put(bHead, aHead);
                     sizeMap.put(aHead, aSize + bSize);
                 } else {
                     fatherMap.put(aHead, bHead);
                     sizeMap.put(bHead, aSize + bSize);
                 }
             }
         }
     }
 }
