import java.util.*;
import java.io.*;
public class Main {
    static int r, c;
    static int[][] fire;
    static int[][] jihun;
    static String[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new String[r][c];
        fire = new int[r][c];
        jihun = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                fire[i][j] = -1;
                jihun[i][j] = -1;
            }
        }
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> route = new LinkedList<>();
        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = s.substring(j, j + 1);
                if (map[i][j].equals("F")) {
                    q.add(new int[]{i, j});
                    fire[i][j] = 0;
                }
                if (map[i][j].equals("J")) {
                    route.add(new int[]{i, j});
                    jihun[i][j] = 0;
                }
            }
        }
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (fire[nx][ny] >= 0 || map[nx][ny].equals("#")) continue;
                fire[nx][ny] = fire[cur[0]][cur[1]] + 1;
                q.add(new int[]{nx, ny});
            }
        }
        
        while (!route.isEmpty()) {
            int[] cur = route.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                    System.out.println(jihun[cur[0]][cur[1]] + 1);
                    return;
                }
                if (jihun[nx][ny] >= 0 || map[nx][ny].equals("#")) continue;
                if (fire[nx][ny] != -1 && jihun[cur[0]][cur[1]] + 1 >= fire[nx][ny]) continue;
                jihun[nx][ny] = jihun[cur[0]][cur[1]] + 1;
                route.add(new int[]{nx, ny});
            }
        }
        System.out.println("IMPOSSIBLE");
    }
}
