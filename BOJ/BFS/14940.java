import java.util.*;
import java.io.*;

public class Main {
    
    static int N, M;
    static int[][] map;
    static int[][] dist;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        dist = new int[N][M];
        
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dist[i][j] = -1;
            }
        }
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    q.add(new int[] {i, j});
                    dist[i][j] = 0;
                }
                if (map[i][j] == 0) {
                    dist[i][j] = 0;
                }
            }
        }
        
        while(!q.isEmpty()) {
            int[] p = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int nx = p[0] + dx[i];
                int ny = p[1] + dy[i];
                if (nx >= 0 && ny >= 0 && nx < N && ny < M && dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[p[0]][p[1]] + 1;
                    q.add(new int[] {nx, ny});
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(dist[i][j] + " ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb);
        
    }
}
