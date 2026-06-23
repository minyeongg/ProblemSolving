import java.io.*;
import java.util.*;

class 기능 개발 {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < progresses.length; i++) {
            int share = (100 - progresses[i]) / speeds[i];
            q.offer((100 - progresses[i]) % speeds[i] > 0 ? share + 1 : share);
        }
        
        List<Integer> list = new ArrayList<>();
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            int cnt = 1;
            while (!q.isEmpty() && q.peek() <= cur) {
                q.poll();
                cnt++;
            }
            list.add(cnt);
        }
        
        return list.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}
