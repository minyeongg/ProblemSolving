import java.util.*;
import java.io.*;

class Main {
    
    static char[][] map = new char[12][6];
    static boolean[][] visited = new boolean[12][6];
    static int cnt = 0;
    static List<Integer[]> group = new ArrayList<>();
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    private static boolean check(int x, int y) {
        char puyoColor = map[x][y];
        Queue<int[]> q = new LinkedList<>();
        List<Integer[]> currentGroup = new ArrayList<>(); // 현재 BFS로 찾은 그룹

        q.add(new int[]{x, y});
        visited[x][y] = true; // 탐색 시작 시 방문 처리
        currentGroup.add(new Integer[]{x, y});

        int size = 1;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];
                if (nx < 0 || ny < 0 || nx >= 12 || ny >= 6) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == map[cur[0]][cur[1]]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                    currentGroup.add(new Integer[]{nx, ny});
                    size++;
                }
            }
        }
        if (size >= 4) {
                group.addAll(currentGroup);
                return true;
        }
        return false;
    }
    
    private static void boom() {
        for (Integer[] p : group) {
            map[p[0]][p[1]] = '.';
        }
        group.clear();
    }
    
    private static void gravity() {
        for (int j = 0; j < 6; j++) {
            char[] tilted = new char[12];
            Arrays.fill(tilted, '.');
            
            int idx = 11;
            for (int i = 11; i >= 0; i--) {
                if (map[i][j] == '.') continue;
                tilted[idx] = map[i][j];
                idx--;
            }
            for (int i = 0; i < 12; i++) {
                map[i][j] = tilted[i];
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int i = 0; i < 12; i++) {
            map[i] = br.readLine().toCharArray();
        }
        
        while(true) {
            visited = new boolean[12][6];
            boolean checked = false;
            
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] != '.' && !visited[i][j]) {
                        if (check(i, j)) {
                            checked = true; // 하나라도 터진경우
                        }
                    }
                }
            } // 한 턴 끝
            if (checked) { // 하나라도 터진경우
                cnt++;
                boom();
                gravity();
            } else {
                break;
            }
        }
        
        System.out.println(cnt);
    }
}
