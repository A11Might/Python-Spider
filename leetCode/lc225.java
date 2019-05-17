import java.util.LinkedList;
import java.util.Queue;

/*
 * @lc app=leetcode.cn id=225 lang=java
 *
 * [225] 用队列实现栈
 * 
 * 使用两个队列，push， 直接加入datas队列
 *              pop，  将datas队列size - 1个元素倒入help，出队datas最后一个元素
 *              peek， 同弹出
 *              empty，datas为空时，栈为中无数据
 */
class MyStack {
    // datas为存储数据的队列，help为辅助队列
    private Queue<Integer> datas;
    private Queue<Integer> help;

    /** Initialize your data structure here. */
    public MyStack() {
        datas = new LinkedList<>();
        help = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        datas.add(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        while (datas.size() != 1) {
            help.add(datas.poll());
        }
        int res = datas.poll();
        datas = help;  // 始终使datas为储存数据的队列
        help = new LinkedList<>(); // 重置help队列
        return res;
    }
    
    /** Get the top element. */
    public int top() {
        while (datas.size() != 1) {
            help.add(datas.poll());
        }
        int res = datas.peek();
        help.add(datas.poll());
        datas = help;
        help = new LinkedList<>();
        return res;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return datas.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */

