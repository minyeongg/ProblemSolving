import java.util.*;
import java.io.*;

class Main {
    static int TC, bX, bY;
    static int[][] board = new int[1002][1002];
    static int[][] visF = new int[1002][1002];
    static int[][] visS = new int[1002][1002];
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        TC = Integer.parseInt(br.readLine());
        
        for (int testNo = 0; testNo < TC; testNo++) {
            boolean escape = false;
            Queue<int[]> Qf = new ArrayDeque<>();
            Queue<int[]> Qs = new ArrayDeque<>();
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            bY = Integer.parseInt(st.nextToken());
            bX = Integer.parseInt(st.nextToken());
            
            // 배열 초기화
            for (int i = 0; i < bX; i++) {
                for (int j = 0; j < bY; j++) {
                    visF[i][j] = 0;
                    visS[i][j] = 0;
                }
            }
            
            for (int i = 0; i < bX; i++) {
                String line = br.readLine();
                for (int j = 0; j < bY; j++) {
                    char c = line.charAt(j);
                    if (c == '#') {
                        board[i][j] = -1;
                    } else {
                        if (c == '@') {
                            Qs.offer(new int[]{i, j});
                            visS[i][j] = 1;
                        } else if (c == '*') {
                            Qf.offer(new int[]{i, j});
                            visF[i][j] = 1;
                        }
                        board[i][j] = 0;
                    }
                }
            }
            
            // 불 확산 BFS
            while (!Qf.isEmpty()) {
                int[] v = Qf.poll();
                for (int k = 0; k < 4; k++) {
                    int nx = v[0] + dx[k];
                    int ny = v[1] + dy[k];
                    if (nx < 0 || bX <= nx || ny < 0 || bY <= ny) continue;
                    if (board[nx][ny] == -1) continue;
                    if (visF[nx][ny] != 0) continue;
                    visF[nx][ny] = visF[v[0]][v[1]] + 1;
                    Qf.offer(new int[]{nx, ny});
                }
            }
            
            // 사람 이동 BFS
            while (!Qs.isEmpty() && !escape) {
                int[] v = Qs.poll();
                for (int k = 0; k < 4; k++) {
                    int nx = v[0] + dx[k];
                    int ny = v[1] + dy[k];
                    if (nx < 0 || bX <= nx || ny < 0 || bY <= ny) {
                        bw.write(visS[v[0]][v[1]] + "\n");
                        escape = true;
                        break;
                    }
                    if (board[nx][ny] == -1) continue;
                    if (visS[nx][ny] != 0) continue;
                    if (visF[nx][ny] != 0 && visF[nx][ny] <= visS[v[0]][v[1]] + 1) continue;
                    visS[nx][ny] = visS[v[0]][v[1]] + 1;
                    Qs.offer(new int[]{nx, ny});
                }
            }
            
            if (!escape) {
                bw.write("IMPOSSIBLE\n");
            }
        }
        
        bw.flush();
        bw.close();
    }
}


import java.util.*;
import java.io.*;

/*
fire: -1 (빈공간, 상근 시작위치) 0 (벽, 불 시작위치)
move: -1 (빈공간, 불 시작 위치) 0 (벽, 상근시작위치)
*/
class Main {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] map = new char[1000][1000];
    static Queue<int[]> f = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String[] str = br.readLine().split(" ");
            int w = Integer.parseInt(str[0]);
            int h = Integer.parseInt(str[1]);
            int[][] move = new int[h][w];
            int[][] fire = new int[h][w];
            Queue<int[]> sg = new LinkedList<>();
            for (int i = 0; i < h; i++) {
                String s = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = s.substring(j, j+1).charAt(0);
                    if (map[i][j] == '*') {
                        f.add(new int[]{i, j});
                        move[i][j] = -1;
                    }
                    if (map[i][j] == '@') {
                        sg.add(new int[]{i, j});
                        fire[i][j] = -1;
                    }
                    if (map[i][j] == '.') {
                        move[i][j] = -1;
                        fire[i][j] = -1;
                    }
                }
            }
            
            while(!f.isEmpty()) {
                int[] cur = f.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur[0] + dx[dir];
                    int ny = cur[1] + dy[dir];
                    if (nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
                    if (fire[nx][ny] >= 0) continue;
                    fire[nx][ny] = fire[cur[0]][cur[1]] + 1;
                    f.add(new int[]{nx, ny});
                }
            }
            boolean escape = false;
            while (!sg.isEmpty() && !escape) {
                int[] cur = sg.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur[0] + dx[dir];
                    int ny = cur[1] + dy[dir];
                    if (nx < 0 || ny < 0 || nx >= h || ny >= w) {
                        System.out.println(move[cur[0]][cur[1]] + 1);
                        escape = true;
                        break;
                    }
                    if (move[nx][ny] >= 0) continue;
                    if (fire[nx][ny] != -1 && move[cur[0]][cur[1]] + 1 >= fire[nx][ny]) continue;
                    move[nx][ny] = move[cur[0]][cur[1]] + 1;
                    sg.add(new int[]{nx, ny});
                }
            }
            if (!escape) {
                System.out.println("IMPOSSIBLE");
            }
        }
    }
}
