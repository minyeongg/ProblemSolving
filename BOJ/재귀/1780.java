import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] paper;
    static int[] cnt = new int[3]; // -1, 0, 1 종이 개수

    // 해당 구간이 모두 같은 수로 채워져 있는지 확인
    static boolean check(int x, int y, int n) {
        int num = paper[x][y];
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (paper[i][j] != num) return false;
            }
        }
        return true;
    }

    static void solve(int x, int y, int n) {
        if (check(x, y, n)) {
            cnt[paper[x][y] + 1]++;  // -1 → 0, 0 → 1, 1 → 2
            return;
        }
        int newN = n / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                solve(x + i * newN, y + j * newN, newN);
            }
        }
    }

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
        for (int i = 0; i < 3; i++) sb.append(cnt[i]).append("\n");
        System.out.print(sb);
    }
}
