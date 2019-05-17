/*
 * @lc app=leetcode.cn id=641 lang=java
 *
 * [641] 设计循环双端队列
 * 
 * 循环数组，使用size使first和last解耦
 */
class MyCircularDeque {
    private int[] datas;
    private int first; // head前面的下标
    private int last; // end后面的下标
    private int size; // 当前队列大小
    private int maxSize; // 队列容量

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        datas = new int[k];
        first = 0;
        last = 1; // first和last不同(他们是将要插入元素的位置)
        size = 0;
        maxSize = k;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is
     * successful.
     */
    public boolean insertFront(int value) {
        if (!isFull()) {
            size++; // 维护size
            datas[first] = value; // 插入元素
            first = correctIndex(first - 1); // 修正指针
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is
     * successful.
     */
    public boolean insertLast(int value) {
        if (!isFull()) {
            size++;
            datas[last] = value;
            last = correctIndex(last + 1);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is
     * successful.
     */
    public boolean deleteFront() {
        if (!isEmpty()) {
            size--;
            first = correctIndex(first + 1); // 将指针后移，相当于删除(会被下次插入的元素覆盖)
            return true;
        } else {
            return false;
        }
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is
     * successful.
     */
    public boolean deleteLast() {
        if (!isEmpty()) {
            size--;
            last = correctIndex(last - 1); // 同上
            return true;
        } else {
            return false;
        }
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (!isEmpty()) {
            return datas[correctIndex(first + 1)];
        } else {
            return -1;
        }
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (!isEmpty()) {
            return datas[correctIndex(last - 1)];
        } else {
            return -1;
        }
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size == maxSize;
    }

    // 修正因增删元素而导致first和last指针不合法的情况
    private int correctIndex(int index) { // 前提是队列不满
        if (index < 0) { // 下标小于0，则跳到数组末尾
            return datas.length - 1;
        } else if (index > datas.length - 1) { // 下标大于数组最大下标，则跳到数组开头
            return 0;
        } else {
            return index; // 指针合法，则不变
        }
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k); boolean param_1 =
 * obj.insertFront(value); boolean param_2 = obj.insertLast(value); boolean
 * param_3 = obj.deleteFront(); boolean param_4 = obj.deleteLast(); int param_5
 * = obj.getFront(); int param_6 = obj.getRear(); boolean param_7 =
 * obj.isEmpty(); boolean param_8 = obj.isFull();
 */
