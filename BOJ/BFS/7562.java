import java.util.*;
import java.io.*;

class Main {
    
    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
    static int I;
    static int[][] dist;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            I = Integer.parseInt(br.readLine());
            dist = new int[I][I];
            for (int i = 0; i < I; i++) {
                for (int j = 0; j < I; j++) {
                    dist[i][j] = -1;
                }
            }
            int[] start = new int[2];
            StringTokenizer st = new StringTokenizer(br.readLine());
            start[0] = Integer.parseInt(st.nextToken());
            start[1] = Integer.parseInt(st.nextToken());
            int[] end = new int[2];
            st = new StringTokenizer(br.readLine());
            end[0] = Integer.parseInt(st.nextToken());
            end[1] = Integer.parseInt(st.nextToken());
            bfs(start[0], start[1]);
            System.out.println(dist[end[0]][end[1]]);
        }
    }
    
    private static void bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        dist[i][j] = 0;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for (int dir = 0; dir < 8; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];
                if (nx < 0 || ny < 0 || nx >= I || ny >= I) continue;
                if (dist[nx][ny] >= 0) continue;
                dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
                q.add(new int[]{nx, ny});    
            }
        }
    }
}
