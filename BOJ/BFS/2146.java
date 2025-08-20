import java.io.*;
import java.util.*;

public class Main {
    static final int MX = 102;
    static int[][] board = new int[MX][MX];
    static int[][] vis = new int[MX][MX];
    static int[][] dist = new int[MX][MX];
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n, cnt = 0;
    static Queue<int[]> Q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1단계: 섬 라벨링
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0 || vis[i][j] == 1) continue;
                cnt++;
                vis[i][j] = 1;
                board[i][j] = cnt;
                Q.add(new int[]{i, j});
                while (!Q.isEmpty()) {
                    int[] cur = Q.poll();
                    for (int dir = 0; dir < 4; dir++) {
                        int nx = cur[0] + dx[dir];
                        int ny = cur[1] + dy[dir];
                        if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                        if (vis[nx][ny] == 1 || board[nx][ny] == 0) continue;
                        board[nx][ny] = cnt;
                        vis[nx][ny] = 1;
                        Q.add(new int[]{nx, ny});
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], -1);
        }

        int ans = Integer.MAX_VALUE;

        // 2단계: 각 육지 칸에서 BFS 시작
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0) {
                    Q.add(new int[]{i, j});
                    dist[i][j] = 0;
                    boolean escape = false;
                    while (!Q.isEmpty() && !escape) {
                        int[] cur = Q.poll();
                        for (int dir = 0; dir < 4; dir++) {
                            int nx = cur[0] + dx[dir];
                            int ny = cur[1] + dy[dir];
                            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                            if (dist[nx][ny] >= 0 || board[i][j] == board[nx][ny]) continue;
                            if (board[nx][ny] != 0 && board[i][j] != board[nx][ny]) {
                                ans = Math.min(ans, dist[cur[0]][cur[1]]);
                                Q.clear();
                                escape = true;
                                break;
                            }
                            dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
                            Q.add(new int[]{nx, ny});
                        }
                    }
                    for (int k = 0; k < n; k++) {
                        Arrays.fill(dist[k], -1);
                    }
                }
            }
        }
        
        System.out.println(ans);
    }
}
