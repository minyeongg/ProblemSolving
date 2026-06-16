import java.io.*;
import java.util.*;

class 방문 길이 {
    
    // 좌표평면 벗어나는지 체크
    private static boolean oob(int nx, int ny) {
        return nx < 0 || ny < 0 || nx > 10 || ny > 10;
    }
    
    // 다음 좌표 결정을 위한 해시맵
    private static final HashMap<Character, int[]> location = new HashMap<>();
    private static void initLocation() {
        location.put('U', new int[]{-1, 0});
        location.put('D', new int[]{1, 0});
        location.put('L', new int[]{0, -1});
        location.put('R', new int[]{0, 1});
    }
    
    public int solution(String dirs) {
        initLocation();
        int x = 5, y = 5; // 시작 좌표
        HashSet<String> answer = new HashSet<>();
        
        // 명령어로 움직이면서 좌표 저장
        for (int i = 0; i < dirs.length(); i++) {
            int[] offset = location.get(dirs.charAt(i));
            int nx = x + offset[0];
            int ny = y + offset[1];
            if (oob(nx, ny)) continue;
            answer.add(x + " " + y + " " + nx + " " + ny);
            answer.add(nx + " " + ny + " " + x + " " + y);
            x = nx;
            y = ny;
        }
        
        return answer.size() / 2;
    }
}
