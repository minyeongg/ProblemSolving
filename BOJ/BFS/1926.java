import java.util.*;
import java.io.*;

class Main {
    
    static int N, M;
    static int[][] paper;
    static boolean[][] visited;
    static int value = 0;
    static int size = 0;
    static int cnt = 0;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        paper = new int[N][M];
        visited = new boolean[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        Queue<int[]> q = new LinkedList<>();
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (paper[i][j] == 1 && !visited[i][j]) {
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                    cnt++;
                    size = 1;
                }
                
                while(!q.isEmpty()) {
                    int[] cur = q.poll();
                    for (int dir = 0; dir < 4; dir++) {
                        int nx = cur[0] + dx[dir];
                        int ny = cur[1] + dy[dir];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                        if (!visited[nx][ny] && paper[nx][ny] == 1) {
                            q.add(new int[]{nx, ny});
                            size++;
                            visited[nx][ny] = true;
                        }
                    }
                }
                value = Math.max(value, size);
            }
        }
        
        System.out.println(cnt);
        if (cnt == 0) {
           System.out.println(0); 
        } else {
            System.out.println(value);
        }
    }
}
