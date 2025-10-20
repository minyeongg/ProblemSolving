// Authored by : BaaaaaaaaaaarkingDog
// Co-authored by : -
// http://boj.kr/8c4c8cda5bcc473281c4cb834f7ae49b
import java.io.*;
import java.util.*;

public class Main {
    // board[dir][i][j][k] : i번째 판을 시계방향으로 dir번 돌렸을 때 (j, k)의 값
    static int[][][][] board = new int[4][5][5][5];
    static int[][][] maze = new int[5][5][5];
    static int[] dx = {1, 0, 0, 0, 0, -1};
    static int[] dy = {0, 1, -1, 0, 0, 0};
    static int[] dz = {0, 0, 0, 1, -1, 0};
    
    static int solve() {
        int[][][] dist = new int[5][5][5];
        if (maze[0][0][0] == 0 || maze[4][4][4] == 0) return 9999;
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 0});
        dist[0][0][0] = 1;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], z = cur[2];
            
            for (int dir = 0; dir < 6; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                int nz = z + dz[dir];
                
                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || nz < 0 || nz >= 5) continue;
                if (maze[nx][ny][nz] == 0 || dist[nx][ny][nz] != 0) continue;
                
                if (nx == 4 && ny == 4 && nz == 4)
                    return dist[x][y][z];
                
                dist[nx][ny][nz] = dist[x][y][z] + 1;
                q.offer(new int[]{nx, ny, nz});
            }
        }
        return 9999;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 5; k++) {
                    board[0][i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
            
            // 90도 회전
            for (int j = 0; j < 5; j++)
                for (int k = 0; k < 5; k++)
                    board[1][i][j][k] = board[0][i][4 - k][j];
            
            // 180도 회전
            for (int j = 0; j < 5; j++)
                for (int k = 0; k < 5; k++)
                    board[2][i][j][k] = board[1][i][4 - k][j];
            
            // 270도 회전
            for (int j = 0; j < 5; j++)
                for (int k = 0; k < 5; k++)
                    board[3][i][j][k] = board[2][i][4 - k][j];
        }
        
        int[] order = {0, 1, 2, 3, 4}; // 판을 쌓는 순서
        int ans = 9999;
        
        do {
            for (int tmp = 0; tmp < 1024; tmp++) {
                int brute = tmp; // 5개의 판에 대해 dir을 정해주기 위한 변수
                for (int i = 0; i < 5; i++) {
                    int dir = brute % 4;
                    brute /= 4;
                    for (int j = 0; j < 5; j++)
                        for (int k = 0; k < 5; k++)
                            maze[i][j][k] = board[dir][order[i]][j][k];
                }
                ans = Math.min(ans, solve());
            }
        } while (nextPermutation(order));
        
        if (ans == 9999) ans = -1;
        System.out.println(ans);
    }
    
    static boolean nextPermutation(int[] arr) {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) i--;
        if (i <= 0) return false;
        
        int j = arr.length - 1;
        while (arr[j] <= arr[i - 1]) j--;
        
        int temp = arr[i - 1];
        arr[i - 1] = arr[j];
        arr[j] = temp;
        
        j = arr.length - 1;
        while (i < j) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return true;
    }
}
