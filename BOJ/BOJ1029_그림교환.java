import java.io.*;
import java.util.*;

public class BOJ1029_그림교환 {
    static int N;
    static int[][] W;
    static int[][][] dp; // dp[cur][visited][price]는 visited명을 확인했을 때의 사람 수 최댓값

    public static int solution(int cur, int price, int visited) {
        if (dp[cur][visited][price] != -1) return dp[cur][visited][price];

        int best = 1;
        for (int next = 0; next < N; next++) {
            if ((visited & (1 << next)) != 0) continue;
            int cost = W[cur][next];
            if (cost < price) continue;
            best = Math.max(best, 1 + solution(next, cost, (visited | (1 << next))));
        }

        return dp[cur][visited][price] = best;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        dp = new int[N][1 << N][10];

        for (int[][] matrix : dp) {
            for (int[] row : matrix) Arrays.fill(row, -1);
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken()); // i가 j에게 판매하는 가격
            }
        }

        System.out.println(solution(0, 0,1));
    }
}
