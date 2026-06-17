import java.io.*;
import java.util.*;

class 짝지어 제거하기
{
    public int solution(String s)
    {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            }
            else {
                stack.push(c);
            }
        }
        
        return stack.isEmpty() ? 1 : 0;
    }
}
