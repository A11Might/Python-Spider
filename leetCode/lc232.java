import java.util.Stack;

/*
 * @lc app=leetcode.cn id=232 lang=java
 *
 * [232] 用栈实现队列
 * 
 * 用两个栈，push， 入队时直接压入datas栈
 *          pop，  出队时将datas中的数据倒入help，弹出help的栈顶
 *          peek， 同弹出
 *          empty，当datas和help都为空时，队列中无数据
 */
class MyQueue {
    // datas和help都可能含有数据
    private Stack<Integer> datas;
    private Stack<Integer> help;

    /** Initialize your data structure here. */
    public MyQueue() {
        datas = new Stack<>();
        help  = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        datas.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (help.size() != 0) {
            return help.pop();
        }
        // help为空时才能将datas中元素倒入help
        while (!datas.isEmpty()) {
            help.push(datas.pop());
        }
        return help.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if (help.size() != 0) {
            return help.peek();
        }
        while (!datas.isEmpty()) {
            help.push(datas.pop());
        }
        return help.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return datas.isEmpty() && help.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

