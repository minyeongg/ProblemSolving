import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int[][] video;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        video = new int[N][N];
        sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                video[i][j] = Integer.parseInt(s.substring(j, j + 1));
            }
        }
        solve(0, 0, N);
        System.out.println(sb);
    }
    
    private static void solve(int x, int y, int n) {
        if (same(x, y, n)) {
            sb.append(video[x][y]);
            return;
        }
        int newN = n / 2;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (i == 0 && j == 0) sb.append("(");
                solve(x + i * newN, y + j * newN, newN);
                if (i == 1 && j == 1) sb.append(")");
            }
        }
    }
    
    private static boolean same(int x, int y, int n) {
        int color = video[x][y];
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (video[i][j] != color) return false;
            }
        }
        return true;
    }
}
