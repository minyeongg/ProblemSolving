import java.util.*;
import java.io.*;

class Main {
    
    static int N;
    static String[][] map;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        map = new String[N][N];
        visited = new boolean[N][N];
        
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.substring(j, j + 1);
            }
        }
        
        int normal = area();
        visited = new boolean[N][N];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].equals("G")) {
                    map[i][j] = "R";
                }
            }
        }
        
        int rg = area();
        
        System.out.printf("%d %d", normal, rg);
    }
    
    private static void bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        visited[i][j] = true;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (!map[nx][ny].equals(map[cur[0]][cur[1]]) || visited[nx][ny]) continue;
                q.add(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
    }
    
    private static int area() {
        int cnt = 0; 
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
              if (!visited[i][j]) {
                  cnt++;
                  bfs(i, j);
              }
            }
        }
        return cnt;
    }
}
