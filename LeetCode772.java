import java.util.*;

public class LeetCode772 {
    // this will be best shortest solution for this question. See Labuladong
    // 笔记来做这个题目。尝试去理解为什么用queue。

    public int calculate(String s) {
        if (s == null)
            return 0;
        Queue<Character> queue = new LinkedList<>();
        for (char c : s.toCharArray()) {
            queue.add(c);
        }

        queue.offer('+'); // should append +
        return cal(queue);
    }

    private int cal(Queue<Character> q) {
        char sign = '+';
        int num = 0;
        Stack<Integer> stack = new Stack<>();
        while (!q.isEmpty()) {
            char c = q.poll();
            if (c == ' ')
                continue;

            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '(') {
                num = cal(q);
            } else {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }

                num = 0;
                sign = c;
                if (c == ')')
                    break;
            }
        }

        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }
}
