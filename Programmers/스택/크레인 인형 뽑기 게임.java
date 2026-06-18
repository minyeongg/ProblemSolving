import java.io.*;
import java.util.*;

class 크레인 인형 뽑기 게임 {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int n = board.length;
        int[] top = new int[n];
        Arrays.fill(top, -1); // 해당 열에 인형이 없는 경우
        
        A: for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (board[i][j] != 0) {
                    top[j] = i;
                    continue A;
                }
            }
        }
        
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        
        for (int idx : moves) {
            int targetIdx = idx - 1;
            if (top[targetIdx] == -1) { // 해당 열에 인형이 없는 경우
                continue;
            }
            
            // 열에서 인형 뽑기
            int target = board[top[targetIdx]][targetIdx];
            board[top[targetIdx]][targetIdx] = 0; 
            if (++top[targetIdx] >= n) top[targetIdx] = -1;
            
            // 바구니에 인형 넣기
            if (!stack.isEmpty() && stack.peek() == target) {
                stack.pop();
                answer += 2;
            } 
            else {
                stack.push(target);
            }
            
        }
        return answer;
    }
}
