import java.util.*;
import java.lang.*;

class Main {
    static int[] s = new int[305];
    static int[] d = new int[305];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            s[i] = sc.nextInt();
        }
        d[1] = s[1];
        d[2] = s[1] + s[2];
        d[3] = s[3] + Math.max(s[1], s[2]);
        for (int i = 4; i <= n; i++) {
            d[i] = Math.max(d[i-3] + s[i-1], d[i-2]) + s[i];
        }
        System.out.println(d[n]);
    }
}
