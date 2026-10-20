import java.io.*;
import java.util.*;

public class BOJ2515_전시장 {
    static int n, s;
    static int[] dp;
    static class Painting implements Comparable<Painting> {
        int len, price;
        public Painting(int len, int price) {this.len = len; this.price = price;}
        @Override
        public int compareTo(Painting p) {
            return this.len - p.len;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        dp = new int[n+1];
        Painting[] paintings = new Painting[n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            paintings[i] = new Painting(len, price);
        }

        Arrays.sort(paintings);

        int j = 0;
        for (int i = 1; i <= n; i++) {
            while ((j + 1 < i) && paintings[j + 1].len <= paintings[i].len - s) {
                j++;
            }
            int skip = (i > 1) ? dp[i - 1] : 0;
            int take = paintings[i].price + ((j >= 1) ? dp[j] : 0);
            dp[i] = Math.max(skip, take);
        }

        System.out.println(dp[n]);
        br.close();
    }
}
