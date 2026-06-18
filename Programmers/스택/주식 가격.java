import java.io.*;
import java.util.*;

class 주식 가격 {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int target = stack.pop();
                answer[target] = i - target;
            }
            stack.push(i);
        }
        
        while (!stack.isEmpty()) {
            int target = stack.pop();
            answer[target] = n - 1 - target;
        }
        return answer;
    }
}
