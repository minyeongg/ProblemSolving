import java.util.*;
import java.io.*;

class Main {
    
    static int w, h, k;
    static int[][] map;
    static int[][][] visited; // visited[x][y][k] = (x,y)에 말 점프 k번 사용했을 때의 거리
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] hx = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] hy = {-2, -1, 1, 2, 2, 1, -1, -2};
    
    static class State {
        int x, y, k;
        State(int x, int y, int k) {
            this.x = x; this.y = y; this.k = k;
        }
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        
        map = new int[h][w];
        visited = new int[h][w][k + 1];
        
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        Queue<State> q = new LinkedList<>();
        q.add(new State(0, 0, 0));
        visited[0][0][0] = 1;
        
        while (!q.isEmpty()) {
            State cur = q.poll();
            int vk = cur.k, vx = cur.x, vy = cur.y;
            
            if (vk < k) {
                for (int dir = 0; dir < 8; dir++) {
                    int nx = vx + hx[dir];
                    int ny = vy + hy[dir];
                    if (nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
                    if (map[nx][ny] == 1) continue;
                    if (visited[nx][ny][vk + 1] > 0) continue;
                    visited[nx][ny][vk + 1] = visited[vx][vy][vk] + 1;
                    q.add(new State(nx, ny, vk + 1));
                }
            }
            
            for (int dir = 0; dir < 4; dir++) {
                int nx = vx + dx[dir];
                int ny = vy + dy[dir];
                if (nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
                if (map[nx][ny] == 1) continue;
                if (visited[nx][ny][vk] > 0) continue;
                visited[nx][ny][vk] = visited[vx][vy][vk] + 1;
                q.add(new State(nx, ny, vk));
            }
        }
        
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= k; i++) {
            if (visited[h - 1][w - 1][i] != 0) {
                ans = Math.min(ans, visited[h - 1][w - 1][i]);
            }
        }
        
        if (ans != Integer.MAX_VALUE) {
            System.out.println(ans - 1);
        } else {
            System.out.println(-1);
        }
    }
}
