/**
 * 用数组结构实现大小固定的队列和栈
 */

public class ArrayToStackQueue {
    /**
     * 数组实现栈
     */
    public static class ArrayStack {
        private Integer[] arr;
        private Integer size;//size代表当前栈中元素数量、待插入数据位置的下标

        public ArrayStack(int initSize) {
            if (initSize < 0) {
                throw new IllegalArgumentException("the initSize is less than 0");
            }
            arr = new Integer[initSize];
            size = 0;
        }
        
        public void push(int obj) {
            if (size == arr.length) {
                throw new ArrayIndexOutOfBoundsException("the stack is full");
            }
            arr[size++] = obj;
        }

        public Integer pop() {
            if (size == 0) {
                throw new ArrayIndexOutOfBoundsException("the stack is empty");
            }
            return arr[--size];//栈弹出一个元素后引用向前移一位
        }

        public Integer peek() {
            if (size == 0) {
                return null;
            }
            return arr[size - 1];//size没变，只看不拿
        }
    }

    /**
     * 数组实现队列
     */
    public static class ArrayQueue {
        private Integer[] arr;
        private Integer size;//将first与last解耦
        private Integer first;//队首数据位置的下标
        private Integer last;//待插入数据位置的下标

        public ArrayQueue(int initSize) {
            if (initSize < 0) {
                throw new IllegalArgumentException("the initSize is less than 0");
            }
            arr = new Integer[initSize];
            size = 0;
            first = 0;
            last = 0;
        }

        public void push(int obj) {
            if (size == arr.length) {
                throw new ArrayIndexOutOfBoundsException("the queue is full");
            }
            ++size;
            arr[last] = obj;
            last = last == arr.length - 1 ? 0 : last + 1;
        }

        public Integer poll() {
            if (size == 0) {
                throw new ArrayIndexOutOfBoundsException("the queue is empty");
            }
            --size;
            int temp = first;
            first = first == arr.length - 1 ? 0 : first + 1;//队列弹出一个元素后引用向后位移一位
            return arr[temp];
        }

        public Integer peek() {
            if (size == 0) {
                return null;
            }
            return arr[first];
        }
    }
}
