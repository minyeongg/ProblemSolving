import java.io.*;
import java.util.*;

class Main {
    static int w, h;
    static char[][] map = new char[1002][1002];
    static int[][] fire = new int[1002][1002];
    static int[][] move = new int[1002][1002];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean oob(int x, int y) {
        return (x < 0 || x >= h || y < 0 || y >= w);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            fire = new int[h][w];
            move = new int[h][w];
            Queue<int[]> f = new LinkedList<>();
            Queue<int[]> m = new LinkedList<>();
            for (int i = 0; i < h; i++) {
                Arrays.fill(fire[i], -1);
                Arrays.fill(move[i], -1);
            }
            for (int i = 0; i < h; i++) {
                char[] ch = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    map[i][j] = ch[j];
                    if (map[i][j] == '@') {
                        m.add(new int[]{i, j});
                        move[i][j] = 0;
                    }
                    if (map[i][j] == '*') {
                        f.add(new int[]{i, j});
                        fire[i][j] = 0;
                    }
                }
            }
            
            while (!f.isEmpty()) {
                int[] cur = f.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur[0] + dx[dir];
                    int ny = cur[1] + dy[dir];
                    if (oob(nx, ny)) continue;
                    if (fire[nx][ny] > -1) continue;
                    if (map[nx][ny] == '#') continue;
                    f.add(new int[]{nx, ny});
                    fire[nx][ny] = fire[cur[0]][cur[1]] + 1;
                }
            }
            boolean escape = false;
            while(!escape && !m.isEmpty()) {
                int[] cur = m.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur[0] + dx[dir];
                    int ny = cur[1] + dy[dir];
                    if (oob(nx, ny)) {
                        System.out.println(move[cur[0]][cur[1]] + 1);
                        escape = true;
                        break;
                    }
                    if (move[nx][ny] > -1) continue;
                    if (map[nx][ny] == '#') continue;
                    if (fire[nx][ny] >= 0 && fire[nx][ny] <= move[cur[0]][cur[1]] + 1) continue;
                    m.add(new int[]{nx, ny});
                    move[nx][ny] = move[cur[0]][cur[1]] + 1;
                }
            }
            if (escape) continue;
            System.out.println("IMPOSSIBLE");
        }
    }
}
