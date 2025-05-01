import java.util.*;
import java.io.*;
public class Main {
    static int m, n, h;
    static int[][][] board;
    static int[][][] dist;
    static int[] dx = {1, 0, -1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1}; // 위 아래순
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        board = new int[h][n][m];
        dist = new int[h][n][m];
        Queue<Point> q = new LinkedList<>();
        
        // Initialize the board and distance array
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    board[i][j][k] = Integer.parseInt(st.nextToken());
                    if (board[i][j][k] == 1) {
                        q.add(new Point(i, j, k));
                        dist[i][j][k] = 0; // Set initial distance to 0 for ripe tomatoes
                    }
                    else if (board[i][j][k] == 0) {
                        dist[i][j][k] = -1; // Mark unripe tomatoes with -1 distance
                    }
                    else { // For board[i][j][k] == -1 (empty)
                        dist[i][j][k] = 0; // Empty spaces don't need to be considered
                    }
                }
            }
        }
        
        // BFS
        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int dir = 0; dir < 6; dir++) {
                int nz = cur.z + dz[dir];
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || nz < 0 || nz >= h) continue;
                if (board[nz][nx][ny] == -1 || dist[nz][nx][ny] >= 0) continue; // Skip empty spaces or already visited
                dist[nz][nx][ny] = dist[cur.z][cur.x][cur.y] + 1;
                q.add(new Point(nz, nx, ny));
            }
        }
        
        int ans = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (board[i][j][k] == 0 && dist[i][j][k] == -1) {
                        System.out.println(-1); // If any tomato is still unripe, return -1
                        return;
                    }
                    if (board[i][j][k] == 0) { // Only consider actual tomatoes
                        ans = Math.max(ans, dist[i][j][k]);
                    }
                }
            }
        }
        System.out.println(ans);
    }
    
    private static class Point {
       int z, x, y; // Changed order to match our array dimensions
       Point(int z, int x, int y) {
           this.z = z;
           this.x = x;
           this.y = y;
       }
    }
}
