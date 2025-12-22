import java.util.*;

class Main {
    static int[] d = new int[1000005];
    static int[] pre = new int[1000005];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        d[1] = 0;
        for (int i = 2; i <= n; i++) {
            d[i] = d[i-1] + 1;
            pre[i] = i-1;
            if (i % 2 == 0 && d[i] > d[i/2] + 1) {
                d[i] = d[i/2] + 1;
                pre[i] = i/2;
            }
            if (i % 3 == 0 && d[i] > d[i/3] + 1) {
                d[i] = d[i/3] + 1;
                pre[i] = i/3;
            }
        }
        System.out.println(d[n]);
        int cur = n;
        while (true) {
            System.out.println(cur);
            if (cur == 1) break;
            cur = pre[cur];
        }
    }
}
