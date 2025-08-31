import java.util.*;
import java.io.*;

class Main {
    static int[][] ice;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;
    static int n, m;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        ice = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                ice[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int year = 0;
        
        while (true) {
            visited = new boolean[n][m];
            int count = 0;
            
            // 빙산 덩어리 개수 세기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!visited[i][j] && ice[i][j] > 0) {
                        bfs(i, j);
                        count++;
                    }
                }
            }
            
            if (count >= 2) {
                System.out.println(year);
                return;
            }
            if (count == 0) {
                System.out.println(0);
                return;
            }
            
            melt();
            year++;
        }
    }
    
    static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        visited[x][y] = true;
        q.add(new int[]{x, y});
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];
                
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (!visited[nx][ny] && ice[nx][ny] > 0) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }
    
    static void melt() {
        int[][] meltAmount = new int[n][m];
        
        // 녹을 양 계산
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (ice[i][j] > 0) {
                    int seaCount = 0;
                    for (int dir = 0; dir < 4; dir++) {
                        int nx = i + dx[dir];
                        int ny = j + dy[dir];
                        if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                        if (ice[nx][ny] == 0) seaCount++;
                    }
                    meltAmount[i][j] = seaCount;
                }
            }
        }
        
        // 녹이기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ice[i][j] -= meltAmount[i][j];
                if (ice[i][j] < 0) ice[i][j] = 0;
            }
        }
    }
}
