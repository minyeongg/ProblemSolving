import java.util.*;
import java.io.*;

class Main {
    static LinkedList<Integer>[] dq = new LinkedList[5];
    
    private static int checkLeft(int start) {
        if (start == 1) {
            return start;
        }
        int prev = start - 1;
        int startValue = dq[start].get(6);
        int prevValue = dq[prev].get(2);
        if (startValue != prevValue) {
            return checkLeft(start - 1);
        }
        return start;
    }
    
    private static int checkRight(int start) {
        if (start == 4) {
            return start;
        }
        int next = start + 1;
        int startValue = dq[start].get(2);
        int nextValue = dq[next].get(6);
        if (startValue != nextValue) {
            return checkRight(start + 1);
        }
        return start;
    }
    
    private static void rotate(int idx, int dir) {
        if (dir == 1) {
            int target = dq[idx].pollLast();
            dq[idx].offerFirst(target);
        } else if (dir == -1) {
            int target = dq[idx].pollFirst();
            dq[idx].offerLast(target);
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        for (int i = 1; i <= 4; i++) {
            dq[i] = new LinkedList<>();
        }
        for (int i = 1; i <= 4; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < 8; j++) {
                dq[i].offer(Integer.parseInt(s.substring(j, j+1)));
            }
        }
        
        int k = sc.nextInt();
        for (int i = 0; i < k; i++) {
            int idx = sc.nextInt();
            int dir = sc.nextInt();
            
            int leftEnd = checkLeft(idx);
            int rightEnd = checkRight(idx);
            
            // 중앙 바퀴를 먼저 회전
            rotate(idx, dir);
            
            // 좌측 바퀴들 회전
            if (leftEnd < idx) {
                int leftDir = 0 - dir;
                for (int j = idx - 1; j >= leftEnd; j--) {
                    rotate(j, leftDir);
                    leftDir = 0 - leftDir;
                }
            }
            
            // 우측 바퀴들 회전
            if (idx < rightEnd) {
                int rightDir = 0 - dir;
                for (int j = idx + 1; j <= rightEnd; j++) {
                    rotate(j, rightDir);
                    rightDir = 0 - rightDir;
                }
            }
        }
        
        int point = 0;
        for (int i = 1; i <= 4; i++) {
            int target = dq[i].peek();
            point += target * (1 << (i - 1));
        }
        System.out.println(point);
    }
}
