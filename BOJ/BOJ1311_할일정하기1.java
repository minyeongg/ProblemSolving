import java.io.*;
import java.util.*;

public class BOJ1311_할일정하기1 {
    static final int INF = (int) 1e9;
    static int N;
    static int[][] W;
    static int[] dp;
    static int solution(int visited) {
        int person = Integer.bitCount(visited);
       if (person == N) return 0;
       if (dp[visited] != -1) return dp[visited];

       int best = INF;
       for (int job = 0; job < N; job++) {
           if ((visited & (1 << job)) != 0) continue;
           best = Math.min(best, W[person][job] + solution(visited | (1 << job)));
       }

       dp[visited] = best;
       return best;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        dp = new int[1 << N];
        Arrays.fill(dp, -1);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(0));
        br.close();
    }
}
