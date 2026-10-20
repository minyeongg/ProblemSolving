import java.io.*;
import java.util.*;

public class BOJ1102_발전소 {
    // YNN 등의 문자열에서 꺼져있는 발전소 수 센 다음에, P개 켜져있기
    // 위해 필요한 수 K개만큼 발전소를 켠다.

    /*
    visited = 0 (아무 발전소도 안 켠 상황)에서부터 시작한다.
    visited한 발전소가 k개면 종료한다.
    dp[visited]가 -1이 아니면 dp[visited]를 리턴한다.
    0번 발전소부터 탐색
        이미 켜져있으면 continue;
        이미 visit 했으면 continue;
        int best = INF;
        최솟값 갱신
    최솟값으로  dp[visited] 갱신
    best 리턴
     */

    static int N, P;
    static final int INF = (int) 1e9;
    static int[][] W;
    static int[] dp;
    static int solution(int visited) {
        int cnt = Integer.bitCount(visited);
        if (cnt >= P) return 0;

        if (dp[visited] != -1) return dp[visited];

        int best = INF;
        for (int next = 0; next < N; next++) {
            if ((visited & (1 << next)) != 0) continue;

            int cost = INF;
            for (int i = 0; i < N; i++) {
                if ((visited & (1 << i)) != 0) {
                    cost = Math.min(cost, W[i][next]);
                }
            }
            best = Math.min(best, cost + solution(visited | (1 << next)));
        }

        dp[visited] = best;
        return best;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[1 << N];
        Arrays.fill(dp, -1);

        int visited = 0;
        String s = br.readLine();
        for (int i = 0; i < s.length(); i++) {
            char isOn = s.charAt(i);
            if (isOn == 'Y') visited += (1 << i);
        }

        P = Integer.parseInt(br.readLine());

        int ans = solution(visited);
        System.out.println(ans >= INF ? -1 : ans);
        br.close();
    }
}
