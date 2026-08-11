import java.util.*;

class 백돌흑돌 {
  
    int N;
    char[][] map;
  
    int countInLine(char[] arr, int len) {
        int bcnt = 0;
        int total = 0;
        boolean metW = false;
        for (int i = 0; i < len; i++) {
            if (arr[i] == 'W') {
                if (metW) {
                    total += bcnt;
                    bcnt = 0;
                }
                else {
                    metW = true;
                }
            }
            else if (arr[i] == 'B') {
                if (metW) {
                    bcnt++;
                }
            }
            else if (arr[i] == '.') {
                if (metW) {
                    bcnt = 0;
                    metW = false;
                }
            }
        }
        return total;
    }

    int scanLine(int x, int y, int dir) {
        char[] arr = new char[N];
        int len = 0;

        if (dir == 0) {                       // 세로
            for (int i = 0; i < N; i++) {
                arr[len] = map[i][y];
                len++;
            }
        }
        else if (dir == 2) {                  // 가로
            for (int i = 0; i < N; i++) {
                arr[len] = map[x][i];
                len++;
            }
        }
        else if (dir == 3) {                  // 우하향 대각선
            int r = x, c = y;
            while (r > 0 && c > 0) {
                r--; c--;
            }
            while (r < N && c < N) {
                arr[len] = map[r][c];
                len++; r++; c++;
            }
        }
        else if (dir == 1) {                  // 우상향 대각선
            int r = x, c = y;
            while (r < N - 1 && c > 0) {
                r++; c--;
            }
            while (r >= 0 && c < N) {
                arr[len] = map[r][c];
                len++; r--; c++;
            }
        }

        return countInLine(arr, len);
    }

    public int solution(char[][] board) {
        N = board.length;
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = board[i][j];
            }
        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '.') {
                    map[i][j] = 'W';
                    int cnt = 0;
                    for (int dir = 0; dir < 4; dir++) {
                        cnt += scanLine(i, j, dir);
                    }
                    answer = Math.max(answer, cnt);
                    map[i][j] = '.';
                }
            }
        }
        return answer;
    }
}
