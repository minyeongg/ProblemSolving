import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[1003];

        // dp[i] =  dp[i - 2] + dp[i - 3];

        dp[0] = dp[1] = 0;
        dp[2] = 1; dp[3] = 1;

        for (int i = 4; i <= N; i++) {
            dp[i] =  (dp[i - 2] + dp[i - 3]) % 10007;
        }

        System.out.println(dp[N]);

    }
}