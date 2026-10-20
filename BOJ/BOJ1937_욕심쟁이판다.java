import java.io.*;
import java.util.*;

public class BOJ1937_욕심쟁이판다 {
    static int n;
    static int[][] map;
    static int[][] dp;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean oob(int x, int y) {
        if (x <= 0 || y <= 0 || x > n || y > n) return true;
        return false;
    }

    static int dfs(int x, int y) {
        if (dp[x][y] != -1) return dp[x][y];
        dp[x][y] = 1;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d], ny = y + dy[d];
            if (oob(nx, ny)) continue;
            if (map[nx][ny] <= map[x][y]) continue;
            dp[x][y] = Math.max(dp[x][y], 1 + dfs(nx, ny));
        }
        return dp[x][y];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        dp = new int[n + 1][n + 1];
        for (int[] row : dp) Arrays.fill(row, -1);

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                answer = Math.max(answer, dfs(i, j));
            }
        }
        System.out.println(answer);
        br.close();
    }
}
