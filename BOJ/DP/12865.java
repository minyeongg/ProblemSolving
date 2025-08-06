import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[][] item = new int[N + 1][2];
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            item[i][0] = w;
            item[i][1] = k;
        }
        
        int[][] dp = new int[N + 1][K + 1];
        
        for (int k = 1; k <= K; k++) {
            for (int n = 1; n <= N; n++) {
                if (k >= item[n][0]) {
                    dp[n][k] = Math.max(dp[n-1][k], item[n][1] + dp[n-1][k - item[n][0]]);;
                } else {
                    dp[n][k] = dp[n-1][k];
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}
