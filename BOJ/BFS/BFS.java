import java.util.*;

public class Main {
    // 보드 정의 (1이 파란 칸, 0이 빨간 칸에 대응)
    static int[][] board = {
        {1,1,1,0,1,0,0,0,0,0},
        {1,0,0,0,1,0,0,0,0,0},
        {1,1,1,0,1,0,0,0,0,0},
        {1,1,0,0,1,0,0,0,0,0},
        {0,1,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0}
    };
    
    static boolean[][] vis = new boolean[502][502]; // 방문 여부 저장
    static int n = 7, m = 10; // n = 행의 수, m = 열의 수
    
    // 상하좌우 방향 정의
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public static void main(String[] args) {
        Queue<Pair> queue = new LinkedList<>();
        
        vis[0][0] = true; // (0, 0)을 방문했다고 명시
        queue.add(new Pair(0, 0)); // 큐에 시작점인 (0, 0)을 삽입
        
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            System.out.print("(" + cur.x + ", " + cur.y + ") -> ");
            
            for (int dir = 0; dir < 4; dir++) { // 상하좌우 칸을 살펴볼 것이다.
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir]; // nx, ny에 dir에서 정한 방향의 인접한 칸의 좌표가 들어감
                
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue; // 범위 밖일 경우 넘어감
                if (vis[nx][ny] || board[nx][ny] != 1) continue; // 이미 방문한 칸이거나 파란 칸이 아닐 경우
                
                vis[nx][ny] = true; // (nx, ny)를 방문했다고 명시
                queue.add(new Pair(nx, ny));
            }
        }
    }
  
    static class Pair {
        int x, y;
        
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
