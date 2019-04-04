import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 如何仅用队列结构实现栈结构？ 如何仅用栈结构实现队列结构？
 */

public class StackAndQueueConvert {
    /**
     * 使用队列实现栈结构
     */
    public static class TwoQueueStack {
        private Queue<Integer> queue;
        private Queue<Integer> help;

        public TwoQueueStack() {
            queue = new LinkedList<Integer>();
            help = new LinkedList<Integer>();
        }

        public void push(int obj) {
            queue.add(obj);
        }

        public Integer pop() {
            if (queue.isEmpty()) {
                throw new RuntimeException("the stack is empty");
            }
            while (queue.size() > 1) {// 将size - 1个元素从queue出队并入队到help数组，剩余最后一个元素即为栈顶
                help.add(queue.poll());
            }
            int temp = queue.poll();//直接出队返回值temp
            swap(queue, help);
            return temp;
        }

        public Integer peek() {
            if (queue.isEmpty()) {
                return null;
            }
            while (queue.size() > 1) {//将size - 1个元素从queue出队并入队到help数组，剩余最后一个元素即为栈顶
                help.add(queue.poll());
            }
            int temp = queue.poll();
            help.add(temp);//出队后需要再入队
            swap(queue, help);
            return temp;
        }

        public void swap(Queue<Integer> a, Queue<Integer> b) {
            Queue<Integer> temp = help;
            help = queue;
            queue = temp;
        }
    }

    /**
     * 使用栈实现队列结构
     */
    public static class TwoStackQueue {
        private Stack<Integer> push;//入队时压入元素的栈
        private Stack<Integer> pop;//出队时弹出元素的栈

        public TwoStackQueue() {
            push = new Stack<Integer>();
            pop = new Stack<Integer>();
        }

        public void push(int obj) {
            push.push(obj);
        }

        public Integer poll() {
            if (push.isEmpty() && pop.isEmpty()) {//当push栈和pop栈都为空时，队列为空
                throw new RuntimeException("the queue is empty");
            } else if (pop.isEmpty()) {//当push栈不为空而pop栈为空时，将push栈中元素依次弹出并压入pop栈，此时栈顶即为队首元素
                while (!push.empty()) {
                    pop.add(push.pop());
                }
            }
            return pop.pop();//pop栈中有元素时直接弹出栈顶，即为队首元素
        }

        public Integer peek() {
            if (push.isEmpty() && pop.isEmpty()) {//当push栈和pop栈都为空时，队列为空
                return null;
            } else if (pop.isEmpty()) {//当push栈不为空而pop栈为空时，将push栈中元素依次弹出并压入pop栈，此时栈顶即为队首元素
                while (!push.empty()) {
                    pop.add(push.pop());
                }
            }
            return pop.peek();//pop栈中有元素时直接peek栈顶，即为队首元素
        }
    }
}
