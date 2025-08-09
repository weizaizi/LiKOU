import java.util.ArrayDeque;
import java.util.Deque;

//155. 最小栈
@SuppressWarnings("all")
class MinStack {

    Deque<Integer> deque = new ArrayDeque<>();
    Deque<Integer> minDeque = new ArrayDeque<>();

    public MinStack() {
        minDeque.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        deque.push(val);
        minDeque.push(Math.min(val, minDeque.peek()));
    }

    public void pop() {
        deque.pop();
        minDeque.pop();
    }

    public int top() {
        return deque.peek();
    }

    public int getMin() {
        return minDeque.peek();
    }
}