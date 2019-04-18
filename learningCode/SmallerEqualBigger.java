/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式 
 * 给定一个单向链表的头节点head，节点的值类型是整型，再给定一个整数pivot。 
 * 现一个调整链表的函数，将链表调整为左部分都是值小于 pivot 的节点，中间部分都是值等于pivot的节点，右部分都是值大于pivot的节点。
 * 除这个要求外，对调整后的节点顺序没有更多的要求。
 * 例如：链表9->0->4->5- >1，pivot=3。调整后链表可以是1->0->4->9->5，也可以是0->1->9->5->4。
 * 总之，满足左部分都是小于3的节点，中间部分都是等于3的节点 ，右部分都是大于3的节点即可。对某部分内部的节点顺序不做要求。
 * 
 * 进阶：在原问题的要求之上再增加如下两个要求。在左、中、右三个部分的内部也做顺序要求，要求每部分里的节点从左到右的顺序与原链表中节点的先后次序一致。
 * 例如：链表9->0->4->5->1，pivot=3。调整后的链表是0->1->9->4->5。 
 * 在满足原问题要求的同时， 左部分节点从左到右为0、1。在原链表中也是先出现0，后出现1；
 * 中间部分在本例中为空，不再讨论；
 * 右部分节点从左到右为9、 4、 5。在原链表中也是先出现9，然后出现4，最后出现5。
 * 如果链表长度为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1)。
 */

 public class SmallerEqualBigger {
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 维护一个节点数组存储节点，问题变为荷兰国旗问题
     * 时间复杂度请达到O(N)，空间复杂度请达到O(N)，不稳定
     * @param head
     * @param piovt
     * @return
     */
    public static Node listPartition1(Node head, int piovt) {
        if (head == null) {
            return head;
        }
        int i = 0;
        Node p = head;
        while (p != null) {
            ++i;
            p = p.next;
        }
        Node[] nodeArr = new Node[i];
        i = 0;
        while (head != null) {
            nodeArr[i++] = head;
            head = head.next;
        }
        arrPartition(nodeArr, piovt);
        for (i = 0; i < nodeArr.length - 1; ++i) {
            nodeArr[i].next = nodeArr[i + 1];
        }
        nodeArr[nodeArr.length - 1].next = null;
        return nodeArr[0];
    }

    public static void arrPartition(Node[] arr, int piovt) {
        int small = -1;
        int big = arr.length;
        int index = 0;
        while (index != big) {
            if (arr[index].value < piovt) {
                swap(arr, ++small, index++);
            } else if (arr[index].value > piovt) {
                swap(arr, --big, index);
            } else {
                ++index;
            }
        }
    }

    public static void swap(Node[] arr, int a, int b) {
        Node temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }

    /**
     * 将原链表拆为小于等于大于pivot的三个子链表后，再合并 
     * 时间复杂度请达到O(N)，额外空间复杂度请达到O(1)，稳定
     * @param head
     * @param pivot
     * @return
     */
    public static Node listPartition2(Node head, int pivot) {
        Node smallHead = null;
        Node smallTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node bigHead = null;
        Node bigTail = null;
        Node next = null;//防止断链
        //将原链表分为小于等于大于三个子链表
        while (head != null) {
            next = head.next;
            head.next = null;//将节点单独拿出来（bigTail.next可能指向前面的节点上导致，循环打印）
            if (head.value < pivot) {
                if (smallHead == null) {
                    smallHead = head;
                    smallTail = head;
                } else {
                    smallTail.next = head;
                    smallTail = head;
                }
            } else if (head.value > pivot) {
                if (bigHead == null) {
                    bigHead = head;
                    bigTail = head;
                } else {
                    bigTail.next = head;
                    bigTail = head;
                }
            } else {
                if (equalHead == null) {
                    equalHead = head;
                    equalTail = head;
                } else {
                    equalTail.next = head;
                    equalTail = head;
                }
            }
            head = next;
        }
        //将链表连接起来
        if (smallTail != null) {
            smallTail.next = equalHead;
            equalTail = equalTail == null ? smallTail : equalTail;
        }
        if (equalTail != null) {
            equalTail.next = bigHead;
        }
        return smallHead != null ? smallHead : equalHead != null ? equalHead : bigHead;
    }

    //for test
    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        // head1 = listPartition1(head1, 4);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }
 }
