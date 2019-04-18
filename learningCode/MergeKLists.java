import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并k个有序链表
 */

 public class MergeKLists {
     public static class Node {
         public int value;
         public Node next;

         public Node(int value) {
             this.value = value;
         }
     }

     public static class MinHeapComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }
         
     }

     /**
      * 维护一个大小为k的最小堆，存入k个链表的最小值，类似快排partition
      * @param nodeList
      * @return
      */
     public static Node mergeKLists1(Node[] nodeList) {
         PriorityQueue<Node> minHeap = new PriorityQueue<>(new MinHeapComparator());
         for (Node temp : nodeList) {
             if (temp != null) {
                 minHeap.add(temp);
             }
         }
         Node res = null;//合并后链表的头结点
         Node end = null;//合并后链表的尾节点
         Node cur = null;//当前节点
         while (!minHeap.isEmpty()) {
            cur = minHeap.poll();
            if (res == null) {
                res = cur;
                end = cur;
            } else {
                end.next = cur;
                end = end.next;
            }
            if (cur.next != null) {
                minHeap.add(cur.next);
            }
         }
         return res;
     }

     public static Node mergeTwoLists(Node head1, Node head2) {
         Node res = new Node(0);
         Node end = res;
         while (head1 != null && head2 != null) {
             if (head1.value < head2.value) {
                 end.next = head1;
                 end = end.next;
                 head1 = head1.next;
             } else {
                 end.next = head2;
                 end = end.next;
                 head2 = head2.next;
             }
         }
         if (head1 == null) {
             end.next = head2;
         } else {
             end.next = head1;
         }
         return res.next;
     }

     /**
      * 用归并排序的分治思想
      */
     public static Node mergeKLists2(Node[] nodeList) {
         int l = nodeList.length;
         if (l == 0) {
             return null;
         } else if (l == 1) {
             return nodeList[0];
         } else if (l == 2) {
             return mergeTwoLists(nodeList[0], nodeList[1]);
         }
         int mid = l / 2;
         Node[] l1 = new Node[mid];
         for (int i = 0; i < mid; ++i) {
             l1[i] = nodeList[i];
         }
         Node[] l2 = new Node[l - mid];
         for (int i = 0, j= mid; j < l; ++i, ++j) {
             l2[i] = nodeList[j];
         }
         return mergeTwoLists(mergeKLists2(l1), mergeKLists2(l2));
     }

     //for test
     public static void main(String[] args) {
         Node a1 = new Node(1);
         Node a2 = new Node(3);
         Node a3 = new Node(5);
         Node b1 = new Node(1);
         Node b2 = new Node(2);
         Node b3 = new Node(3);
         Node c1 = new Node(4);
         Node c2 = new Node(6);
         a1.next = a2;
         a2.next = a3;
         a3.next = null;
         b1.next = b2;
         b2.next = b3;
         b3.next = null;
         c1.next = c2;
         c2.next = null;
         Node[] nodeList = {a1, b1, c1};
         Node first = mergeKLists2(nodeList);
         while (first != null) {
             System.out.println(first.value);
             first = first.next;
         }
     }
 }
