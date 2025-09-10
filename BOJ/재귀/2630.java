import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int[][] paper;
    static int[] cnt = new int[2];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        paper = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve(0, 0, N);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 2; i++) {
            sb.append(cnt[i] + "\n");
        }
        System.out.println(sb);
    }
    
    private static void solve(int x, int y, int n) {
        if (same(x, y, n)) {
            cnt[paper[x][y]]++;
            return;
        }
        int newN = n / 2;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                solve(x + i * newN, y + j * newN, newN);
            }
        }
    }
    
    private static boolean same(int x, int y, int n) {
        int color = paper[x][y];
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (paper[i][j] != color) return false;
            }
        }
        return true;
    }
}
