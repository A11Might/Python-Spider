package class_6;

import java.util.HashSet;
import java.util.Stack;

/**
 * 深度优先遍历 
 * 1，利用栈实现 
 * 2，从源节点开始把节点按照深度放入栈，然后弹出 
 * 3，每弹出一个点，把该节点下一个没有进过栈的邻接点放入栈 
 * 4，直到栈变空
 */

 public class DFS {
     public static void dfs(Node node) {
         if (node == null) {
             return;
         }
         Stack<Node> stack = new Stack<>();
         HashSet<Node> set = new HashSet<>();
         Node cur = null;
         stack.push(node);
         System.out.println(node.value);
         set.add(node);
         while (!stack.isEmpty()) {
             cur = stack.pop();
             for (Node next : cur.nexts) {
                 if (!set.contains(next)) {
                     stack.push(cur);
                     stack.push(next);
                     System.out.println(next.value); // 第一次压入栈中时打印
                     set.add(next);
                     break;
                 }
             }
         }
     }
 }