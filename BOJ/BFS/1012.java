import java.util.*;
import java.io.*;

class Main {
    
    static int M, N, K;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
        
            map = new int[M][N];
            visited = new boolean[M][N];
        
            Queue<int[]> q = new LinkedList<>();
        
            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y] = 1;
            }
            
            int count = 0;
            
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {
                        q.add(new int[]{i, j});
                        count++;
                        while(!q.isEmpty()) {
                            int[] cur = q.poll();
                            for (int dir = 0; dir < 4; dir++) {
                                int nx = cur[0] + dx[dir];
                                int ny = cur[1] + dy[dir];
                                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                                if (map[nx][ny] == 0 || visited[nx][ny]) continue;
                                visited[nx][ny] = true;
                                q.add(new int[]{nx, ny});
                            }
                        } 
                    }
                }
            }
            
            System.out.println(count);
        } 
    }
}
