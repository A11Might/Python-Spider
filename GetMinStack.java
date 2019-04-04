import java.util.Stack;

/**
 * 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作。 
 * 1． pop、push、getMin操作的时间复杂度都是O(1)。
 * 2． 设计的栈类型可以使用现成的栈结构。
 */

public class GetMinStack {
    /**
     * 数据栈和最小值栈中元素个数相同
     */
    public static class MyStack1 {
        private Stack<Integer> data;//储存压入数据的栈
        private Stack<Integer> min;//储存当前栈中最小值的栈
    
        public MyStack1() {
            data = new Stack<Integer>();
            min = new Stack<Integer>();
        }
        
        public void push(int obj) {
            if (min.isEmpty()) {//当最小值栈为空时，obj直接压入min
                min.push(obj);
            } else if (obj < min.peek()) {//当入栈元素小于最小栈栈顶时，obj直接压入min
                min.push(obj);
            } else {//其他情况为最小栈栈顶元素仍未当前栈的最小值，再将其压入min一次
                min.push(min.peek());
            }
            data.push(obj);
        }
    
        public Integer pop() {
            if (data.isEmpty()) {
                throw new RuntimeException("the stack is empty");
            }
            min.pop();//date栈进行pop()操作，min栈也跟着pop()，维持min栈顶为当前栈的最小值
            return data.pop();
        }
    
        public Integer peek() {
            if (data.isEmpty()) {
                return null;
            }
            return data.peek();
        }
    
        public Integer getMin() {
            if (data.isEmpty()) {
                throw new RuntimeException("the stack is empty");
            }
            return min.peek();
        }
    }

    /**
     * 数据栈中元素大于等于最小值栈中元素个数
     */  
    public static class MyStack2 {
        private Stack<Integer> data2;
        private Stack<Integer> min2;

        public MyStack2() {
            data2 = new Stack<Integer>();
            min2 = new Stack<Integer>();
        }

        /**
         * 当min2栈为空或入栈元素小于最小栈栈顶时，obj直接压入min2，其他情况min2栈不变
         * @param obj
         */
        public void push(int obj) {
            if (min2.isEmpty()) {
                min2.push(obj);
            } else if (obj <= min2.peek()) {
                min2.push(obj);
            }
            data2.push(obj);
        }

        /**
         * 只有当data2栈顶元素等于min2栈顶时，min2才pop()，否则只有data2栈pop()
         * 当压入元素小于等于min2栈顶时，min2才会压入当前元素，即当压入元素大于min2栈顶的时，栈的最小值没变，重复使用当前最小值栈的栈顶当最小值
         * 当data2出栈元素为当前的最小值时，最小值栈才一起出栈，否则最小值栈不变
         * @return
         */
        public Integer pop() {
            if (data2.isEmpty()) {
                throw new RuntimeException("the stack is empty");
            }
            int temp = data2.pop();
            if (temp == min2.peek()) {
                min2.pop();
            }
            return temp;
        }

        public Integer peek() {
            if (data2.isEmpty()) {
                return null;
            }
            return data2.peek();
        }

        public Integer getMin() {
            if (data2.isEmpty()) {
                throw new RuntimeException("the stack is empty");
            }
            return min2.peek();
        }

        public static void main(String[] args) {
            MyStack1 stack1 = new MyStack1();
            stack1.push(3);
            System.out.println(stack1.getMin());
            stack1.push(4);
            System.out.println(stack1.getMin());
            stack1.push(1);
            System.out.println(stack1.getMin());
            System.out.println(stack1.pop());
            System.out.println(stack1.getMin());

            System.out.println("=============");

            MyStack1 stack2 = new MyStack1();
            stack2.push(3);
            System.out.println(stack2.getMin());
            stack2.push(4);
            System.out.println(stack2.getMin());
            stack2.push(1);
            System.out.println(stack2.getMin());
            System.out.println(stack2.pop());
            System.out.println(stack2.getMin());
        }
    }
}