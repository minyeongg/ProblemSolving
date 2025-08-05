import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static char[][] board;
    static int[][][] dist; // dist[x][y][0]: 벽 안 부순 경우, dist[x][y][1]: 벽 부순 경우
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static boolean oob(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

    static int bfs() {
        dist = new int[n][m][2];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                Arrays.fill(dist[i][j], -1);

        Queue<int[]> q = new ArrayDeque<>();
        dist[0][0][0] = dist[0][0][1] = 1;
        q.offer(new int[]{0, 0, 0}); // x, y, broken

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], broken = cur[2];

            if (x == n - 1 && y == m - 1) return dist[x][y][broken];

            int nextDist = dist[x][y][broken] + 1;

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (oob(nx, ny)) continue;

                // 벽이 아니고 방문하지 않은 경우
                if (board[nx][ny] == '0' && dist[nx][ny][broken] == -1) {
                    dist[nx][ny][broken] = nextDist;
                    q.offer(new int[]{nx, ny, broken});
                }

                // 벽이고 아직 안 부쉈을 경우
                if (board[nx][ny] == '1' && broken == 0 && dist[nx][ny][1] == -1) {
                    dist[nx][ny][1] = nextDist;
                    q.offer(new int[]{nx, ny, 1});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            board[i] = row.toCharArray();
        }

        System.out.println(bfs());
    }
}
