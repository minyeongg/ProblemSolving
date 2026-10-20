import java.io.*;
import java.util.*;

public class BOJ15486_퇴사2 {
    static int N;
    static int[] T;
    static int[] P;
    static long[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        T = new int[N + 1];
        P = new int[N + 1];
        dp = new long[N + 2];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i] 정의: i일이 시작되기 전까지 벌 수 있는 최대 수익

        for (int i = 1; i <= N; i++) {
            // i일에 일함
            if (i + T[i] <= N + 1) {
                dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
            }

            //i일에 일하지 않음
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }
        System.out.println(dp[N + 1]);
    }
}
