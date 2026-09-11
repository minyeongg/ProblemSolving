import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static boolean[][] v;
    static int[][] map;
    static boolean oob(int x, int y) {
        if (x < 1 || y < 1 || x > N || y > N) return true;
        return false;
    }
    static int[] dx = {-1, 0, 1, 0}; // 상좌하
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        // N, r, c, d 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        int r = Integer.parseInt(input[1]);
        int c = Integer.parseInt(input[2]);
        int d = Integer.parseInt(input[3]) - 1; // 0 - indexed;
        int[] dirMap = {0, 2, 1, 3};
        d = dirMap[d];

        v = new boolean[N + 2][N + 2];
        map = new int[N + 2][N + 2];

        int total = 0;
        int cnt = 0;

        // 격자 정보 입력받기
        for (int i = 1; i <= N; i++) {
            input = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(input[j - 1]);
                if (map[i][j] == 0) total++;
            }
        }

        v[r][c] = true;
        sb.append(r).append(" ").append(c).append("\n");
        cnt++;

        while (cnt < total) {
            // 1. 인접 탐험
            while (true) {
               int[] next = getNext(r, c, d);
               if (next[0] == -1) break;
               r = next[0]; c = next[1]; d = next[2];
               v[r][c] = true;
               cnt++;
               sb.append(r).append(" ").append(c).append("\n");
            }

            if (cnt >= total) break;

            // 2. 가장 가까운 바다로 이동
            int[][] distFrom = bfs(r, c);

            int tr = -1, tc = -1, minDist = -1;
            for (int i = 1; i <= N; i++)
                for (int j = 1; j <= N; j++) {
                    if (map[i][j] != 0 || v[i][j] || distFrom[i][j] == -1) continue;
                    if (minDist == -1 || distFrom[i][j] < minDist) {
                        tr = i; tc = j; minDist = distFrom[i][j];
                    }
                }
            
            int[][] distTo = bfs(tr, tc);

            int[] priority = {1, 2, 3, 0};
            while (r != tr || c != tc) {
                for (int dir : priority) {
                    int nr = r + dx[dir], nc = c + dy[dir];
                    if (!oob(nr, nc) && map[nr][nc] == 0 && distTo[nr][nc] == distTo[r][c] - 1) {
                        r = nr; c = nc; d = dir;
                        break;
                    }
                }
            }

            v[r][c] = true;
            cnt++;
            sb.append(r).append(" ").append(c).append("\n");
        }

        System.out.println(sb);
        br.close();

    }

    static int[] getNext(int r, int c, int d) {
        int[] deltas = {0, 1, 3, 2};
        for (int delta : deltas) {
            int nd = (d + delta) % 4;
            int nr = r + dx[nd];
            int nc = c + dy[nd];
            if (oob(nr, nc)) continue;
            if (map[nr][nc] == 0 && !v[nr][nc]) {
                return new int[] {nr, nc, nd};
            }
        }
        return new int[] {-1, -1, -1};
    }

    static int[][] bfs(int r, int c) {
        int[][] dist = new int[N + 2][N + 2];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], -1);
        }

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {r, c});
        dist[r][c] = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nr = cur[0] + dx[dir], nc = cur[1] + dy[dir];
                if (oob(nr, nc)) continue;
                if (map[nr][nc] != 0) continue;
                if (dist[nr][nc] != -1) continue;
                dist[nr][nc] = dist[cur[0]][cur[1]] + 1;
                q.add(new int[]{nr, nc});
            }
            
        }

        return dist;
    }
    
}
