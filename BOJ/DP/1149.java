import java.util.*;

class Main {
    static int[][] r = new int[1005][3];
    static int[][] d = new int[1005][3];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 3; j++) {
                r[i][j] = sc.nextInt();
            }
        }
        d[1][0] = r[1][0];
        d[1][1] = r[1][1];
        d[1][2] = r[1][2];
        for (int i = 2; i <= n; i++) {
            d[i][0] = Math.min(d[i-1][1], d[i-1][2]) + r[i][0];
            d[i][1] = Math.min(d[i-1][0], d[i-1][2]) + r[i][1];
            d[i][2] = Math.min(d[i-1][0], d[i-1][1]) + r[i][2];
        }
        int mn = Integer.MAX_VALUE;
        for (int j = 0; j < 3; j++) {
            mn = Math.min(d[n][j], mn);
        }
        System.out.println(mn);
    }
}
