import java.io.*;
import java.util.*;
import java.lang.*;

public class BOJ16991_외판원순회3 {
    static int N;
    static final double INF = 1e9;
    static double[][] W;
    static double[][] dp;
    static class City {
        int idx, x, y;
        public City(int idx, int x, int y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
    }

    public static double solution(int cur, int visited) {
        if (visited == ((1 << N) - 1)) {
            return W[cur][0];
        }

        if (dp[cur][visited] != -1) return dp[cur][visited];

        double best = INF;
        for (int next = 0; next < N; next++) {
            if ((visited & (1 << next)) != 0) continue;
            best = Math.min(best, W[cur][next] + solution(next, visited | (1 << next)));
        }

        dp[cur][visited] = best;
        return best;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        City[] cities = new City[N];
        W = new double[N][N];
        dp = new double[N][1 << N];
        for (double[] row : dp) Arrays.fill(row, -1);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            cities[i] = new City(i, x, y);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int x1 = cities[i].x, y1 = cities[i].y;
                int x2 = cities[j].x, y2 = cities[j].y;
                W[i][j] = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
            }
        }

        System.out.println(solution(0, 1));
    }
}
