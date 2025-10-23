/*
static: int[][] board, visited, dx, dy
oob
solve(x, y, depth)
main:
    board 입력받기
    각 칸을 선택
        깊이 4의 DFS
        최댓값 갱신
    해당 칸 선택 해제
    최댓값 출력
*/

import java.util.*;
import java.io.*;

class Main {
    static int n, m, mx, sum;
    static int[][] board = new int[502][502];
    static boolean[][] visited = new boolean[502][502];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean oob(int x, int y) {
        if (x < 0 || y < 0 || x >= n || y >= m) return true;
        return false;
    }
    static void solve(int x, int y, int depth) {
        if (depth >= 4) {
            mx = Math.max(mx, sum);
            return;
        }
        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (oob(nx, ny)) continue;
            if (visited[nx][ny]) continue;
            if (depth == 2) {
                visited[nx][ny] = true;
                sum += board[nx][ny];
                solve(x, y, depth + 1);
                visited[nx][ny] = false;
                sum -= board[nx][ny];
            }
            visited[nx][ny] = true;
            sum += board[nx][ny];
            solve(nx, ny, depth + 1);
            visited[nx][ny] = false;
            sum -= board[nx][ny];
         }
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                sum += board[i][j];
                solve(i, j, 1);
                visited[i][j] = false;
                sum -= board[i][j];
            }
        }
        
        System.out.println(mx);
    }
}

