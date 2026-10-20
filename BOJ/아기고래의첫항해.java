import java.io.*;
import java.util.*;

public class 아기고래의첫항해{
    public static int[] dx = {0, -1, 0, 1, 0};
    public static int[] dy = {0, 0, 1, 0, -1};
    public static int CUR_D = 0; // 초기화할때만 방향 조정해주기.
    // 1: 상 2: 우 3: 하 4: 좌. 시계 방향: + 1 % 4 반시계 방향:  + 2 % 4 + 1
    public static int cx, cy;
    public static StringBuilder sb = new StringBuilder();
    public static int N;
    public static boolean[][] v;
    public static int[][] map;
    public static boolean oob(int x, int y) {
        if (x < 1 || y < 1 || x > N || y > N) return true;
        return false;
    }
    public static void main(String[] args) throws Exception {
        // N, r, c, d 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        cx = Integer.parseInt(input[1]);
        cy = Integer.parseInt(input[2]);
        switch(Integer.parseInt(input[3])) {
            case 1: CUR_D = 1; break;
            case 2: CUR_D = 3; break;
            case 3: CUR_D = 4; break;
            case 4: CUR_D = 2; break;
            default: break;
        }

        v = new boolean[N + 2][N + 2];
        map = new int[N + 2][N + 2];

        // 격자 정보 입력받기
        for (int i = 1; i <= N; i++) {
            input = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(input[j - 1]);
            }
        }
        v[cx][cy] = true;
        sb.append(cx).append(" ").append(cy).append("\n");
        while (true) {
            if (!explore()) {
                if (!move()) {
                    break;
                }
            }
        }

        System.out.println(sb);
        br.close();

    }

    //  boolean 인접 탐험 ()
    /**
     for d in 4:
     새 방향에 방문안한 칸이 있으면 이동
     N[][] true로 갱신
     CUR_D 갱신
     cx, cy 갱신
     sb에 추가
     return true;
     return false;
     */
    public static boolean explore() {
        for (int d = 1; d <= 4; d++) {
            int tmp = CUR_D;
            switch(d) {
                case 1: break;
                case 2: tmp = (CUR_D + 2) % 4 + 1; break;
                case 3: tmp = CUR_D % 4 + 1; break;
                case 4: tmp = (CUR_D % 4 + 1) % 4 + 1; break;
                default: break;
            }
            int nx = cx + dx[tmp];
            int ny = cy + dy[tmp];
            if (oob(nx, ny)) continue;
            if (!v[nx][ny] && map[nx][ny] == 0) {
                v[nx][ny] = true;
                CUR_D = tmp;
                sb.append(nx).append(" ").append(ny).append("\n");
                cx = nx; cy = ny;
                return true;
            }

        }
        return false;
    }

    public static boolean move() {
        int[] target = bfs();
        if (target == null) return false;
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 2][N + 2];
        q.add(new int[] {cx, cy});
        visited[cx][cy] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curx = cur[0], cury = cur[1];
            int[] dir = {4, 3, 2, 1};
            for (int d : dir) {
                int nx = curx + dx[d];
                int ny = cury + dy[d];

                if (nx == target[0] && ny == target[1]) {
                    CUR_D = d;
                    cx = nx; cy = ny;
                    v[nx][ny] = true;
                    sb.append(nx).append(" ").append(ny).append("\n");
                    return true;
                }
                if (oob(nx, ny)) continue;
                if (map[nx][ny] == 1) continue;
                if (visited[nx][ny]) continue;
                q.add(new int[] {nx, ny});
                visited[nx][ny] = true;
            }
        }
        return false;
    }



    // 가장 가까운 바다로 이동 ()
        /**
         * 상 좌 우 하 순으로 bfs 돌면서 다음칸 선택
         * 좌 하 우 상 순서로 탐색하면서 이동
         * CUR_D 갱신
         * sb에 추가
         * return true;
         *
         * return false;
         *
        */

    //상 좌 우 하 순으로 bfs 돌면서 다음칸 선택
    public static int[] bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 2][N + 2];
        q.add(new int[] {cx, cy});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curx = cur[0], cury = cur[1];
            int[] dir = {1, 4, 2, 3};
            for (int d : dir) {
                int nx = curx + dx[d];
                int ny = cury + dy[d];
                if (oob(nx, ny)) continue;
                if (map[nx][ny] == 1) continue;
                if (visited[nx][ny]) continue;
                visited[nx][ny] = true;
                if (v[nx][ny]) {
                    q.add(new int[] {nx, ny});
                }
                else {
                    return new int[]{nx, ny};
                }
            }
        }

        return null;
    }
}
