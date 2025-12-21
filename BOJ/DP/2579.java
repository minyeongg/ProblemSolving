import java.util.*;
import java.lang.*;

class Main {
    static int[] s = new int[305];
    static int[][] d = new int[305][3];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            s[i] = sc.nextInt();
        }
        d[1][1] = s[1];
        d[2][1] = s[2];
        d[2][2] = s[1] + s[2];
        for (int i = 3; i <= n; i++) {
            d[i][1] = Math.max(d[i-2][1], d[i-2][2]) + s[i];
            d[i][2] = d[i-1][1] + s[i];
        }
        System.out.println(Math.max(d[n][1], d[n][2]));
    }
}
